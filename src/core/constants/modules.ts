export type ModuleInfo={id:string;label:string;group:string;icon:string;description:string};

export const modules:ModuleInfo[]=[
{id:'dashboard',label:'Início',group:'Projeto',icon:'⌂',description:'Resumo e acessos rápidos.'},
{id:'projects',label:'Projetos',group:'Projeto',icon:'▣',description:'Projetos, notas e histórico.'},

{id:'beams',label:'Vigas',group:'Análise Estrutural',icon:'━',description:'Vigas isostáticas e hiperestáticas.'},
{id:'frames',label:'Pórticos',group:'Análise Estrutural',icon:'Π',description:'Modelação e análise de pórticos 2D.'},
{id:'trusses',label:'Treliças',group:'Análise Estrutural',icon:'△',description:'Treliças planas e esforços axiais.'},
{id:'combinations',label:'Combinações',group:'Análise Estrutural',icon:'⌁',description:'Combinações de ações EC0/EC1.'},
{id:'diagrams',label:'Gráficos técnicos',group:'Análise Estrutural',icon:'▥',description:'Diagramas de esforços e deformadas.'},
{id:'columns',label:'Pilares',group:'Betão Armado',icon:'▮',description:'Pilares e flexocompressão EC2.'},
{id:'slabs',label:'Lajes',group:'Betão Armado',icon:'▦',description:'Lajes uni e bidirecionais.'},

{id:'footings',label:'Sapatas',group:'Geotecnia e Fundações',icon:'▰',description:'Sapatas EC7/EC2.'},
{id:'soils',label:'Solos',group:'Geotecnia e Fundações',icon:'◒',description:'Caracterização geotécnica.'},
{id:'slopes',label:'Taludes',group:'Geotecnia e Fundações',icon:'◢',description:'Estabilidade de taludes.'},

{id:'walls',label:'Muros',group:'Contenção e Estabilização',icon:'▥',description:'Muros de suporte e contenção.'},
{id:'soilnails',label:'Pregagens',group:'Contenção e Estabilização',icon:'↘',description:'Pregagens e solo grampeado.'},
{id:'anchors',label:'Ancoragens',group:'Contenção e Estabilização',icon:'⌁',description:'Ancoragens ativas e passivas.'},
{id:'shotcrete',label:'Betão projetado',group:'Contenção e Estabilização',icon:'▧',description:'Camadas, fibras, malhas e consumos.'},
{id:'drainage',label:'Drenagem',group:'Contenção e Estabilização',icon:'≈',description:'Máscaras drenantes e drenos.'},
{id:'rockfill',label:'Enrocamentos',group:'Contenção e Estabilização',icon:'◆',description:'Proteção e volumes.'},

{id:'hydraulics',label:'Hidráulica',group:'Hidráulica e Drenagem',icon:'≋',description:'Canais, valetas, PH e drenagem.'},
{id:'library',label:'Biblioteca Técnica',group:'Referência',icon:'▤',description:'Materiais, normas, fórmulas e tabelas.'},
{id:'settings',label:'Configurações',group:'Referência',icon:'⚙',description:'Preferências da aplicação.'}
];
