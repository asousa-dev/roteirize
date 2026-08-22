# Requisitos não funcionais

[← Voltar ao índice da documentação](../README.md)

## 11.1 Usabilidade

- A criação de uma viagem deve ser dividida em etapas curtas.
- O sistema deve permitir salvar e continuar depois.
- Alertas devem explicar o problema e oferecer uma ação possível.
- O usuário não deve perder alterações por navegar entre as telas.
- A interface deve diferenciar dados confirmados, estimados e ausentes.

## 11.2 Desempenho

Metas iniciais, a serem confirmadas por testes:

- Respostas internas simples: até 500 ms no percentil 95 em ambiente controlado.
- Carregamento inicial de uma tela principal: até 2 segundos em condição normal.
- Geração de até 40 atrações em 7 dias: preferencialmente até 5 segundos.
- Toda geração acima de 2 segundos deve exibir progresso.
- Consultas externas devem possuir timeout e cache.

As metas não incluem latência imprevisível de fornecedores externos.

## 11.3 Confiabilidade

- Alterações persistidas não podem desaparecer após recarregar a página.
- Uma falha externa não deve corromper o roteiro salvo.
- Uma geração interrompida não deve substituir o último roteiro válido.
- Operações críticas devem ser transacionais.

## 11.4 Segurança

- Senhas nunca devem ser armazenadas em texto puro.
- Tokens e segredos não devem ser registrados em logs.
- Toda operação privada deve validar autenticação e propriedade do recurso.
- Entradas devem ser validadas no frontend e novamente no backend.
- Dependências devem ser atualizadas e auditadas regularmente.

## 11.5 Acessibilidade

- Navegação completa por teclado.
- Foco visível.
- Contraste adequado.
- Rótulos associados aos campos.
- Textos alternativos quando necessários.
- Estado do mapa também representado em lista.
- Cores não podem ser o único meio de comunicar conflitos.
- Meta de conformidade: nível AA das boas práticas de acessibilidade aplicáveis.

## 11.6 Compatibilidade

- Últimas versões estáveis dos principais navegadores.
- Layout utilizável a partir de 360 px de largura.
- Desktop como experiência completa.
- Celular com navegação e edição simplificadas.

## 11.7 Manutenibilidade

- Código organizado por funcionalidades.
- Regras do algoritmo isoladas de controllers e componentes visuais.
- Contratos externos protegidos por adaptadores.
- Migrações versionadas.
- Testes para regras críticas.
- Documentação atualizada junto com mudanças relevantes.

## 11.8 Privacidade

- Coletar apenas dados necessários.
- Tornar viagens privadas por padrão.
- Preparar mecanismos de exclusão e exportação de dados antes de um deploy público.
- Não utilizar localização em segundo plano no MVP.

---
