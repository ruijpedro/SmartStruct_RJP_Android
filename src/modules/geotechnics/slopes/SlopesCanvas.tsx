import React from 'react';
import type { Input } from './model';
export function SlopesCanvas({ input }: { input: Input }) {
  return (
    <svg viewBox="0 0 360 220" role="img" aria-label="Esquema Taludes" style={{ width: '100%', maxWidth: 520, border: '1px solid #d8e1e8', borderRadius: 12, background: '#fff' }}>
      <rect x="0" y="0" width="360" height="220" fill="#ffffff"/>
      <path d="M40 180 L150 60 L320 180 Z" fill="#f1eadc" stroke="#173447" strokeWidth="3"/>
      <line x1="40" y1="180" x2="320" y2="180" stroke="#0b8f6a" strokeWidth="4"/>
      <text x="24" y="30" fontSize="16" fill="#173447" fontWeight="700">Taludes</text>
      <text x="24" y="52" fontSize="12" fill="#49606f">γ={input.gamma} kN/m³ · φ={input.phi}° · c={input.cohesion} kPa</text>
    </svg>
  );
}
