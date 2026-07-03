import type {ColumnResult} from './ColumnTypes';
export function buildColumnReport(result:ColumnResult):string{return ['SmartStruct_RJP - Column', 'Uso académico e apoio técnico IP', JSON.stringify(result.values)].join('\n');}
