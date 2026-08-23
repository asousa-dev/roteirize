# Roteirize

> Construtor inteligente de roteiros de viagem que organiza atrações em uma agenda diária possível, eficiente e explicável.

## Status

O projeto está em fase de planejamento e documentação. A primeira versão será executada localmente e terá foco em viagens turísticas dentro de uma única cidade.

## Problema

Planejar uma viagem exige combinar atrações, horários de funcionamento, reservas, distâncias, tempo de visita e ritmo dos viajantes. Quando essas informações ficam separadas, o resultado costuma ter deslocamentos desnecessários, conflitos de horário e atividades que não cabem nos dias disponíveis.

## Solução proposta

O Roteirize permitirá que o usuário informe destino, datas, hospedagem, lugares de interesse e restrições. O sistema montará uma programação diária considerando:

- proximidade entre os lugares;
- tempo de deslocamento;
- duração de cada visita;
- dias e horários de funcionamento;
- reservas com horário fixo;
- prioridades;
- pausas e ritmo da viagem;
- atividades bloqueadas pelo usuário.

O resultado será apresentado em uma linha do tempo integrada a um mapa. Alterações manuais poderão ser preservadas durante o recálculo.

## Escopo inicial

- Cadastro e autenticação.
- Criação de viagens de uma a catorze dias.
- Uma cidade por viagem.
- Pesquisa e cadastro manual de atrações.
- Prioridade, duração, disponibilidade e reservas.
- Geração automática do roteiro.
- Mapa, linha do tempo, conflitos e justificativas.
- Edição manual, bloqueios e recálculo.
- Execução local.

## Tecnologias planejadas

- Frontend: Next.js e TypeScript.
- Backend: Java e Spring Boot.
- Banco de dados: PostgreSQL.
- Ambiente local: Docker Compose.
- Mapas e rotas: integração protegida por adaptadores.

As versões exatas serão definidas no início da implementação.

## Documentação

A documentação completa está organizada por domínio em [docs/README.md](docs/README.md).

Áreas principais:

- produto e negócio;
- requisitos;
- domínio e dados;
- arquitetura e algoritmo;
- API e integrações;
- frontend e experiência;
- segurança;
- testes;
- ambiente e integração contínua;
- backlog e roadmap.

## Estrutura prevista

~~~text
roteirize/
├── frontend/
├── backend/
├── docs/
├── .github/
├── compose.yaml
└── README.md
~~~

## Forma de desenvolvimento

O projeto será desenvolvido incrementalmente:

1. Fundação do repositório e ambiente.
2. Autenticação.
3. Cadastro de viagens.
4. Cadastro de atrações.
5. Planejamento manual.
6. Algoritmo inicial.
7. Recálculo e bloqueios.
8. Qualidade, acessibilidade e preparação para publicação.

## Licença

MIT License.
