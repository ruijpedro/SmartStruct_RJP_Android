export function solveSimpleBeam({L,q}:{L:number;q:number}){const RA=q*L/2; const RB=RA; const Mmax=q*L*L/8; const Vmax=q*L/2; return {RA,RB,Mmax,Vmax};}
