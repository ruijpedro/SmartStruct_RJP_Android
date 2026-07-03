import React from 'react';
import { examples } from './SlopesExamples';
import { solve } from './SlopesSolver';
import { SlopesCanvas } from './SlopesCanvas';
import { SlopesForm } from './SlopesForm';
export default function SlopesPage() {
  const input = examples[0];
  const result = solve(input);
  return (
    <section className="workspace">
      <header><h2>Taludes</h2><p>Uso académico e apoio técnico IP · validação por técnico habilitado.</p></header>
      <div className="two-col">
        <SlopesForm input={input} />
        <div className="module-card"><SlopesCanvas input={input} /><strong>FS/valor: {result.factor}</strong></div>
      </div>
    </section>
  );
}
