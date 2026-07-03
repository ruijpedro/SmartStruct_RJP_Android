import React from 'react';
import { examples } from './RetainingwallsExamples';
import { solve } from './RetainingwallsSolver';
import { RetainingwallsCanvas } from './RetainingwallsCanvas';
import { RetainingwallsForm } from './RetainingwallsForm';
export default function RetainingwallsPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Muros</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <RetainingwallsForm input={input} />
        <div className="module-card"><RetainingwallsCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
