import type {BeamInput,BeamResult} from './BeamTypes';
export function solveBeamSolver(input:BeamInput):BeamResult{const l=input.length||1; const q=input.load||0; return {ok:true,checks:['Cálculo preliminar'],values:{l,q,principal:q*l*l/8}};}
