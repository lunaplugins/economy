package dev.luna.economy.api.currencies;

import dev.luna.economy.api.CurrencyType;
import net.kyori.adventure.key.Key;

public final class DoubleCurrency extends AbstractCurrency<DoubleCurrency> {
  DoubleCurrency(final Key key, final CurrencyType<DoubleCurrency> type) {
    super(key, type);
  }
}
