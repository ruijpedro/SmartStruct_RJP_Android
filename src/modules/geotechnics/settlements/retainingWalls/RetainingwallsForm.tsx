import React from 'react';
import type { Input } from './model';
export function RetainingwallsForm({ input }: { input: Input }) {
  return (
    <div className="module-card">
      <h3>Muros</h3>
      <div className="field-grid">
        <label>Identificação <input defaultValue={input.name} /></label>
        <label>γ (kN/m³) <input type="number" defaultValue={input.gamma} /></label>
        <label>φ (°) <input type="number" defaultValue={input.phi} /></label>
        <label>c (kPa) <input type="number" defaultValue={input.cohesion} /></label>
      </div>
    </div>
  );
}
