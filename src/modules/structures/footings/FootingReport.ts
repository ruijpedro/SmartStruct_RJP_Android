import type {FootingResult} from './FootingTypes';
export function buildFootingReport(result:FootingResult):string{return ['SmartStruct_RJP - Footing', 'Uso académico e apoio técnico IP', JSON.stringify(result.values)].join('\n');}
