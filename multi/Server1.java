//Server
import java.io.*;
import java.net.*;
public class Server1 {
private static final int PORT = 5000;
private static final int MAX_ATTEMPTS = 5;
public static void main(String[] args) {
try {
ServerSocket serverSocket = new ServerSocket(PORT);
System.out.println("Server started. Waiting for players to connect...");
Socket player1Socket = serverSocket.accept();
System.out.println("Player 1 connected.");
Socket player2Socket = serverSocket.accept();
System.out.println("Player 2 connected.");
BufferedReader player1In = new BufferedReader(new
InputStreamReader(player1Socket.getInputStream()));
PrintWriter player1Out = new PrintWriter(player1Socket.getOutputStream(), true);
BufferedReader player2In = new BufferedReader(new
InputStreamReader(player2Socket.getInputStream()));
PrintWriter player2Out = new PrintWriter(player2Socket.getOutputStream(), true);
player1Out.println("You are Player 1. Please think of a word for Player 2 to guess.");
player2Out.println("You are Player 2. Wait for Player 1 to choose a word.");
String wordToGuess = player1In.readLine().trim().toLowerCase();
int attemptsLeft = MAX_ATTEMPTS;
boolean wordGuessed = false;
while (attemptsLeft > 0 && !wordGuessed) {
player2Out.println("Attempts left: " + attemptsLeft + ". Guess the word:");
String guess = player2In.readLine().trim().toLowerCase();
if (guess.equals(wordToGuess)) {
player2Out.println("Congratulations! You guessed the word.");
wordGuessed = true;
} else {
player2Out.println("Incorrect guess. Try again.");
attemptsLeft--;
}
}
if (!wordGuessed) {
player2Out.println("Out of attempts. The word was: " + wordToGuess);
}
player1Socket.close();
player2Socket.close();
serverSocket.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}

