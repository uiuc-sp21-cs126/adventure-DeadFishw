package student.adventure;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The layout of the map.
 */
public class Siebel {
    private String startingRoom;
    private String endingRoom;
    private Room[] rooms;


    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public String getStartingRoom() {
        return startingRoom;
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
            String command = scanner.nextLine();
            if (command.trim().equalsIgnoreCase("quit") || command.trim().equalsIgnoreCase("exit")) {
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
        Character character = new Character(rooms[0], this);
        runGame(character);
    }
}
