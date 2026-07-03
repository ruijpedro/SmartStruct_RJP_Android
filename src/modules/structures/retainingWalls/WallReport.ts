import type {WallResult} from './WallTypes';
export function buildWallReport(result:WallResult):string{return ['SmartStruct_RJP - Wall', 'Uso académico e apoio técnico IP', JSON.stringify(result.values)].join('\n');}
