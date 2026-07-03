export interface ColumnInput{id?:string; length:number; width?:number; height?:number; load:number; material?:string}
export interface ColumnResult{ok:boolean; checks:string[]; values:Record<string,number|string>}
