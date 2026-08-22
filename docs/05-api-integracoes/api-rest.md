# Contrato inicial da API

[← Voltar ao índice da documentação](../README.md)

Esta seção define recursos e intenções. Os esquemas definitivos devem ser publicados por OpenAPI junto ao backend.

## 18.1 Convenções

- Prefixo: /api/v1
- JSON em requisições e respostas.
- Paginação com page, size e sort onde necessário.
- Datas no formato ISO.
- Respostas de erro no formato Problem Details ou estrutura equivalente consistente.
- IDs tratados como valores opacos.

## 18.2 Autenticação

| Método | Rota | Finalidade |
|---|---|---|
| POST | /auth/register | Criar conta |
| POST | /auth/login | Iniciar sessão |
| POST | /auth/refresh | Renovar sessão |
| POST | /auth/logout | Encerrar sessão |
| GET | /auth/me | Obter usuário autenticado |

### Exemplo de cadastro

~~~json
{
  "name": "Lucas Oliveira",
  "email": "lucas@example.com",
  "password": "senha-informada-pelo-usuario"
}
~~~

### Exemplo de resposta pública

~~~json
{
  "id": "uuid",
  "name": "Lucas Oliveira",
  "email": "lucas@example.com",
  "role": "USER"
}
~~~

## 18.3 Viagens

| Método | Rota | Finalidade |
|---|---|---|
| GET | /trips | Listar viagens próprias |
| POST | /trips | Criar viagem |
| GET | /trips/{tripId} | Detalhar viagem |
| PATCH | /trips/{tripId} | Alterar viagem |
| DELETE | /trips/{tripId} | Excluir viagem |
| GET | /trips/{tripId}/summary | Obter resumo |

### Exemplo de criação

~~~json
{
  "title": "Roma em sete dias",
  "city": "Roma",
  "countryCode": "IT",
  "timezone": "Europe/Rome",
  "startDate": "2027-05-10",
  "endDate": "2027-05-16",
  "pace": "BALANCED",
  "travelMode": "WALKING",
  "defaultStartTime": "09:00",
  "defaultEndTime": "19:00",
  "defaultBreakMinutes": 60
}
~~~

## 18.4 Dias

| Método | Rota | Finalidade |
|---|---|---|
| GET | /trips/{tripId}/days | Listar dias |
| PATCH | /trips/{tripId}/days/{date} | Personalizar dia |

## 18.5 Pesquisa de lugares

| Método | Rota | Finalidade |
|---|---|---|
| GET | /places/search | Pesquisar no provedor |
| POST | /places/manual | Criar local manual |
| GET | /places/{placeId} | Detalhar local |

Parâmetros possíveis de pesquisa:

- query;
- city;
- latitude;
- longitude;
- language;
- limit.

O frontend não deve chamar diretamente provedores que exijam chave secreta.

## 18.6 Atrações da viagem

| Método | Rota | Finalidade |
|---|---|---|
| GET | /trips/{tripId}/places | Listar atrações |
| POST | /trips/{tripId}/places | Adicionar atração |
| GET | /trips/{tripId}/places/{tripPlaceId} | Detalhar |
| PATCH | /trips/{tripId}/places/{tripPlaceId} | Alterar configuração |
| DELETE | /trips/{tripId}/places/{tripPlaceId} | Remover da viagem |
| PUT | /trips/{tripId}/places/{tripPlaceId}/availability | Substituir disponibilidade |

### Exemplo de configuração

~~~json
{
  "placeId": "uuid",
  "visitDurationMinutes": 120,
  "priority": 5,
  "notes": "Comprar ingresso antecipadamente",
  "fixedDate": null,
  "fixedStartTime": null
}
~~~

## 18.7 Roteiro

| Método | Rota | Finalidade |
|---|---|---|
| GET | /trips/{tripId}/itinerary | Obter roteiro atual |
| POST | /trips/{tripId}/itinerary/generations | Solicitar geração |
| GET | /trips/{tripId}/itinerary/generations/{generationId} | Consultar geração |
| POST | /trips/{tripId}/itinerary/recalculate-day | Recalcular um dia |
| PATCH | /trips/{tripId}/itinerary/items/{itemId} | Mover ou alterar item |
| POST | /trips/{tripId}/itinerary/items/{itemId}/lock | Bloquear |
| DELETE | /trips/{tripId}/itinerary/items/{itemId}/lock | Desbloquear |
| DELETE | /trips/{tripId}/itinerary/items/{itemId} | Retirar da agenda |
| GET | /trips/{tripId}/itinerary/conflicts | Listar conflitos |

### Solicitação de geração

~~~json
{
  "preserveLockedItems": true,
  "returnToBaseAtEndOfDay": true,
  "algorithmProfile": "BALANCED"
}
~~~

### Resumo de geração

~~~json
{
  "generationId": "uuid",
  "status": "SUCCEEDED",
  "plannedPlaces": 18,
  "unplannedPlaces": 2,
  "conflicts": 1,
  "totalTravelMinutes": 305,
  "algorithmVersion": "planner-v1"
}
~~~

## 18.8 Estrutura de erro

~~~json
{
  "type": "https://roteirize.app/problems/resource-conflict",
  "title": "O roteiro foi alterado",
  "status": 409,
  "detail": "A viagem foi modificada em outra operação. Recarregue os dados e tente novamente.",
  "instance": "/api/v1/trips/uuid",
  "code": "TRIP_VERSION_CONFLICT",
  "correlationId": "uuid",
  "fieldErrors": []
}
~~~

## 18.9 Status HTTP esperados

- 200: consulta ou alteração concluída;
- 201: recurso criado;
- 204: operação concluída sem corpo;
- 400: requisição inválida;
- 401: não autenticado;
- 403: autenticado sem permissão;
- 404: recurso inexistente ou não visível;
- 409: conflito de estado ou versão;
- 422: regras de negócio impedem a operação;
- 429: limite de uso;
- 502 ou 503: integração indisponível;
- 500: falha interna não prevista.

---
