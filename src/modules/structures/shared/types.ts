export type CheckState='ok'|'warning'|'fail';
export interface CheckResult{label:string; state:CheckState; value?:number|string; limit?:number|string; note?:string}
export interface ResultBlock{title:string; rows:Record<string,string|number>}
export interface Point{x:number;y:number}
export interface DiagramPoint{x:number;v:number;m?:number;d?:number}
