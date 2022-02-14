package interview.blackjack.decks

import java.io.File

fun readDeckDefinitionFromFile(path: String) = File(path).readText().split(",").map { it.trim() }
