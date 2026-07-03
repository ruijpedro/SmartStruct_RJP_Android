import type {ColumnInput,ColumnResult} from './ColumnTypes';
export function solveColumnInteraction(input:ColumnInput):ColumnResult{const l=input.length||1; const q=input.load||0; return {ok:true,checks:['Cálculo preliminar'],values:{l,q,principal:q*l*l/8}};}
