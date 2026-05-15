# Diseno de Base de Datos

## 1. Estrategia de persistencia
- PostgreSQL por microservicio OLTP.
- Redis para cache de lectura, token/session y rate limit counters.
- MongoDB para read-models analiticos y documentos de auditoria enriquecidos.

## 2. SQL de referencia (account-service)
```sql
CREATE TABLE IF NOT EXISTS account (
  id UUID PRIMARY KEY,
  customer_id UUID NOT NULL,
  account_number VARCHAR(24) NOT NULL UNIQUE,
  account_type VARCHAR(20) NOT NULL,
  currency CHAR(3) NOT NULL,
  balance NUMERIC(18,2) NOT NULL DEFAULT 0,
  status VARCHAR(20) NOT NULL,
  version BIGINT NOT NULL DEFAULT 0,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS account_movement (
  id UUID PRIMARY KEY,
  account_id UUID NOT NULL REFERENCES account(id),
  movement_type VARCHAR(20) NOT NULL,
  amount NUMERIC(18,2) NOT NULL,
  reference VARCHAR(64),
  trace_id VARCHAR(64) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_account_customer_id ON account(customer_id);
CREATE INDEX idx_movement_account_date ON account_movement(account_id, created_at DESC);
```

## 3. SQL de referencia (transfer-service)
```sql
CREATE TABLE IF NOT EXISTS transfer_tx (
  id UUID PRIMARY KEY,
  reference VARCHAR(40) NOT NULL UNIQUE,
  source_account_id UUID NOT NULL,
  target_account_id UUID NOT NULL,
  amount NUMERIC(18,2) NOT NULL CHECK (amount > 0),
  currency CHAR(3) NOT NULL,
  status VARCHAR(20) NOT NULL,
  idempotency_key VARCHAR(80) NOT NULL,
  trace_id VARCHAR(64) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX uq_transfer_idempotency ON transfer_tx(idempotency_key);
CREATE INDEX idx_transfer_status_date ON transfer_tx(status, created_at DESC);

CREATE TABLE IF NOT EXISTS outbox_event (
  id UUID PRIMARY KEY,
  aggregate_type VARCHAR(60) NOT NULL,
  aggregate_id VARCHAR(64) NOT NULL,
  event_type VARCHAR(80) NOT NULL,
  payload JSONB NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_outbox_status_created ON outbox_event(status, created_at);
```

## 4. Particionamiento
- `account_movement`: particion mensual por `created_at`.
- `transfer_tx`: particion mensual para historico > 50M rows.
- Politica retencion: datos calientes 12 meses, historico archivado.

## 5. Backup y recuperacion
- Full backup diario + WAL continuo.
- PITR habilitado (RPO <= 5 min).
- Prueba de restauracion mensual automatizada.
- Replicas en zona secundaria para DR.

## 6. Convenciones de schema
- PK UUID v7.
- Auditoria: `created_at`, `updated_at`, `created_by` cuando aplique.
- `version` para optimistic locking.
- Enums representados como `VARCHAR` con validacion app + check constraints cuando convenga.
