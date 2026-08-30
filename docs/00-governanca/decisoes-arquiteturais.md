# Decisões arquiteturais iniciais

[← Voltar ao índice da documentação](../README.md)

## ADR-001 — Monorepositório

**Decisão:** frontend, backend e documentação no mesmo repositório.

**Motivo:** facilita coordenação, issues, documentação e execução local.

**Consequência:** pipeline deve identificar as partes alteradas ou executar ambos enquanto o projeto for pequeno.

## ADR-002 — Frontend e backend separados

**Decisão:** Next.js não será responsável pelas regras principais do backend.

**Motivo:** objetivo educacional de construir uma API própria e separar responsabilidades.

**Consequência:** será necessário administrar CORS, sessão e contratos.

## ADR-003 — Backend em Java com Spring Boot

**Decisão:** utilizar Java e Spring Boot.

**Motivo:** aprofundar orientação a objetos, APIs, segurança, testes e arquitetura backend.

**Consequência:** curva inicial maior que uma solução totalmente JavaScript.

## ADR-004 — PostgreSQL

**Decisão:** banco relacional.

**Motivo:** o domínio possui relações, consistência e consultas estruturadas.

**Consequência:** mudanças exigem migrações bem cuidadas.

## ADR-005 — Monólito modular

**Decisão:** não usar microserviços no MVP.

**Motivo:** reduzir complexidade operacional e manter foco no produto.

**Consequência:** limites dos módulos devem ser respeitados internamente.

## ADR-006 — Provedor externo atrás de adaptadores

**Decisão:** não espalhar modelos de mapas pelo domínio.

**Motivo:** custos, limites e fornecedores podem mudar.

**Consequência:** exige modelos internos e conversão.

## ADR-007 — Heurística incremental

**Decisão:** começar com algoritmo simples e testável.

**Motivo:** uma solução boa e explicável entrega valor antes de uma otimização perfeita.

**Consequência:** o primeiro resultado pode não ser globalmente ótimo.

## ADR-008 — Viagem privada por padrão

**Decisão:** somente o proprietário acessa a viagem no MVP.

**Motivo:** privacidade e autorização simples.

**Consequência:** colaboração será adicionada com modelo explícito de permissões.

## ADR-009 — Geoapify para autocomplete de cidades

**Status:** aceita.

**Contexto:** o destino de uma viagem precisa representar uma cidade real. Um campo de texto livre permitiria valores inválidos e não forneceria coordenadas para mapas, rotas e cálculos de deslocamento.

**Decisão:** utilizar a Address Autocomplete API da Geoapify como primeiro provedor de busca de cidades.

A integração será realizada exclusivamente pelo backend e permanecerá protegida pela abstração definida na ADR-006. O restante da aplicação utilizará o contrato interno do Roteirize, sem depender dos modelos da Geoapify.

**Motivos:**

- permite restringir os resultados a cidades;
- fornece nome, país, estado e coordenadas;
- retorna um identificador para cada localização;
- atende às necessidades iniciais de autocomplete e geocodificação;
- pode ser substituída sem modificar o contrato público da API.

**Consequências:**

- a busca de cidades depende da disponibilidade de um serviço externo;
- a chave deve ser fornecida pela variável `GEOAPIFY_API_KEY`;
- erros do provedor devem ser convertidos em respostas padronizadas;
- destinos selecionados passam a ser armazenados como dados estruturados;
- uma troca futura de provedor exigirá apenas um novo adaptador e seu mapeamento.

---
