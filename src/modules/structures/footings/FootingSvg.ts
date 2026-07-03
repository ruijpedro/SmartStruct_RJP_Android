import type {FootingInput} from './FootingTypes';
export function drawFootingSvg(input:FootingInput):string{return `<svg viewBox="0 0 400 180" role="img"><rect x="40" y="80" width="320" height="18" fill="none" stroke="currentColor"/><text x="40" y="125">$Footing ${input.length} m</text></svg>`;}
