package dev.lunaplugins.economy.api.currencies;

import java.math.BigInteger;

class ConversionUtils {

  static long downSampleToLong(final BigInteger value) {
    if (value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) >= 0) {
      return Long.MAX_VALUE;
    } else if (value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) <= 0) {
      return Long.MIN_VALUE;
    } else {
      return value.longValueExact();
    }
  }

  static int downSampleToInt(final BigInteger value) {
    if (value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 0) {
      return Integer.MAX_VALUE;
    } else if (value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) <= 0) {
      return Integer.MIN_VALUE;
    } else {
      return value.intValueExact();
    }
  }

  private ConversionUtils() {
    // Utility class
  }
}
