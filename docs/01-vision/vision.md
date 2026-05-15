# Banking Platform Enterprise - Documento de Vision

## 1. Objetivos estrategicos
- Construir un banco digital cloud-native con disponibilidad 99.95% para canales criticos (consulta de saldo, transferencias, wallet, pagos).
- Reducir time-to-market para nuevas capacidades (productos, promociones, integraciones) mediante arquitectura de microservicios y entrega continua.
- Cumplir requisitos regulatorios de trazabilidad, seguridad, privacidad y auditoria para operaciones financieras.
- Habilitar analitica operacional y de negocio near-real-time para deteccion de fraude, comportamiento y salud del sistema.

## 2. Alcance
### Incluye
- Canales digital web y API externa.
- Dominios: clientes, cuentas, tarjetas, transferencias, billetera, prestamos, pagos, autenticacion, notificaciones, reportes y fraude.
- Arquitectura: Quarkus Java 21, API Gateway, Kafka, PostgreSQL, Redis, MongoDB (reporting/auditoria flexible), AKS, Terraform, ArgoCD.

### No incluye (release inicial)
- Core bancario legacy replacement completo.
- Canales de agencia fisica y ATM propietarios.
- Motor AML avanzado con IA explicable (se integra en roadmap).

## 3. Stakeholders
- Board y Steering Committee.
- Product Owner Banca Digital.
- Compliance, Riesgo Operacional, Seguridad y Auditoria.
- Operaciones TI, SRE, Soporte N2/N3.
- Clientes finales (persona natural y pyme).
- Partners: proveedores de servicios, camara interbancaria, pasarelas.

## 4. Supuestos clave
- Identity provider corporativo compatible con OIDC.
- Integracion con red interbancaria via API segura.
- Entornos separados (dev, qa, staging, prod) en suscripciones Azure distintas.
- Equipo habilitado para trabajo trunk-based + feature flags.

## 5. Riesgos y mitigaciones
- Riesgo de consistencia distribuida en transferencias: Saga orquestada + outbox + idempotency keys.
- Riesgo de fraude en pagos instantaneos: scoring en tiempo real y reglas de velocity/risk.
- Riesgo de latencia por crecimiento: cache Redis, particionamiento Kafka, autoescalado por HPA/KEDA.
- Riesgo de drift infraestructura: GitOps con ArgoCD y politicas OPA.
- Riesgo de exposicion de secretos: Azure Key Vault + CSI Driver + rotacion automatizada.

## 6. KPIs de negocio y tecnologia
- Tasa de exito transferencias > 99.5%.
- Tiempo medio login < 700 ms p95.
- Tiempo medio transferencia interna < 1.5 s p95.
- Lead time change < 1 dia en servicios no criticos, < 3 dias en criticos.
- MTTR < 30 minutos.
- Cobertura pruebas backend >= 80% lineal y >= 70% branch en servicios core.

## 7. Roadmap evolutivo
### Fase 1 (MVP regulado)
- Onboarding cliente, cuentas, transferencias internas, wallet P2P, login MFA.
- Observabilidad base, CI/CD, despliegue AKS.

### Fase 2
- Prestamos con scoring extendido, pagos servicios completos, tarjetas credito/debito full.
- Reporteria avanzada exportable PDF/Excel.

### Fase 3
- Open Banking APIs, motor de recomendaciones, antifraude ML, autoservicio empresarial.

## 8. Estructura de repositorio objetivo
```text
/banking-platform
  /docs
  /infrastructure
  /kubernetes
  /helm
  /terraform
  /gateway-service
  /auth-service
  /customer-service
  /account-service
  /card-service
  /transfer-service
  /wallet-service
  /loan-service
  /payment-service
  /notification-service
  /reporting-service
  /fraud-detection-service
  /config-service
  /frontend-app
  /observability
  /scripts
  /.github/workflows
```
