import type {WallInput} from './WallTypes';
export function drawWallSvg(input:WallInput):string{return `<svg viewBox="0 0 400 180" role="img"><rect x="40" y="80" width="320" height="18" fill="none" stroke="currentColor"/><text x="40" y="125">$Wall ${input.length} m</text></svg>`;}
