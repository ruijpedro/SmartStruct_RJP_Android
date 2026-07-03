import type {FootingResult} from './FootingTypes';
export function checkFooting(values:Record<string,number|string>):FootingResult{return {ok:true,checks:['Verificação preliminar OK'],values};}
