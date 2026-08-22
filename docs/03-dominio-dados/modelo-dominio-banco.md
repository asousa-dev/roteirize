# Modelo de domínio e banco de dados

[← Voltar ao índice da documentação](../README.md)

## 16.1 Entidades principais

~~~mermaid
erDiagram
    USER ||--o{ TRIP : owns
    TRIP ||--|{ TRIP_DAY : contains
    TRIP ||--o{ TRIP_PLACE : selects
    PLACE ||--o{ TRIP_PLACE : references
    TRIP_PLACE ||--o{ AVAILABILITY_WINDOW : has
    TRIP_DAY ||--o{ ITINERARY_ITEM : schedules
    TRIP_PLACE ||--o| ITINERARY_ITEM : becomes
    ITINERARY_ITEM ||--o{ ROUTE_LEG : starts
    TRIP ||--o{ ROUTE_GENERATION : records
~~~

## 16.2 User

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| name | texto | Obrigatório |
| email | texto normalizado | Único |
| password_hash | texto | Nunca retornar |
| role | enum | USER ou ADMIN |
| status | enum | ACTIVE, BLOCKED ou DELETED |
| created_at | data e hora | UTC |
| updated_at | data e hora | UTC |

## 16.3 Trip

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| owner_id | UUID | Referência ao usuário |
| title | texto | Obrigatório |
| city | texto | Obrigatório |
| country_code | texto | Código do país |
| timezone | texto | Fuso IANA |
| start_date | data | Obrigatório |
| end_date | data | Obrigatório |
| base_place_id | UUID opcional | Hospedagem ou ponto-base |
| travel_mode | enum | WALKING, DRIVING ou outro suportado |
| pace | enum | RELAXED, BALANCED, INTENSE |
| default_start_time | hora local | Obrigatório |
| default_end_time | hora local | Obrigatório |
| default_break_minutes | inteiro | Não negativo |
| itinerary_status | enum | DRAFT, READY, GENERATED, STALE, CONFLICTED |
| created_at | data e hora | UTC |
| updated_at | data e hora | UTC |
| version | inteiro | Concorrência otimista |

## 16.4 TripDay

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| trip_id | UUID | Referência à viagem |
| date | data | Única dentro da viagem |
| start_time | hora local | Pode sobrescrever padrão |
| end_time | hora local | Pode sobrescrever padrão |
| is_enabled | booleano | Permite dia sem passeios |
| notes | texto opcional | Observações |

## 16.5 Place

Representa um local geográfico reutilizável.

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| provider | texto | Origem ou MANUAL |
| provider_place_id | texto opcional | Identificador externo |
| name | texto | Nome original |
| formatted_address | texto opcional | Endereço |
| latitude | decimal | Obrigatório |
| longitude | decimal | Obrigatório |
| category | texto opcional | Categoria externa |
| source_updated_at | data e hora opcional | Atualização da origem |
| created_at | data e hora | UTC |

Índice único recomendado quando houver provedor: provider + provider_place_id.

## 16.6 TripPlace

Representa o interesse do usuário em visitar um lugar naquela viagem.

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| trip_id | UUID | Referência à viagem |
| place_id | UUID | Referência ao local |
| custom_name | texto opcional | Nome no roteiro |
| category | texto opcional | Categoria para a viagem |
| visit_duration_minutes | inteiro | 15 a 480 |
| priority | inteiro | 1 a 5 |
| notes | texto opcional | Privado |
| fixed_date | data opcional | Para reserva |
| fixed_start_time | hora opcional | Para reserva |
| preferred_day | data opcional | Preferência flexível |
| preferred_start_time | hora opcional | Preferência flexível |
| source_confidence | enum | CONFIRMED, EXTERNAL, ESTIMATED, UNKNOWN |
| created_at | data e hora | UTC |
| updated_at | data e hora | UTC |

Restrição única recomendada: trip_id + place_id, permitindo exceção apenas mediante decisão explícita.

## 16.7 AvailabilityWindow

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| trip_place_id | UUID | Referência à atração da viagem |
| day_of_week | inteiro opcional | 1 a 7 |
| specific_date | data opcional | Exceção por data |
| opens_at | hora local | Início |
| closes_at | hora local | Fim |
| is_closed | booleano | Fechado na data ou dia |
| source | enum | MANUAL ou EXTERNAL |

## 16.8 ItineraryItem

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| trip_day_id | UUID | Dia planejado |
| trip_place_id | UUID | Atração |
| position | inteiro | Ordem no dia |
| planned_start_at | data e hora | No fuso da viagem |
| planned_end_at | data e hora | No fuso da viagem |
| is_locked | booleano | Preservar no recálculo |
| is_fixed | booleano | Derivado de reserva |
| source | enum | GENERATED ou MANUAL |
| warning_count | inteiro | Campo derivado opcional |
| version | inteiro | Concorrência otimista |

## 16.9 RouteLeg

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| trip_day_id | UUID | Dia |
| from_item_id | UUID opcional | Origem; nulo pode representar base |
| to_item_id | UUID opcional | Destino; nulo pode representar base |
| distance_meters | inteiro | Distância |
| duration_seconds | inteiro | Tempo |
| travel_mode | enum | Modo utilizado |
| geometry | dado geográfico opcional | Linha para mapa |
| provider | texto | Origem do cálculo |
| estimated | booleano | Indica fallback |

## 16.10 RouteGeneration

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| trip_id | UUID | Viagem |
| status | enum | RUNNING, SUCCEEDED, FAILED, CANCELLED |
| algorithm_version | texto | Obrigatório |
| input_version | texto | Identifica entradas |
| parameters_json | JSON | Parâmetros usados |
| metrics_json | JSON | Métricas do resultado |
| warnings_json | JSON | Resumo dos avisos |
| started_at | data e hora | UTC |
| finished_at | data e hora opcional | UTC |
| error_code | texto opcional | Sem segredo ou stack trace |

## 16.11 Session ou RefreshToken

| Campo | Tipo conceitual | Regra |
|---|---|---|
| id | UUID | Chave primária |
| user_id | UUID | Usuário |
| token_hash | texto | Nunca salvar token puro |
| expires_at | data e hora | Expiração |
| revoked_at | data e hora opcional | Revogação |
| created_at | data e hora | Criação |
| user_agent | texto opcional | Auditoria |

## 16.12 Convenções de dados

- IDs públicos em UUID.
- Instantes do sistema armazenados em UTC.
- Datas e horas de passeios interpretadas no fuso da viagem.
- Coordenadas com precisão suficiente para roteamento.
- Exclusões em cascata somente quando intencionais.
- Campos de auditoria em entidades principais.
- Migrações nunca devem ser alteradas depois de aplicadas em ambientes compartilhados.

---
