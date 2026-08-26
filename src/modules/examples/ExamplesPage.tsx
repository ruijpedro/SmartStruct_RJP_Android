import React from 'react';
import { examples } from './ExamplesExamples';
import { solve } from './ExamplesSolver';
import { ExamplesCanvas } from './ExamplesCanvas';
import { ExamplesForm } from './ExamplesForm';
export default function ExamplesPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Exemplos Geo</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <ExamplesForm input={input} />
        <div className="module-card"><ExamplesCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
