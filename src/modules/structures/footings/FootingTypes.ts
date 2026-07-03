export interface FootingInput{id?:string; length:number; width?:number; height?:number; load:number; material?:string}
export interface FootingResult{ok:boolean; checks:string[]; values:Record<string,number|string>}
