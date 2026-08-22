# Regras de negócio

[← Voltar ao índice da documentação](../README.md)

## 12.1 Viagem

| ID | Regra |
|---|---|
| RN-VIA-001 | A data final não pode ser anterior à data inicial. |
| RN-VIA-002 | O MVP aceita viagens de 1 a 14 dias. |
| RN-VIA-003 | Uma viagem pertence a exatamente um usuário no MVP. |
| RN-VIA-004 | Cada viagem possui um fuso horário associado ao destino. |
| RN-VIA-005 | A cidade e o país são obrigatórios. |
| RN-VIA-006 | O horário final de um dia deve ser posterior ao inicial. |
| RN-VIA-007 | Alterar datas recria os dias somente após confirmação do usuário. |
| RN-VIA-008 | Remover um dia remove sua programação, mas não remove automaticamente as atrações da viagem. |

## 12.2 Atrações

| ID | Regra |
|---|---|
| RN-LUG-001 | Toda atração precisa de nome e coordenadas válidas. |
| RN-LUG-002 | A duração deve ficar entre 15 minutos e 8 horas no MVP. |
| RN-LUG-003 | A duração será armazenada em minutos. |
| RN-LUG-004 | A prioridade varia de 1, baixa, a 5, indispensável. |
| RN-LUG-005 | A mesma atração não deve ser adicionada duas vezes sem confirmação. |
| RN-LUG-006 | Dados externos podem ser sobrescritos manualmente para aquela viagem. |
| RN-LUG-007 | Uma reserva fixa deve possuir data e horário. |
| RN-LUG-008 | Uma atração sem horário conhecido pode ser planejada, mas recebe indicação de incerteza. |

## 12.3 Geração

| ID | Regra |
|---|---|
| RN-GER-001 | O algoritmo deve respeitar todas as restrições rígidas ou declarar conflito. |
| RN-GER-002 | Itens bloqueados não podem ser movidos durante o recálculo. |
| RN-GER-003 | Reservas fixas têm precedência sobre atrações flexíveis. |
| RN-GER-004 | Atrações de maior prioridade devem ter maior chance de inclusão. |
| RN-GER-005 | O sistema não deve ocultar atrações não encaixadas. |
| RN-GER-006 | O roteiro anterior permanece ativo até a nova geração terminar com sucesso. |
| RN-GER-007 | A mesma entrada e a mesma versão do algoritmo devem produzir resultado reproduzível sempre que possível. |
| RN-GER-008 | Todo resultado deve registrar a versão do algoritmo utilizada. |
| RN-GER-009 | Alterações relevantes tornam a última geração desatualizada. |
| RN-GER-010 | Uma falha de rota externa deve permitir estimativa alternativa identificada como menos precisa. |

## 12.4 Edição

| ID | Regra |
|---|---|
| RN-EDI-001 | Uma alteração manual pode criar um alerta, mas não deve ser silenciosamente desfeita. |
| RN-EDI-002 | Mover um item recalcula os horários posteriores do mesmo dia. |
| RN-EDI-003 | Um item fixo ou bloqueado exige confirmação antes de ser movido manualmente. |
| RN-EDI-004 | Excluir um item da agenda não precisa removê-lo da lista de atrações; ele pode voltar para “Não planejadas”. |

## 12.5 Exclusão

| ID | Regra |
|---|---|
| RN-EXC-001 | A exclusão de viagem exige confirmação explícita. |
| RN-EXC-002 | A exclusão deve remover ou anonimizar dados dependentes conforme a política definida. |
| RN-EXC-003 | No desenvolvimento, exclusão lógica pode ser utilizada para auditoria, desde que não apareça ao usuário. |

---
