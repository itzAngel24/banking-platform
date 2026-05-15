import { ReactNode } from "react";
export function Panel({ title, children }: { title: string; children: ReactNode }) { return <section className="rounded-2xl bg-white p-5 shadow-sm ring-1 ring-slate-100"><h3 className="font-display text-lg text-slate-900">{title}</h3><div className="mt-4">{children}</div></section>; }
