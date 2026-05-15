# Frontend App - Fase 3

## Stack
- React 18 + TypeScript
- Tailwind CSS
- Vite
- Recharts
- React Router

## Vistas implementadas
- Login seguro (usuario/password/mfa)
- Dashboard financiero
- Gestion de cuentas
- Wallet (P2P / QR-like)
- Prestamos
- Reportes

## Ejecucion local
1. `cd frontend-app`
2. `cp .env.example .env` (PowerShell: `Copy-Item .env.example .env`)
3. `npm install`
4. `npm run dev`

## Integracion API
- Base URL configurable en `VITE_API_BASE_URL`
- Cliente HTTP central en `src/services/apiClient.ts`
- En ausencia de backend local, login y wallet tienen fallback de simulacion para demo.

## Arquitectura
- `src/app`: router
- `src/components`: layout y UI reusable
- `src/features`: modulos por dominio
- `src/services`: capa de integracion API
- `src/styles`: estilos globales Tailwind
