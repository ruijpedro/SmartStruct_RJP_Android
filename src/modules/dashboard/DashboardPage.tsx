import { modules } from '../../core/constants/modules';

type DashboardProps={onOpen:(id:string)=>void};

type QuickItem={id:string;label:string;code:string;symbol:string;tone:string};
type CategoryItem={id:string;title:string;subtitle:string;symbol:string;tone:string};

const quick:QuickItem[]=[
  {id:'beams',label:'Vigas',code:'EC2',symbol:'━',tone:'blue'},
  {id:'columns',label:'Pilares',code:'EC2',symbol:'▮',tone:'green'},
  {id:'slabs',label:'Lajes',code:'EC2',symbol:'▦',tone:'orange'},
  {id:'footings',label:'Fundações',code:'EC7',symbol:'▰',tone:'violet'},
  {id:'walls',label:'Muros',code:'EC7',symbol:'▥',tone:'teal'},
  {id:'combinations',label:'Combinações',code:'EC1',symbol:'⌁',tone:'red'},
  {id:'diagrams',label:'Gráficos',code:'Técnicos',symbol:'▥',tone:'amber'},
];

const categories:CategoryItem[]=[
  {id:'beams',title:'Análise Estrutural',subtitle:'Vigas, pórticos, treliças, apoios, cargas e diagramas',symbol:'━',tone:'blue'},
  {id:'columns',title:'Betão Armado',subtitle:'Vigas, pilares, lajes, sapatas e verificações EC2',symbol:'▮',tone:'green'},
  {id:'soils',title:'Geotecnia',subtitle:'Solos, ensaios, capacidade de carga e assentamentos',symbol:'◒',tone:'violet'},
  {id:'walls',title:'Contenção',subtitle:'Muros, ancoragens, pregagens, betão projetado e drenagem',symbol:'▥',tone:'teal'},
  {id:'slopes',title:'Taludes',subtitle:'Estabilidade, reforço, drenagem e proteção',symbol:'◢',tone:'orange'},
  {id:'hydraulics',title:'Hidráulica & Drenagem',subtitle:'Canais, valetas, coletores, bueiros e passagens',symbol:'≈',tone:'blue'},
  {id:'library',title:'Biblioteca Técnica',subtitle:'Materiais, perfis, solos, fórmulas, tabelas e normas',symbol:'▤',tone:'amber'},
  {id:'settings',title:'Ferramentas',subtitle:'Conversões, calculadoras rápidas e utilitários',symbol:'⚙',tone:'blue'},
];

const recent=[
  {title:'Viga contínua',meta:'Análise estrutural',id:'beams'},
  {title:'Pilar EC2',meta:'Betão armado',id:'columns'},
  {title:'Muro de suporte',meta:'Contenção EC7',id:'walls'},
  {title:'Sapata isolada',meta:'Fundação EC7',id:'footings'},
];

export function DashboardPage({onOpen}:DashboardProps){
  return <div className="dashboard-v25">
    <section className="dashboard-hero">
      <div>
        <div className="hero-brand-line"><span className="hero-logo">S</span><h1>SmartStruct_RJP</h1></div>
        <div className="hero-meta"><span className="version-pill">V25</span><span>EC2 / EC7 · EC1 · Gráficos técnicos</span></div>
      </div>
      <div className="hero-actions" aria-label="Ações rápidas">
        <button title="Projetos" onClick={()=>onOpen('projects')}>▣</button>
        <button title="Biblioteca" onClick={()=>onOpen('library')}>▤</button>
        <button title="Configurações" onClick={()=>onOpen('settings')}>⚙</button>
      </div>
    </section>

    <section className="quick-strip" aria-label="Módulos rápidos">
      {quick.map(item=><button key={item.id} className={`quick-module tone-${item.tone}`} onClick={()=>onOpen(item.id)}>
        <span className="quick-symbol">{item.symbol}</span>
        <strong>{item.label}</strong>
        <small>{item.code}</small>
        <span className="quick-accent"/>
      </button>)}
    </section>

    <section className="dashboard-section">
      <div className="section-title"><span>▦</span><strong>Módulos principais</strong></div>
      <div className="category-grid">
        {categories.map(item=><button key={item.title} className={`category-card tone-${item.tone}`} onClick={()=>onOpen(item.id)}>
          <span className="category-icon">{item.symbol}</span>
          <span className="category-copy"><strong>{item.title}</strong><small>{item.subtitle}</small></span>
          <span className="category-arrow">›</span>
        </button>)}
      </div>
    </section>

    <section className="dashboard-section recent-section">
      <div className="section-title recent-title"><span>◷</span><strong>Projetos recentes</strong><button onClick={()=>onOpen('projects')}>Ver todos ›</button></div>
      <div className="recent-grid">
        {recent.map((item,index)=><button key={item.title} className="recent-card" onClick={()=>onOpen(item.id)}>
          <span className={`recent-icon tone-${['blue','green','teal','violet'][index]}`}>{['⌁','▮','▥','▰'][index]}</span>
          <span><strong>{item.title}</strong><small>{item.meta}</small></span>
        </button>)}
      </div>
    </section>
  </div>
}
