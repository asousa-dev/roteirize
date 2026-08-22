# Documentação do Roteirize

[← Voltar à apresentação do projeto](../README.md)

Esta pasta reúne a documentação funcional e técnica do Roteirize. O conteúdo está separado por domínio para permitir manutenção, revisão e evolução sem concentrar tudo em um único arquivo.

## Como navegar

As pastas são numeradas apenas para manter uma ordem de leitura. Os documentos continuam independentes e podem ser acessados diretamente.

### 00 — Governança

- [Visão da documentação](00-governanca/visao-documentacao.md)
- [Estrutura do repositório](00-governanca/estrutura-repositorio.md)
- [Decisões arquiteturais](00-governanca/decisoes-arquiteturais.md)
- [Checklists](00-governanca/checklists.md)
- [Histórico da documentação](00-governanca/historico.md)

### 01 — Produto e negócio

- [Resumo executivo](01-produto-negocio/resumo-executivo.md)
- [Problema, visão e proposta de valor](01-produto-negocio/problema-visao-proposta.md)
- [Objetivos e escopo](01-produto-negocio/objetivos-escopo.md)
- [Público-alvo e personas](01-produto-negocio/publico-alvo-personas.md)

### 02 — Requisitos

- [Papéis e permissões](02-requisitos/papeis-permissoes.md)
- [Jornadas do usuário](02-requisitos/jornadas-usuario.md)
- [Requisitos funcionais](02-requisitos/requisitos-funcionais.md)
- [Requisitos não funcionais](02-requisitos/requisitos-nao-funcionais.md)
- [Regras de negócio](02-requisitos/regras-negocio.md)
- [Conflitos e exceções](02-requisitos/conflitos-excecoes.md)

### 03 — Domínio e dados

- [Modelo de domínio e banco de dados](03-dominio-dados/modelo-dominio-banco.md)
- [Glossário](03-dominio-dados/glossario.md)

### 04 — Arquitetura e backend

- [Arquitetura técnica](04-arquitetura-backend/arquitetura-tecnica.md)
- [Algoritmo de geração de roteiros](04-arquitetura-backend/algoritmo-roteiros.md)
- [Observabilidade e tratamento de erros](04-arquitetura-backend/observabilidade-erros.md)

### 05 — API e integrações

- [Contrato inicial da API](05-api-integracoes/api-rest.md)
- [Integrações externas](05-api-integracoes/integracoes-externas.md)

### 06 — Frontend e experiência

- [Telas e experiência do usuário](06-frontend-ux/telas-experiencia.md)

### 07 — Segurança

- [Autenticação, segurança e privacidade](07-seguranca/autenticacao-seguranca-privacidade.md)

### 08 — Testes

- [Estratégia de testes](08-testes/estrategia-testes.md)

### 09 — DevOps e ambiente

- [Ambiente local](09-devops/ambiente-local.md)
- [Git e integração contínua](09-devops/git-integracao-continua.md)

### 10 — Planejamento

- [Backlog inicial](10-roadmap/backlog-inicial.md)
- [Roadmap de desenvolvimento](10-roadmap/roadmap-desenvolvimento.md)
- [Métricas e riscos](10-roadmap/metricas-riscos.md)
- [Critérios de conclusão](10-roadmap/criterios-conclusao.md)
- [Próximos passos](10-roadmap/proximos-passos.md)

## Ordem recomendada de leitura

Para entender o produto:

1. Resumo executivo.
2. Problema, visão e proposta de valor.
3. Objetivos e escopo.
4. Jornadas do usuário.
5. Requisitos funcionais.

Para iniciar a implementação:

1. Estrutura do repositório.
2. Arquitetura técnica.
3. Modelo de domínio e banco de dados.
4. Contrato inicial da API.
5. Roadmap de desenvolvimento.

Para trabalhar no planejador:

1. Regras de negócio.
2. Conflitos e exceções.
3. Algoritmo de geração de roteiros.
4. Estratégia de testes.

## Regra de manutenção

Mudanças no comportamento do produto devem atualizar o documento correspondente no mesmo commit. Decisões arquiteturais relevantes devem ser registradas antes ou junto da implementação.
