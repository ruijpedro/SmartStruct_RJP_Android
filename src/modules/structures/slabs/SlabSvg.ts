import type {SlabInput} from './SlabTypes';
export function drawSlabSvg(input:SlabInput):string{return `<svg viewBox="0 0 400 180" role="img"><rect x="40" y="80" width="320" height="18" fill="none" stroke="currentColor"/><text x="40" y="125">$Slab ${input.length} m</text></svg>`;}
