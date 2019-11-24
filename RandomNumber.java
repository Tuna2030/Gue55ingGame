/**
 * This class is for creating random number for the game class.
 * 
 * @author Tuna Rezaiazar
 * @version 28.03.2018
 */
public class RandomNumber
{
    /**
     * Constructor to create new RandomNumber object.
     */
    public RandomNumber()
    {

    }

    /**
     * Method to create and return a random number between given integer parameters.
     */
    public int getRandom(int min , int max)
    {
        int random = (int)(Math.random() * (max - min + 1) + min);
        return random;
    }  
}