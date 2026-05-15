CREATE TABLE IF NOT EXISTS transfer_tx (
  id UUID PRIMARY KEY,
  reference VARCHAR(40) NOT NULL UNIQUE,
  source_account_id VARCHAR(64) NOT NULL,
  target_account_id VARCHAR(64) NOT NULL,
  amount NUMERIC(18,2) NOT NULL,
  currency CHAR(3) NOT NULL,
  status VARCHAR(20) NOT NULL,
  idempotency_key VARCHAR(80) NOT NULL UNIQUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS outbox_event (
  id UUID PRIMARY KEY,
  aggregate_type VARCHAR(60) NOT NULL,
  aggregate_id VARCHAR(64) NOT NULL,
  event_type VARCHAR(80) NOT NULL,
  payload JSONB NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_outbox_status_created ON outbox_event(status, created_at);
