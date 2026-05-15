# Fase 2.1 - Infra/CI-CD y Back Pendiente Completado

## Infraestructura y despliegue
- Docker Compose local: `infrastructure/docker/docker-compose.yml`
- Kubernetes base: `kubernetes/base/*.yaml`
- Helm chart: `helm/banking-platform`
- ArgoCD app: `kubernetes/argocd/application.yaml`
- Terraform AKS dev: `terraform/envs/dev/*.tf`

## CI/CD
- CI backend con build/test/security/docker: `.github/workflows/ci-backend.yml`
- CD hacia AKS con ArgoCD bootstrap y smoke: `.github/workflows/cd-aks.yml`

## Observabilidad
- Prometheus scrape config: `observability/prometheus/prometheus.yml`
- Dashboard Grafana inicial: `observability/grafana/dashboards/banking-overview.json`
- Lineamientos Kibana/Elasticsearch: `observability/loki-kibana-notes.md`

## Pendientes backend cerrados
- Migraciones Flyway:
  - `account-service/src/main/resources/db/migration/V1__account.sql`
  - `transfer-service/src/main/resources/db/migration/V1__transfer_and_outbox.sql`
- Outbox base en transfer-service:
  - `OutboxEventEntity`
  - `OutboxRepository`
  - persistencia outbox dentro de `TransferService`
- Testcontainers base:
  - `AccountPostgresContainerIT`
  - `TransferPostgresContainerIT`

## Estado
- Build backend validado: `mvn -q -DskipTests compile` OK.
- Frontend reservado para Fase 3, segun definicion.
