package interview.blackjack.decks

import interview.blackjack.cards.parseCard

fun parseDeck(cardSymbols: List<String>) = Deck(cardSymbols.map(::parseCard))
