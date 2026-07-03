import type { Input, Result } from './model';
export function solve(input: ExamplesInput): ExamplesResult {
  const gamma = Number(input.gamma || 18);
  const phi = Number(input.phi || 30);
  const cohesion = Number(input.cohesion || 0);
  const factor = Math.max(0.1, (cohesion / 10) + Math.tan((phi * Math.PI) / 180) + gamma / 100);
  return { ok: factor >= 1.0, factor: Number(factor.toFixed(2)), notes: ['Cálculo preliminar para apoio académico/IP', 'Validar sempre com metodologia regulamentar aplicável'] };
}
