export type ModuleInfo={id:string;label:string;group:string;icon:string;description:string};
export const modules:ModuleInfo[]=[
{id:'dashboard',label:'Início',group:'Projeto',icon:'🏠',description:'Dashboard e acessos rápidos.'},
{id:'projects',label:'Projetos',group:'Projeto',icon:'📁',description:'Projetos e histórico.'},
{id:'beams',label:'Vigas',group:'Estruturas',icon:'📐',description:'Vigas isostáticas e hiperestáticas.'},
{id:'columns',label:'Pilares',group:'Estruturas',icon:'🏛️',description:'Pilares EC2.'},
{id:'slabs',label:'Lajes',group:'Estruturas',icon:'▦',description:'Lajes uni e bidirecionais.'},
{id:'footings',label:'Fundações',group:'Estruturas',icon:'⬛',description:'Sapatas EC7/EC2.'},
{id:'walls',label:'Muros',group:'Estruturas',icon:'🧱',description:'Muros e contenções.'},
{id:'combinations',label:'Combinações',group:'Estruturas',icon:'Σ',description:'Combinações EC0/EC1.'},
{id:'diagrams',label:'Gráficos técnicos',group:'Estruturas',icon:'📊',description:'Esforços, deformadas e diagramas.'},
{id:'soils',label:'Solos',group:'Geotecnia',icon:'🌍',description:'Caracterização geotécnica.'},
{id:'slopes',label:'Taludes',group:'Geotecnia',icon:'⛰️',description:'Estabilidade de taludes.'},
{id:'soilnails',label:'Pregagens',group:'Estabilização',icon:'🔩',description:'Pregagens e solo grampeado.'},
{id:'anchors',label:'Ancoragens',group:'Estabilização',icon:'⚓',description:'Ancoragens ativas/passivas.'},
{id:'shotcrete',label:'Betão projetado',group:'Estabilização',icon:'🧱',description:'Camadas, fibras e malhas.'},
{id:'drainage',label:'Drenagem',group:'Estabilização',icon:'💧',description:'Máscaras drenantes e drenos.'},
{id:'rockfill',label:'Enrocamentos',group:'Estabilização',icon:'🪨',description:'Proteção e volumes.'},
{id:'library',label:'Biblioteca técnica',group:'Referência',icon:'📚',description:'Materiais, normas e fórmulas.'},
{id:'settings',label:'Configurações',group:'Referência',icon:'⚙️',description:'Preferências da aplicação.'}
];
