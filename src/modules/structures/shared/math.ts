export const round=(v:number,d=2)=>Number.isFinite(v)?Number(v.toFixed(d)):0;
export const clamp=(v:number,min:number,max:number)=>Math.max(min,Math.min(max,v));
export const mm2=(m:number)=>m*1_000_000;
export const m2=(mm:number)=>mm/1_000_000;
