# Critérios de conclusão

[← Voltar ao índice da documentação](../README.md)

## 30.1 Definition of Ready

Uma tarefa está pronta para desenvolvimento quando:

- possui objetivo claro;
- possui critérios de aceitação;
- dependências são conhecidas;
- regra de negócio está documentada;
- dúvidas importantes foram resolvidas;
- tamanho permite implementação e teste isolados.

## 30.2 Definition of Done

Uma tarefa está concluída quando:

- código implementado;
- comportamento atende aos critérios;
- testes relevantes passam;
- lint e build passam;
- erros e carregamento foram tratados;
- autorização foi verificada;
- interface foi testada em tamanhos relevantes;
- documentação foi atualizada;
- não existem segredos ou dados sensíveis no commit;
- mudança foi revisada antes de entrar em main.

## 30.3 Critério de conclusão do MVP

O MVP estará concluído quando um usuário conseguir:

1. criar conta;
2. entrar;
3. criar uma viagem de até 14 dias;
4. configurar destino, base, horários e ritmo;
5. adicionar lugares externos e manuais;
6. definir prioridade, duração e reservas;
7. gerar um roteiro;
8. entender conflitos e itens não planejados;
9. visualizar agenda e mapa;
10. mover e bloquear uma atividade;
11. recalcular sem perder bloqueios;
12. sair, entrar novamente e encontrar os dados preservados.

Além disso:

- jornada principal coberta por teste ponta a ponta;
- regras críticas cobertas por testes;
- aplicação local iniciada por instruções do README;
- banco criado por migrações;
- pipeline do GitHub aprovado;
- documentação coerente com o comportamento.

---
