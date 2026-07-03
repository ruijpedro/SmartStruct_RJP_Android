import React from 'react';
import { examples } from './CptExamples';
import { solve } from './CptSolver';
import { CptCanvas } from './CptCanvas';
import { CptForm } from './CptForm';
export default function CptPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>CPT</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <CptForm input={input} />
        <div className="module-card"><CptCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
