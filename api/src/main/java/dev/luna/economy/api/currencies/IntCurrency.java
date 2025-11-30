package dev.luna.economy.api.currencies;

import dev.luna.economy.api.LunaCurrency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public interface IntCurrency extends LunaCurrency {

  @Override
  default long longValue(UUID uuid) {
    return this.intValue(uuid);
  }

  @Override
  default float floatValue(UUID uuid) {
    return this.intValue(uuid);
  }

  @Override
  default double doubleValue(UUID uuid) {
    return this.intValue(uuid);
  }

  @Override
  default BigInteger bigIntValue(UUID uuid) {
    return BigInteger.valueOf(this.intValue(uuid));
  }

  @Override
  default BigDecimal bigDecimalValue(UUID uuid) {
    return BigDecimal.valueOf(this.intValue(uuid));
  }

  @Override
  default long longValue(UUID uuid, long value) {
    return this.intValue(uuid, Math.clamp(value, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }

  @Override
  default float floatValue(UUID uuid, float value) {
    return this.intValue(uuid, Math.round(value));
  }

  @Override
  default double doubleValue(UUID uuid, double value) {
    return this.longValue(uuid, Math.round(value));
  }

  @Override
  default BigInteger bigIntValue(UUID uuid, BigInteger value) {
    if (value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 0) {
      return BigInteger.valueOf(this.intValue(uuid, Integer.MAX_VALUE));
    } else if (value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) <= 0) {
      return BigInteger.valueOf(this.intValue(uuid, Integer.MIN_VALUE));
    } else {
      return BigInteger.valueOf(this.intValue(uuid, value.intValueExact()));
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
    if (value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 0) {
      return BigInteger.valueOf(this.addIntValue(uuid, Integer.MAX_VALUE));
    } else if (value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) <= 0) {
      return BigInteger.valueOf(this.addIntValue(uuid, Integer.MIN_VALUE));
    } else {
      return BigInteger.valueOf(this.addIntValue(uuid, value.intValueExact()));
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
