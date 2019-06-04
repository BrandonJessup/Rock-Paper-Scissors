/*
 * Authored by Brandon Jessup
 * on June 3rd 2019
 * 
 * A game of rock-paper-scissors played against a computer opponent.
 */

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
	private static String playerName;
	private static boolean playAgain = true;
	private static String playerMove;
	private static String computerMove;
	private static String winner;
	
	public static void main(String[] args) {
		playerNamePrompt();
		while(playerWantsToPlay()) {
			startNewRound();
			playAgainPrompt();
		}
		displayExitMessage();
	}
	
	private static void playerNamePrompt() {
		System.out.print("Enter your name: ");
		Scanner input = new Scanner(System.in);
		playerName = input.next();
	}
	
	private static boolean playerWantsToPlay() {
		return playAgain;
	}
	
	private static void startNewRound() {
		announceGameStart();
		getAndValidatePlayerMove();
		getComputerMove();
		displayChoices();
		determineWinner();
		displayWinner();
	}
	
	private static void announceGameStart() {
		System.out.println("\nStarting new game...");
	}
	
	private static void getAndValidatePlayerMove() {
		String move = movePrompt();
		while (!moveIsValid(move)) {
			System.out.println("\nThat's not a valid option, please try again.");
			move = movePrompt();
		}
		playerMove = move;
	}
	
	private static String movePrompt() {
		System.out.println("What will you throw?");
		Scanner input = new Scanner(System.in);
		return input.next();
	}
	
	private static boolean moveIsValid(String move) {
		if (move.contentEquals("rock") ||
			move.contentEquals("paper") ||
			move.contentEquals("scissors"))
			return true;
		else
			return false;
	}
	
	private static void getComputerMove() {
		int randomNumber = generateNumberInRangeInclusive(0, 2);
		if (randomNumber == 0)
			computerMove = "rock";
		else if (randomNumber == 1)
			computerMove = "paper";
		else
			computerMove = "scissors";
	}
	
	private static int generateNumberInRangeInclusive(int lowerBound, int upperBound) {
		upperBound += 1;
		Random rng = new Random();
		return rng.nextInt(upperBound - lowerBound) + lowerBound;
	}
	
	private static void displayChoices() {
		String output = "";
		output += "\n" + playerName + " chooses " + playerMove;
		output += ", computer chooses " + computerMove + ".";
		System.out.println(output);
	}
	
	private static void determineWinner() {
		if (playerMove.contentEquals(computerMove))
			winner = "neither";
		else if (playerMove.contentEquals("rock")) {
			if (computerMove.contentEquals("paper"))
				winner = "computer";
			else
				winner = "player";
		}
		else if (playerMove.contentEquals("paper")) {
			if (computerMove.contentEquals("scissors"))
				winner = "computer";
			else
				winner = "player";
		}
		else if (playerMove.contentEquals("scissors")) {
			if (computerMove.contentEquals("rock"))
				winner = "computer";
			else
				winner = "player";
		}
	}
	
	private static void displayWinner() {
		String output = "";
		if (winner.contentEquals("neither"))
			output += "It's a tie!";
		else {
			output += capitalize(getWinnerMove());
			output += " beats ";
			output += getLoserMove();
			output += ", ";
			output += getWinnerName();
			output += " wins!";
		}
		System.out.println(output);
	}
	
	private static String capitalize(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}
	
	private static String getWinnerMove() {
		if (winner.contentEquals("player"))
			return playerMove;
		else
			return computerMove;
	}
	
	private static String getLoserMove() {
		if (winner.contentEquals("player"))
			return computerMove;
		else
			return playerMove;
	}
	
	private static String getWinnerName() {
		if (winner.contentEquals("computer"))
			return "the computer";
		else
			return playerName;
	}
	
	private static void playAgainPrompt() {
		System.out.println("\nWould you like to play again?");
		System.out.print("Y/N: ");
		Scanner input = new Scanner(System.in);
		String response = input.next();
		if (response.contentEquals("y") ||
			response.contentEquals("Y") ||
			response.contentEquals("yes"))
			playAgain = true;
		else
			playAgain = false;
	}
	
	private static void displayExitMessage() {
		System.out.println("\nThanks for playing!");
	}
}
