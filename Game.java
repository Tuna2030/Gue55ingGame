import java.util.Scanner;

/**
 * This is a hidden number finding game and player compete against computer.
 * Game has 4 rounds and highest score achiving player will win.
 * If both players can not find the hidden number, they will get points according to their last guess proximity.
 *
 * @author Tuna Rezaiazar
 * @version 28.03.2018
 */
public class Game
{
    //Player
    private Player player1;
    //Computer
    private Player player2;
    //Hidden generated number for each game.
    private int gameNumber;
    //Maximum limit for the entered number.
    private int max;
    //Minimum limit for the entered number.
    private int min;
    //Random number generator object
    private RandomNumber rm;
    //Scanner object to get input from the user.
    Scanner scan;

    /**
     * Constructor to create the game object.
     */
    public Game()
    {
        this.max = 100;
        this.min = 1;
        this.rm = new RandomNumber();
        scan = new Scanner(System.in);
    }

    /**
     * Method to check if the player's guessed number is lower, higher, equal or out of range.
     * Returns 1 if the guessed number is lower than hidden number.
     * Returns 2 if the guessed number is higher than hidden number.
     * Returns 3 if the guessed number is equal to hidden number.
     * Returns 4 if the guessed number is out of the range.
     */
    public int comparison(Player p)
    {   
        if (p.getGuess() == gameNumber) return 3;
        if (p.getGuess() < gameNumber && p.getGuess() >= min) 
        { 
            min = p.getGuess() + 1;
            return 1;
        }
        if (p.getGuess() > gameNumber && p.getGuess() <= max) 
        { 
            max = p.getGuess() - 1; 
            return 2;
        }
        if (p.getGuess() < gameNumber && p.getGuess() < min || p.getGuess() > gameNumber && p.getGuess() > max ) return 4;
        return 0;
    }

    /**
     * Method to set the computer guess number between correct range.
     */
    public void computerEnterNumber()
    {
        if (rm.getRandom(1, 20) != 20)
        {
            player2.setGuess(rm.getRandom(min, max));
            System.out.println("Computer guessed: " + player2.getGuess());
        }
        else 
        {
            player2.setGuess(999);
            System.out.println("Computer abandons this round!\n");
        }
    }

    /**
     * Method to show message to console about the guessed number status according to hidden number.
     */
    public void displayAttempt(int x)
    {
        switch(x)
        {
            case 1: System.out.println("Hidden number is higher!\n"); break;
            case 2: System.out.println("Hidden number is lower!\n"); break;
            case 3: System.out.println("Hidden number is found!"); break;
            default: System.out.println("Entered number is not in the correct range!\n"); break;
        }
    }

    /**
     * Method to display end game scores and the player who win.
     */
    public void displayResult()
    {
        if (gameNumber == player1.getGuess() || gameNumber == player2.getGuess());
        else System.out.println("Hidden number was: " + gameNumber);
        System.out.println("\n*** Result of the Game ***");
        System.out.println(player1.getName() + " Score: " + player1.getScore());
        System.out.println("Computer Score:" + player2.getScore());
        if (player1.getScore() < player2.getScore()) System.out.println("\nComputer Wins!");
        if (player1.getScore() > player2.getScore()) System.out.println("\n" + player1.getName() + " Wins!");
        if (player1.getScore() == player2.getScore()) System.out.println("\nIt is a tie :(");
    }

    /**
     * Method to display cumulative round end score for both players.
     */
    public void displayRoundEnd()
    {
        if (gameNumber == player1.getGuess() || gameNumber == player2.getGuess());
        else System.out.println("Hidden number was: " + gameNumber);
        System.out.println("\n*** Result of the Round ***");
        System.out.println(player1.getName() + " Score: " + player1.getScore());
        System.out.println("Computer Score:" + player2.getScore());
    }

