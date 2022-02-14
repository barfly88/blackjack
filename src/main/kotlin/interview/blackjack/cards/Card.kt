package interview.blackjack.cards

data class Card(
    val suit: CardSuit,
    val value: CardValue
)

enum class CardSuit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

enum class CardValue(val score: Int) {
    V2(2), V3(3), V4(4), V5(5), V6(6), V7(7), V8(8), V9(9), V10(10), JACK(10), QUEEN(10), KING(10), ACE(11)
}

fun getAllCards() = CardSuit.values().flatMap { suit -> CardValue.values().map { value -> Card(suit, value) } }
