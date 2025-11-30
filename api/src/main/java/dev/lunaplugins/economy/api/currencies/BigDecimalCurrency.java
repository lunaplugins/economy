package dev.lunaplugins.economy.api.currencies;

import dev.lunaplugins.economy.api.LunaCurrency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public interface BigDecimalCurrency extends LunaCurrency {

  @Override
  default int intValue(final UUID uuid) {
    return ConversionUtils.downSampleToInt(this.bigIntValue(uuid));
  }

  @Override
  default long longValue(final UUID uuid) {
    return ConversionUtils.downSampleToLong(this.bigIntValue(uuid));
  }

  @Override
  default float floatValue(final UUID uuid) {
    return this.bigDecimalValue(uuid).floatValue();
  }

  @Override
  default double doubleValue(final UUID uuid) {
    return this.bigDecimalValue(uuid).doubleValue();
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid) {
    return this.bigDecimalValue(uuid).toBigInteger();
  }

  @Override
  default int intValue(final UUID uuid, final int value) {
    return ConversionUtils.downSampleToInt(this.bigDecimalValue(uuid, BigDecimal.valueOf(value)).toBigInteger());
  }

  @Override
  default long longValue(final UUID uuid, final long value) {
    return ConversionUtils.downSampleToLong(this.bigDecimalValue(uuid, BigDecimal.valueOf(value)).toBigInteger());
  }

  @Override
  default float floatValue(final UUID uuid, final float value) {
    return this.bigDecimalValue(uuid, BigDecimal.valueOf(value)).floatValue();
  }

  @Override
  default double doubleValue(final UUID uuid, final double value) {
    return this.bigDecimalValue(uuid, BigDecimal.valueOf(value)).doubleValue();
  }

  @Override
  default BigInteger bigIntValue(final UUID uuid, final BigInteger value) {
    return this.bigDecimalValue(uuid, new BigDecimal(value)).toBigInteger();
  }

  @Override
  default int addIntValue(final UUID uuid, final int value) {
    return ConversionUtils.downSampleToInt(this.addBigDecimalValue(uuid, BigDecimal.valueOf(value)).toBigInteger());
  }

  @Override
  default long addLongValue(final UUID uuid, final long value) {
    return ConversionUtils.downSampleToLong(this.addBigDecimalValue(uuid, BigDecimal.valueOf(value)).toBigInteger());
  }

  @Override
  default float addFloatValue(final UUID uuid, final float value) {
    return this.addBigDecimalValue(uuid, BigDecimal.valueOf(value)).floatValue();
  }

  @Override
  default double addDoubleValue(final UUID uuid, final double value) {
    return this.addBigDecimalValue(uuid, BigDecimal.valueOf(value)).doubleValue();
  }

  @Override
  default BigInteger addBigIntValue(final UUID uuid, final BigInteger value) {
    return this.addBigDecimalValue(uuid, new BigDecimal(value)).toBigInteger();
  }

  @Override
  default boolean isInteger() {
    return false;
  }
}
