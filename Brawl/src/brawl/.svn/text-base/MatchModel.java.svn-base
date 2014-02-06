package brawl;

import brawl.model.AI;
import brawl.model.Base;
import brawl.model.Card;
import brawl.model.Column;
import brawl.model.Move;
import brawl.model.Player;
import brawl.model.enums.CardType;
import brawl.model.enums.PlayerID;
import brawl.model.enums.GameMode;
import brawl.utils.KeyMapper;
import brawl.utils.MoveValidator;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The MatchModel class represents the current state that
 * the current game is in. This includes both players as well as the columns
 * and the current turn.
 *
 * @author FBRD-Stephen Hill
 * @version 1.0
 */
public class MatchModel extends Observable
{
    /**
     * Reference to player one, which may be a human or an AI
     */
    private final Player p1;
    /**
     * Reference to player two, which may be a human or an AI
     */
    private final Player p2;

    /**
     * Models the 1-3 Columns on the game board
     */
    private ArrayList<Column> columns;
    /**
     * Tracks which Player is currently allowed to move next
     */
    private PlayerID turn;
    /**
     * Tracks whether or not the player whose turn it is has drawn yet
     */
    private boolean hasDrawn;
    
    /**
     * Flag that represents whether or not the match is turn-based
     */
    private GameMode mode;

    private static final int PLAYCLEAR = -2;
    private static final int TIMER1 = 6000;
    private static final int TIMER2 = 6500;

    private Timer timer1;
    private Timer timer2;

    /**
     * Constructs a new GameModel
     * @param p1 Player 1
     * @param p2 Player 2
     * @param gm GameMode enum
     */
    public MatchModel(final Player p1, final Player p2, GameMode gm)
    {
        //SET p1 to new Player
        this.p1 = p1;
        this.p2 = p2;
        //SET p2 to new Player
        //SET columns to new ArrayList
        columns = new ArrayList<Column>();
        //ADD new Column with new Base with 0

        p1.getDeck().getDeck().pop();
        Base base = new Base(PlayerID.PLAYER1);
        columns.add(new Column(base));

        //ADD new Column with new Base with 1
        p2.getDeck().getDeck().pop();
        base = new Base(PlayerID.PLAYER2);
        columns.add(new Column(base));

        setTimers(TIMER1, TIMER2);

        //SET turn to -1 /*How do we know if it's tournament mode or not*/
        if (gm == GameMode.TOURNAMENT)
        {
            turn = null;
        }
        else
        {
            turn = PlayerID.PLAYER1;
        }
        hasDrawn = false;
        mode = gm;
    }

    /**
     * Resets AI timers to a given delay (for use in pausing and unpausing)
     * @param delay time delay in milliseconds
     */
    public void resetTimers(long delay)
    {
        setTimers(delay, delay);
    }

