import type {FootingInput} from './FootingTypes';
export function validateFooting(input:FootingInput):string[]{const e:string[]=[]; if(input.length<=0)e.push('Comprimento inválido'); if(input.load<0)e.push('Carga inválida'); return e;}
