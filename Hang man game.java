/* How to Play Hangman:

I'm thinking of a secret word.
You try to guess the word by suggesting a letter.
If the letter is in the word, I'll fill in the correct letter.
If the letter is not in the word, I'll draw a part of a hangman's gallows.
Keep guessing until you correctly guess the word or the gallows is complete.
If you guess the word correctly, you win! If the gallows is complete before you guess the word, you lose.
you can copy the below code and run in any compiler */


import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Hangman {
    private static final String[] WORDS = {
            "java", "code", "game", "play", "fun", "sun", "hat", "car", "boy", "girl",
            "dog", "cat", "house", "tree", "apple", "book", "pen", "paper", "bike", "run",
            "jump", "happy", "smile", "laugh", "cry", "sad", "mad", "glad", "sleep", "dream",
            "eat", "food", "drink", "water", "juice", "milk", "cake", "ice", "cream", "pie",
            "red", "blue", "green", "yellow", "orange", "purple", "pink", "black", "white",
            "gray", "big", "small", "long", "short", "fat", "thin", "old", "new", "hot",
            "cold", "beach", "park", "city", "town", "village", "farm", "animal", "bird",
            "fish", "horse", "music", "song", "dance", "sing", "instrument", "guitar",
            "piano", "drum", "violin", "sport", "ball", "team", "win", "lose", "score",
            "goal", "shoot", "run", "computer", "phone", "tablet", "laptop", "desktop",
            "internet", "website", "email", "password", "login"
    };

    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            String word = getRandomWord();
            char[] guessedWord = new char[word.length()];
            Arrays.fill(guessedWord, '_');
            Set<Character> guessedLetters = new HashSet<>();
            int attempts = MAX_ATTEMPTS;

            System.out.println("Welcome to Hangman!");
            System.out.println("I'm thinking of a word that has " + word.length() + " letters.");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the word.");

            while (true) {
                System.out.println("Guessed word: " + new String(guessedWord));
                System.out.print("Enter a letter: ");
                char guess = scanner.next().toLowerCase().charAt(0);

                if (guessedLetters.contains(guess)) {
                    System.out.println("You've already guessed that letter. Try again.");
                    continue;
                }

                guessedLetters.add(guess);

                if (word.contains(Character.toString(guess))) {
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == guess) {
                            guessedWord[i] = guess;
                        }
                    }
                } else {
                    System.out.println("That letter is not in the word.");
                    attempts--;
                }

                if (Arrays.equals(word.toCharArray(), guessedWord)) {
                    System.out.println("You win! The word was " + word);
                    break;
                }

                if (attempts == 0) {
                    System.out.println("You lose. The word was " + word);
                    break;
                }
            }

            System.out.print("Play again? (yes/no): ");
            String response = scanner.next().toLowerCase();

            while (!response.equals("yes") && !response.equals("no")) {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
                response = scanner.next().toLowerCase();
            }

            playAgain = response.equals("yes");
        }

        System.out.println("Thanks for playing Hangman!");
        scanner.close();
    }

    private static String getRandomWord() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }
}
