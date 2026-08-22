# Arquitetura técnica

[← Voltar ao índice da documentação](../README.md)

## 17.1 Stack de referência

### Frontend

- Next.js;
- React;
- TypeScript;
- CSS Modules inicialmente;
- biblioteca de validação de formulários;
- biblioteca de requisições e cache de servidor quando necessária;
- biblioteca de mapas compatível com o provedor escolhido.

### Backend

- Java;
- Spring Boot;
- Spring Web;
- Spring Security;
- validação de entrada;
- acesso a dados com JPA;
- migrações de banco;
- documentação OpenAPI;
- testes unitários e de integração.

### Dados e infraestrutura

- PostgreSQL;
- Docker Compose para dependências locais;
- Redis somente quando cache distribuído ou filas forem necessários;
- armazenamento de arquivos apenas em versões que realmente precisem.

## 17.2 Estilo arquitetural

Começar como um **monólito modular**:

- uma aplicação frontend;
- uma aplicação backend;
- um banco principal;
- módulos internos bem separados.

Microserviços não fazem parte do MVP. Eles adicionariam complexidade operacional sem resolver um problema atual.

## 17.3 Visão geral

~~~mermaid
flowchart TD
    A["Navegador"] --> B["Next.js"]
    B --> C["API Spring Boot"]
    C --> D["PostgreSQL"]
    C --> E["Gateway de lugares"]
    C --> F["Gateway de rotas"]
    E --> G["Provedor externo"]
    F --> G
~~~

## 17.4 Camadas do backend

### API

- controllers;
- DTOs;
- autenticação;
- tradução de erros;
- documentação do contrato.

### Aplicação

- casos de uso;
- coordenação de transações;
- autorização sobre recursos;
- comandos e consultas.

### Domínio

- entidades;
- objetos de valor;
- regras;
- interfaces de repositório;
- algoritmo de planejamento;
- códigos de conflito.

### Infraestrutura

- persistência;
- clientes HTTP;
- cache;
- segurança técnica;
- configuração;
- observabilidade.

Regra de dependência: o domínio não deve depender de controllers, banco ou fornecedor de mapas.

## 17.5 Módulos do backend

- auth;
- users;
- trips;
- places;
- itinerary;
- routing;
- admin;
- shared.

## 17.6 Responsabilidades do frontend

- renderizar a experiência;
- validar para oferecer retorno rápido;
- administrar estado visual;
- chamar a API;
- representar carregamento e falhas;
- nunca decidir sozinho regras de autorização ou regras críticas.

## 17.7 Responsabilidades do backend

- autenticar e autorizar;
- validar dados;
- aplicar regras de negócio;
- gerar roteiros;
- persistir;
- integrar com fornecedores;
- garantir consistência;
- produzir erros padronizados.

## 17.8 Comunicação

- JSON sobre HTTPS em produção.
- API versionada sob /api/v1.
- Horários enviados em formato padronizado e acompanhados do contexto de fuso.
- Identificador de correlação em cada requisição.

## 17.9 Concorrência

- Usar campo de versão em viagens e itens importantes.
- Rejeitar atualização baseada em versão antiga com erro de conflito.
- Impedir gerações simultâneas para a mesma viagem.
- No MVP individual, não é necessário sincronismo em tempo real.

---
