import styles from "./page.module.css";
import TripManager from "./components/TripManager";

type HealthResponse = {
  status: string;
  message: string;
};

const apiUrl = 
  process.env.API_URL ?? "http://localhost:8080";

async function getApiHealth(): Promise<HealthResponse | null> {
  try {
    const response = await fetch(
      `${apiUrl}/api/v1/health`,
      {
        cache: "no-store",
      }
    );

    if (!response.ok) {
      return null;
    }

    const data: HealthResponse = await response.json();

    return data;
  } catch {
    return null;
  }
}

export default async function Home() {
  const health = await getApiHealth();

  return (
    <div className={styles.page}>
      <main className={styles.main}>
        <section className={styles.intro}>
          <h1>Roteirize</h1>

          <p>
            Construtor inteligente de roteiros de viagem.
          </p>

          <p>
            Status da API:{" "}
            <strong>{health?.status ?? "OFFLINE"}</strong>
          </p>

          <p>
            {health?.message ??
              "Não foi possível conectar ao backend."}
          </p>
        </section>
        <TripManager />
      </main>
    </div>
  );
}