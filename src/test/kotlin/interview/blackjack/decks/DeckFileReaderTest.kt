package interview.blackjack.decks

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class DeckFileReaderTest : StringSpec({

    "should open file and split card symbols" {
        val testDeckFilePath = ClassLoader.getSystemClassLoader().getResource("test_deck.txt")!!.path

        readDeckDefinitionFromFile(testDeckFilePath) shouldBe listOf("CA", "D5", "H9", "HQ", "S8")
    }

})
