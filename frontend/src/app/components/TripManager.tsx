"use client";

import { useEffect, useState } from "react";
import type { FormEvent } from "react";

type Trip = {
  id: string;
  destination: string;
  startDate: string;
  endDate: string;
};

type ApiError = {
  message?: string;
  errors?: string[];
};

async function getTrips(): Promise<Trip[]> {
  const response = await fetch("/api/trips", {
    cache: "no-store",
  });

  if (!response.ok) {
    throw new Error("Não foi possível carregar");
  }

  return (await response.json()) as Trip[];
}

function formatDate(date: string): string {
  const [year, month, day] = date.split("-");

  return `${day}/${month}/${year}`;
}

export default function TripManager() {
  const [trips, setTrips] = useState<Trip[]>([]);
  const [errors, setErrors] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [isSubmitting, setIsSubmitting] =
    useState(false);

  useEffect(() => {
    async function loadTrips() {
      try {
        const data = await getTrips();
        setTrips(data);
      } catch {
        setErrors([
          "Não foi possível carregar as viagens.",
        ]);
      } finally {
        setIsLoading(false);
      }
    }

    void loadTrips();
  }, []);

  async function handleSubmit(
    event: FormEvent<HTMLFormElement>
  ) {
    event.preventDefault();

    const form = event.currentTarget;
    const formData = new FormData(form);

    const trip = {
      destination: String(
        formData.get("destination") ?? ""
      ),
      startDate: String(
        formData.get("startDate") ?? ""
      ),
      endDate: String(
        formData.get("endDate") ?? ""
      ),
    };

    setErrors([]);
    setIsSubmitting(true);

    try {
      const response = await fetch("/api/trips", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(trip),
      });

      const data = (await response.json()) as ApiError;

      if (!response.ok) {
        setErrors(
          data.errors ?? [
            data.message ??
              "Não foi possível criar a viagem.",
          ]
        );

        return;
      }

      form.reset();

      const updatedTrips = await getTrips();
      setTrips(updatedTrips);
    } catch {
      setErrors([
        "Não foi possível conectar ao servidor.",
      ]);
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <section>
      <div>
        <h2>Crie uma viagem</h2>

        <form onSubmit={handleSubmit}>
          <label>
            Destino
            <input
              type="text"
              name="destination"
              placeholder="Ex.: Lisboa"
              required
            />
          </label>

          <label>
            Data inicial
            <input
              type="date"
              name="startDate"
              required
            />
          </label>

          <label>
            Data final
            <input
              type="date"
              name="endDate"
              required
            />
          </label>

          <button
            type="submit"
            disabled={isSubmitting}
          >
            {isSubmitting
              ? "Criando..."
              : "Criar viagem"}
          </button>
        </form>

        {errors.length > 0 && (
          <div role="alert">
            <ul>
              {errors.map((error, index) => (
                <li key={`${error}-${index}`}>
                  {error}
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>

      <div>
        <h2>Viagens cadastradas</h2>

        {isLoading ? (
          <p>Carregando viagens...</p>
        ) : trips.length === 0 ? (
          <p>Nenhuma viagem cadastrada.</p>
        ) : (
          <ul>
            {trips.map((trip) => (
              <li key={trip.id}>
                <h3>{trip.destination}</h3>
                <p>
                  {formatDate(trip.startDate)}
                  {" até "}
                  {formatDate(trip.endDate)}
                </p>
              </li>
            ))}
          </ul>
        )}
      </div>
    </section>
  );
}