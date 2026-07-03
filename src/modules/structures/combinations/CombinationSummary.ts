import type {CombinationResult} from './CombinationTypes';
export const summarizeCombinations=(items:CombinationResult[])=>items.map(i=>`${i.name}: ${i.value.toFixed(2)} kN/m`).join('\n');
