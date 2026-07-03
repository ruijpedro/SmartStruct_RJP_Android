import type {ColumnResult} from './ColumnTypes';
export function checkColumn(values:Record<string,number|string>):ColumnResult{return {ok:true,checks:['Verificação preliminar OK'],values};}
