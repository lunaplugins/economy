package dev.lunaplugins.economy.api.currencies;

import dev.lunaplugins.economy.api.LunaCurrency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public interface DoubleCurrency extends LunaCurrency {

  @Override
  default int intValue(final UUID uuid) {
    return (int) this.doubleValue(uuid);
  }

  @Override
  default long longValue(final UUID uuid) {
    return (long) this.doubleValue(uuid);
  }

  @Override
  default float floatValue(final UUID uuid) {
    return (float) this.doubleValue(uuid);
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid) {
    return this.bigDecimalValue(uuid).toBigInteger();
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid) {
    return BigDecimal.valueOf(this.doubleValue(uuid));
  }

  @Override
  default int intValue(final UUID uuid, final int value) {
    return (int) this.doubleValue(uuid, value);
  }

  @Override
  default long longValue(final UUID uuid, final long value) {
    return (long) this.doubleValue(uuid, value);
  }

  @Override
  default float floatValue(final UUID uuid, final float value) {
    return (float) this.doubleValue(uuid, value);
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid, final BigInteger value) {
    return this.bigDecimalValue(uuid, new BigDecimal(value)).toBigInteger();
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid, final BigDecimal value) {
    return BigDecimal.valueOf(this.doubleValue(uuid, value.doubleValue()));
  }

  @Override
  default int addIntValue(final UUID uuid, final int value) {
    return (int) this.addDoubleValue(uuid, value);
  }

  @Override
  default long addLongValue(final UUID uuid, final long value) {
    return (long) this.addDoubleValue(uuid, value);
  }

  @Override
  default float addFloatValue(final UUID uuid, final float value) {
    return (float) this.addDoubleValue(uuid, value);
  }

  @Override
  default BigInteger addBigIntValue(final UUID uuid, final BigInteger value) {
    return this.addBigDecimalValue(uuid, new BigDecimal(value)).toBigInteger();
  }

  @Override
  default BigDecimal addBigDecimalValue(final UUID uuid, final BigDecimal value) {
    return BigDecimal.valueOf(this.addDoubleValue(uuid, value.doubleValue()));
  }

  @Override
  default boolean isInteger() {
    return false;
  }
}
