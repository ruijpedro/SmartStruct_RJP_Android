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
      {active!=='dashboard' && <Topbar title={current?.label||'SmartStruct_RJP'}/>} 
      <div className={active==='dashboard'?'content dashboard-content':'content'}>
        {active==='dashboard'?<DashboardPage onOpen={setActive}/>:active==='beams'?<BeamPage/>:<PlaceholderModule module={current}/>} 
      </div>
      <nav className="mobile-bottom-nav" aria-label="Navegação principal">
        <button className={active==='dashboard'?'active':''} onClick={()=>setActive('dashboard')}><span>⌂</span><small>Início</small></button>
        <button className={active==='projects'?'active':''} onClick={()=>setActive('projects')}><span>▣</span><small>Projetos</small></button>
        <button className="mobile-add" onClick={()=>setActive('beams')}><span>＋</span></button>
        <button className={active==='library'?'active':''} onClick={()=>setActive('library')}><span>▤</span><small>Biblioteca</small></button>
        <button className={active==='settings'?'active':''} onClick={()=>setActive('settings')}><span>⚙</span><small>Definições</small></button>
      </nav>
    </main>
  </div>
}
