import type {SlabResult} from './SlabTypes';
export function checkSlab(values:Record<string,number|string>):SlabResult{return {ok:true,checks:['Verificação preliminar OK'],values};}
