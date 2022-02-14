package interview.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.io.FileNotFoundException

internal class BlackJackGameTest : StringSpec({

    "should throw exception on wrong number of arguments" {
        assertThrows<IllegalArgumentException> {
            main(arrayOf("too", "many", "arguments"))
        }.message shouldBe "Program arguments should have 1 element (path to the deck definition file) or be empty (random deck)."
    }

    "should throw exception on non existing file" {
        assertThrows<FileNotFoundException> {
            main(arrayOf("non/existing/deck.definition"))
        }
    }

    "should complete normally without arguments" {
        assertDoesNotThrow {
            main(emptyArray())
        }
    }

    "should complete normally with existing deck definition as argument" {
        assertDoesNotThrow {
            val testDeckFilePath = ClassLoader.getSystemClassLoader().getResource("test_deck.txt")!!.path
            main(arrayOf(testDeckFilePath))
        }
    }
})