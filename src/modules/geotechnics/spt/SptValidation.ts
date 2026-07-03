import type { Input } from './model';
export function validate(input: Input): string[] {
  const errors: string[] = [];
  if (!input.name) errors.push('Identificação obrigatória.');
  if (input.gamma <= 0) errors.push('Peso volúmico deve ser positivo.');
  if (input.phi < 0 || input.phi > 45) errors.push('Ângulo de atrito fora do intervalo recomendado.');
  return errors;
}
