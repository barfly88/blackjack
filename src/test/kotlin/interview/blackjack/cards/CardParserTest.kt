package interview.blackjack.cards

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

internal class CardParserTest : StringSpec({

    "should throw exception on invalid card symbols" {
        setOf("X10", "D1", "d2", "D11", "DD", "1", "11", "", "D 2").forEach { invalidSymbol ->
            assertThrows<InvalidCardSymbolException> {
                parseCard(invalidSymbol)
            }.invalidSymbol shouldBe invalidSymbol
        }
    }

    "should parse correct card symbols" {
        val symbolAndExpectedCard = mapOf(
            "D2" to Card(CardSuit.DIAMONDS, CardValue.V2),
            "DA" to Card(CardSuit.DIAMONDS, CardValue.ACE),
            "CJ" to Card(CardSuit.CLUBS, CardValue.JACK),
            "SK" to Card(CardSuit.SPADES, CardValue.KING),
            "SQ" to Card(CardSuit.SPADES, CardValue.QUEEN),
            "H10" to Card(CardSuit.HEARTS, CardValue.V10)
        )

        symbolAndExpectedCard.forEach { (symbol, expected) ->
            parseCard(symbol).suit shouldBe expected.suit
            parseCard(symbol).value.score shouldBe expected.value.score
        }

    }
})