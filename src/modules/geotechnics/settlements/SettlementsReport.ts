import type { Input, Result } from './model';
export function createReport(input: Input, result: Result): string {
  return [`# Recalques`, `Elemento: ${input.name}`, `Resultado: ${result.factor}`, ...result.notes].join('\n');
}
