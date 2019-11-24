/**
 * This class is for creating players for the game class.
 * 
 * @author Tuna Rezaiazar
 * @version 28.03.2018
 */
public class Player
{
    //Name of the player
    private String name;
    //Score of the player
    private int score;
    //Last guessed number
    private int guess;
    /**
     * Constructor to create a new player object.
     */
    public Player(String name)
    {
        this.name = name;
        this.score = 0;
        this.guess = 0;
    }

    /**
     * Accessor method to player guess field.
     */
    public int getGuess()
    {
        return this.guess;
    }

    /**
     * Accessor method to player name field.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Accessor method to player score field.
     */
    public int getScore()
    {
        return this.score;
    }

    /**
     * Mutator method to set player guess field.
     */
    public void setGuess(int guess)
    { 
        this.guess = guess;
    }

    /**
     * Mutator method to set player name field.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Mutator method to set player score field.
     */
    public void setScore(int score)
    { 
        this.score = score;
    }
}
