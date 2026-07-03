export interface SlabInput{id?:string; length:number; width?:number; height?:number; load:number; material?:string}
export interface SlabResult{ok:boolean; checks:string[]; values:Record<string,number|string>}
