# SmartStruct_RJP V7 Profissional

Aplicação Android/Web de apoio ao cálculo estrutural e geotécnico, em português de Portugal, preparada para evoluir como ferramenta académica/profissional RJP.

## V7 — evolução gráfica e combinações

Inclui tudo da V6 e acrescenta:

- **Combinações EC0/EC1**:
  - ELU fundamental;
  - ELS rara;
  - ELS frequente;
  - ELS quase permanente;
  - identificação automática do caso condicionante.

- **Novo separador Gráficos**:
  - esquema de viga biapoiada;
  - cargas distribuídas;
  - reações;
  - diagramas VEd/MEd representados graficamente;
  - deformada/flecha;
  - secção de pilar com armadura longitudinal e estribos;
  - planta de laje com armaduras principais/secundárias;
  - sapata com distribuição de tensões no solo;
  - muro de suporte com terreno, impulso triangular e resultante ativa.

## Módulos existentes

- Vigas EC2;
- Pilares EC2;
- Lajes EC2;
- Fundações/Sapatas EC2-EC7;
- Muros de suporte EC7/EC2;
- Combinações de cargas;
- Gráficos/desenhos técnicos.

## Nota de utilização

Ferramenta académica/de apoio. Os resultados devem ser verificados antes de qualquer utilização profissional.

## Build Android

O workflow `.github/workflows/android.yml` está incluído para gerar APK no GitHub Actions.
