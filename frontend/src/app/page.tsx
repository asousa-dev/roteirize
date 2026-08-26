import styles from "./page.module.css";
import TripManager from "./components/TripManager";

type HealthResponse = {
  status: string;
  message: string;
};

const apiUrl =
  process.env.API_URL ?? "http://localhost:8080";

async function getApiHealth():
  Promise<HealthResponse | null> {
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

    const data: HealthResponse =
      await response.json();

    return data;
  } catch {
    return null;
  }
}

export default async function Home() {
  const health = await getApiHealth();
  const isApiOnline = health?.status === "UP";

  return (
    <div className={styles.page}>
      <main className={styles.main}>
        <header className={styles.header}>
          <div className={styles.brand}>
            Roteirize
          </div>

          <nav
            className={styles.navigation}
            aria-label="Navegação principal"
          >
            <a
              className={`${styles.navItem} ${styles.navItemActive}`}
              href="#viagens"
            >
              Minhas viagens
            </a>

            <span
              className={`${styles.navItem} ${styles.navItemDisabled}`}
              aria-disabled="true"
            >
              Roteiros
            </span>

            <span
              className={`${styles.navItem} ${styles.navItemDisabled}`}
              aria-disabled="true"
            >
              Explorar
            </span>
          </nav>

          <div
            className={`${styles.apiStatus} ${
              isApiOnline
                ? styles.online
                : styles.offline
            }`}
            title={
              health?.message ??
              "Não foi possível conectar ao backend."
            }
          >
            <span className={styles.statusDot} />
            API {isApiOnline ? "online" : "offline"}
          </div>
        </header>

        <section className={styles.hero}>
          <div className={styles.heroContent}>
            <span className={styles.eyebrow}>
              Planeje sem complicação
            </span>

            <h1>Para onde vamos agora?</h1>

            <p className={styles.heroDescription}>
              Crie suas viagens, organize as datas e
              transforme cada destino em um roteiro
              pensado para você.
            </p>
          </div>

          <div
            className={styles.heroVisual}
            aria-hidden="true"
          >
            <span className={styles.visualLabel}>
              Roteiro inteligente
            </span>

            <div className={styles.route}>
              <span className={styles.routePoint} />
              <span className={styles.routeLine} />
              <span className={styles.plane}>✈</span>
            </div>

            <strong>Seu próximo destino</strong>
            <p>começa com um bom planejamento.</p>
          </div>
        </section>

        <section
          id="viagens"
          className={styles.workspace}
          aria-label="Gerenciamento de viagens"
        >
          <TripManager />
        </section>
      </main>
    </div>
  );
}