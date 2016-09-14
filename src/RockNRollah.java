/*
 * RockNRollah.java
 */

import java.security.SecureRandom;
import java.util.Random;  // inferior quality class

/**
 *
 * @author Michael Floerchinger
 */
public class RockNRollah 
{
       
    public static void main(String[] args) 
    {
    	// Create a Random object
    	SecureRandom randomNumbers = new SecureRandom();
    	
    	// Initialize a couple of variables to hold the values of the two dice
    	int dice1 = 0;
		int dice2 = 0;
		int sumOfDice = 0;
		int numOfRolls = 36000000;
		
    	// Initialize the array that will hold the results
		int[] frequency = new int[13];
		
    	/* Hypothetical questions: (no, I don't want answers)
    	 *   How many different sum values do we need to account for?
    	 *   How big does the array need to be?
    	 *   Why might you make it (slightly) larger? (Note: my answer does not use an unnecessarily large array, and I'm not encouraging you to; just giving you something to think about) 
    	 */
    	
		
		// Roll the dice (a lot) and sum and tally the results
		for (int roll = 1; roll <= numOfRolls; roll++)
		{
			dice1 = 1 + randomNumbers.nextInt(6);
			dice2 = 1 + randomNumbers.nextInt(6);
			sumOfDice = dice1 + dice2;
			++frequency[sumOfDice];
		}
		
        
		// Display the frequencies
        System.out.println("Results after " + numOfRolls + " rolls");
        System.out.println("-------------------------");
        System.out.println("Value\tFrequency");
        
        
        // Loop, displaying the rows for the body of the table, each on its own line.
        //   The required format for each row is to display the sum, followed by a tab, followed by the number of times that sum came up. Do not include any additional formatting
        for (int face = 2; face < frequency.length; face++)
		{
			System.out.println(face + "\t" + frequency[face]);
			
		}
    }
    
}
