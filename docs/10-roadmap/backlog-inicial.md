# Backlog inicial

[← Voltar ao índice da documentação](../README.md)

## 26.1 Épico A — Fundação

### US-A01 — Estruturar repositório

Como desenvolvedor, quero separar frontend, backend e documentação para manter o projeto compreensível.

Critérios:

- estrutura criada;
- README inicial;
- aplicações executam localmente;
- arquivos temporários ignorados;
- sem segredos versionados.

### US-A02 — Banco local

Como desenvolvedor, quero iniciar o PostgreSQL de forma reproduzível.

Critérios:

- banco inicia por comando documentado;
- credenciais de desenvolvimento vêm do ambiente;
- aplicação valida a conexão;
- nenhuma credencial real está no Git.

### US-A03 — Migrações

Como desenvolvedor, quero versionar o esquema.

Critérios:

- migração inicial automática;
- banco vazio chega ao estado atual;
- erro de migração impede inicialização silenciosamente incorreta.

## 26.2 Épico B — Conta

### US-B01 — Cadastro

Como visitante, quero criar uma conta para salvar viagens.

Critérios:

- campos obrigatórios validados;
- e-mail normalizado;
- duplicidade rejeitada;
- senha nunca retornada;
- sucesso direciona para o fluxo definido.

### US-B02 — Login

Como usuário, quero entrar com segurança.

Critérios:

- credenciais inválidas não revelam qual campo falhou;
- sessão criada;
- acesso privado permitido;
- tentativas excessivas podem ser limitadas.

### US-B03 — Logout

Como usuário, quero encerrar minha sessão.

Critérios:

- sessão revogada;
- cookie removido;
- rota privada volta a exigir autenticação.

## 26.3 Épico C — Viagens

### US-C01 — Criar viagem

Como usuário, quero registrar destino e datas.

Critérios:

- validação de datas;
- geração dos TripDays;
- fuso definido;
- viagem pertence ao usuário.

### US-C02 — Listar viagens

Como usuário, quero visualizar meus planejamentos.

Critérios:

- apenas viagens próprias;
- ordenação coerente;
- estado vazio;
- status do roteiro.

### US-C03 — Configurar viagem

Como usuário, quero definir base, horários e ritmo.

Critérios:

- padrões aplicados aos dias;
- dias personalizáveis;
- alteração relevante marca roteiro como desatualizado.

### US-C04 — Excluir viagem

Como usuário, quero remover uma viagem.

Critérios:

- confirmação;
- propriedade validada;
- dependências tratadas;
- viagem deixa de aparecer.

## 26.4 Épico D — Atrações

### US-D01 — Pesquisar lugar

Como usuário, quero encontrar atrações por nome.

Critérios:

- consulta passa pelo backend;
- resultados exibem contexto suficiente;
- carregamento e erro;
- limites do provedor tratados.

### US-D02 — Adicionar atração

Como usuário, quero incluir um lugar na viagem.

Critérios:

- duplicidade detectada;
- duração e prioridade padrão;
- coordenadas salvas;
- roteiro marcado como desatualizado.

### US-D03 — Lugar manual

Como usuário, quero adicionar um lugar ausente.

Critérios:

- nome e coordenadas obrigatórios;
- seleção no mapa;
- origem marcada como manual.

### US-D04 — Configurar visita

Como usuário, quero definir duração, prioridade e disponibilidade.

Critérios:

- limites validados;
- dados persistidos;
- alterações refletem no próximo cálculo.

### US-D05 — Reserva fixa

Como usuário, quero registrar ingresso com horário.

Critérios:

- data pertence à viagem;
- horário validado;
- algoritmo preserva o compromisso;
- conflito fica visível.

## 26.5 Épico E — Planejamento manual

### US-E01 — Criar agenda por dia

Como usuário, quero organizar atrações manualmente antes da automação.

Critérios:

- inserir em um dia;
- reordenar;
- horários recalculados;
- conflitos básicos detectados.

### US-E02 — Visualizar no mapa

Como usuário, quero ver a ordem geográfica.

Critérios:

- marcadores numerados;
- lista e mapa sincronizados;
- estado sem rota;
- atribuições exigidas visíveis.

## 26.6 Épico F — Geração inteligente

### US-F01 — Matriz de deslocamento

Como sistema, quero estimar o custo entre pontos.

Critérios:

- contrato interno independente do fornecedor;
- timeout;
- cache;
- fallback;
- precisão registrada.

### US-F02 — Gerar roteiro v1

Como usuário, quero distribuir atrações automaticamente.

Critérios:

- respeita datas e horas;
- favorece prioridades;
- considera deslocamento;
- retorna não planejadas;
- salva somente após sucesso.

### US-F03 — Explicar resultado

Como usuário, quero entender decisões importantes.

Critérios:

- motivos por códigos;
- texto compreensível;
- conflitos com ações sugeridas;
- sem afirmar precisão inexistente.

### US-F04 — Bloquear item

Como usuário, quero preservar uma escolha.

Critérios:

- estado visível;
- recálculo não move;
- conflito continua explicado.

### US-F05 — Recalcular dia

Como usuário, quero ajustar uma parte sem destruir todo o roteiro.

Critérios:

- apenas o dia solicitado muda;
- bloqueios preservados;
- métricas atualizadas;
- falha mantém versão anterior.

## 26.7 Épico G — Qualidade

### US-G01 — Erros padronizados

Como desenvolvedor, quero erros consistentes.

Critérios:

- correlationId;
- código estável;
- detalhe seguro;
- campos inválidos identificados.

### US-G02 — Jornada ponta a ponta

Como desenvolvedor, quero detectar regressões.

Critérios:

- fluxo principal automatizado;
- executa no pipeline;
- dados independentes;
- resultado repetível.

### US-G03 — Acessibilidade

Como usuário de tecnologia assistiva, quero planejar sem depender do mouse ou somente das cores.

Critérios:

- teclado;
- foco;
- rótulos;
- alertas textuais;
- mapa representado por lista.

---
