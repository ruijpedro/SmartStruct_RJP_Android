import { useMemo, useState } from 'react';
import { modules } from '../../core/constants/modules';

export function Sidebar({active,onSelect}:{active:string;onSelect:(id:string)=>void}){
  const groups=useMemo(()=>[...new Set(modules.map(m=>m.group))],[ ]);
  const activeGroup=modules.find(m=>m.id===active)?.group;
  const [collapsed,setCollapsed]=useState<Record<string,boolean>>({});

  const toggle=(group:string)=>setCollapsed(v=>({...v,[group]:!v[group]}));

  return <aside className="sidebar">
    <div className="brandrow">
      <div>
        <div className="brand">SmartStruct_RJP</div>
        <div className="brand-sub">Engenharia Civil</div>
      </div>
    </div>
    <nav className="sidenav">
      {groups.map(group=>{
        const groupModules=modules.filter(m=>m.group===group);
        const isOpen=activeGroup===group || !collapsed[group];
        return <section className="navgroup" key={group}>
          <button className="navgroup-header" onClick={()=>toggle(group)} aria-expanded={isOpen}>
            <span>{group}</span><span className="chevron">{isOpen?'−':'+'}</span>
          </button>
          {isOpen && <div className="navitems">
            {groupModules.map(m=><button key={m.id} title={m.description} className={'navbtn '+(active===m.id?'active':'')} onClick={()=>onSelect(m.id)}>
              <span className="navicon">{m.icon}</span><span className="navlabel">{m.label}</span>
            </button>)}
          </div>}
        </section>
      })}
    </nav>
  </aside>
}
