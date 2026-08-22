# Estrutura do repositório

[← Voltar ao índice da documentação](../README.md)

## 21.1 Monorepositório

~~~text
roteirize/
├── frontend/
├── backend/
├── docs/
│   ├── product/
│   ├── architecture/
│   ├── api/
│   └── adr/
├── .github/
│   ├── workflows/
│   └── ISSUE_TEMPLATE/
├── compose.yaml
├── .env.example
├── CONTRIBUTING.md
├── LICENSE
└── README.md
~~~

## 21.2 Frontend por funcionalidade

~~~text
frontend/src/
├── app/
├── features/
│   ├── auth/
│   ├── trips/
│   ├── places/
│   └── itinerary/
├── components/
│   └── ui/
├── services/
├── hooks/
├── types/
├── utils/
└── styles/
~~~

## 21.3 Backend por funcionalidade

~~~text
backend/src/main/java/.../roteirize/
├── auth/
├── user/
├── trip/
├── place/
├── itinerary/
├── routing/
├── admin/
├── shared/
└── config/
~~~

Dentro de cada módulo, separar API, aplicação, domínio e infraestrutura somente quando isso ajudar a leitura. Evitar uma arquitetura tão abstrata que dificulte o aprendizado.

## 21.4 Documentos recomendados no GitHub

- README.md: apresentação e início rápido.
- CONTRIBUTING.md: fluxo de contribuição.
- docs/product/requirements.md: requisitos.
- docs/architecture/overview.md: arquitetura.
- docs/architecture/data-model.md: banco.
- docs/api/api.md: convenções.
- docs/adr/: decisões.

Esta documentação-mestre pode ser dividida nesses arquivos quando o repositório for criado.

---
