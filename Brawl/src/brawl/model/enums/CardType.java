package brawl.model.enums;

/**
 * The CardType class represents the type of a Card.
 *
 * @author FBRD-Paul Doyle
 * @version 1.0
 */
public enum CardType
{
    /** enumeration to represent card type of hit*/
    HIT(1, "Hi"),
    /** enumeration to represent card type of hit2*/
    HIT2(2, "H2"),
    /** enumeration to represent card type of block*/
    BLOCK(0, "Bl"),
    /** enumeration to represent card type of press*/
    PRESS(0, "Pr"),
    /** enumeration to represent card type of base*/
    BASE(0, "Ba"),
    /** enumeration to represent card type of clear*/
    CLEAR(0, "Cl"),
    /** enumeration to represent card type of freeze*/
    FREEZE(0, "Fr");

    private int value;
    private String stringRef;
    /**
     * Sets the specified Card with the correct value.
     *
     * @param value How many points the Card is worth.
     */
    private CardType(int value, String stringRef)
    {
        //SET this.value to value
        this.value = value;
        this.stringRef = stringRef;
    }

    /**
     * Accesses the value of the given Card.
     *
     * @return the value of the given Card
     */
    public int getValue()
    {
        //RETURN value
        return value;
    }

    /**
     * Creates a string representation of a CardType
     * @return A string representation of a CardType
     */
    @Override
    public String toString()
    {
        return stringRef;
    }
}
