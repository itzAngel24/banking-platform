# Fase 2 - Backend Implementado (Quarkus Java 21)

## Arquitectura de codigo
- Monorepo Maven multi-modulo con 13 microservicios.
- Estructura por servicio orientada a Hexagonal/Clean:
  - `api`: controladores REST
  - `application`: casos de uso/servicios
  - `domain`: entidades y DTOs de negocio
  - `infrastructure`: adapters (repos, kafka, redis, etc.)
  - `common`: cross-cutting (manejo de errores)

## Microservicios incluidos
1. auth-service
2. customer-service
3. account-service
4. card-service
5. transfer-service
6. wallet-service
7. loan-service
8. payment-service
9. notification-service
10. reporting-service
11. fraud-detection-service
12. gateway-service
13. config-service

## Implementacion funcional fase 2
- `auth-service`
  - `POST /api/v1/auth/login`
  - Login request/response con token model.
- `customer-service`
  - `POST /api/v1/customers`
  - Caso de uso de alta cliente + repositorio in-memory adapter.
- `account-service`
  - `POST /api/v1/accounts`
  - Alta de cuentas con contrato de dominio.
- `transfer-service`
  - `POST /api/v1/transfers/internal`
  - Caso de uso transferencia + publicacion evento `transfer.completed.v1`.
- `wallet-service`
  - `POST /api/v1/wallet/payments`
  - Transferencia wallet P2P basica.
- Todos los servicios
  - `GET /api/v1/{servicio}/health`
  - `GlobalExceptionMapper` centralizado.

## Tecnologias backend aplicadas
- Java 21
- Quarkus REST Reactive
- SmallRye OpenAPI
- SmallRye Health
- OIDC/JWT base
- Kafka messaging base
- Redis client base
- Hibernate Panache + PostgreSQL driver
- OpenTelemetry extension
- JUnit5 + RestAssured

## Build validado
- Comando ejecutado: `mvn -q -DskipTests compile`
- Resultado: compilacion exitosa en todos los modulos.

## Siguiente endurecimiento (fase 2.1 recomendada)
- Persistencia real (PostgreSQL) para customer/account/transfer.
- Outbox transactional para eventos de transferencias.
- Validaciones bean validation y reglas antifraude.
- Seguridad JWT firmada real + MFA challenge flow completo.
- Pruebas de integracion por servicio con Testcontainers.
