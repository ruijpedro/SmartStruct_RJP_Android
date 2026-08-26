import { ChevronRight } from 'lucide-react';
import { ModuleIcon } from '../../ui/components/ModuleIcon';

type OpenFn = (id: string) => void;

type Quick = { id: string; label: string; code: string; tone: string };
const quick: Quick[] = [
  { id: 'beams', label: 'Vigas', code: 'EC2', tone: 'blue' },
  { id: 'columns', label: 'Pilares', code: 'EC2', tone: 'green' },
  { id: 'slabs', label: 'Lajes', code: 'EC2', tone: 'orange' },
  { id: 'footings', label: 'Fundações', code: 'EC7', tone: 'violet' },
  { id: 'walls', label: 'Muros', code: 'EC7', tone: 'cyan' },
  { id: 'combinations', label: 'Combinações', code: 'EC1', tone: 'red' },
  { id: 'diagrams', label: 'Gráficos', code: 'Técnicos', tone: 'amber' },
];

type Area = { id: string; title: string; subtitle: string; target: string; tone: string };
const areas: Area[] = [
  { id: 'analysis', title: 'Análise Estrutural', subtitle: 'Vigas, pórticos, treliças, apoios, cargas e diagramas', target: 'beams', tone: 'blue' },
  { id: 'concrete', title: 'Betão Armado', subtitle: 'Vigas, pilares, lajes, sapatas e verificações EC2', target: 'columns', tone: 'green' },
  { id: 'geotechnics', title: 'Geotecnia', subtitle: 'Solos, ensaios, capacidade de carga e assentamentos', target: 'soils', tone: 'violet' },
  { id: 'containment', title: 'Contenção', subtitle: 'Muros, ancoragens, pregagens, betão projetado e drenagem', target: 'walls', tone: 'cyan' },
  { id: 'slopes', title: 'Taludes', subtitle: 'Estabilidade, reforço, drenagem e proteção', target: 'slopes', tone: 'orange' },
  { id: 'hydraulics', title: 'Hidráulica & Drenagem', subtitle: 'Canais, valetas, coletores, bueiros e passagens', target: 'drainage', tone: 'blue' },
  { id: 'library', title: 'Biblioteca Técnica', subtitle: 'Materiais, perfis, solos, fórmulas, tabelas e normas', target: 'library', tone: 'amber' },
  { id: 'tools', title: 'Ferramentas', subtitle: 'Conversões, calculadoras rápidas e utilitários', target: 'settings', tone: 'slate' },
];

export function DashboardPage({onOpen}:{onOpen:OpenFn}){
  return <div className="dashboard-v26">
    <section className="quick-panel" aria-label="Acessos rápidos">
      <div className="quick-grid">
        {quick.map(item => <button className={`quick-card tone-${item.tone}`} key={item.id} onClick={()=>onOpen(item.id)}>
          <span className="quick-icon"><ModuleIcon id={item.id} size={34}/></span>
          <span className="quick-label">{item.label}</span>
          <span className="quick-code">{item.code}</span>
          <span className="quick-line" />
        </button>)}
      </div>
    </section>

    <section className="modules-panel">
      <div className="section-heading"><span className="section-mark"><ModuleIcon id="dashboard" size={18}/></span><strong>Módulos principais</strong></div>
      <div className="area-grid">
        {areas.map(area => <button className="area-card" key={area.id} onClick={()=>onOpen(area.target)}>
          <span className={`area-icon tone-${area.tone}`}><ModuleIcon id={area.id} size={34}/></span>
          <span className="area-copy"><strong>{area.title}</strong><small>{area.subtitle}</small></span>
          <span className="area-arrow"><ChevronRight size={18}/></span>
        </button>)}
      </div>
    </section>
  </div>
}
