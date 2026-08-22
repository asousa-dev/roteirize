# Telas e experiência do usuário

[← Voltar ao índice da documentação](../README.md)

## 14.1 Mapa de navegação

~~~mermaid
flowchart TD
    A["Página inicial"] --> B["Cadastro ou login"]
    B --> C["Minhas viagens"]
    C --> D["Criar viagem"]
    C --> E["Visão da viagem"]
    D --> E
    E --> F["Atrações"]
    E --> G["Planejador"]
    E --> H["Configurações"]
    G --> I["Resultado e alertas"]
~~~

## 14.2 Página inicial

Objetivo:

- explicar o problema resolvido;
- mostrar o funcionamento em três passos;
- apresentar uma demonstração visual;
- direcionar para cadastro e login.

Conteúdo sugerido:

- título: “Seus lugares. Um roteiro que realmente cabe na viagem.”
- breve explicação;
- exemplo de mapa e linha do tempo;
- benefícios;
- chamada para ação.

## 14.3 Cadastro

Campos:

- nome;
- e-mail;
- senha;
- confirmação de senha;
- aceite dos termos quando houver deploy.

Estados:

- vazio;
- validação durante preenchimento;
- envio;
- e-mail duplicado;
- sucesso.

## 14.4 Login

Campos:

- e-mail;
- senha.

Ações:

- entrar;
- ir para cadastro;
- recuperar senha, pós-MVP.

## 14.5 Minhas viagens

Elementos:

- botão “Nova viagem”;
- cartões de viagens;
- filtros: próximas, atuais e passadas;
- menu de editar, duplicar futuramente e excluir;
- estado vazio com orientação.

Cada cartão exibe:

- título;
- destino;
- datas;
- quantidade de dias;
- quantidade de atrações;
- status do planejamento.

Status possíveis:

- configuração incompleta;
- pronto para gerar;
- gerado;
- desatualizado;
- com conflitos.

## 14.6 Assistente de nova viagem

### Etapa 1 — Destino e datas

- título opcional sugerido automaticamente;
- cidade;
- país;
- fuso horário detectado;
- data inicial;
- data final.

### Etapa 2 — Base e deslocamento

- busca da hospedagem;
- seleção no mapa;
- modo principal de deslocamento;
- opção “ainda não sei onde vou ficar”.

### Etapa 3 — Ritmo e horários

- tranquilo;
- moderado;
- intenso;
- horário padrão de início;
- horário padrão de término;
- pausa desejada.

### Etapa 4 — Confirmação

- resumo;
- editar etapas;
- criar viagem.

## 14.7 Visão geral da viagem

Exibe:

- destino e datas;
- progresso de configuração;
- quantidade de atrações;
- quantidade planejada;
- alertas;
- atalhos para adicionar lugares e gerar;
- última atualização do roteiro.

## 14.8 Tela de atrações

Divisão recomendada:

- pesquisa;
- resultados;
- atrações adicionadas;
- painel ou modal de configuração.

Dados editáveis:

- nome personalizado;
- duração;
- prioridade;
- observação;
- disponibilidade;
- reserva fixa;
- categoria;
- confirmação das coordenadas.

Filtros:

- prioridade;
- categoria;
- com dados incompletos;
- com reserva;
- planejadas e não planejadas.

## 14.9 Planejador

### Desktop

- coluna esquerda: dias e linha do tempo;
- área central ou direita: mapa;
- painel lateral: detalhes e alertas.

### Celular

- alternância entre “Agenda” e “Mapa”;
- cartões de atividades;
- ações em menu;
- edição por formulário quando arrastar não for acessível.

Elementos da atividade:

- horário;
- nome;
- duração;
- deslocamento até o próximo ponto;
- prioridade;
- estado de bloqueio;
- alertas;
- menu de ações.

## 14.10 Modal de geração

Antes de gerar:

- quantidade de atrações;
- quantidade de dias;
- dados ausentes;
- itens fixos;
- opção de preservar itens bloqueados.

Durante:

- etapa atual;
- indicador de progresso;
- possibilidade de cancelar quando tecnicamente seguro.

Depois:

- quantidade planejada;
- quantidade não planejada;
- tempo total de deslocamento;
- conflitos;
- resumo das principais decisões.

## 14.11 Estados obrigatórios por tela

Toda tela relevante deve prever:

- carregando;
- vazia;
- sucesso;
- erro recuperável;
- erro sem acesso;
- dados desatualizados;
- funcionamento parcial por falha externa.

---
