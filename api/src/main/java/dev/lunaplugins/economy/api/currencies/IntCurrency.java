package dev.lunaplugins.economy.api.currencies;

import dev.lunaplugins.economy.api.LunaCurrency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public interface IntCurrency extends LunaCurrency {

  @Override
  default long longValue(final UUID uuid) {
    return this.intValue(uuid);
  }

  @Override
  default float floatValue(final UUID uuid) {
    return this.intValue(uuid);
  }

  @Override
  default double doubleValue(final UUID uuid) {
    return this.intValue(uuid);
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid) {
    return BigInteger.valueOf(this.intValue(uuid));
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid) {
    return BigDecimal.valueOf(this.intValue(uuid));
  }

  @Override
  default long longValue(final UUID uuid, final long value) {
    return this.intValue(uuid, Math.clamp(value, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }

  @Override
  default float floatValue(final UUID uuid, final float value) {
    return this.intValue(uuid, Math.round(value));
  }

  @Override
  default double doubleValue(final UUID uuid, final double value) {
    return this.longValue(uuid, Math.round(value));
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid, final BigInteger value) {
    return BigInteger.valueOf(this.intValue(uuid, ConversionUtils.downSampleToInt(value)));
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid, final BigDecimal value) {
    return BigDecimal.valueOf(this.doubleValue(uuid, value.doubleValue()));
  }

  @Override
  default long addLongValue(final UUID uuid, final long value) {
    return this.addIntValue(uuid, Math.clamp(value, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }

  @Override
  default float addFloatValue(final UUID uuid, final float value) {
    return this.addIntValue(uuid, Math.round(value));
  }

  @Override
  default double addDoubleValue(final UUID uuid, final double value) {
    return this.addLongValue(uuid, Math.round(value));
  }

  @Override
  default BigInteger addBigIntValue(final UUID uuid, final BigInteger value) {
    return BigInteger.valueOf(this.addIntValue(uuid, ConversionUtils.downSampleToInt(value)));
  }

  @Override
  default BigDecimal addBigDecimalValue(final UUID uuid, final BigDecimal value) {
    return BigDecimal.valueOf(this.addDoubleValue(uuid, value.doubleValue()));
  }

  @Override
  default boolean isInteger() {
    return true;
  }
}
