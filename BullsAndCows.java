/*
 * Bulls and Cows
 *
 * --Jack LaManna--
 *
 * implements a deductive logic game for guessing a sequence of n unique digits
 */
import java.util.Scanner;

public class BullsAndCows
{
	public static final int DIGITS = 4;
	public static final int TURNS = 10;

	/*
	 * explains how the game works
	 */

	public static void printRules()
	{
		System.out.println("\nGuess the " + DIGITS + " digit number; " +
			"all digits are unique.");
		System.out.println("A cow means right digit, wrong position.");
		System.out.println("A bull means right digit, right position.");
		System.out.println("You have " + TURNS + " turns.");
		System.out.println("Enter 'q' to quit at any time.");
		System.out.println("Good luck!");

		
	}
	/*

	 * returns true if the character is contained in the string
	 */

	public static boolean contains(String s, char c)
	{
		boolean result = false;
		for (int i=0; i <s.length(); i++)
		{
			char a = s.charAt(i);
			//System.out.println(a);
			if (c ==a)
				result = true;
		}
		return result;
	}
	/*
	 * returns a string of unique randomly-chosen digits
	 */

	public static String pickNumber()
	{
		int counter = 0;
		String numString = "";
		while (numString.length() < DIGITS)
		{
			int num  = ((int) (Math.random() *10));
			String val = Integer.toString(num);
			char func = val.charAt(0);
			if (counter == 0)
				;
			else if (contains(numString, func))
				;
			else
				numString += func;
				counter ++;
			}
		return numString;
	}

	/*
	 *  compares the user's guess to the random sequence and returns the
	 *  number of bulls and cows
	 */

	public static int [] tallyResults (String number, String guess)
	{
    int results[] = {0,0};
    for (int i  = 0; i < guess.length(); i++)
    {
      if (guess.charAt(i) == number.charAt(i))
        results[0] +=1;
      else if (contains(number, guess.charAt(i)))
        results[1] +=1;
    }
    return results;
  }
	/*
	 * returns true if the string contains a duplicate character
	 */
	 public static boolean hasDuplicates (String s)
 	{
 		for (int i =0; i < s.length(); i++)
 		{
 			for (int x = i+1; x <s.length(); x++)
 			{
 				if (s.charAt(i) == s.charAt(x))
 					return true;
 			}
 		}
 		return false;
 	}

	/*
	 * returns true if the string contains a non-digit
	 */

	public static boolean hasNonDigits (String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
				if ((s.charAt(i) < '0') || (s.charAt(i) > '9'))
					return true;
		}
		return false;
	}

	/*
	 * gets the user's guess and displays the bulls and cows until game over
	 */

	public static void playGame()
	{
		System.out.println("");
		String computerChoice = pickNumber();
		//System.out.println(computerChoice);
		//System.out.println(computerChoice);
		int i = 0;
		while (i < TURNS)
		{
			//INPUT

			Scanner input = new Scanner(System.in);
			System.out.println("TURN " + (i+1) + ":");
			System.out.println("Enter your guess: ");
			String userChoice = input.next();

			//Exception Handling
			char c = (char)(0 + '0');

			if (userChoice.charAt(0) == 'q')
				break;

			if(userChoice.charAt(0) == c)
			{
				System.out.println("Do not start with 0!.");
				System.out.println("");
			}
			else if (hasNonDigits(userChoice) || hasDuplicates(userChoice) || userChoice.length() != 4)
			{
				System.out.println("4 Unique Digits Please.");
				System.out.println("");
			}

			//RESULTS
			else
			{
				int [] results = tallyResults(computerChoice, userChoice);
				System.out.println(results[0] + " bulls " + results[1] + " cows ");
				System.out.println(" ");
				if (results[0] == 4)
				{
					System.out.println("Congrats you won!");
					break;
				}
			}
		i++;
	}
	System.out.println("The number was: " + computerChoice);
}

	public static void main (String [] args)
	{
		printRules();
		playGame();
	}
}
