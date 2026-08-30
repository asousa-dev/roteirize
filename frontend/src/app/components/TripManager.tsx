"use client";

import { useEffect, useState } from "react";
import type { FormEvent } from "react";
import DestinationAutocomplete, {
  type LocationSuggestion,
} from "./DestinationAutocomplete";
import styles from "./trip-manager.module.css";

type Trip = {
  id: string;
  destination: LocationSuggestion;
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
    throw new Error(
      "Não foi possível carregar as viagens."
    );
  }

  return (await response.json()) as Trip[];
}

function formatDate(date: string): string {
  const [year, month, day] = date.split("-");

  return `${day}/${month}/${year}`;
}

export default function TripManager() {
  const [trips, setTrips] = useState<Trip[]>([]);
  const [errors, setErrors] = useState<string[]>(
    []
  );
  const [
    selectedDestination,
    setSelectedDestination,
  ] = useState<LocationSuggestion | null>(null);
  const [
    destinationValidationMessage,
    setDestinationValidationMessage,
  ] = useState<string | null>(null);
  const [formResetKey, setFormResetKey] =
    useState(0);
  const [isLoading, setIsLoading] =
    useState(true);
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

  function handleDestinationSelection(
    location: LocationSuggestion | null
  ) {
    setSelectedDestination(location);

    if (location) {
      setDestinationValidationMessage(null);
    }
  }

  async function handleSubmit(
    event: FormEvent<HTMLFormElement>
  ) {
    event.preventDefault();
    setErrors([]);

    if (!selectedDestination) {
      setDestinationValidationMessage(
        "Selecione uma cidade na lista de sugestões."
      );

      return;
    }

    const form = event.currentTarget;
    const formData = new FormData(form);

    const trip = {
      destination: selectedDestination,
      startDate: String(
        formData.get("startDate") ?? ""
      ),
      endDate: String(
        formData.get("endDate") ?? ""
      ),
    };

    setDestinationValidationMessage(null);
    setIsSubmitting(true);

    try {
      const response = await fetch("/api/trips", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(trip),
      });

      const data =
        (await response.json()) as ApiError;

      if (!response.ok) {
        const responseErrors =
          data.errors && data.errors.length > 0
            ? data.errors
            : [
                data.message ??
                  "Não foi possível criar a viagem.",
              ];

        setErrors(responseErrors);
        return;
      }

      form.reset();
      setSelectedDestination(null);
      setDestinationValidationMessage(null);
      setFormResetKey(
        (current) => current + 1
      );

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
    <section className={styles.container}>
      <div className={styles.panel}>
        <h2 className={styles.heading}>
          Crie uma viagem
        </h2>

        <form
          className={styles.form}
          onSubmit={handleSubmit}
        >
          <DestinationAutocomplete
            key={formResetKey}
            onSelectionChange={
              handleDestinationSelection
            }
            validationMessage={
              destinationValidationMessage ??
              undefined
            }
          />

          <label className={styles.field}>
            Data inicial
            <input
              className={styles.input}
              type="date"
              name="startDate"
              required
            />
          </label>

          <label className={styles.field}>
            Data final
            <input
              className={styles.input}
              type="date"
              name="endDate"
              required
            />
          </label>

          <button
            className={styles.button}
            type="submit"
            disabled={isSubmitting}
          >
            {isSubmitting
              ? "Criando..."
              : "Criar viagem"}
          </button>
        </form>

        {errors.length > 0 && (
          <div
            className={styles.error}
            role="alert"
          >
            <ul className={styles.errorList}>
              {errors.map((error, index) => (
                <li key={`${error}-${index}`}>
                  {error}
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>

      <div className={styles.panel}>
        <h2 className={styles.heading}>
          Viagens cadastradas
        </h2>

        {isLoading ? (
          <p className={styles.empty}>
            Carregando viagens...
          </p>
        ) : trips.length === 0 ? (
          <p className={styles.empty}>
            Nenhuma viagem cadastrada.
          </p>
        ) : (
          <ul className={styles.tripList}>
            {trips.map((trip) => (
              <li
                className={styles.tripCard}
                key={trip.id}
              >
                <h3>
                  {trip.destination.displayName}
                </h3>

                <p className={styles.tripDate}>
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