# Observabilidade e tratamento de erros

[← Voltar ao índice da documentação](../README.md)

## 23.1 Logs

Registrar:

- início e conclusão de geração;
- duração;
- quantidade de atrações e dias;
- código de erro;
- falhas externas;
- cache hit ou miss em nível apropriado;
- correlationId.

Não registrar:

- senha;
- token;
- cookie;
- corpo completo de autenticação;
- endereço privado sem necessidade;
- stack trace para o cliente.

## 23.2 Métricas futuras

- duração das requisições;
- duração da geração;
- taxa de falhas;
- chamadas por provedor;
- taxa de cache;
- quantidade de conflitos;
- quantidade planejada;
- uso por funcionalidade.

## 23.3 Saúde

Endpoints internos de saúde podem verificar:

- aplicação;
- banco;
- cache quando existir.

Falha em um fornecedor externo não precisa derrubar toda a aplicação, mas deve alterar o estado de disponibilidade da função dependente.

## 23.4 Códigos de erro

Manter catálogo versionado:

- AUTH_INVALID_CREDENTIALS;
- EMAIL_ALREADY_USED;
- TRIP_NOT_FOUND;
- TRIP_VERSION_CONFLICT;
- INVALID_TRIP_DATES;
- PLACE_ALREADY_ADDED;
- INVALID_AVAILABILITY;
- GENERATION_ALREADY_RUNNING;
- ITINERARY_INFEASIBLE;
- ROUTING_PROVIDER_UNAVAILABLE;
- EXTERNAL_RATE_LIMITED.

---
