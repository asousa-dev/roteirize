const apiUrl =
  process.env.API_URL ?? "http://localhost:8080";

export async function GET() {
  try {
    const response = await fetch(
      `${apiUrl}/api/v1/trips`,
      {
        cache: "no-store",
      }
    );

    const data = await response.json();

    return Response.json(data, {
      status: response.status,
    });
  } catch {
    return Response.json(
      {
        status: 503,
        message: "Backend indisponível",
        errors: [
          "Não foi possível conectar à API do Roteirize",
        ],
      },
      {
        status: 503,
      }
    );
  }
}

export async function POST(request: Request) {
  try {
    const body = await request.json();

    const response = await fetch(
      `${apiUrl}/api/v1/trips`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(body),
      }
    );

    const data = await response.json();

    return Response.json(data, {
      status: response.status,
    });
  } catch {
    return Response.json(
      {
        status: 503,
        message: "Backend indisponível",
        errors: [
          "Não foi possível conectar à API do Roteirize",
        ],
      },
      {
        status: 503,
      }
    );
  }
}