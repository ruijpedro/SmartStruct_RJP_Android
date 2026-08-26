import React from 'react';
import { examples } from './FootingsExamples';
import { solve } from './FootingsSolver';
import { FootingsCanvas } from './FootingsCanvas';
import { FootingsForm } from './FootingsForm';
export default function FootingsPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Sapatas EC7</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <FootingsForm input={input} />
        <div className="module-card"><FootingsCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
