# Kibana / Elasticsearch Notes

- Log format expected: JSON structured logs from Quarkus.
- Fields: timestamp, level, loggerName, message, traceId, spanId, service.
- Recommended index template: logs-banking-*
- Lifecycle policy: hot 7 days, warm 30 days, cold 180 days.
