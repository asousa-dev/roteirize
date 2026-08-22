# Geração inteligente do roteiro

[← Voltar ao índice da documentação](../README.md)

## 15.1 Objetivo

Construir uma agenda que maximize o atendimento das prioridades do usuário e minimize conflitos e deslocamentos, respeitando o tempo disponível.

O problema combina características de:

- roteamento;
- agendamento com janelas de tempo;
- seleção de atividades;
- distribuição de carga;
- otimização com múltiplos critérios.

Não é necessário encontrar a solução matemática perfeita no MVP. É necessário produzir uma solução boa, rápida, explicável e testável.

## 15.2 Entradas

### Viagem

- datas;
- fuso horário;
- localização-base;
- modo de deslocamento;
- horário de início e término por dia;
- ritmo;
- pausa.

### Atração

- coordenadas;
- duração;
- prioridade;
- disponibilidade;
- reserva fixa;
- categoria;
- dia preferido;
- horário preferido;
- bloqueio;
- dados confirmados ou estimados.

### Deslocamentos

- matriz de tempos;
- matriz de distâncias;
- origem do cálculo;
- horário em que a estimativa foi obtida.

## 15.3 Saídas

- atrações planejadas;
- dia e posição de cada atração;
- horários estimados;
- trechos de deslocamento;
- atrações não planejadas;
- alertas;
- justificativas;
- pontuação total;
- métricas da solução;
- versão do algoritmo.

## 15.4 Processo proposto para a versão inicial

### Etapa 1 — Validar dados

- verificar datas;
- verificar coordenadas;
- verificar duração;
- detectar reservas fora da viagem;
- detectar bloqueios incompatíveis;
- marcar dados incompletos.

### Etapa 2 — Obter matriz de deslocamentos

Para cada par relevante de pontos:

- consultar o provedor de rotas;
- reutilizar resultado em cache;
- aplicar timeout;
- usar uma estimativa geográfica como fallback;
- marcar o grau de precisão.

### Etapa 3 — Posicionar itens fixos

- reservas;
- atividades bloqueadas;
- compromissos com dia obrigatório.

Esses itens criam espaços antes e depois deles.

### Etapa 4 — Agrupar atrações flexíveis

Agrupar por proximidade geográfica, respeitando:

- número de dias;
- capacidade aproximada;
- disponibilidade;
- prioridade.

Uma primeira implementação pode usar agrupamento simples por distância e inserção gulosa. Algoritmos mais sofisticados devem entrar somente após existirem testes e métricas.

### Etapa 5 — Inserir atividades

Ordenar candidatos por:

- prioridade;
- restrição de horário;
- custo de inserção;
- proximidade;
- duração;
- dificuldade de encaixe.

Para cada candidato, testar posições possíveis e escolher a que causar menor custo sem violar restrições rígidas.

### Etapa 6 — Melhorar a ordem

Aplicar melhorias locais:

- trocar duas atrações;
- mover uma atração para outro ponto;
- inverter pequenos trechos;
- mover entre dias;
- reduzir deslocamento sem quebrar horários.

### Etapa 7 — Construir a linha do tempo

- iniciar no horário diário;
- calcular chegada;
- aguardar abertura quando necessário;
- somar duração;
- inserir deslocamento;
- respeitar pausa;
- terminar no limite definido.

### Etapa 8 — Explicar e persistir

- registrar métricas;
- explicar itens não planejados;
- salvar nova versão apenas se válida;
- manter o roteiro anterior até o fim.

## 15.5 Função de pontuação conceitual

Os pesos devem ser configuráveis e testados, não espalhados pelo código.

~~~text
pontuacao =
    cobertura_de_prioridades
  + eficiencia_de_deslocamento
  + respeito_as_preferencias
  + equilibrio_entre_dias
  - penalidade_por_tempo_ocioso
  - penalidade_por_excesso_de_carga
  - penalidade_por_dados_incertos
~~~

Restrições rígidas não devem ser compensadas por uma pontuação alta. Uma solução que viola uma reserva é inválida ou deve ser explicitamente apresentada como conflitante.

## 15.6 Ritmo

Valores iniciais sugeridos:

| Ritmo | Ocupação máxima aproximada do período diário | Intervalo recomendado |
|---|---:|---:|
| Tranquilo | 65% | 30 minutos |
| Moderado | 80% | 20 minutos |
| Intenso | 90% | 10 minutos |

Esses números são parâmetros iniciais, não verdades universais. O usuário poderá ajustar horários e durações.

## 15.7 Explicabilidade

O sistema deve gerar justificativas curtas a partir de códigos e dados, por exemplo:

- “Incluído na terça porque fica a 8 minutos de duas atrações já planejadas.”
- “Movido para quarta porque não abre na terça.”
- “Não planejado porque requer 3 horas e não existe um intervalo disponível.”
- “Mantido às 15h porque está bloqueado pelo usuário.”

Não é necessário usar IA generativa para essas mensagens.

## 15.8 Reprodutibilidade

Cada geração deve registrar:

- versão do algoritmo;
- parâmetros;
- hash ou versão das entradas;
- horário de execução;
- resultado;
- duração;
- provedor de rotas;
- avisos.

## 15.9 Evolução do algoritmo

1. Ordenação manual com validação.
2. Vizinho mais próximo por dia.
3. Inserção gulosa com prioridades e janelas.
4. Melhorias locais.
5. Agrupamento geográfico.
6. Processamento assíncrono.
7. Comparação entre múltiplas soluções.
8. Otimizador mais avançado, se as métricas justificarem.

---
