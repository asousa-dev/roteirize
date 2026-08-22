# Autenticação, segurança e privacidade

[← Voltar ao índice da documentação](../README.md)

## 20.1 Autenticação proposta

- Credenciais enviadas apenas por conexão segura em produção.
- Senha protegida por função de hash apropriada e salt.
- Sessão por cookies HttpOnly.
- Cookies Secure em produção.
- Política SameSite configurada conforme a hospedagem.
- Tokens de renovação armazenados no servidor apenas como hash.
- Rotação e revogação de renovação.
- Proteção contra CSRF quando a autenticação usar cookies.

Não armazenar tokens sensíveis em localStorage.

## 20.2 Autorização

Para toda rota com tripId:

1. identificar usuário;
2. carregar ou localizar a viagem;
3. verificar propriedade ou permissão;
4. somente então executar o caso de uso.

Não confiar que um UUID difícil de adivinhar protege o recurso.

## 20.3 Validação

- DTOs validam formato.
- Casos de uso validam regras.
- Banco aplica restrições de unicidade e integridade.
- Frontend repete validações apenas para melhorar a experiência.

## 20.4 Proteções

- limitação de tentativas de login;
- limitação de chamadas a pesquisa e geração;
- CORS restritivo;
- cabeçalhos de segurança;
- tamanho máximo de corpo;
- prevenção de injeção por consultas parametrizadas;
- escape de conteúdo exibido;
- não retornar stack traces;
- segredos apenas em variáveis de ambiente;
- arquivos de ambiente fora do Git.

## 20.5 Dados pessoais

Dados iniciais:

- nome;
- e-mail;
- senha protegida;
- viagens;
- locais de hospedagem informados;
- preferências e notas.

O endereço de hospedagem pode revelar informação sensível sobre a localização temporária do usuário. Deve permanecer privado por padrão e pode ser ocultado em compartilhamentos.

## 20.6 Preparação para LGPD

Antes de publicar:

- política de privacidade;
- base legal e finalidade;
- consentimentos quando aplicáveis;
- exportação de dados;
- exclusão de conta;
- prazo de retenção;
- canal de contato;
- registro de fornecedores;
- revisão de dados enviados a terceiros.

---
