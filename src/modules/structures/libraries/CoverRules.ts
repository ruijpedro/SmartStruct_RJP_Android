export const nominalCover=(exposure:string)=> exposure.startsWith('XD')||exposure.startsWith('XS') ? 45 : exposure.startsWith('XC') ? 30 : 25;
