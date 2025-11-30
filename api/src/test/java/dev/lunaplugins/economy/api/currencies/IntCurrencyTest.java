package dev.lunaplugins.economy.api.currencies;

import net.kyori.adventure.key.Key;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NullMarked
class IntCurrencyTest {

  @Test
  void testBasicOperations() {
    final IntCurrency impl = new DummyImpl();

    assertEquals(0, impl.intValue("dummy", 24));
    assertEquals(24, impl.intValue("dummy"));
    assertEquals(24, impl.addIntValue("dummy", 5));
    assertEquals(29, impl.intValue("dummy"));
    assertEquals(29, impl.addIntValue("dummy", -10));
    assertEquals(19, impl.intValue("dummy"));
    assertEquals(19, impl.intValue("dummy", 0));
    assertEquals(0, impl.intValue("dummy"));
  }

  @Test
  void testBasicLongOperations() {
    final IntCurrency impl = new DummyImpl();

    assertEquals(0, impl.longValue("dummy", 24));
    assertEquals(24, impl.longValue("dummy"));
    assertEquals(24, impl.addLongValue("dummy", 5));
    assertEquals(29, impl.longValue("dummy"));
    assertEquals(29, impl.addLongValue("dummy", -10));
    assertEquals(19, impl.longValue("dummy"));
    assertEquals(19, impl.longValue("dummy", 0));
    assertEquals(0, impl.longValue("dummy"));
  }

  @Test
  void testLongOverflow() {
    final IntCurrency impl = new DummyImpl();

    assertEquals(0, impl.longValue("dummy", Long.MAX_VALUE));
    assertEquals(Integer.MAX_VALUE, impl.longValue("dummy"));
    assertEquals(Integer.MAX_VALUE, impl.addLongValue("dummy", Long.MIN_VALUE));
    assertEquals(Integer.MIN_VALUE, impl.longValue("dummy"));
  }

  @Test
  void testFloatOperations() {
    final IntCurrency impl = new DummyImpl();

    assertEquals(0, impl.floatValue("dummy", 24.2f));
    assertEquals(24.0f, impl.addFloatValue("dummy", 0.8f));
    assertEquals(25.0f, impl.addFloatValue("dummy", 1.2f));
    assertEquals(26.0f, impl.floatValue("dummy", Float.POSITIVE_INFINITY));
    assertEquals(Integer.MAX_VALUE, impl.intValue("dummy"));
  }

  @Test
  void testDoubleOperations() {
    final IntCurrency impl = new DummyImpl();

    assertEquals(0, impl.doubleValue("dummy", 240.2));
    assertEquals(240.0, impl.addDoubleValue("dummy", 0.8));
    assertEquals(241.0, impl.addDoubleValue("dummy", 1.2));
    assertEquals(242.0, impl.doubleValue("dummy", Double.POSITIVE_INFINITY));
    assertEquals(Integer.MAX_VALUE, impl.intValue("dummy"));
  }

  @Test
  void testBigIntOperations() {
    final IntCurrency impl = new DummyImpl();

    assertEquals(BigInteger.ZERO, impl.bigIntValue("dummy", BigInteger.valueOf(25_000)));
    assertEquals(BigInteger.valueOf(25_000), impl.addBigIntValue("dummy", BigInteger.ONE.negate()));
    assertEquals(BigInteger.valueOf(24_999), impl.bigIntValue("dummy"));
  }

  @Test
  void testBigDecimalOperations() {
    final IntCurrency impl = new DummyImpl();

    assertEquals(BigDecimal.valueOf(0.0), impl.bigDecimalValue("dummy", BigDecimal.valueOf(25_000)));
    assertEquals(BigDecimal.valueOf(25_000.0), impl.addBigDecimalValue("dummy", BigDecimal.TWO.negate()));
    assertEquals(BigDecimal.valueOf(24_998.0), impl.bigDecimalValue("dummy", new BigDecimal(Double.MAX_VALUE)));
    assertEquals(Integer.MAX_VALUE, impl.intValue("dummy"));
  }

  static class DummyImpl implements IntCurrency {
    private int value = 0;

    @Override
    public Key key() {
      return Key.key("dummy:int");
    }

    @Override
    public @Nullable UUID uuidForName(final String name) {
      return UUID.randomUUID();
    }

    @Override
    public int intValue(final UUID uuid) {
      return this.value;
    }

    @Override
    public int intValue(final UUID uuid, final int value) {
      final int prevValue = this.value;
      this.value = value;
      return prevValue;
    }

    @Override
    public int addIntValue(final UUID uuid, final int value) {
      final int prevValue = this.value;
      this.value += value;
      return prevValue;
    }
  }
}
