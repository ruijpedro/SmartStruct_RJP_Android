import type {SlabInput} from './SlabTypes';
export function slabTwoWay(input:SlabInput):Record<string,number|string>{const l=input.length||1; const q=input.load||0; return {length:l,load:q,value:q*l};}