    /**
     * Method to get guess number input from the player.
     */
    public void playerEnterNumber()
    {
        try
        {
            System.out.println("Please enter the guessed number(999 to abandon the round!):");
            int guess = Integer.parseInt((scan.nextLine()).trim());
            if (guess == 999)
            {
                player1.setGuess(guess);
            }
            else
            {
                if (guess <= 100 && guess >= 1) player1.setGuess(guess);              
                else 
                {
                    System.out.println("Wrong input! You need to enter between 1-100!\n");
                    playerEnterNumber();
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Wrong input! You need to enter number!\n");
            playerEnterNumber();
        }
    }

    /**
     * Method to calculate the points after each round.
     */
    public void score(int numberofturns, Player p)
    {
        switch (numberofturns)
        {
            case 1: p.setScore(p.getScore() + 20); break;
            case 2: p.setScore(p.getScore() + 15); break;
            case 3: p.setScore(p.getScore() + 11); break;
            case 4: p.setScore(p.getScore() + 8); break;
            case 5: p.setScore(p.getScore() + 6); break;
            case 6: p.setScore(p.getScore() + 5); break;
            default:
            {
                tieScore(player1);
                tieScore(player2);
                break;
            }
        }
    }

    /**
     * Method to get name input from the player.
     */
    public void setPlayerName()
    {
        String name;
        System.out.println("Please enter your less than 8 characters nickname:\n");
        name = scan.nextLine();
        String trimtext = "";
        for (int i = 0; i<name.length(); i++) 
        {
            if (name.charAt(i) != ' ') trimtext = trimtext + name.charAt(i);
        }
        name = trimtext;
        if (name.length() <= 8)
        {
            player1 = new Player(name);
        }
        else
        {
            System.out.println("\nYour name must be less than 8 characters!\n");
            setPlayerName();
        }
    }

    /**
     * Method to start the game.
     */
    public void startGame()
    {
        while (true)
        {
            welcome();
            setPlayerName();
            player2 = new Player("Computer");
            for (int round = 1; round<5; round++)
            {
                System.out.println("\nRound " + round + "!");
                gameNumber = rm.getRandom(1, 100);
                System.out.println("\nRandom Number between 1-100 is generated!");
                System.out.println("Rolling dice for who to start!");

                if (rm.getRandom(1, 2) == 1) startRound(player1, player2);
                else startRound(player2, player1);

                if (round != 4) displayRoundEnd();
                max = 100; 
                min = 1;
            }
            displayResult();
            System.out.println("\nTo exit from the game please enter N/n!");
            String end = scan.nextLine().trim().toLowerCase();
            if (end.equals("n")) break;
        }
    }

    /**
     * Main method for each round.
     */
    public void startRound(Player p1, Player p2)
    {
        System.out.println(p1.getName() + " starts guessing!\n");
        int attempt = 1;
        for ( ; attempt < 7; attempt++)
        {
            if (attempt == 1 || attempt == 3 || attempt == 5)
            {
                System.out.println(attempt + ". Attempt to find the hidden number! (Range: " + min + " to " + max + ")");
                if (p1.getName() == "Computer") computerEnterNumber();
                else playerEnterNumber();
                if (p1.getGuess() == 999) break;
                displayAttempt(comparison(p1));
                if (comparison(p1) == 3)
                {
                    score(attempt, p1);
                    break;
                }
            }
            else
            {
                System.out.println(attempt + ". Attempt to find the hidden number! (Range: " + min + " to " + max + ")");
                if (p2.getName() == "Computer") computerEnterNumber();
                else playerEnterNumber();
                if (p2.getGuess() == 999) break;
                displayAttempt(comparison(p2));
                if (comparison(p2) == 3)
                {
                    score(attempt, p2);
                    break;
                }
            }
        }
        if (attempt == 7) score(attempt, p1);
    }

    /**
     * Method to calculate proximity points in case of hidden number is not found at the end of the round.
     */
    public void tieScore(Player p)
    {
        int differ = 0;
        if (p.getGuess() < gameNumber) differ = gameNumber - p.getGuess();
        else differ = p.getGuess() - gameNumber;
        differ = 10 - differ;
        if (differ >= 0 && differ < 10) p.setScore(p.getScore() + differ);
    }

    /**
     * Method to print game welcome message.
     */
    public void welcome()
    {
        System.out.println("\nWelcome to Gue55ing Game!\n");
    }
}