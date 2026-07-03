import type {WallInput} from './WallTypes';
export function validateWall(input:WallInput):string[]{const e:string[]=[]; if(input.length<=0)e.push('Comprimento inválido'); if(input.load<0)e.push('Carga inválida'); return e;}