    /**
     * Sets up the timer for the first AI player
     * @param delay a value for the amount to delay the AI's timer
     * @param player the player whose timer is being set up
     * @return a timer object for the AI
     */
    public Timer setUpAI(long delay, final PlayerID player)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    boolean canDraw = (mode == GameMode.TOURNAMENT || !hasDrawn);
                    Move move = getPlayer(player).getNextMove(columns, canDraw);
                    if (move.isDraw())
                    {
                        drawCard(move.getSource());
                    }
                    else if (move.getColumn() == KeyMapper.PASS_COLUMN)
                    {
                        pass(player);
                    }
                    else
                    {
                        playDiscard(move.getSource(), move.getSide(),
                                move.getColumn());
                    }
                }
            }, delay, ((AI) getPlayer(player)).getDifficulty());

        return timer;
    }

    /**
     * Cancels (both) AI timer(s)
     */
    public void pauseAI()
    {
        if (p1.getClass() == AI.class)
        {
            timer1.cancel();
        }
        if (p2.getClass() == AI.class)
        {
            timer2.cancel();
        }
    }

    /**
     * Plays a Card from a discard pile onto a Column
     *
     * @param player reference to which Player's discard is being played from
     * @param side which side of the Column the Card is played on.
     * @param column the index representing the Column the Card is played to.
     * @pre at least one card exists on the discard pile of the given side
     * @post the card is removed from the player's discard pile and added to
     * the column on the given side
     */
    public void playDiscard(PlayerID player, PlayerID side, int column)
    {
        Card tempcard = null;
        if (mode == GameMode.TOURNAMENT || turn == player)
        {
            Player playerObj = getPlayer(player);
            tempcard = playerObj.getDeck().peekDiscard();
        }

        if (tempcard != null)
        {
            if (tempcard.getType() == CardType.BASE)
            {
                playBase(player, side, column);
            }
            else if (tempcard.getType() == CardType.CLEAR)
            {
                playClear(player, side, column);
            }
            else if (tempcard.getType() == CardType.FREEZE)
            {
                if (playFreeze(player, side, column))
                {
                    switchTurns();
                }
            }
            else if (validateMove(player, side, column, tempcard))
            {
                switchTurns();
            }
        }
    }

    private void setTimers(long delay1, long delay2)
    {
        if (p1.getClass() == AI.class)
        {
            timer1 = setUpAI(delay1, PlayerID.PLAYER1);
        }

        if (p2.getClass() == AI.class)
        {
            timer2 = setUpAI(delay2, PlayerID.PLAYER2);
        }
    }

    /**
     * Validates move
     *
     * @param player reference to which Player's discard is being played from
     * @param side which side of the Column the Card is played on.
     * @param column the index representing the Column the Card is played to.
     * @param tempcard Card to be played
     * @return true if the move was successful
     */
    private boolean validateMove(PlayerID player, PlayerID side,
            int column, Card tempcard)
    {
        int colNdx = getColumnNdx(column);
        // write a function that returns the column index

        if(tempcard.getType() == CardType.PRESS)
        {
            tempcard = playPress(side, colNdx, tempcard);
        }

        if (colNdx >= 0 && !columns.get(colNdx).isFrozen() &&
            MoveValidator.isValid(tempcard, columns.get(colNdx).peekTopCard(side)))
        {
            columns.get(colNdx).addCard(tempcard, side);
            this.getPlayer(player).getDeck().popDiscard();
             // CALL setChanged() and notifyObservers()
            setChanged();
            notifyObservers(new Move(player, side, colNdx));
            return true;
        }
        return false;
    }

    private int getColumnNdx(int column)
    {
        switch(columns.size())
        {
            case 1:
                if (column == 1)
                {
                    return 0;
                }
                else if (column == 0 || column == 2)
                {
                    return -1;
                }
                break;
            case 2:
                if (column == 2)
                {
                    return 1;
                }
                else if (column == 1)
                {
                    return -1;
                }
                break;
            default:
                break;
        }
        return column;
    }

    /**
     * Plays a Press Card from the discard pile
     *
     * @param side Which side of the Column the Card is played on
     * @param colNdx Which column the Card is played on
     * @param tempcard The card to be played
     */
    private Card playPress(PlayerID side, int colNdx, Card tempcard)
    {
        if(side == PlayerID.PLAYER1 && !columns.get(colNdx).getP1Stack().isEmpty())
        {
            tempcard = new Card(columns.get(colNdx).getP1Stack().peek().getColor(),
                    CardType.PRESS);
        }
        else if(!columns.get(colNdx).getP2Stack().isEmpty())
        {
            tempcard = new Card(columns.get(colNdx).getP2Stack().peek().getColor(),
                    CardType.PRESS);
        }
        return tempcard;
    }

    /**
     * Plays a Freeze from a discard pile onto a Column
     *
     * @param player reference to which Player's discard is being played from
     * @param side which side of the Column the Freeze is played on.
     * @param column the index representing the Column the Freeze is played to.
     *
     * @return whether or not a freeze was successfully played
     */
    private boolean playFreeze(PlayerID player, PlayerID side, int column)
    {
        int colToFreeze = -1;
        switch(columns.size())
        {
            case 1:
                if (column != 1)
                {
                    break;
                }
                colToFreeze = 0;
                break;
            case 2:
                if (column == 1)
                {
                    break;
                }
                colToFreeze = column;
                if (column == 2)
                {
                    colToFreeze = 1;
                }
                break;
            default:
                colToFreeze = column;
                break;
        }
        if (colToFreeze >= 0 && !columns.get(colToFreeze).isFrozen())
        {
            columns.get(colToFreeze).freeze();
            getPlayer(player).getDeck().popDiscard();
            setChanged();
            notifyObservers(new Move(player, side, colToFreeze));
            return true;            
        }
        return false;
    }

    /**
     * Plays a Base from a discard pile onto a Column
     *
     * @param player reference to which Player's discard is being played from
     * @param side which side of the Column the Base is played on.
     * @param column the index representing the Column the Base is played to.
     */
    private void playBase(PlayerID player, PlayerID side, int column)
    {
        // Check based on size of columns
        int colNdx = -1;
        switch(columns.size())
        {
            case 1:
                //Play Right side
                if(column == 2)
                {
                    addColumn(player, 1);
                    colNdx = 1;
                    this.getPlayer(player).getDeck().popDiscard();
                    switchTurns();
                }
                //Play Left side
                else if(column == 0)
                {
                    addColumn(player, column);
                    colNdx = 0;
                    this.getPlayer(player).getDeck().popDiscard();
                    switchTurns();
                }
                break;
            case 2:
                if (column != 1)
                {
                    addColumn(player, column);
                    colNdx = column;
                    this.getPlayer(player).getDeck().popDiscard();
                }
                break;
            default:
                break;
        }
        if (colNdx != -1)
        {
            setChanged();
            notifyObservers(new Move(player, side, colNdx));
            switchTurns();
        }
    }

    /**
     * Plays a Clear from a discard pile onto a Column
     *
     * @param player reference to which Player's discard is being played from
     * @param side which side of the Column the Clear is played on.
     * @param column the index representing the Column the Clear is played to.
     */
    private void playClear(PlayerID player, PlayerID side, int column)
    {
        switch(columns.size())
        {
            case 1:
                break;
            case 2:
                if(column == 2 && !columns.get(column-1).isFrozen())
                {
                    clearColumn(column-1);
                    this.getPlayer(player).getDeck().popDiscard();
                    switchTurns();
                    setChanged();
                    notifyObservers(new Move(player, side, PLAYCLEAR));
                }
                else if(column == 0 && !columns.get(column).isFrozen())
                {
                    clearColumn(column);
                    this.getPlayer(player).getDeck().popDiscard();
                    switchTurns();
                    setChanged();
                    notifyObservers(new Move(player, side, PLAYCLEAR));
                }
                break;
            default:
                if(column != 1 && !columns.get(column).isFrozen())
                {
                    clearColumn(column);
                    this.getPlayer(player).getDeck().popDiscard();
                    switchTurns();
                    setChanged();
                    notifyObservers(new Move(player, side, PLAYCLEAR));
                }
                break;
        }
    }

    /**
     * Determines whether or not the game is over
     *
     * @return whether or not all bases are frozen
     */
    public boolean isGameOver()
    {
        // FOR EACH column in columns
        for (Column col : columns)
        {
            // IF CALL isFrozen on column is false
            if (!col.isFrozen())
            {
                // RETURN false
                return false;
            }
            // END IF
        }
        // END LOOP
        // End the AI timers
        pauseAI();
        // RETURN true
        return true;
    }

    /**
     * Draws a Card from a Deck and places it on top of the discard pile
     *
     * @param player which Player's Deck the Card is drawn from.
     * @pre there is at least one card in the remaining stack of the given
     * Player's Deck
     * @post the Card is popped off of the remaining and pushed to the top
     * of the discard for the given Player's Deck
     */
    public void drawCard(PlayerID player)
    {
        Player playerObj = getPlayer(player);
        //SET PUSH from discard of player to POP from remaining of player
        if (mode == GameMode.TOURNAMENT || (player == turn && !hasDrawn))
        {
            if(playerObj.getDeck().getDeck().size() != 0)
            {
                playerObj.getDeck().draw();
                hasDrawn = true;
                setChanged();
                notifyObservers(new Move(player, null, -1));
            }
        }
    }

    /**
     * Adds a new column with the given inputs
     *
     * @param owner the Player whose base is being played
     * @param index the index at which it will be placed
     */
    void addColumn(PlayerID owner, int index)
    {
        // CALL add on columns at index with CREATE new Column with owner
        Base base = new Base(owner);
        columns.add(index, new Column(base));
    }

    /**
     * Removes a Column and its associated base at the given columnIndex
     *
     * @param column the index representing the column the Card is played to.
     * @pre columns contains at least one instance of Column
     * @post columns contains one fewer instance of Column
     */
    void clearColumn(int column)
    {
        // CALL remove on columns with index of column
        columns.remove(column);
    }

    /**
     * Returns the turn as a PlayerID, representing which Player
     *
     * @return the current turn.
     */
    public PlayerID getTurn()
    {
        //RETURN turn
        return turn;
    }

    /**
     * Returns who the winner is of the game is as an int corresponding to
     * player 1 or 2
     *
     * @return the winner of the game.
     */
    public PlayerID getWinner()
    {
        int tempP1 = 0;
        int tempP2 = 0;
        //IF SUM of winners of columns for p1 > SUM of winners of columns for p2
        for(Column col: columns)
        {
            if(col.getCurrentWinner() == PlayerID.PLAYER1)
            {
                tempP1++;
            }
            else
            {
                tempP2++;
            }
        }
        if(tempP1 > tempP2)
        {
            //  RETURN PLAYER1
            return PlayerID.PLAYER1;
        }
        //ELSE IF SUM of winners of columns for p1 equals SUM of winners
        else if(tempP1 < tempP2)
        {
        //  RETURN PLAYER2
            return PlayerID.PLAYER2;
        }
        //ELSE
        //  RETURN null
        return null;
    }

    /**
     * Allows a player to pass, changing the turn to his or her opponent
     *
     * @param player the player who wants to pass
     */
    public void pass(PlayerID player)
    {
        if (player == turn)
        {
            switchTurns();
        }
    }

    /**
     * Sets the turn to the opposite player
     */
    void switchTurns()
    {
        if((mode == GameMode.TRAINING) || (mode == GameMode.TUTORIAL))
        {
            turn = turn.getOther();
            hasDrawn = false;
        }

        setChanged();
        notifyObservers(turn);
    }

    /**
     * Returns the move that corresponds with a given keyboard code
     *
     * @param keyCode represents the keyboard button that was pressed
     * @return move contains parameters for the move corresponding to the
     * keycode
     */
    public Move getMove(int keyCode)
    {
        // SET move to CALL getMove with keyCode on p1
        Move p1move = p1.getMove(keyCode);
        // SET move to CALL getMove with keyCode on p2
        Move p2move = p2.getMove(keyCode);
        // RETURN move
        if(p1move != null)
        {
            return p1move;
        }
        return p2move;
    }

    /**
     * Accessor for the columns.
     *
     * @return columns
     */
    public ArrayList<Column> getColumns()
    {
        return columns;
    }

    /**
     * Accessor for the players.
     *
     * @param player the player to access.
     * @return the player with the matching PlayerID
     */
    public Player getPlayer(PlayerID player)
    {
        if (player == PlayerID.PLAYER1)
        {
            return p1;
        }
        return p2;
    }

    /**
     * Getter function for mode
     * @return mode
     */
    public GameMode getGameMode()
    {
        return mode;
    }
}