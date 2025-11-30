rootProject.name = "LunaEconomy"

sequenceOf("api", "plugin").forEach {
  include(it)
}
