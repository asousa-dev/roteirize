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

---
