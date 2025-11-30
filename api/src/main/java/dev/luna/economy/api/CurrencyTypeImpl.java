package dev.luna.economy.api;

import java.util.function.Predicate;

public record CurrencyTypeImpl<T extends LunaCurrency>(
  String name,
  Class<T> currencyClass,
  Predicate<T> instancePredicate
) implements CurrencyType<T> {

  public CurrencyTypeImpl(final String name, final Class<T> currencyClass) {
    this(name, currencyClass, (curr) -> true);
  }

  @Override
  public boolean isInstance(final LunaCurrency currency) {
    if (!this.currencyClass.isAssignableFrom(currency.getClass())) {
      return false;
    }
    return this.instancePredicate.test(this.currencyClass.cast(currency));
  }

  @Override
  public T cast(final LunaCurrency currency) {
    if (this.currencyClass.isAssignableFrom(currency.getClass())) {
      return currencyClass.cast(currency);
    }
    throw new ClassCastException("You cannot cast a " + currency.type().name() + " to a " + this.name());
  }
}
