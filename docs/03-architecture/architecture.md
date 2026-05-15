# Diseno Arquitectonico Enterprise

## 1. Arquitectura logica
- Frontend React (BFF opcional en gateway) consume API Gateway.
- Gateway enruta a microservicios Quarkus.
- Servicios persistentes por dominio (database-per-service).
- Integracion asincrona por Kafka + outbox transactional.
- Anti-fraude en tiempo real desacoplado via eventos.

## 2. Microservicios y responsabilidades
- auth-service: OIDC/OAuth2 integration, JWT issuance, MFA, session control.
- customer-service: onboarding, perfil, identidad, KYC state.
- account-service: lifecycle de cuentas, saldos, movimientos.
- card-service: tarjetas, limites, CVV dinamico, bloqueos.
- transfer-service: transferencias internas/interbancarias, saga orchestration.
- wallet-service: alias celular, QR, P2P transferencias instantaneas.
- loan-service: simulacion, solicitud, cronograma, cuotas.
- payment-service: pagos servicios, recargas, autopagos.
- reporting-service: proyecciones, KPIs, exportaciones.
- fraud-detection-service: reglas, scoring, alertas.
- notification-service: push, email, SMS, webhooks.
- config-service: configuracion centralizada/feature flags.
- gateway-service: authz edge, throttling, routing, observabilidad edge.

## 3. C4 - Context
```mermaid
C4Context
    title Banking Platform - Context Diagram
    Person(customer, "Cliente", "Usuario banca digital")
    Person(analyst, "Analista Riesgo", "Monitorea fraude")
    System(bank, "Banking Platform", "Servicios digitales bancarios")
    System_Ext(idp, "Enterprise IdP", "OIDC Provider")
    System_Ext(interbank, "Red Interbancaria", "Pagos interbancarios")
    System_Ext(billers, "Proveedores de Servicios", "Agua, Luz, Internet")
    Rel(customer, bank, "Usa web/app", "HTTPS")
    Rel(analyst, bank, "Consulta reportes y alertas", "HTTPS")
    Rel(bank, idp, "Federacion de identidad", "OIDC/OAuth2")
    Rel(bank, interbank, "Transferencias", "mTLS API")
    Rel(bank, billers, "Pagos", "mTLS API")
```

## 4. C4 - Container
```mermaid
C4Container
    title Banking Platform - Container Diagram
    Person(customer, "Cliente")
    Container(web, "Frontend App", "React + TypeScript", "Canal digital")
    Container(gw, "Gateway Service", "Quarkus", "Routing, authz, rate limiting")
    Container(auth, "Auth Service", "Quarkus", "JWT, MFA, sessions")
    Container(core, "Domain Microservices", "Quarkus", "Customer, Account, Transfer, Wallet, etc.")
    Container(kafka, "Kafka Cluster", "Kafka", "Event bus")
    ContainerDb(pg, "PostgreSQL", "RDBMS", "OLTP por servicio")
    ContainerDb(redis, "Redis", "In-memory", "Cache, token/session")
    ContainerDb(mongo, "MongoDB", "Document DB", "Reporting read models")
    Rel(customer, web, "Usa", "HTTPS")
    Rel(web, gw, "Invoca APIs", "HTTPS")
    Rel(gw, auth, "AuthN/AuthZ", "REST/gRPC")
    Rel(gw, core, "Negocio", "REST/gRPC")
    Rel(core, kafka, "Publica/consume", "SASL_SSL")
    Rel(core, pg, "Persistencia", "JDBC")
    Rel(core, redis, "Cache", "RESP")
    Rel(core, mongo, "Read models", "Mongo")
```

## 5. Diagrama de componentes (simplificado)
```mermaid
flowchart LR
    UI[Frontend React] --> GW[API Gateway]
    GW --> AUTH[auth-service]
    GW --> CUS[customer-service]
    GW --> ACC[account-service]
    GW --> TRF[transfer-service]
    GW --> WAL[wallet-service]
    GW --> LON[loan-service]
    GW --> PAY[payment-service]
    GW --> CRD[card-service]
    TRF --> FRD[fraud-detection-service]
    TRF --> ACC
    WAL --> ACC
    PAY --> ACC
    ALL[(Kafka)]
    CUS --> ALL
    ACC --> ALL
    TRF --> ALL
    WAL --> ALL
    LON --> ALL
    PAY --> ALL
    CRD --> ALL
    NTF[notification-service] --> ALL
    RPT[reporting-service] --> ALL
```

