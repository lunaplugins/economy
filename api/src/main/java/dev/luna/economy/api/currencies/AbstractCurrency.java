package dev.luna.economy.api.currencies;

import dev.luna.economy.api.LunaCurrency;
import dev.luna.economy.api.CurrencyType;
import net.kyori.adventure.key.Key;

public abstract class AbstractCurrency<T extends AbstractCurrency<T>> implements LunaCurrency {
  protected final Key key;
  protected final CurrencyType<T> type;

  protected AbstractCurrency(final Key key, final CurrencyType<T> type) {
    this.key = key;
    this.type = type;
  }

  @Override
  public Key key() {
    return this.key;
  }

  @Override
  public CurrencyType<T> type() {
    return this.type;
  }
}
