import React from 'react';
import { examples } from './SettlementsExamples';
import { solve } from './SettlementsSolver';
import { SettlementsCanvas } from './SettlementsCanvas';
import { SettlementsForm } from './SettlementsForm';
export default function SettlementsPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Recalques</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <SettlementsForm input={input} />
        <div className="module-card"><SettlementsCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