## 6. Diagrama de despliegue (AKS)
```mermaid
flowchart TB
    subgraph Azure
      subgraph AKS
        ING[NGINX Ingress]
        GW[gateway-service pods]
        SVC[Domain service pods]
        OBS[OTel Collector]
      end
      ACR[Azure Container Registry]
      KV[Azure Key Vault]
      MON[Azure Monitor]
      KAF[Kafka Managed/Operator]
      PG[(PostgreSQL Flexible Server)]
      RED[(Azure Cache for Redis)]
      ES[(Elasticsearch)]
      GRA[Grafana]
      PRO[Prometheus]
    end
    ING --> GW
    GW --> SVC
    SVC --> KAF
    SVC --> PG
    SVC --> RED
    SVC --> ES
    SVC --> OBS
    OBS --> MON
    PRO --> GRA
    AKS --> ACR
    AKS --> KV
```

## 7. Secuencia transferencia interna (saga)
```mermaid
sequenceDiagram
    autonumber
    participant U as Usuario
    participant G as Gateway
    participant T as Transfer Service
    participant F as Fraud Service
    participant A as Account Service
    participant K as Kafka

    U->>G: POST /transfers/internal
    G->>T: request + JWT + Idempotency-Key
    T->>F: score(transaction)
    F-->>T: score OK
    T->>A: reserve(debit)
    A-->>T: reserved
    T->>A: apply(credit)
    A-->>T: credited
    T->>K: publish TransferCompleted
    T-->>G: 201 COMPLETED
    G-->>U: success
```

## 8. Eventos Kafka (catalogo inicial)
- `customer.created.v1`
- `customer.kyc.updated.v1`
- `account.created.v1`
- `account.balance.updated.v1`
- `transfer.initiated.v1`
- `transfer.completed.v1`
- `transfer.failed.v1`
- `wallet.payment.qr.completed.v1`
- `loan.approved.v1`
- `payment.service.completed.v1`
- `fraud.alert.raised.v1`
- `notification.dispatch.requested.v1`

### Ejemplo de payload
```json
{
  "eventId": "c96d4f57-6a2d-4adf-9bc9-1fe8a0c391d8",
  "eventType": "transfer.completed.v1",
  "occurredAt": "2026-05-15T16:21:44Z",
  "traceId": "b5a8e2a0c2f44e7d8ed5f0f8f2db6fc8",
  "data": {
    "transferId": "TRX-20260515-000991",
    "sourceAccountId": "ACC-1001",
    "targetAccountId": "ACC-1022",
    "amount": 120.50,
    "currency": "PEN",
    "status": "COMPLETED"
  }
}
```

## 9. ADRs
### ADR-001: Database per service
- Decision: cada microservicio posee su esquema/bd.
- Rationale: bajo acoplamiento y autonomia de despliegue.
- Consequence: consistencia eventual inter-servicios.

### ADR-002: Saga orchestration para transferencias
- Decision: transfer-service orquesta pasos de debito/credito.
- Rationale: control transaccional distribuido y compensaciones.
- Consequence: complejidad operativa adicional.

### ADR-003: Outbox pattern obligatorio en eventos de negocio
- Decision: persistir evento outbox en misma transaccion local.
- Rationale: evitar dual-write inconsistency.
- Consequence: componente relay adicional.

### ADR-004: gRPC interno + REST externo
- Decision: REST para canales externos, gRPC para trafico este-oeste sensible a latencia.
- Consequence: doble contrato y gobernanza API.

## 10. Niveles de resiliencia
- Retry con backoff exponencial y jitter para integraciones externas.
- Circuit breaker por dependencia remota.
- Bulkhead por pool de conexiones y thread limits.
- Timeout estricto por endpoint y tipo de operacion.
- Idempotencia para operaciones monetarias.
