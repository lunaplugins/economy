package dev.luna.economy.api;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

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
///
/// All methods **have to be thread-safe**. If you require their completion inside a [CompletableFuture],
/// it is safe to run them inside a [CompletableFuture#supplyAsync(Supplier)] for later use.
@NullMarked
public interface LunaCurrency extends CurrencyPrecision, Keyed {
  /// The key of this currency. It has to be unique and is used
  /// to differentiate different currencies.
  ///
  /// @return the key of this currency
  Key key();

  /// A utility method for retrieving a [UUID] from a [String], assuming the name
  /// is present in the database.
  @Nullable UUID uuidForName(String name);

  //<editor-fold desc="A bunch of numerical getters">

  /// A getter for a user's balance.
  ///
  /// This variant returns an `int`. Any overflowing values may be clamped.
  /// Decimal values are always rounded down.
  ///
  /// @param uuid UUID of the player to check
  /// @return balance of the provided user
  int intValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns an `int`. Any overflowing values may be clamped.
  /// Decimal values are always rounded down.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to check
  /// @return balance of the provided user
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
  /// @param uuid UUID of the player to check
  /// @return balance of the provided user
  long longValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a `long`. Any overflowing values may be clamped.
  /// Decimal values are always rounded down.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to check
  /// @return balance of the provided user
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
  /// @param uuid UUID of the player to check
  /// @return balance of the provided user
  float floatValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a `float`.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to check
  /// @return balance of the provided user
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
  /// @param uuid UUID of the player to check
  /// @return balance of the provided user
  double doubleValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a `double`.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to check
  /// @return balance of the provided user
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
  /// @param uuid UUID of the player to check
  /// @return balance of the provided user
  BigInteger bigIntValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a [BigInteger]. Decimal values are always rounded down.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to check
  /// @return balance of the provided user
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
  /// @param uuid UUID of the player to check
  /// @return balance of the provided user
  BigDecimal bigDecimalValue(UUID uuid);

  /// A getter for a user's balance.
  ///
  /// This variant returns a [BigDecimal].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to check
  /// @return balance of the provided user
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

  /// A setter for a user's balance.
  ///
  /// This variant uses a `int` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// @param uuid UUID of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  int intValue(UUID uuid, int value);

  /// A setter for a user's balance.
  ///
  /// This variant uses a `int` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If not matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  default int intValue(final String name, final int value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.intValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// A setter for a user's balance.
  ///
  /// This variant uses a `long` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// @param uuid UUID of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  long longValue(UUID uuid, long value);

  /// A setter for a user's balance.
  ///
  /// This variant uses a `long` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If not matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  default long longValue(final String name, final long value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.longValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// A setter for a user's balance.
  ///
  /// This variant uses a `float` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// @param uuid UUID of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  float floatValue(UUID uuid, float value);

  /// A setter for a user's balance.
  ///
  /// This variant uses a `float` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If not matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  default float floatValue(final String name, final float value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.floatValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// A setter for a user's balance.
  ///
  /// This variant uses a `double` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// @param uuid UUID of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  double doubleValue(UUID uuid, double value);

  /// A setter for a user's balance.
  ///
  /// This variant uses a `double` both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If not matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  default double doubleValue(final String name, final double value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.doubleValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// A setter for a user's balance.
  ///
  /// This variant uses a [BigInteger] both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// @param uuid UUID of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  BigInteger bigIntValue(UUID uuid, BigInteger value);

  /// A setter for a user's balance.
  ///
  /// This variant uses a [BigInteger] both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If not matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  default BigInteger bigIntValue(final String name, final BigInteger value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.bigIntValue(uuid, value);
    } else {
      return BigInteger.ZERO;
    }
  }

  /// A setter for a user's balance.
  ///
  /// This variant uses a [BigDecimal] both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// @param uuid UUID of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
  BigDecimal bigDecimalValue(UUID uuid, BigDecimal value);

  /// A setter for a user's balance.
  ///
  /// This variant uses a [BigDecimal] both as an input parameter and
  /// return value. This method returns the value that was set *before*
  /// this operation has been run.
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If not matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value new value of the player's balance
  /// @return old value of the player's balance
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

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `addIntValue` type. The method follows the same rules as
  /// its setter method variant [#addIntValue(UUID,int)].
  ///
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  int addIntValue(UUID uuid, int value);

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `int` type. The method follows the same rules as
  /// its setter method variant [#addIntValue(String,int)].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value value to add (or subtract if negative to the player's balance
  /// @return old value of the player's balance
  default int addIntValue(final String name, final int value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addIntValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `addLongValue` type. The method follows the same rules as
  /// its setter method variant [#addLongValue(UUID,long)].
  ///
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  long addLongValue(UUID uuid, long value);

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `long` type. The method follows the same rules as
  /// its setter method variant [#addLongValue(String,long)].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  default long addLongValue(final String name, final long value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addLongValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `addFloatValue` type. The method follows the same rules as
  /// its setter method variant [#addFloatValue(UUID,float)].
  ///
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  float addFloatValue(UUID uuid, float value);

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `float` type. The method follows the same rules as
  /// its setter method variant [#addFloatValue(String,float)].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  default float addFloatValue(final String name, final float value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addFloatValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `double` type. The method follows the same rules as
  /// its setter method variant [#addDoubleValue(UUID,double)].
  ///
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  double addDoubleValue(UUID uuid, double value);

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a `double` type. The method follows the same rules as
  /// its setter method variant [#addDoubleValue(String,double)].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  default double addDoubleValue(final String name, final double value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addDoubleValue(uuid, value);
    } else {
      return 0;
    }
  }

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a [BigInteger] type. The method follows the same rules as
  /// its setter method variant [#addBigIntValue(UUID,BigInteger)].
  ///
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  BigInteger addBigIntValue(UUID uuid, BigInteger value);

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a [BigInteger] type. The method follows the same rules as
  /// its setter method variant [#addBigIntValue(String,BigInteger)].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  default BigInteger addBigIntValue(final String name, final BigInteger value) {
    final UUID uuid = this.uuidForName(name);
    if (uuid != null) {
      return this.addBigIntValue(uuid, value);
    } else {
      return BigInteger.ZERO;
    }
  }

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a [BigDecimal] type. The method follows the same rules as
  /// its setter method variant [#addBigDecimalValue(UUID,BigDecimal)].
  ///
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
  BigDecimal addBigDecimalValue(UUID uuid, BigDecimal value);

  /// Add (or subtract, if given a negative amount) from a user's balance.
  ///
  /// This variant uses a [BigDecimal] type. The method follows the same rules as
  /// its setter method variant [#addBigDecimalValue(UUID,BigDecimal)].
  ///
  /// This special method tries to resolve the provided username using the [#uuidForName(String)]
  /// method. If no matching [UUID] is found, this method returns `0`.
  ///
  /// @param name name of the player to use
  /// @param value value to add (or subtract if negative) to the player's balance
  /// @return old value of the player's balance
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
