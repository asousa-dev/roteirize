# Integrações externas

[← Voltar ao índice da documentação](../README.md)

## 19.1 Necessidades

O produto pode precisar de:

- geocodificação;
- pesquisa de lugares;
- dados básicos de atrações;
- cálculo de rotas;
- matriz de tempos;
- geometria para desenhar o trajeto;
- horários de funcionamento, quando disponíveis.

## 19.2 Princípio de abstração

O domínio não deve conhecer nomes, formatos ou IDs específicos do fornecedor.

Interfaces conceituais:

- PlaceSearchGateway;
- GeocodingGateway;
- DirectionsGateway;
- TravelTimeMatrixGateway.

Cada implementação converte a resposta externa para modelos internos.

## 19.3 Estratégia de integração

- Todo provedor externo deve ser acessado pelo backend.
- O acesso deve ocorrer por interfaces e adaptadores.
- Modelos externos não devem fazer parte do domínio.
- Chaves de API devem permanecer em variáveis de ambiente.
- As integrações devem possuir limites de tempo e tratamento de falhas.
- Informações essenciais também poderão ser cadastradas manualmente.
- A substituição de um provedor não deve alterar o contrato público da API.

## 19.4 Autocomplete de cidades

O Roteirize utiliza a Geoapify como provedor de autocomplete e geocodificação de cidades.

A integração consulta a Address Autocomplete API com os seguintes parâmetros:

| Parâmetro | Finalidade |
|---|---|
| `text` | Texto digitado pelo usuário |
| `type=city` | Restringir os resultados a cidades |
| `format=json` | Solicitar resposta em JSON |
| `limit=6` | Limitar a quantidade de sugestões |
| `apiKey` | Autenticar a aplicação no provedor |

Documentação oficial: [Geoapify Address Autocomplete](https://apidocs.geoapify.com/docs/geocoding/address-autocomplete/)

## 19.5 Mapeamento de dados

Os dados retornados pelo provedor são convertidos para o contrato interno do Roteirize.

| Geoapify | Roteirize |
|---|---|
| `place_id` | `providerId` |
| `formatted` | `displayName` |
| `city` | `city` |
| `state` | `state` |
| `country` | `country` |
| `country_code` | `countryCode` |
| `lat` | `latitude` |
| `lon` | `longitude` |

O identificador do provedor é armazenado como um valor opaco. O restante da aplicação não deve interpretar sua estrutura.

## 19.6 Segurança

A chave da Geoapify é fornecida ao backend pela variável de ambiente:

~~~text
GEOAPIFY_API_KEY
~~~

A chave:

- não deve ser incluída no código-fonte;
- não deve ser enviada ao frontend;
- não deve utilizar o prefixo `NEXT_PUBLIC_`;
- não deve ser registrada em logs;
- não deve ser incluída em commits ou arquivos versionados.

O frontend consulta somente a API do Roteirize, que realiza a comunicação com o provedor.

## 19.7 Tratamento de falhas

Quando o provedor estiver indisponível, rejeitar a chave ou retornar uma resposta inválida, o backend deve:

- impedir que detalhes internos da integração sejam expostos;
- devolver uma resposta padronizada;
- utilizar o status HTTP `503 Service Unavailable`;
- permitir que a aplicação continue funcionando nas áreas que não dependem da integração.

## 19.8 Limites e custos

Antes de qualquer deploy:

- revisar termos de uso;
- revisar limites de requisição;
- verificar exigências de atribuição;
- estimar custo por usuário;
- impedir abuso;
- evitar consultas repetidas;
- nunca utilizar infraestrutura pública comunitária como se fosse serviço ilimitado.

## 19.9 Fallback geográfico

Quando uma rota não estiver disponível, o sistema pode estimar a distância pela fórmula de Haversine e aplicar um fator configurável para aproximar o trajeto real.

Essa estimativa:

- deve ser marcada como aproximada;
- não deve gerar instruções de navegação;
- não deve ser apresentada como tempo preciso;
- não substitui uma rota real em decisões críticas.

## 19.10 Cache

Chave conceitual:

- coordenada de origem arredondada;
- coordenada de destino arredondada;
- modo;
- versão do provedor.

O prazo de validade depende do tipo de dado:

- coordenadas de lugar: longo;
- rota estática: médio;
- tempo sujeito a trânsito: curto;
- horário de funcionamento: precisa de atualização e fonte.

---
