package dev.lunaplugins.economy.api.currencies;

import dev.lunaplugins.economy.api.LunaCurrency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public interface BigIntCurrency extends LunaCurrency {

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
    return this.bigIntValue(uuid).floatValue();
  }

  @Override
  default double doubleValue(final UUID uuid) {
    return this.bigIntValue(uuid).doubleValue();
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid) {
    return new BigDecimal(this.bigIntValue(uuid));
  }

  @Override
  default int intValue(final UUID uuid, final int value) {
    return ConversionUtils.downSampleToInt(this.bigIntValue(uuid, BigInteger.valueOf(value)));
  }

  @Override
  default long longValue(final UUID uuid, final long value) {
    return ConversionUtils.downSampleToLong(this.bigIntValue(uuid, BigInteger.valueOf(value)));
  }

  @Override
  default float floatValue(final UUID uuid, final float value) {
    return this.bigIntValue(uuid, BigDecimal.valueOf(value).toBigInteger()).floatValue();
  }

  @Override
  default double doubleValue(final UUID uuid, final double value) {
    return this.bigIntValue(uuid, BigDecimal.valueOf(value).toBigInteger()).doubleValue();
  }

  @Override
  default BigDecimal bigDecimalValue(final UUID uuid, final BigDecimal value) {
    return new BigDecimal(this.bigIntValue(uuid, value.toBigInteger()));
  }

  @Override
  default int addIntValue(final UUID uuid, final int value) {
    return ConversionUtils.downSampleToInt(this.addBigIntValue(uuid, BigInteger.valueOf(value)));
  }

  @Override
  default long addLongValue(final UUID uuid, final long value) {
    return ConversionUtils.downSampleToLong(this.addBigIntValue(uuid, BigInteger.valueOf(value)));
  }

  @Override
  default float addFloatValue(final UUID uuid, final float value) {
    return this.addBigIntValue(uuid, BigDecimal.valueOf(value).toBigInteger()).floatValue();
  }

  @Override
  default double addDoubleValue(final UUID uuid, final double value) {
    return this.addBigIntValue(uuid, BigDecimal.valueOf(value).toBigInteger()).doubleValue();
  }

  @Override
  default BigDecimal addBigDecimalValue(final UUID uuid, final BigDecimal value) {
    return new BigDecimal(this.addBigIntValue(uuid, value.toBigInteger()));
  }

  @Override
  default boolean isInteger() {
    return true;
  }
}
