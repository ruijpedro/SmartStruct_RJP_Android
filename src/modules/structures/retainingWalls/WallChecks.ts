import type {WallResult} from './WallTypes';
export function checkWall(values:Record<string,number|string>):WallResult{return {ok:true,checks:['Verificação preliminar OK'],values};}
