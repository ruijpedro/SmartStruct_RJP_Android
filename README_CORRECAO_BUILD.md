# Correção Build WebApp - SmartStruct_RJP

Este patch corrige o erro TypeScript:

TS5107: Option 'moduleResolution=node10' is deprecated.

## Como aplicar

1. Extrair este ZIP.
2. Copiar o ficheiro `tsconfig.json` para a raiz do repositório.
3. Substituir o `tsconfig.json` existente.
4. Fazer commit e push.
5. Voltar a correr o GitHub Actions `build-web`.

## Alteração principal

- `moduleResolution` passou para `bundler`.
- Adicionado `ignoreDeprecations: "6.0"`.
