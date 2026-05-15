import { Navigate, Route, Routes } from "react-router-dom";
import { useMemo, useState } from "react";
import { AppShell } from "../components/layout/AppShell";
import { LoginPage } from "../features/auth/LoginPage";
import { DashboardPage } from "../features/dashboard/DashboardPage";
import { AccountsPage } from "../features/accounts/AccountsPage";
import { WalletPage } from "../features/wallet/WalletPage";
import { LoansPage } from "../features/loans/LoansPage";
import { ReportsPage } from "../features/reports/ReportsPage";
export function AppRouter() { const [session, setSession] = useState<{ user: string; token: string } | null>(null); const auth = useMemo(() => ({ login: (u: string, t: string) => setSession({ user: u, token: t }), logout: () => setSession(null) }), []); if (!session) return <LoginPage onLogin={auth.login} />; return <AppShell user={session.user} onLogout={auth.logout}><Routes><Route path="/dashboard" element={<DashboardPage />} /><Route path="/accounts" element={<AccountsPage />} /><Route path="/wallet" element={<WalletPage />} /><Route path="/loans" element={<LoansPage />} /><Route path="/reports" element={<ReportsPage />} /><Route path="*" element={<Navigate to="/dashboard" replace />} /></Routes></AppShell>; }
