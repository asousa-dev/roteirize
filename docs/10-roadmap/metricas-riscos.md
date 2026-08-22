# Métricas de sucesso e riscos

[← Voltar ao índice da documentação](../README.md)

## 28. Métricas de sucesso

### 28.1 Métrica principal

Percentual de viagens em que o usuário consegue gerar e manter um roteiro sem conflito bloqueante.

### 28.2 Métricas de produto

- taxa de conclusão da criação de viagem;
- média de atrações adicionadas;
- taxa de geração concluída;
- percentual de atrações planejadas;
- quantidade de alterações manuais após geração;
- percentual de gerações aceitas sem regeneração imediata;
- número de conflitos por viagem;
- retorno para consultar uma viagem criada.

### 28.3 Métricas técnicas

- tempo de resposta;
- duração da geração;
- taxa de falhas externas;
- taxa de cache;
- erros por endpoint;
- testes no pipeline;
- regressões do algoritmo.

### 28.4 Indicadores de qualidade do algoritmo

- prioridade total atendida;
- tempo total de deslocamento;
- violações rígidas;
- diferença de carga entre dias;
- quantidade não planejada;
- estabilidade após pequenas alterações.

---

---

## 29. Riscos e respostas

| Risco | Impacto | Resposta |
|---|---|---|
| Escopo grande demais | Projeto nunca termina | MVP rígido e roadmap por entregas |
| API externa cara ou limitada | Funções indisponíveis | Adaptadores, cache, fallback e cadastro manual |
| Horários incorretos | Roteiro inviável | Mostrar fonte, permitir correção e sinalizar incerteza |
| Algoritmo complexo | Bloqueio no desenvolvimento | Evolução incremental e solução heurística |
| Interface de mapa difícil no celular | Experiência ruim | Agenda como representação principal no mobile |
| Datas e fusos incorretos | Erros graves | Modelagem explícita e testes |
| Perda de alterações | Desconfiança | Transações e preservação da última versão válida |
| Código excessivamente abstrato | Aprendizado prejudicado | Modularidade simples e refatoração guiada por necessidade |
| Falhas de segurança | Exposição de dados | Revisão, testes, limites e padrões seguros |
| Dependência de dados em tempo real | Resultado instável | MVP baseado também em entradas manuais |

---
