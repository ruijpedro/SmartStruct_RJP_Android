import type {WallInput} from './WallTypes';
export function wallInputs(input:WallInput):Record<string,number|string>{const l=input.length||1; const q=input.load||0; return {length:l,load:q,value:q*l};}
