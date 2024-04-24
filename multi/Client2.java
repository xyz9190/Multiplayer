
//Client2
import java.io.*;
import java.net.*;
public class Client2 {
private static final String SERVER_IP = "localhost";
private static final int PORT = 5000;
public static void main(String[] args) {
try {
Socket socket = new Socket(SERVER_IP, PORT);
BufferedReader in = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
String serverMessage = in.readLine();
System.out.println(serverMessage);
BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
if (serverMessage.startsWith("You are Player 1")) {
System.out.println("Enter a word for Player 2 to guess:");
String word = userInput.readLine().trim().toLowerCase();
out.println(word);
}
while (true) {
String message = in.readLine();
if (message == null) {
break;
}
System.out.println(message);
if (message.startsWith("Attempts left")) {
String guess = userInput.readLine().trim().toLowerCase();
out.println(guess);
}
}
socket.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}

