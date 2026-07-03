import type {CheckResult} from './types';
export const ok=(label:string,value?:string|number):CheckResult=>({label,state:'ok',value});
export const warn=(label:string,note?:string):CheckResult=>({label,state:'warning',note});
export const fail=(label:string,note?:string):CheckResult=>({label,state:'fail',note});
