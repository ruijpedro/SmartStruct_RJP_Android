import type { Input, Result } from './model';
export function solve(input: PressuresInput): PressuresResult {
  const phi = Number(input.phi || 30);
  const s = Math.sin((phi * Math.PI) / 180);
  const ka = (1 - s) / (1 + s);
  const kp = (1 + s) / (1 - s);
  return { ok: true, factor: Number(ka.toFixed(3)), notes: [`Ka=${ka.toFixed(3)}`, `Kp=${kp.toFixed(3)}`, 'Rankine preliminar'] };
}
