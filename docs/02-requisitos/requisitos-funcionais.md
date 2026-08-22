# Requisitos funcionais

[← Voltar ao índice da documentação](../README.md)

Os requisitos usam identificadores permanentes. Quando um requisito mudar, seu identificador deve ser preservado e a alteração registrada.

## 10.1 Autenticação e conta

| ID | Requisito | Prioridade |
|---|---|---|
| RF-AUT-001 | Permitir cadastro com nome, e-mail e senha. | Obrigatório |
| RF-AUT-002 | Impedir cadastro de e-mail já utilizado. | Obrigatório |
| RF-AUT-003 | Permitir login e logout. | Obrigatório |
| RF-AUT-004 | Manter a sessão do usuário com segurança. | Obrigatório |
| RF-AUT-005 | Exibir os dados básicos da conta autenticada. | Obrigatório |
| RF-AUT-006 | Permitir alteração de nome. | Desejável |
| RF-AUT-007 | Permitir recuperação de senha. | Pós-MVP |
| RF-AUT-008 | Permitir exclusão da própria conta. | Pós-MVP |

## 10.2 Viagens

| ID | Requisito | Prioridade |
|---|---|---|
| RF-VIA-001 | Criar uma viagem com título, cidade, país, data inicial e data final. | Obrigatório |
| RF-VIA-002 | Listar as viagens do usuário. | Obrigatório |
| RF-VIA-003 | Exibir viagens futuras, atuais e passadas. | Desejável |
| RF-VIA-004 | Editar os dados da viagem. | Obrigatório |
| RF-VIA-005 | Excluir uma viagem mediante confirmação. | Obrigatório |
| RF-VIA-006 | Definir localização de hospedagem ou ponto-base. | Obrigatório |
| RF-VIA-007 | Definir horário padrão de início e término dos passeios. | Obrigatório |
| RF-VIA-008 | Personalizar o horário de um dia específico. | Obrigatório |
| RF-VIA-009 | Definir ritmo tranquilo, moderado ou intenso. | Obrigatório |
| RF-VIA-010 | Definir modo principal de deslocamento quando suportado. | Obrigatório |

## 10.3 Lugares e atrações

| ID | Requisito | Prioridade |
|---|---|---|
| RF-LUG-001 | Pesquisar lugares por texto. | Obrigatório |
| RF-LUG-002 | Exibir nome, endereço e posição no mapa. | Obrigatório |
| RF-LUG-003 | Adicionar um resultado à viagem. | Obrigatório |
| RF-LUG-004 | Cadastrar um lugar manualmente. | Obrigatório |
| RF-LUG-005 | Remover um lugar da viagem. | Obrigatório |
| RF-LUG-006 | Definir duração estimada da visita. | Obrigatório |
| RF-LUG-007 | Definir prioridade de 1 a 5. | Obrigatório |
| RF-LUG-008 | Adicionar observações. | Desejável |
| RF-LUG-009 | Definir dias e horários disponíveis manualmente. | Obrigatório |
| RF-LUG-010 | Marcar uma visita como compromisso fixo. | Obrigatório |
| RF-LUG-011 | Identificar possíveis lugares duplicados. | Desejável |
| RF-LUG-012 | Exibir a origem e a atualização dos dados externos. | Desejável |

## 10.4 Planejamento

| ID | Requisito | Prioridade |
|---|---|---|
| RF-ROT-001 | Gerar uma programação para os dias da viagem. | Obrigatório |
| RF-ROT-002 | Considerar distância, duração, disponibilidade e prioridade. | Obrigatório |
| RF-ROT-003 | Considerar reservas e compromissos fixos. | Obrigatório |
| RF-ROT-004 | Usar a hospedagem como início e término quando configurado. | Obrigatório |
| RF-ROT-005 | Permitir a configuração de pausa para refeição. | Desejável |
| RF-ROT-006 | Informar atrações que não puderam ser encaixadas. | Obrigatório |
| RF-ROT-007 | Justificar alertas e exclusões. | Obrigatório |
| RF-ROT-008 | Exibir horários previstos de início e término. | Obrigatório |
| RF-ROT-009 | Exibir tempo previsto de deslocamento entre itens. | Obrigatório |
| RF-ROT-010 | Permitir bloquear uma atividade em um dia e horário. | Obrigatório |
| RF-ROT-011 | Recalcular preservando atividades bloqueadas. | Obrigatório |
| RF-ROT-012 | Recalcular somente um dia. | Obrigatório |
| RF-ROT-013 | Recalcular todo o roteiro. | Obrigatório |
| RF-ROT-014 | Manter registro da última geração. | Desejável |
| RF-ROT-015 | Impedir duas gerações simultâneas para a mesma viagem. | Obrigatório |

## 10.5 Edição manual

| ID | Requisito | Prioridade |
|---|---|---|
| RF-EDI-001 | Mover uma atração para outro dia. | Obrigatório |
| RF-EDI-002 | Reordenar atrações dentro do mesmo dia. | Obrigatório |
| RF-EDI-003 | Alterar manualmente o horário de uma visita. | Desejável |
| RF-EDI-004 | Mostrar o impacto da alteração nos itens posteriores. | Obrigatório |
| RF-EDI-005 | Permitir desfazer a última alteração local. | Pós-MVP |
| RF-EDI-006 | Marcar o roteiro como desatualizado quando dados relevantes mudarem. | Obrigatório |

## 10.6 Visualização

| ID | Requisito | Prioridade |
|---|---|---|
| RF-VIS-001 | Exibir o roteiro separado por dia. | Obrigatório |
| RF-VIS-002 | Exibir uma linha do tempo diária. | Obrigatório |
| RF-VIS-003 | Exibir no mapa os pontos e a ordem de visita. | Obrigatório |
| RF-VIS-004 | Destacar reservas, bloqueios e conflitos. | Obrigatório |
| RF-VIS-005 | Exibir resumo de tempo em atrações e deslocamentos. | Desejável |
| RF-VIS-006 | Adaptar a interface para celular e desktop. | Obrigatório |

## 10.7 Compartilhamento futuro

| ID | Requisito | Versão |
|---|---|---|
| RF-COM-001 | Criar link público somente para leitura. | 1.1 |
| RF-COM-002 | Revogar um link público. | 1.1 |
| RF-COM-003 | Exportar roteiro em PDF. | 1.1 |
| RF-COM-004 | Convidar outro usuário. | 1.2 |
| RF-COM-005 | Definir participante como editor ou visualizador. | 1.2 |
| RF-COM-006 | Registrar alterações colaborativas. | 1.2 |

---
