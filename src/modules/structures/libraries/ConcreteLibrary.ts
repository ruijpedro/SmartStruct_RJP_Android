export interface ConcreteGrade{name:string; fck:number; fcd:number; ecm:number}
export const concreteGrades:ConcreteGrade[]=[{name:'C20/25',fck:20,fcd:13.3,ecm:30},{name:'C25/30',fck:25,fcd:16.7,ecm:31},{name:'C30/37',fck:30,fcd:20.0,ecm:33},{name:'C35/45',fck:35,fcd:23.3,ecm:34}];
export const getConcrete=(name='C25/30')=>concreteGrades.find(c=>c.name===name)??concreteGrades[1];
