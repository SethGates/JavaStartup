import java.util.Scanner;

public class RandomNumberGuesser {
    public static void main(String[] args) {
        // establish "input"
        Scanner input = new Scanner(System.in);

        int randomValue = 1 + (int) (100 * Math.random()); // Selects through 1-100. Change value to whatever youd like.

        System.out.println("Guess a number between 1 - 100"); // Ask user for input
        int numberOfGuesses = 0; // Sets baseline for number of guesses so it can increase at every attempt.
        int userValue = input.nextInt(); // Establish "input"

        while (userValue != randomValue) { // Evaluator for accuracy for H or L
            if (userValue < randomValue) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
            numberOfGuesses ++;
            System.out.println("Guess a number between 1 - 100"); // Ask question again after incorrect input
            userValue = input.nextInt();
        }
        System.out.println("You won! " + randomValue);
        System.out.println("Your total guesses " + numberOfGuesses);
        

        input.close();

    }

}
