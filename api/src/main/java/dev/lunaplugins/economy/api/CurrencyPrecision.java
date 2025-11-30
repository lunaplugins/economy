package dev.lunaplugins.economy.api;

public interface CurrencyPrecision {
  boolean isInteger();

  default boolean isDecimal() {
    return !this.isInteger();
  }
}
