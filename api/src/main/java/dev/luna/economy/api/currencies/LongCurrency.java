package dev.luna.economy.api.currencies;

import dev.luna.economy.api.LunaCurrency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public interface LongCurrency extends LunaCurrency {

  @Override
  default int intValue(UUID uuid) {
    return Math.clamp(this.longValue(uuid), Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  @Override
  default float floatValue(UUID uuid) {
    return this.longValue(uuid);
  }

  @Override
  default double doubleValue(UUID uuid) {
    return this.longValue(uuid);
  }

  @Override
  default BigInteger bigIntValue(UUID uuid) {
    return BigInteger.valueOf(this.longValue(uuid));
  }

  @Override
  default BigDecimal bigDecimalValue(UUID uuid) {
    return BigDecimal.valueOf(this.longValue(uuid));
  }

  @Override
  default int intValue(UUID uuid, int value) {
    return Math.clamp(this.longValue(uuid, value), Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  @Override
  default float floatValue(UUID uuid, float value) {
    return this.longValue(uuid, Math.round(value));
  }

  @Override
  default double doubleValue(UUID uuid, double value) {
    return this.longValue(uuid, Math.round(value));
  }

  @Override
  default BigInteger bigIntValue(UUID uuid, BigInteger value) {
    if (value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) >= 0) {
      return BigInteger.valueOf(this.longValue(uuid, Long.MAX_VALUE));
    } else if (value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) <= 0) {
      return BigInteger.valueOf(this.longValue(uuid, Long.MIN_VALUE));
    } else {
      return BigInteger.valueOf(this.longValue(uuid, value.longValueExact()));
    }
  }

  @Override
  default BigDecimal bigDecimalValue(UUID uuid, BigDecimal value) {
    return BigDecimal.valueOf(this.doubleValue(uuid, value.doubleValue()));
  }

  @Override
  default long addLongValue(UUID uuid, long value) {
    return this.addIntValue(uuid, Math.clamp(value, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }

  @Override
  default float addFloatValue(UUID uuid, float value) {
    return this.addIntValue(uuid, Math.round(value));
  }

  @Override
  default double addDoubleValue(UUID uuid, double value) {
    return this.addLongValue(uuid, Math.round(value));
  }

  @Override
  default BigInteger addBigIntValue(UUID uuid, BigInteger value) {
    if (value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) >= 0) {
      return BigInteger.valueOf(this.addLongValue(uuid, Long.MAX_VALUE));
    } else if (value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) <= 0) {
      return BigInteger.valueOf(this.addLongValue(uuid, Long.MIN_VALUE));
    } else {
      return BigInteger.valueOf(this.addLongValue(uuid, value.longValueExact()));
    }
  }

  @Override
  default BigDecimal addBigDecimalValue(UUID uuid, BigDecimal value) {
    return BigDecimal.valueOf(this.addDoubleValue(uuid, value.doubleValue()));
  }

  @Override
  default boolean isInteger() {
    return true;
  }
}
