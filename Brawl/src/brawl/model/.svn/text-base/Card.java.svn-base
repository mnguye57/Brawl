package brawl.model;

import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * The Card class represents a component of a deck which a player plays on the
 * board.
 * @author FBRD-Minh Nguyen
 * @version 1.0
 */
public class Card
{
    private static final String RESOURCE_DIR = "resource/";
    /**
     * The color of a card instance.
     */
    private Color color;
    /**
     * The Brawl card type of a card instance.
     */
    private CardType type;

    private static final Map<Color, String> COLOR_MAP;
    private static final Map<CardType, String> TYPE_MAP;

    static
    {
        COLOR_MAP = new EnumMap(Color.class);
        COLOR_MAP.put(Color.RED, "Red");
        COLOR_MAP.put(Color.BLUE, "Blue");
        COLOR_MAP.put(Color.GREEN, "Green");
        COLOR_MAP.put(Color.COLORLESS, "");
        TYPE_MAP = new EnumMap(CardType.class);
        TYPE_MAP.put(CardType.HIT, "Hit");
        TYPE_MAP.put(CardType.HIT2, "Hit2");
        TYPE_MAP.put(CardType.BLOCK, "Block");
        TYPE_MAP.put(CardType.PRESS, "Press");
        TYPE_MAP.put(CardType.BASE, "Base");
        TYPE_MAP.put(CardType.CLEAR, "Clear");
        TYPE_MAP.put(CardType.FREEZE, "Freeze");
    }

    /**
     * Constructs a Card given a color and type.
     *
     * @param color the color assigned to the card
     * @param type the type of card (hit, block, etc.)
     */
    public Card(Color color, CardType type)
    {
        this.color = color;
        this.type = type;
    }

    /**
     * Accessor to the color of the card.
     *
     * @return the color of the card
     */
    public Color getColor()
    {
        // RETURN color
        return color;
    }

    /**
     * Accessor to the type of the card.
     *
     * @return the type of the card
     */
    public CardType getType()
    {
        // RETURN type
        return type;
    }

    /**
     * Get the pathname for the image
     *
     * @param character Character of the Base
     * @return the string pathname for the image
     */
    public String getImagePath(BrawlCharacter character)
    {
        String path = RESOURCE_DIR;
        if (type == CardType.HIT || type == CardType.HIT2 || type == CardType.BLOCK)
        {
            path += COLOR_MAP.get(this.color);
        }
        if (type == CardType.BASE)
        {
            path += character.getJsonKey().toLowerCase();
        }
        path += TYPE_MAP.get(this.type) + "Card.png";
        return path;
    }

    /**
     * Generates a card object from the given string, following the same rules
     * as the toString() method
     * 
     * @param cardStr a string representing a card
     * @return a valid card object, or null if the string did not match with a
     * valid card object
     */
    public static Card parseCard(String cardStr)
    {
        Color newColor = null;
        CardType newType = null;
        for (Color enumColor : Color.values())
        {
            if (enumColor.toString().equals(cardStr.substring(0, 1)))
            {
                newColor = enumColor;
                break;
            }
        }
        for (CardType enumType : CardType.values())
        {
            if (enumType.toString().equals(cardStr.substring(1)))
            {
                newType = enumType;
                break;
            }
        }
        if (newColor != null && newType != null)
        {
            return new Card(newColor, newType);
        }
        return null;
    }

    /**
     * Overrides the native toString() method to give succinct card names for
     * testing purposes
     * @return a three-character string unique to this card's set of values
     */
    @Override
    public String toString()
    {
        return color.toString() + type.toString();
    }

    /**
     * Overrides the default equals method in Java.
     * Compares another card to this card. Returns true if they are equal, false
     * otherwise.
     * @param obj Object to compare this Card to
     * @return True if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        Card card = (Card)obj;
        return (card.getColor().equals(color) && card.getType().equals(type));
    }
}