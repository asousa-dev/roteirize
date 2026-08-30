const apiUrl =
  process.env.API_URL ?? "http://localhost:8080";

export async function GET(request: Request) {
  const requestUrl = new URL(request.url);
  const query =
    requestUrl.searchParams.get("query")?.trim() ?? "";

  if (query.length < 3 || query.length > 100) {
    return Response.json(
      {
        status: 400,
        message: "Busca inválida.",
        errors: [
          "Informe entre 3 e 100 caracteres.",
        ],
      },
      {
        status: 400,
      }
    );
  }

  try {
    const backendUrl = new URL(
      "/api/v1/locations/cities",
      apiUrl
    );

    backendUrl.searchParams.set("query", query);

    const response = await fetch(backendUrl, {
      cache: "no-store",
    });

    const data = await response.json();

    return Response.json(data, {
      status: response.status,
    });
  } catch {
    return Response.json(
      {
        status: 503,
        message: "Backend indisponível.",
        errors: [
          "Não foi possível conectar à API do Roteirize.",
        ],
      },
      {
        status: 503,
      }
    );
  }
}