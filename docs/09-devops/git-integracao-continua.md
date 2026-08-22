# Git e integração contínua

[← Voltar ao índice da documentação](../README.md)

## 25.1 Branches

Para um projeto individual:

- main sempre funcional;
- branches curtas por funcionalidade;
- exemplos: feat/trip-creation, fix/itinerary-overlap.

Não é necessário manter uma branch develop permanente.

## 25.2 Commits

Formato sugerido:

~~~text
feat: add trip creation endpoint
fix: preserve locked itinerary items
test: cover fixed reservation conflicts
docs: update route generation rules
refactor: isolate routing provider adapter
~~~

Cada commit deve representar uma mudança compreensível.

## 25.3 Pull requests

Mesmo trabalhando sozinho, pull requests podem registrar:

- objetivo;
- mudanças;
- como testar;
- imagens quando houver interface;
- riscos;
- issue relacionada.

## 25.4 Pipeline

Em cada pull request:

1. instalar dependências;
2. validar formatação;
3. executar lint;
4. compilar frontend;
5. executar testes frontend;
6. compilar backend;
7. executar testes backend;
8. validar migrações;
9. gerar ou validar contrato OpenAPI.

## 25.5 Proteções

Antes de publicar:

- impedir merge com pipeline quebrado;
- proteger main;
- ativar análise de dependências;
- revisar exposição acidental de segredos.

---
