import { BookOpen, Moon, Settings, Sun } from 'lucide-react';
import { useState } from 'react';
import { modules } from '../../core/constants/modules';
import { ModuleIcon } from '../components/ModuleIcon';

const primaryIds = ['dashboard','beams','columns','slabs','footings','walls','combinations','diagrams'];
const groups = [
  { title: 'Geotecnia', ids: ['soils','slopes'] },
  { title: 'Estabilização', ids: ['soilnails','anchors','shotcrete','drainage','rockfill'] },
];

export function Sidebar({active,onSelect}:{active:string;onSelect:(id:string)=>void}){
  const [dark,setDark]=useState(true);
  const get=(id:string)=>modules.find(m=>m.id===id);
  return <aside className="sidebar sidebar-v26">
    <div className="brand-block">
      <div className="brand-symbol" aria-hidden="true"><span/><span/><span/></div>
      <div><div className="brand">SmartStruct_RJP</div><div className="version-chip">V26</div></div>
    </div>

    <nav className="side-scroll" aria-label="Navegação principal">
      <div className="nav-primary">
        {primaryIds.map(id=>{const m=get(id); if(!m)return null; return <button key={id} className={'navbtn '+(active===id?'active':'')} onClick={()=>onSelect(id)}><ModuleIcon id={id} size={19}/><span>{m.label}</span></button>})}
      </div>

      {groups.map(g=><div className="navgroup" key={g.title}><div className="navtitle">{g.title}</div>{g.ids.map(id=>{const m=get(id); if(!m)return null; return <button key={id} className={'navbtn '+(active===id?'active':'')} onClick={()=>onSelect(id)}><ModuleIcon id={id} size={18}/><span>{m.label}</span></button>})}</div>)}

      <div className="navgroup"><div className="navtitle">Referência</div>
        <button className={'navbtn '+(active==='library'?'active':'')} onClick={()=>onSelect('library')}><BookOpen size={18}/><span>Biblioteca técnica</span></button>
        <button className={'navbtn '+(active==='settings'?'active':'')} onClick={()=>onSelect('settings')}><Settings size={18}/><span>Configurações</span></button>
      </div>
    </nav>

    <div className="sidebar-footer">
      <button className="theme-toggle" onClick={()=>setDark(!dark)} aria-label="Alternar tema">{dark?<Moon size={17}/>:<Sun size={17}/>}<span>Tema escuro</span><i className={dark?'on':''}/></button>
    </div>
  </aside>
}
