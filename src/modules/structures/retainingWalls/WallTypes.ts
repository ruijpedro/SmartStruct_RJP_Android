export interface WallInput{id?:string; length:number; width?:number; height?:number; load:number; material?:string}
export interface WallResult{ok:boolean; checks:string[]; values:Record<string,number|string>}
