package dev.luna.economy.api.currencies;

import dev.luna.economy.api.CurrencyType;
import net.kyori.adventure.key.Key;

public final class BigIntCurrency extends AbstractCurrency<BigIntCurrency> {
  BigIntCurrency(final Key key, final CurrencyType<BigIntCurrency> type) {
    super(key, type);
  }
}
