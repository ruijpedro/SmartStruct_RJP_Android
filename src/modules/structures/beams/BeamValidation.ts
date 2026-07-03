import type {BeamInput} from './BeamTypes';
export function validateBeam(input:BeamInput):string[]{const e:string[]=[]; if(input.length<=0)e.push('Comprimento inválido'); if(input.load<0)e.push('Carga inválida'); return e;}
