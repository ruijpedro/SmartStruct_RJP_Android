import type {BeamResult} from './BeamTypes';
export function buildBeamReport(result:BeamResult):string{return ['SmartStruct_RJP - Beam', 'Uso académico e apoio técnico IP', JSON.stringify(result.values)].join('\n');}
