import React from 'react';
import { examples } from './PressuresExamples';
import { solve } from './PressuresSolver';
import { PressuresCanvas } from './PressuresCanvas';
import { PressuresForm } from './PressuresForm';
export default function PressuresPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Impulsos</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <PressuresForm input={input} />
        <div className="module-card"><PressuresCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
