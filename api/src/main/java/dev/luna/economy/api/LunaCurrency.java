package dev.luna.economy.api;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

/// A general representation of a single currency type.
///
/// A currency in the general sense is no more than a simple
/// interface where you can retrieve and set some numerical value using
/// an [UUID] or [String] key.
///
/// This superinterface is implemented by more specific types for
/// specific precisions/types of numbers; this distinction is important
/// for certain optimization purposes.
///
/// This interface allows for getting and setting the values using
/// various pre-implemented number types (`int`, `long`, `float`, `double`,
/// [BigInteger], and [BigDecimal]). Due to the nature of the backing
/// implementations, these may lose precision. The general expectation for overflow-able
/// numbers is that they instead get set to the highest value, making overflowing not possible.
/// This is, however, dependent on the backing implementation.
@NullMarked
public interface LunaCurrency extends Keyed {
  /// The key of this currency. It has to be unique and is used
  /// to differentiate different currencies.
  ///
  /// @return the key of this currency
  Key key();

  /// The type of this currency. This can be used to further
  /// distinguish (and cast) this general interface.
  ///
  /// @return the type of this currency
  CurrencyType<?> type();

  /// A utility method for retrieving a [UUID] from a [String], assuming the name
  /// is present in the database.
  @Nullable UUID uuidForName(String name);

  //<editor-fold desc="A bunch of numerical getters">

  /// A getter for a user's balance.
  ///
  /// This variant returns an `int`. Any overflowing values may be clamped.
  /// Decimal values are always rounded down.
  ///
  /// @param uuid the UUID of the player to check
  /// @return the balance of the provided user
  int intValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns an `int`. Any overflowing values may be clamped.
  /// Decimal values are always rounded down.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name the name of the player to check
  /// @return the balance of the provided user
  default int intValue(final String name) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.intValue(uuid);
    } else {
      return 0;
    }
  }

  /// A getter for a user's balance.
  ///
  /// This variant returns a `long`. Any overflowing values may be clamped.
  /// Decimal values are always rounded down.
  ///
  /// @param uuid the UUID of the player to check
  /// @return the balance of the provided user
  long longValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a `long`. Any overflowing values may be clamped.
  /// Decimal values are always rounded down.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name the name of the player to check
  /// @return the balance of the provided user
  default long longValue(final String name) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.longValue(uuid);
    } else {
      return 0;
    }
  }

  /// A getter for a user's balance.
  ///
  /// This variant returns a `float`.
  ///
  /// @param uuid the UUID of the player to check
  /// @return the balance of the provided user
  float floatValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a `float`.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name the name of the player to check
  /// @return the balance of the provided user
  default float floatValue(final String name) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.floatValue(uuid);
    } else {
      return 0;
    }
  }

  /// A getter for a user's balance.
  ///
  /// This variant returns a `double`.
  ///
  /// @param uuid the UUID of the player to check
  /// @return the balance of the provided user
  double doubleValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a `double`.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name the name of the player to check
  /// @return the balance of the provided user
  default double doubleValue(final String name) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.doubleValue(uuid);
    } else {
      return 0;
    }
  }

  /// A getter for a user's balance.
  ///
  /// This variant returns a [BigInteger]. Decimal values are always rounded down.
  ///
  /// @param uuid the UUID of the player to check
  /// @return the balance of the provided user
  BigInteger bigIntValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a [BigInteger]. Decimal values are always rounded down.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name the name of the player to check
  /// @return the balance of the provided user
  default BigInteger bigIntValue(final String name) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.bigIntValue(uuid);
    } else {
      return BigInteger.ZERO;
    }
  }

  /// A getter for a user's balance.
  ///
  /// This variant returns a [BigDecimal].
  ///
  /// @param uuid the UUID of the player to check
  /// @return the balance of the provided user
  BigDecimal bigDecimalValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a [BigDecimal].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name the name of the player to check
  /// @return the balance of the provided user
  default BigDecimal bigDecimalValue(final String name) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.bigDecimalValue(uuid);
    } else {
      return BigDecimal.ZERO;
    }
  }
  //</editor-fold>

  //<editor-fold desc="A bunch of numerical setters">
  int intValue(UUID uuid, int value);

  default int intValue(final String name, final int value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.intValue(uuid, value);
    } else {
      return 0;
    }
  }

  long longValue(UUID uuid, long value);

  default long longValue(final String name, final long value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.longValue(uuid, value);
    } else {
      return 0;
    }
  }

  float floatValue(UUID uuid, float value);

  default float floatValue(final String name, final float value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.floatValue(uuid, value);
    } else {
      return 0;
    }
  }

  double doubleValue(UUID uuid, double value);

  default double doubleValue(final String name, final double value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.doubleValue(uuid, value);
    } else {
      return 0;
    }
  }

  BigInteger bigIntValue(UUID uuid, BigInteger value);

  default BigInteger bigIntValue(final String name, final BigInteger value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.bigIntValue(uuid, value);
    } else {
      return BigInteger.ZERO;
    }
  }

  BigDecimal bigDecimalValue(UUID uuid, BigDecimal value);

  default BigDecimal bigDecimalValue(final String name, final BigDecimal value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.bigDecimalValue(uuid, value);
    } else {
      return BigDecimal.ZERO;
    }
  }
  //</editor-fold>

  //<editor-fold desc="A bunch of numerical adders">
  int addIntValue(UUID uuid, int value);

  default int addIntValue(final String name, final int value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addIntValue(uuid, value);
    } else {
      return 0;
    }
  }

  long addLongValue(UUID uuid, long value);

  default long addLongValue(final String name, final long value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addLongValue(uuid, value);
    } else {
      return 0;
    }
  }

  float addFloatValue(UUID uuid, float value);

  default float addFloatValue(final String name, final float value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addFloatValue(uuid, value);
    } else {
      return 0;
    }
  }

  double addDoubleValue(UUID uuid, double value);

  default double addDoubleValue(final String name, final double value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addDoubleValue(uuid, value);
    } else {
      return 0;
    }
  }

  BigInteger addBigIntValue(UUID uuid, BigInteger value);

  default BigInteger addBigIntValue(final String name, final BigInteger value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addBigIntValue(uuid, value);
    } else {
      return BigInteger.ZERO;
    }
  }

  BigDecimal addBigDecimalValue(UUID uuid, BigDecimal value);

  default BigDecimal addBigDecimalValue(final String name, final BigDecimal value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addBigDecimalValue(uuid, value);
    } else {
      return BigDecimal.ZERO;
    }
  }
  //</editor-fold>
}
