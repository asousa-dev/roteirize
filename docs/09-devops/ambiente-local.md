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

| Variável | Finalidade |
|---|---|
| `GEOAPIFY_API_KEY` | Autenticar as consultas de cidades na Geoapify |

A chave deve ser definida no mesmo terminal usado para iniciar o backend.

No PowerShell:

~~~powershell
$roteirizeGeoapifyKey = Read-Host "Chave da Geoapify" -AsSecureString

$env:GEOAPIFY_API_KEY = [System.Net.NetworkCredential]::new(
  "",
  $roteirizeGeoapifyKey
).Password

Remove-Variable roteirizeGeoapifyKey
~~~

Depois, no mesmo terminal:

~~~powershell
cd backend
.\mvnw.cmd spring-boot:run
~~~

O arquivo `application.properties` referencia a variável sem armazenar seu valor:

~~~properties
integrations.geoapify.base-url=https://api.geoapify.com/v1/geocode
integrations.geoapify.api-key=${GEOAPIFY_API_KEY:}
~~~

A chave real não deve ser escrita no `application.properties` nem em qualquer arquivo versionado.

### Frontend

O frontend utiliza a seguinte variável no arquivo `frontend/.env.local`:

~~~properties
API_URL=http://localhost:8080
~~~

`API_URL` é utilizada somente pelo servidor Next.js para acessar o backend. Como ela não possui o prefixo `NEXT_PUBLIC_`, seu valor não é exposto diretamente ao navegador.

O arquivo versionado `frontend/.env.example` apresenta apenas o formato esperado:

~~~properties
API_URL=http://localhost:8080
~~~

## 24.4 Arquivo de exemplo

O repositório deve conter .env.example com nomes e explicações, nunca valores reais.

## 24.5 Dados iniciais

O ambiente de desenvolvimento deve permitir:

- criar usuário de demonstração;
- criar viagem de exemplo;
- adicionar atrações fictícias ou estáveis;
- executar o algoritmo sem depender da internet em testes.

---
