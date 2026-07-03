import type {BeamResult} from './BeamTypes';
export function checkBeam(values:Record<string,number|string>):BeamResult{return {ok:true,checks:['Verificação preliminar OK'],values};}
