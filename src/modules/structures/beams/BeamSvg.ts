import type {BeamInput} from './BeamTypes';
export function drawBeamSvg(input:BeamInput):string{return `<svg viewBox="0 0 400 180" role="img"><rect x="40" y="80" width="320" height="18" fill="none" stroke="currentColor"/><text x="40" y="125">$Beam ${input.length} m</text></svg>`;}
