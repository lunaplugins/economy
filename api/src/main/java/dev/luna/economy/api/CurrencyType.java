package dev.luna.economy.api;

import dev.luna.economy.api.currencies.BigIntCurrency;

public interface CurrencyType<T extends LunaCurrency> {
  CurrencyType<BigIntCurrency> BIG_INT = new CurrencyTypeImpl<>("BigIntCurrency", BigIntCurrency.class);
  CurrencyType<BigIntCurrency> DOUBLE = new CurrencyTypeImpl<>("DoubleCurrency", BigIntCurrency.class);

  boolean isInstance(LunaCurrency currency);

  T cast(LunaCurrency currency);

  String name();
}
