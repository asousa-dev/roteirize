"use client";

import { useEffect, useState } from "react";
import type { KeyboardEvent } from "react";
import formStyles from "./trip-manager.module.css";
import styles from "./destination-autocomplete.module.css";

export type LocationSuggestion = {
  providerId: string;
  displayName: string;
  city: string;
  state: string | null;
  country: string;
  countryCode: string;
  latitude: number;
  longitude: number;
};

type ApiError = {
  message?: string;
  errors?: string[];
};

type DestinationAutocompleteProps = {
  onSelectionChange: (
    location: LocationSuggestion | null
  ) => void;
  validationMessage?: string;
};

export default function DestinationAutocomplete({
  onSelectionChange,
  validationMessage,
}: DestinationAutocompleteProps) {
  const [query, setQuery] = useState("");
  const [selected, setSelected] =
    useState<LocationSuggestion | null>(null);
  const [suggestions, setSuggestions] = useState<
    LocationSuggestion[]
  >([]);
  const [isSearching, setIsSearching] =
    useState(false);
  const [hasSearched, setHasSearched] =
    useState(false);
  const [isOpen, setIsOpen] = useState(false);
  const [searchError, setSearchError] = useState<
    string | null
  >(null);
  const [activeIndex, setActiveIndex] =
    useState(-1);

  useEffect(() => {
    const normalizedQuery = query.trim();

    if (
      selected ||
      normalizedQuery.length < 3
    ) {
      return;
    }

    const controller = new AbortController();

    const timeout = window.setTimeout(
      async () => {
        setIsSearching(true);
        setSearchError(null);

        try {
          const parameters = new URLSearchParams({
            query: normalizedQuery,
          });

          const response = await fetch(
            `/api/locations/cities?${parameters}`,
            {
              cache: "no-store",
              signal: controller.signal,
            }
          );

          const data = (await response.json()) as
            | LocationSuggestion[]
            | ApiError;

          if (!response.ok) {
            const error = data as ApiError;

            throw new Error(
              error.errors?.[0] ??
                error.message ??
                "Não foi possível buscar cidades."
            );
          }

          setSuggestions(
            data as LocationSuggestion[]
          );
          setHasSearched(true);
          setActiveIndex(-1);
        } catch (error) {
          if (
            error instanceof DOMException &&
            error.name === "AbortError"
          ) {
            return;
          }

          setSuggestions([]);
          setHasSearched(true);
          setSearchError(
            error instanceof Error
              ? error.message
              : "Não foi possível buscar cidades."
          );
        } finally {
          if (!controller.signal.aborted) {
            setIsSearching(false);
          }
        }
      },
      350
    );

    return () => {
      window.clearTimeout(timeout);
      controller.abort();
    };
  }, [query, selected]);

  function handleQueryChange(
    value: string
  ) {
    setQuery(value);
    setSelected(null);
    setSuggestions([]);
    setHasSearched(false);
    setIsSearching(false);
    setSearchError(null);
    setActiveIndex(-1);
    setIsOpen(true);
    onSelectionChange(null);
  }

  function selectSuggestion(
    suggestion: LocationSuggestion
  ) {
    setQuery(suggestion.displayName);
    setSelected(suggestion);
    setSuggestions([]);
    setHasSearched(false);
    setSearchError(null);
    setActiveIndex(-1);
    setIsOpen(false);
    onSelectionChange(suggestion);
  }

  function handleKeyDown(
    event: KeyboardEvent<HTMLInputElement>
  ) {
    if (event.key === "Escape") {
      setIsOpen(false);
      setActiveIndex(-1);
      return;
    }

    if (suggestions.length === 0) {
      return;
    }

    if (event.key === "ArrowDown") {
      event.preventDefault();
      setIsOpen(true);
      setActiveIndex((current) =>
        current >= suggestions.length - 1
          ? 0
          : current + 1
      );
    }

    if (event.key === "ArrowUp") {
      event.preventDefault();
      setIsOpen(true);
      setActiveIndex((current) =>
        current <= 0
          ? suggestions.length - 1
          : current - 1
      );
    }

    if (
      event.key === "Enter" &&
      activeIndex >= 0
    ) {
      event.preventDefault();
      selectSuggestion(
        suggestions[activeIndex]
      );
    }
  }

  const shouldShowMenu =
    isOpen &&
    query.trim().length >= 3 &&
    !selected &&
    (isSearching ||
      hasSearched ||
      suggestions.length > 0);

  const fieldMessage =
    validationMessage ?? searchError;

  return (
    <label className={formStyles.field}>
      Destino

      <div className={styles.autocomplete}>
        <input
          className={`${formStyles.input} ${
            fieldMessage
              ? styles.inputInvalid
              : ""
          }`}
          type="text"
          value={query}
          placeholder="Comece a digitar uma cidade"
          autoComplete="off"
          required
          role="combobox"
          aria-autocomplete="list"
          aria-expanded={shouldShowMenu}
          aria-controls="destination-suggestions"
          aria-activedescendant={
            activeIndex >= 0
              ? `destination-option-${activeIndex}`
              : undefined
          }
          onChange={(event) =>
            handleQueryChange(event.target.value)
          }
          onFocus={() => setIsOpen(true)}
          onBlur={() => {
            window.setTimeout(
              () => setIsOpen(false),
              120
            );
          }}
          onKeyDown={handleKeyDown}
        />

        {shouldShowMenu && (
          <div
            id="destination-suggestions"
            className={styles.suggestions}
            role="listbox"
            aria-label="Sugestões de cidades"
          >
            {isSearching ? (
              <p className={styles.status}>
                Buscando cidades...
              </p>
            ) : suggestions.length > 0 ? (
              suggestions.map(
                (suggestion, index) => (
                  <button
                    id={`destination-option-${index}`}
                    className={`${styles.suggestion} ${
                      activeIndex === index
                        ? styles.suggestionActive
                        : ""
                    }`}
                    key={suggestion.providerId}
                    type="button"
                    role="option"
                    aria-selected={
                      activeIndex === index
                    }
                    onMouseDown={(event) =>
                      event.preventDefault()
                    }
                    onClick={() =>
                      selectSuggestion(suggestion)
                    }
                  >
                    <strong>
                      {suggestion.city}
                    </strong>

                    <span>
                      {[
                        suggestion.state,
                        suggestion.country,
                      ]
                        .filter(Boolean)
                        .join(", ")}
                    </span>
                  </button>
                )
              )
            ) : (
              <p className={styles.status}>
                Nenhuma cidade encontrada.
              </p>
            )}
          </div>
        )}
      </div>

      {fieldMessage && (
        <span
          className={styles.fieldMessage}
          role="alert"
        >
          {fieldMessage}
        </span>
      )}
    </label>
  );
}