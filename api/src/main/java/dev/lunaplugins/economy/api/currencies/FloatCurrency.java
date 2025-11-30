package dev.lunaplugins.economy.api.currencies;

import dev.lunaplugins.economy.api.LunaCurrency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public interface FloatCurrency extends LunaCurrency {

  @Override
  default int intValue(final UUID uuid) {
    return (int) this.floatValue(uuid);
  }

  @Override
  default long longValue(final UUID uuid) {
    return (long) this.floatValue(uuid);
  }

  @Override
  default double doubleValue(final UUID uuid) {
    return this.floatValue(uuid);
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid) {
    return this.bigDecimalValue(uuid).toBigInteger();
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid) {
    return BigDecimal.valueOf(this.floatValue(uuid));
  }

  @Override
  default int intValue(final UUID uuid, int value) {
    return (int) this.floatValue(uuid, value);
  }

  @Override
  default long longValue(final UUID uuid, long value) {
    return (long) this.floatValue(uuid, value);
  }

  @Override
  default double doubleValue(final UUID uuid, double value) {
    return this.floatValue(uuid, (float) value);
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid, final BigInteger value) {
    return this.bigDecimalValue(uuid, new BigDecimal(value)).toBigInteger();
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid, final BigDecimal value) {
    return BigDecimal.valueOf(this.floatValue(uuid, value.floatValue()));
  }

  @Override
  default int addIntValue(final UUID uuid, final int value) {
    return (int) this.addFloatValue(uuid, value);
  }

  @Override
  default long addLongValue(final UUID uuid, final long value) {
    return (long) this.addFloatValue(uuid, value);
  }

  @Override
  default double addDoubleValue(final UUID uuid, final double value) {
    return this.addFloatValue(uuid, (float) value);
  }

  @Override
  default BigInteger addBigIntValue(final UUID uuid, final BigInteger value) {
    return this.addBigDecimalValue(uuid, new BigDecimal(value)).toBigInteger();
  }

  @Override
  default BigDecimal addBigDecimalValue(final UUID uuid, final BigDecimal value) {
    return BigDecimal.valueOf(this.addFloatValue(uuid, value.floatValue()));
  }

  @Override
  default boolean isInteger() {
    return false;
  }
}
