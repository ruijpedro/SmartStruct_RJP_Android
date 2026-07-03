import React from 'react';
import { examples } from './SoilExamples';
import { solve } from './SoilSolver';
import { SoilCanvas } from './SoilCanvas';
import { SoilForm } from './SoilForm';
export default function SoilPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Solos</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <SoilForm input={input} />
        <div className="module-card"><SoilCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
