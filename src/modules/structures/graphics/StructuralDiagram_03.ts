import type {DiagramPoint} from '../shared/types';
export const structuralDiagram03=(points:DiagramPoint[])=>points.map(p=>`${p.x}:${p.v}`).join('|');
