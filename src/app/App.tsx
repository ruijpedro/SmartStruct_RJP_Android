import { useState } from 'react';
import { Sidebar } from '../ui/layout/Sidebar';
import { Topbar } from '../ui/layout/Topbar';
import { DashboardPage } from '../modules/dashboard/DashboardPage';
import { BeamPage } from '../modules/structures/beams/BeamPage';
import { PlaceholderModule } from '../ui/components/PlaceholderModule';
import { modules } from '../core/constants/modules';
export function App(){ const [active,setActive]=useState('dashboard'); const current=modules.find(m=>m.id===active); return <div className="app"><Sidebar active={active} onSelect={setActive}/><main><Topbar title={current?.label||'SmartStruct_RJP'}/><div className="content">{active==='dashboard'?<DashboardPage onOpen={setActive}/>:active==='beams'?<BeamPage/>:<PlaceholderModule module={current}/>}</div></main></div> }
