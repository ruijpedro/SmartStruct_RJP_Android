export interface BeamInput{id?:string; length:number; width?:number; height?:number; load:number; material?:string}
export interface BeamResult{ok:boolean; checks:string[]; values:Record<string,number|string>}
