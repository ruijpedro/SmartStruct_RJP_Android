import type {BeamInput} from './BeamTypes';
export function beamStudySteps(input:BeamInput):Record<string,number|string>{const l=input.length||1; const q=input.load||0; return {length:l,load:q,value:q*l};}
