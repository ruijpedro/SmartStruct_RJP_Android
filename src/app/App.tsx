import { BookOpen, Folder, Home, Plus, Settings } from 'lucide-react';
import { useState } from 'react';
import { Sidebar } from '../ui/layout/Sidebar';
import { Topbar } from '../ui/layout/Topbar';
import { DashboardPage } from '../modules/dashboard/DashboardPage';
import { BeamPage } from '../modules/structures/beams/BeamPage';
import { PlaceholderModule } from '../ui/components/PlaceholderModule';
import { modules } from '../core/constants/modules';

export function App(){
  const [active,setActive]=useState('dashboard');
  const current=modules.find(m=>m.id===active);
  return <div className="app">
    <Sidebar active={active} onSelect={setActive}/>
    <main>
      <Topbar title={active==='dashboard'?'Dashboard':current?.label||'SmartStruct_RJP'}/>
      <div className="content">{active==='dashboard'?<DashboardPage onOpen={setActive}/>:active==='beams'?<BeamPage/>:<PlaceholderModule module={current}/>}</div>
    </main>
    <nav className="mobile-dock" aria-label="Navegação móvel">
      <button className={active==='dashboard'?'active':''} onClick={()=>setActive('dashboard')}><Home size={20}/><span>Início</span></button>
      <button className={active==='projects'?'active':''} onClick={()=>setActive('projects')}><Folder size={20}/><span>Projetos</span></button>
      <button className="dock-plus" onClick={()=>setActive('beams')} aria-label="Novo cálculo"><Plus size={26}/></button>
      <button className={active==='library'?'active':''} onClick={()=>setActive('library')}><BookOpen size={20}/><span>Biblioteca</span></button>
      <button className={active==='settings'?'active':''} onClick={()=>setActive('settings')}><Settings size={20}/><span>Config.</span></button>
    </nav>
  </div>
}
