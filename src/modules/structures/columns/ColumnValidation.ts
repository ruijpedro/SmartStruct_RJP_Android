import type {ColumnInput} from './ColumnTypes';
export function validateColumn(input:ColumnInput):string[]{const e:string[]=[]; if(input.length<=0)e.push('Comprimento inválido'); if(input.load<0)e.push('Carga inválida'); return e;}
