package dev.luna.economy.api;

public interface CurrencyPrecision {
  boolean isInteger();

  default boolean isDecimal() {
    return !this.isInteger();
  }
}
