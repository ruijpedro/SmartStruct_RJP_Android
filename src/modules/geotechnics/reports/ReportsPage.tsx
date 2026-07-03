import React from 'react';
import { examples } from './ReportsExamples';
import { solve } from './ReportsSolver';
import { ReportsCanvas } from './ReportsCanvas';
import { ReportsForm } from './ReportsForm';
export default function ReportsPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Relatórios Geo</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <ReportsForm input={input} />
        <div className="module-card"><ReportsCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
