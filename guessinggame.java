// Zacharia Kornas
// 05.04.2021
// CSE 142 Section AX
// TA: Paul George Druta
// Take-home Assessment #5: GuessingGame
//
// This program allows the user to play multiple games
// where they guess a randomly generated number between
// 0 and a specified max value
import java.util.*;

public class GuessingGame {
   public static final int MAX_VALUE = 100; // Changes the maximum value of
                                            // of the randmly generated number
   
   public static void main(String[] args) {
      // Creates an object that can read user input from console
      Scanner console = new Scanner(System.in);
      // Creates an object that generates pseudo-random numbers
      // Seeded 42 for testing purposes
      Random r = new Random(42);
      
      haiku();
      
      char reply = 'Y';
      int totalGames = 0;
      int totalGuesses = 0; 
      int bestGuess = 1000000;
      while (reply == 'Y') {
         totalGames++;
         int numGuesses = singleGame(console, r);
         totalGuesses += numGuesses;
         reply = multipleGames(reply, console);
         if (numGuesses <= bestGuess) {
            bestGuess = numGuesses;
         }
      }
      stats(totalGames, totalGuesses, bestGuess);
   }
   
   // Prints a Haiku
   public static void haiku() {
      System.out.println("Do you love numbers?");
      System.out.println("Guess what number I'm thinking");
      System.out.println("There is no reward");
   }
   
   // The guessing game that the user can play
   // Computer generates random number and user has to guess
   // Can play multiple times
   // Returns number of guesses made by user in a game
   // Parameters:
   //    console - The object used to read user input
   //    r - The object that generates pseudo-random numbers
   public static int singleGame(Scanner console, Random r) {
      System.out.println();
      int num = r.nextInt(MAX_VALUE) + 1;
      System.out.println("I'm thinking of a number between 1 and " + MAX_VALUE + "...");
      int guess = 0; // Randomly generated number cannot equal 0
                     // Guess set to 0 to ensure the guess loop starts
      int numGuesses = 0; 
      while (guess != num || numGuesses >= 1000000) {
         System.out.print("Your guess? ");
         guess = console.nextInt();
         numGuesses++;
         if (guess > num) {
            System.out.println("It's lower.");
         } else if (guess < num) {
            System.out.println("It's higher.");
         }
      }
      if (numGuesses == 1) {
         System.out.println("You got it right in 1 guess!");
      } else {
         System.out.println("You got it right in " + numGuesses + " guesses!");
      }
      return numGuesses;       
   }
   
   // Asks the user if they would like to play multiple games
   // Returns the users answer
   // Parameters:
   //    reply - The value that will be assigned the users reply
   //    console - The object used to read user input
   public static char multipleGames(char reply, Scanner console) {
      reply = 'Y'; // sets reply to Y to start the game loop
      System.out.print("Do you want to play again? ");
      String answer = console.next();
      answer = answer.toUpperCase();
      reply = answer.charAt(0);
      return reply;
   }
   
   // Prints user stats based on the games they played
   // Only displays stats once user indicates they do not
   // want to play anymore in game method
   // Parameters:
   //    totalGames - Number of games played
   //    totalGuesses - Number of guesses in all games
   //    bestGuess - Number of guesses in the shortest game
   public static void stats(int totalGames, int totalGuesses, int bestGuess) {
      System.out.println();
      System.out.println("Overall results:");
      System.out.println("Total games   = " + totalGames);
      System.out.println("Total guesses = " + totalGuesses);
      System.out.println("Guesses/game  = " + round1((double) totalGuesses /
                                                     (double) totalGames));
      System.out.println("Best game     = " + bestGuess);
   }

   // Rounds any number to one decimal places
   // Returns rounded value
   // Parameters
   //    num - the value to round
   public static double round1(double num) {
      return Math.round(num * 10.0) / 10.0;
   }
}
