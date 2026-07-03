import React from 'react';
import { examples } from './GroundwaterExamples';
import { solve } from './GroundwaterSolver';
import { GroundwaterCanvas } from './GroundwaterCanvas';
import { GroundwaterForm } from './GroundwaterForm';
export default function GroundwaterPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Água/Freático</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <GroundwaterForm input={input} />
        <div className="module-card"><GroundwaterCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
