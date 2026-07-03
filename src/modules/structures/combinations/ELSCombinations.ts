import type {ActionValue,CombinationResult} from './CombinationTypes';
export function elsCharacteristic(actions:ActionValue[]):CombinationResult{const v=actions.reduce((s,a)=>s+a.value,0);return {name:'ELS característica',value:v,expression:'G + Q'};}
