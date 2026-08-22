# Tratamento de conflitos e exceções

[← Voltar ao índice da documentação](../README.md)

## 13.1 Tipos de restrição

### Restrições rígidas

Não podem ser violadas sem gerar conflito:

- atração fechada;
- atividade fora das datas da viagem;
- reserva fixa;
- item bloqueado;
- sobreposição de horários;
- tempo diário indisponível;
- coordenadas inexistentes para cálculo geográfico;
- deslocamento que torna impossível cumprir um compromisso.

### Preferências flexíveis

Podem ser parcialmente descumpridas com penalidade:

- ritmo;
- equilíbrio entre os dias;
- preferência por iniciar perto da hospedagem;
- diversidade de categorias;
- preferência por menos deslocamentos;
- horário desejado, mas não obrigatório;
- prioridade relativa.

## 13.2 Catálogo inicial de alertas

| Código | Situação | Ação sugerida |
|---|---|---|
| HORARIO_FECHADO | Visita fora do funcionamento | Mover para outro horário ou dia |
| RESERVA_INALCANCAVEL | Não há tempo para chegar à reserva | Remover ou encurtar atividade anterior |
| SOBREPOSICAO | Duas atividades ocupam o mesmo período | Reorganizar um dos itens |
| DIA_EXCESSIVO | Carga superior ao ritmo escolhido | Mover uma atividade |
| DESLOCAMENTO_ALTO | Tempo de transporte desproporcional | Agrupar com outro dia |
| DADO_INCOMPLETO | Horário ou duração desconhecidos | Preencher manualmente |
| ROTA_INDISPONIVEL | Serviço de rotas falhou | Tentar novamente ou usar estimativa |
| NAO_PLANEJADO | Atração não coube | Aumentar período ou reduzir duração |
| DADO_DESATUALIZADO | Informação externa pode estar antiga | Confirmar na fonte oficial |

## 13.3 Severidades

- **Informação:** não exige ação.
- **Aviso:** roteiro continua utilizável, mas merece atenção.
- **Conflito:** compromete uma parte do planejamento.
- **Bloqueio:** impede concluir uma geração válida.

## 13.4 Comunicação

Uma mensagem de erro deve responder:

1. O que aconteceu?
2. Por que aconteceu?
3. O que o usuário pode fazer?

Exemplo:

> O Museu fecha às 17h, mas a chegada estimada é 17h20. Mova a visita para outro dia ou reduza uma atividade anterior.

---
