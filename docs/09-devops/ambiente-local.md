# Ambiente local

[← Voltar ao índice da documentação](../README.md)

## 24.1 Pré-requisitos conceituais

- Git;
- Node.js compatível com o frontend;
- Java compatível com o backend;
- Docker e Docker Compose para o banco;
- editor de código;
- cliente HTTP opcional.

As versões exatas serão fixadas no início da implementação e registradas no README.

## 24.2 Serviços locais

| Serviço | Porta sugerida |
|---|---:|
| Frontend | 3000 |
| Backend | 8080 |
| PostgreSQL | 5432 |
| Redis futuro | 6379 |

## 24.3 Variáveis de ambiente

### Backend

~~~text
DATABASE_URL=
DATABASE_USERNAME=
DATABASE_PASSWORD=
APP_FRONTEND_ORIGIN=
AUTH_ACCESS_TOKEN_SECRET=
AUTH_REFRESH_TOKEN_SECRET=
PLACE_PROVIDER=
PLACE_PROVIDER_API_KEY=
ROUTING_PROVIDER=
ROUTING_PROVIDER_API_KEY=
~~~

### Frontend

~~~text
NEXT_PUBLIC_API_BASE_URL=
NEXT_PUBLIC_MAP_STYLE_URL=
~~~

Somente variáveis realmente públicas podem usar prefixo público.

## 24.4 Arquivo de exemplo

O repositório deve conter .env.example com nomes e explicações, nunca valores reais.

## 24.5 Dados iniciais

O ambiente de desenvolvimento deve permitir:

- criar usuário de demonstração;
- criar viagem de exemplo;
- adicionar atrações fictícias ou estáveis;
- executar o algoritmo sem depender da internet em testes.

---
