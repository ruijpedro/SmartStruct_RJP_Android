export const rebarDiameters=[6,8,10,12,14,16,20,25,32];
export const barArea=(phi:number)=>Math.PI*phi*phi/4;
export const barsArea=(n:number,phi:number)=>n*barArea(phi);
