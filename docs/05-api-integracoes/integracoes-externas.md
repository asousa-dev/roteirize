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

## 19.3 Estratégia inicial

- Selecionar um provedor para desenvolvimento.
- Encapsular o acesso no backend.
- Definir timeout.
- Aplicar cache.
- Registrar origem e horário da informação.
- Oferecer cadastro manual.
- Não depender de horários externos para o funcionamento básico.

## 19.4 Limites e custos

Antes de qualquer deploy:

- revisar termos de uso;
- revisar limites de requisição;
- verificar exigências de atribuição;
- estimar custo por usuário;
- impedir abuso;
- evitar consultas repetidas;
- nunca utilizar infraestrutura pública comunitária como se fosse serviço ilimitado.

## 19.5 Fallback geográfico

Quando uma rota não estiver disponível, o sistema pode estimar a distância pela fórmula de Haversine e aplicar um fator configurável para aproximar o trajeto real.

Essa estimativa:

- deve ser marcada como aproximada;
- não deve gerar instruções de navegação;
- não deve ser apresentada como tempo preciso;
- não substitui uma rota real em decisões críticas.

## 19.6 Cache

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
