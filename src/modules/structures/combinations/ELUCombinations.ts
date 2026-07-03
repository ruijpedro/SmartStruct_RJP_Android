import type {ActionValue,CombinationResult} from './CombinationTypes';
export function eluFundamental(actions:ActionValue[]):CombinationResult{const g=actions.filter(a=>a.type==='G').reduce((s,a)=>s+a.value,0);const q=actions.filter(a=>a.type==='Q').reduce((s,a)=>s+a.value,0);return {name:'ELU fundamental',value:1.35*g+1.5*q,expression:'1.35G + 1.50Q'};}
