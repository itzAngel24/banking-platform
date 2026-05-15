# Requisitos Funcionales y No Funcionales

## 1. Historias de usuario (seleccion representativa por modulo)

## Clientes
- Como prospecto, quiero registrarme con DNI/CE y validar identidad para abrir una cuenta digital.
- Como cliente, quiero actualizar direcciones y contactos para mantener mi perfil vigente.
- Como compliance analyst, quiero visualizar el estado KYC para bloquear operaciones de alto riesgo.

## Cuentas
- Como cliente, quiero abrir cuenta ahorro/corriente/sueldo para administrar mis fondos.
- Como cliente, quiero consultar saldo y movimientos en tiempo real.
- Como operador, quiero bloquear/desbloquear cuentas por eventos de riesgo.

## Tarjetas
- Como cliente, quiero activar y bloquear mi tarjeta para controlar seguridad.
- Como cliente, quiero configurar limites diarios y usar CVV dinamico.
- Como cliente, quiero pagar la deuda de tarjeta desde mi cuenta.

## Transferencias
- Como cliente, quiero transferir entre mis cuentas internas de forma instantanea.
- Como cliente, quiero transferir a otros bancos con validaciones antifraude.
- Como cliente, quiero programar transferencias recurrentes.

## Wallet
- Como usuario, quiero registrarme con celular y asociar cuentas.
- Como usuario, quiero enviar dinero (yapeo/plin-like) por numero o QR.
- Como usuario, quiero recibir solicitudes de dinero y confirmar/rechazar.

## Prestamos
- Como cliente, quiero simular y solicitar un prestamo.
- Como analista, quiero evaluar score y aprobar/rechazar con reglas.
- Como cliente, quiero ver cronograma y pagar cuotas.

## Pagos de servicios
- Como cliente, quiero pagar agua/luz/internet/telefonia.
- Como cliente, quiero registrar pago automatico y recargas.

## Seguridad
- Como cliente, quiero iniciar sesion con MFA.
- Como auditor, quiero trazabilidad completa de eventos de seguridad.

## 2. Casos de uso clave
1. UC-001 Onboarding de cliente con KYC.
2. UC-010 Apertura de cuenta ahorro.
3. UC-020 Transferencia interna inmediata.
4. UC-021 Transferencia interbancaria con chequeo antifraude.
5. UC-030 Pago QR wallet.
6. UC-040 Solicitud de prestamo y evaluacion crediticia.
7. UC-050 Pago de servicio con debito de cuenta.
8. UC-060 Login MFA y emision de JWT.
9. UC-070 Generacion de reporte financiero exportable.

## 3. Acceptance criteria (ejemplos Gherkin)
```gherkin
Scenario: Transferencia interna exitosa
  Given un cliente autenticado con saldo suficiente
  And una cuenta origen activa
  When envia una transferencia interna por S/ 120.50
  Then la transferencia se marca COMPLETED
  And se descuentan fondos de la cuenta origen
  And se acredita la cuenta destino
  And se publica evento TransferCompleted en Kafka
```

```gherkin
Scenario: Login MFA obligatorio
  Given un usuario con MFA habilitado
  When ingresa credenciales correctas
  Then recibe challenge OTP valido por 60 segundos
  When confirma OTP correcto
  Then obtiene access token JWT y refresh token
```

## 4. Reglas de negocio
- RB-001 Ninguna cuenta puede quedar en saldo negativo salvo cuenta corriente con sobregiro habilitado.
- RB-002 Transferencias > umbral configurable requieren score antifraude >= minimo permitido.
- RB-003 KYC incompleto restringe operaciones de alto valor.
- RB-004 Idempotency-Key obligatoria en endpoints de transferencia/pago/solicitud prestamo.
- RB-005 Limite diario configurable por producto y segmento de cliente.

## 5. Requisitos no funcionales
- Disponibilidad: 99.95% mensual para APIs criticas.
- Escalabilidad: crecimiento horizontal x10 en transacciones sin downtime.
- Rendimiento: p95 < 2s en APIs de cliente, p95 < 1.5s en transferencias internas.
- Seguridad: cifrado TLS 1.3 en transito, AES-256 en reposo, OIDC/OAuth2, JWT firmado asimetrico.
- Observabilidad: logs estructurados JSON, trazas distribuidas, metricas RED+USE.
- Resiliencia: circuit breaker + retry con jitter + bulkhead.
- Auditoria: retencion de logs de seguridad 365 dias; eventos criticos inmutables.

## 6. SLAs y SLOs
- SLA API Core: 99.95%.
- SLO Latencia transfer-service p95: <= 1.5s.
- Error budget mensual: 0.05% downtime.
- RPO <= 5 minutos, RTO <= 30 minutos para dominios core.

## 7. Requisitos de compliance y seguridad
- Trazabilidad E2E de transacciones (traceId, spanId, correlationId).
- Principio de minimo privilegio RBAC en cluster y aplicaciones.
- Rotacion de secretos cada 90 dias.
- Hardening de imagenes, escaneo SAST/DAST/SCA en pipeline.

## 8. Matriz de trazabilidad (extracto)
| ID Req | Modulo | Servicio | Endpoint/Evento | Prueba asociada |
|---|---|---|---|---|
| FR-TR-001 | Transferencias internas | transfer-service | POST /api/v1/transfers/internal | IT-TR-001 |
| FR-WA-002 | Pago QR | wallet-service | POST /api/v1/wallet/payments/qr | E2E-WA-003 |
| NFR-SEC-004 | JWT + MFA | auth-service | POST /oauth/token, /mfa/verify | SEC-AT-002 |
| NFR-OBS-001 | Trazabilidad | todos | Kafka headers + trace context | OBS-TR-001 |
