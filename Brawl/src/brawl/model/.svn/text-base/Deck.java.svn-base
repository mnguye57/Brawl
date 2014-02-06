package brawl.model;

import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A Deck class represents a collection of all the cards owned by a player.
 * A Deck can be reset and shuffled.
 * 
 * @author FBRD-Minh Nguyen
 * @version 1.0
 */
public class Deck
{
    private final int numFreezes = 3;
    /**
     * Contains the cards that have been drawn into the discard pile but not
     * yet played
     */
    private Stack<Card> discard;
    /**
     * Contains the remaining cards on the deck, that the player has yet to
     * draw
     */
    private Stack<Card> remaining;
    /**
     * Holds all cards in the deck. Do not modify.
     */
    private Stack<Card> masterDeck;
    /**
     * Reference to the character whose cards are in this deck
     */
    private BrawlCharacter character;

    /**
     * Construct a Deck using the data from a given character.
     *
     * @param character the character to create the deck from
     */
    public Deck(BrawlCharacter character)
    {
        // SET this.character to character
        this.character = character;
        shuffle();
    }

    /**
     * Access the character of the deck.
     * @return the character of the deck
     */
    public brawl.model.enums.BrawlCharacter getCharacter()
    {
        // RETURN character
        return character;
    }

    /**
     * Reset the deck to it's original state.
     *
     * @pre the deck may have cards moved between its remaining and discard or
     * removed (to be played on Columns)
     * @post the deck is restored to its original stack of cards
     */
    public void reset()
    {
        // CALL remaining.addAll with discard
        remaining.clear();
        remaining.addAll(masterDeck);
        // CALL discard.clear
        discard.clear();
    }

    /**
     * Shuffle the cards in the deck using the predefined Collections.shuffle
     * method.
     *
     * @pre the remaining stack contains cards
     * @post the cards in the remaining stack have been randomly rearranged
     */
    public void shuffle()
    {
        this.remaining = new Stack<Card>();
        this.discard = new Stack<Card>();

        // PUSH freeze cards onto the bottom of the stack
        for (int ndx = 0; ndx < numFreezes; ndx++)
        {
            this.remaining.push(new Card(Color.COLORLESS, CardType.FREEZE));
        }

        // Add the rest of the cards from the deck
        this.remaining.addAll(getCardsFromJSON());

        // Pull a Base card out of the deck
        for (int ndx = 0; ndx < remaining.size(); ndx++)
        {
            if (remaining.get(ndx).getType() == CardType.BASE)
            {
                remaining.remove(ndx);
                ndx = remaining.size();
            }
        }
        // Put the base card on top of the deck
        this.remaining.push(new Card(Color.COLORLESS, CardType.BASE));
        // Create master deck
        masterDeck = new Stack<Card>();
        masterDeck.addAll(remaining);
    }

    /**
     * Peeks at the top card on the discard pile
     *
     * @return a reference to top card on the discard pile
     */
    public Card peekDiscard()
    {
        // RETURN CALL discard.peek
        if (discard.isEmpty())
        {
            return null;
        }

        return discard.peek();
    }

    /**
     * Removes and returns the top card from the discard pile
     * Assumes that peekDiscard() is called before calling this method
     * @return top card from the discard pile
     */
    public Card popDiscard()
    {
        // RETURN CALL discard.pop
        if (discard.isEmpty())
        {
            return null;
        }
        return discard.pop();

    }

    /**
     * Places top card of remaining on top of discard stack
     * @pre the remaining stack contains cards
     * @post the top card of remaining is on top of discard
     */
    public void draw()
    {
        //CALL push of discard with CALL pop of remaining
        if (!remaining.isEmpty())
        {
            discard.push(remaining.pop());
        }
    }

    /**
     * Returns the remaining cards in the deck
     *
     * @return this Deck
     */
    public Stack<Card> getDeck()
    {
        return this.remaining;
    }

    /**
     * Returns if there are any remaining cards to draw.
     *
     * @return true if there are more cards to draw
     */
    public boolean isEmpty()
    {
        return this.remaining.isEmpty();
    }

    private String readDecksJSON()
    {
        String text = "";
            /* Scan in the whole file as a String */
        text = new Scanner(getClass().getClassLoader()
            .getResourceAsStream("resource/decks.json"))
            .useDelimiter("\\A").next();
        return text;
    }

    private ArrayList<Card> getCardsFromJSON()
    {
        /* Read entire json into a string */
        String decksAsString = readDecksJSON();
        ArrayList<Card> cardsList = new ArrayList<Card>();

        JSONParser parser = new JSONParser();

        /* Get the root JSON object */
        Object obj = null;
        try
        {
            obj = parser.parse(decksAsString);
        }
        catch (ParseException e)
        {
            System.out.println("Parsing error.  Check that JSON file has correct"
                + "syntax.");
        }

        /* Pull the desired character out of the map */
        Map<String, JSONArray> decksMap = (Map<String, JSONArray>) obj;
        JSONArray deckArray = decksMap.get(character.getJsonKey());

        /* Loop through the contents of the array and add cards */
        for (Object cardDefinition : deckArray)
        {
            Map cardAttribs = (Map)cardDefinition;
            String type = (String) cardAttribs.get("card");
            long count = (Long) cardAttribs.get("count");
            String color = (String) cardAttribs.get("color");

            if (color == null)
            {
                color = "colorless";
            }
   
            for (int ndx = 0; ndx < count; ndx++)
            {
                cardsList.add(new Card(Color.valueOf(color.toUpperCase()),
                    CardType.valueOf(type.toUpperCase())));
            }
        }

        Collections.shuffle(cardsList);
        return cardsList;
    }

    /**
     * Creates a deck from the string parameter
     * @param cardString A string from which to generate a deck
     */
    public void generateDeck(String cardString)
    {
        remaining.clear();
        discard.clear();
        String[] cards = cardString.split(" ");
        for (String card : cards)
        {
            remaining.push(Card.parseCard(card));
        }
    }
}