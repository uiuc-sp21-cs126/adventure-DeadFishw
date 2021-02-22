package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameEngine {
    Character character;
    Layout layout;

    public GameEngine() throws IOException {
        File file = new File("src/main/resources/siebel.json");
        ObjectMapper mapper = new ObjectMapper();
        layout = mapper.readValue(file, Layout.class);
    }

    public GameEngine(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        layout = mapper.readValue(file, Layout.class);
    }

    /**
     * Method to run mid-game. Take in commands and do the actions.
     *
     * @param character the current character in the came
     */
    public static void runGame(Character character) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println(character);
            String command = Command.getCommand();
            if (command.trim().equalsIgnoreCase("quit") ||
                    command.trim().equalsIgnoreCase("exit")) {
                return;
            } else if (command.startsWith("go")) {
                if (character.goSomewhere(command)) {
                    break;
                }
            } else if (command.trim().equalsIgnoreCase("examine")) {
                System.out.println(character);
            } else if (command.startsWith("take")) {
                character.take(command);
            } else if (command.startsWith("drop")) {
                character.drop(command);
            } else {
                System.out.println("I don't understand " + command);
            }
        }
    }

    /**
     * method to call while starting the game.
     */
    public void start() {
        Character character = new Character(layout.getRooms()[0], layout);
        runGame(character);
    }
}
