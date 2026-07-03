import type {SlabResult} from './SlabTypes';
export function buildSlabReport(result:SlabResult):string{return ['SmartStruct_RJP - Slab', 'Uso académico e apoio técnico IP', JSON.stringify(result.values)].join('\n');}
