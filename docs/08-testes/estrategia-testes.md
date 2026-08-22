# Estratégia de testes e cenário de aceitação

[← Voltar ao índice da documentação](../README.md)

## 22. Estratégia de testes

### 22.1 Pirâmide

- Muitos testes unitários.
- Testes de integração para banco, segurança e provedores.
- Menos testes ponta a ponta, cobrindo jornadas críticas.

### 22.2 Backend

#### Testes unitários

- validação de datas;
- cálculo de capacidade diária;
- conflito de horário;
- seleção por prioridade;
- preservação de bloqueios;
- função de pontuação;
- explicações;
- fallback de distância.

#### Testes de integração

- repositórios com PostgreSQL real em contêiner;
- migrações;
- autenticação;
- autorização por propriedade;
- transações;
- endpoints;
- clientes externos simulados;
- cache.

#### Testes de contrato

- formato das respostas;
- erros padronizados;
- compatibilidade entre frontend e OpenAPI;
- conversão dos fornecedores externos.

### 22.3 Frontend

#### Testes de componentes

- formulário de viagem;
- validações;
- cartão de atração;
- alertas;
- linha do tempo;
- estados vazios e de erro.

#### Testes ponta a ponta

1. cadastrar e entrar;
2. criar viagem;
3. adicionar atrações;
4. gerar roteiro;
5. mover e bloquear item;
6. recalcular;
7. sair e entrar novamente;
8. confirmar persistência.

### 22.4 Casos críticos do algoritmo

| Caso | Resultado esperado |
|---|---|
| Uma atração em um dia | Planejada dentro do horário |
| Duas atrações próximas | Ordenação com baixo deslocamento |
| Reserva fixa | Horário preservado |
| Atração fechada | Não planejada ou movida |
| Duração maior que o dia | Não planejada com explicação |
| Item bloqueado | Posição preservada |
| Falha no provedor | Fallback sinalizado |
| Mais atrações que tempo | Prioridades mais altas favorecidas |
| Mesmas entradas | Resultado reproduzível |
| Atualização concorrente | Erro 409 sem perda silenciosa |

### 22.5 Dados de teste

Criar conjuntos fixos:

- cidade pequena com cinco pontos;
- cidade com pontos distantes;
- viagem de um dia;
- viagem de sete dias;
- horários incompletos;
- reservas conflitantes;
- coordenadas inválidas;
- falha simulada de fornecedor.

Os testes não devem depender de internet real.

### 22.6 Meta inicial de cobertura

Cobertura numérica não substitui qualidade. Como referência:

- regras de domínio e algoritmo: alta cobertura;
- controllers simples: cobertura suficiente por integração;
- componentes críticos: principais estados;
- jornadas: ao menos o caminho principal e um erro importante.

---

---

## Apêndice A — Cenário completo de aceitação

### Viagem

- Destino: Roma.
- Período: 10 a 13 de maio.
- Horário diário: 9h às 19h.
- Ritmo: moderado.
- Base: hospedagem informada.

### Atrações

- Coliseu: prioridade 5, duração 2 horas, reserva às 10h do primeiro dia.
- Fórum Romano: prioridade 5, duração 2 horas.
- Fontana di Trevi: prioridade 4, duração 45 minutos.
- Panteão: prioridade 4, duração 1 hora.
- Museus Vaticanos: prioridade 5, duração 3 horas, reserva no segundo dia.
- Villa Borghese: prioridade 3, duração 2 horas.
- Atração fictícia fechada em todas as datas: prioridade 2.

### Resultado esperado

- reservas preservadas;
- Fórum considerado próximo ao Coliseu;
- atração fechada não planejada;
- horários e deslocamentos exibidos;
- explicação para o item não planejado;
- possibilidade de bloquear Fontana di Trevi;
- recálculo posterior preserva o bloqueio;
- roteiro permanece salvo após nova sessão.

---
