export interface SteelGrade{name:string; fyk:number; fyd:number}
export const steelGrades:SteelGrade[]=[{name:'A400',fyk:400,fyd:348},{name:'A500',fyk:500,fyd:435},{name:'A500NR',fyk:500,fyd:435}];
export const getSteel=(name='A500')=>steelGrades.find(s=>s.name===name)??steelGrades[1];
