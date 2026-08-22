# Papéis e permissões

[← Voltar ao índice da documentação](../README.md)

## 8.1 Visitante

Pode:

- acessar a página inicial;
- entender o funcionamento;
- criar uma conta;
- fazer login;
- abrir um roteiro público quando o compartilhamento existir.

Não pode:

- criar ou alterar viagens;
- acessar dados privados;
- gerar roteiros.

## 8.2 Usuário autenticado

Pode:

- administrar o próprio perfil;
- criar, visualizar, editar e excluir suas viagens;
- adicionar atrações;
- gerar e modificar roteiros;
- visualizar alertas;
- bloquear itens;
- compartilhar roteiros em versões futuras.

Não pode:

- acessar viagens privadas de outros usuários;
- executar funções administrativas.

## 8.3 Administrador

Inicialmente utilizado apenas para manutenção.

Pode:

- visualizar contas e status;
- bloquear contas quando necessário;
- consultar registros operacionais;
- revisar conteúdos denunciados em versões futuras.

O administrador não deve visualizar senhas, tokens ou segredos.

## 8.4 Matriz de autorização do MVP

| Recurso | Visitante | Usuário proprietário | Outro usuário | Administrador |
|---|---:|---:|---:|---:|
| Criar viagem | Não | Sim | Não | Sim, para si |
| Ver viagem privada | Não | Sim | Não | Apenas suporte autorizado |
| Alterar viagem | Não | Sim | Não | Não por padrão |
| Excluir viagem | Não | Sim | Não | Apenas procedimento administrativo |
| Gerar roteiro | Não | Sim | Não | Não por padrão |
| Administrar usuários | Não | Não | Não | Sim |

---
