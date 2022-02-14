package interview.blackjack.cards

fun parseCard(cardSymbol: String): Card {
    if (!"^[CDHS]([2-9]|10|J|Q|K|A)\$".toRegex().matches(cardSymbol))
        throw InvalidCardSymbolException(cardSymbol)

    return Card(
        parseSuit(cardSymbol),
        parseValue(cardSymbol)
    )
}

private fun parseValue(cardSymbol: String) = when (val v = cardSymbol.substring(1)) {
    "J" -> CardValue.JACK
    "Q" -> CardValue.QUEEN
    "K" -> CardValue.KING
    "A" -> CardValue.ACE
    "10" -> CardValue.V10
    else -> CardValue.values().first { it.score == v.toInt() }
}

private fun parseSuit(cardSymbol: String) = when (cardSymbol[0]) {
    'C' -> CardSuit.CLUBS
    'D' -> CardSuit.DIAMONDS
    'H' -> CardSuit.HEARTS
    else -> CardSuit.SPADES
}

class InvalidCardSymbolException(val invalidSymbol: String) : Exception()
