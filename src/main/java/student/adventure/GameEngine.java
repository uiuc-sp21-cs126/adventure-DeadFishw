package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import student.server.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameEngine implements AdventureService {
    Layout layout;
    Map<Integer, AdventureGameInServer> games;
    AdventureGameInConsole consoleGame;


    public GameEngine() throws IOException {
        File file = new File("src/main/resources/siebel.json");
        ObjectMapper mapper = new ObjectMapper();
        layout = mapper.readValue(file, Layout.class);
    }

    public GameEngine(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        layout = mapper.readValue(file, Layout.class);
        games = new HashMap<>();
    }

    /**
     * Check if the game should be continued.
     *
     * @param command the command to check.
     * @return true for to be continued.
     */
    public static boolean isGameContinued(Command command) {
        return command.getCommandName().trim().equalsIgnoreCase("quit") ||
                command.getCommandName().trim().equalsIgnoreCase("exit");
    }


    /**
     * Method to run mid-game. Take in commands and do the actions.
     *
     * @param character the current character in the game
     */
    public void runInConsole(Character character) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(character);
        String command = scanner.nextLine();
        if (command.trim().equalsIgnoreCase("quit") ||
                command.trim().equalsIgnoreCase("exit")) {
            return;
        } else if (command.startsWith("go")) {
            consoleGame.goSomewhere(command);
        } else if (command.trim().equalsIgnoreCase("examine")) {
            System.out.println(character);
        } else if (command.startsWith("take")) {
            consoleGame.take(command);
        } else if (command.startsWith("drop")) {
            consoleGame.drop(command);
        } else {
            System.out.println("I don't understand " + command);
        }
        runInConsole(character);
    }

    /**
     * method to call while starting the game.
     */
    public void startInConsole() {
        Character character = new Character(layout);
        consoleGame = new AdventureGameInConsole(character,  layout);
        runInConsole(character);
    }

    @Override
    public void reset() {
        games.clear();
    }

    @Override
    public int newGame() throws AdventureException {
        Character character = new Character(layout);
        Random generater = new Random();
        int id = generater.nextInt(200000);;
        while (games.keySet().contains(id)) {
            generater.nextInt(200000);
        }
        games.put(id, new AdventureGameInServer(character,
                new GameStatus(false, id, character.toString(),
                        null, null, new AdventureState(), character.getCommandOptions()),
                layout));
        return id;
    }

    @Override
    public GameStatus getGame(int id) {
        if (id < 1 || id > games.size()) {
            throw new IllegalArgumentException();
        }
        return games.get(id).getGameStatus();
    }

    @Override
    public boolean destroyGame(int id) {
        if(games.keySet().contains(id)) {
            games.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void executeCommand(int id, Command command) {
        AdventureGameInServer game = games.get(id);
        if (command.getCommandName().equalsIgnoreCase("go")) {
            game.goSomewhere(command.getCommandValue());
        } else if (command.getCommandName().trim().equalsIgnoreCase("examine")) {
            game.examine();
        } else if (command.getCommandName().trim().equalsIgnoreCase("take")) {
            game.take(command.getCommandValue());
        } else if (command.getCommandName().trim().equalsIgnoreCase("drop")) {
            game.drop(command.getCommandValue());
        } else {
            game.sayDoNotUnderstand(command.getCommandName() + " " + command.getCommandValue());
        }
    }

    @Override
    public SortedMap<String, Integer> fetchLeaderboard() {
        return null;
    }
}
