import React from 'react';
import { examples } from './SptExamples';
import { solve } from './SptSolver';
import { SptCanvas } from './SptCanvas';
import { SptForm } from './SptForm';
export default function SptPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>SPT</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <SptForm input={input} />
        <div className="module-card"><SptCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
