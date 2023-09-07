package student.adventure;

import student.server.GameStatus;

import java.util.HashMap;

public class MessagePrinter {
    public static GameStatus printNoDirection(GameStatus status, String direction) {
        return new GameStatus(false, status.getId(), "I can't go \"" +
                direction.trim().toLowerCase() +
                "\"!", status.getImageUrl(),status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printTooMuchLoad(GameStatus status, String item) {
        return new GameStatus(false, status.getId(), "I can't take \"" +
                item.trim().toLowerCase() +
                "\"! It's too heavy for me!", status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printWinMessage(GameStatus status) {
        return new GameStatus(false, status.getId(),  "Congratulations! You have slayed the dragon! You are the hero of the town!",
                "https://swordoficastrastories.files.wordpress.com/2014/11/dragon6_by_benflores-d84ju1c.jpg?w=580&h=580&crop=1",
                status.getVideoUrl(), status.getState(), new HashMap<>());
    }

    public static GameStatus printLoseMessage(GameStatus status) {
        return new GameStatus(false, status.getId(),  "You meet your own death. Try challenge the dragon with more force next time.",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), new HashMap<>());
    }

    public static GameStatus printCannotTake(GameStatus status, String item) {
        return new GameStatus(false, status.getId(),  "There is no item \"" + item.trim() + "\" in the room.",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printDoNotHaveItemDrop(GameStatus status, String item) {
        return new GameStatus(false, status.getId(),  "You don't have \"" + item.trim() + "\"!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printLevelTooLowToDrop(GameStatus status, String item) {
        return new GameStatus(false, status.getId(),  "You cannot drop \"" + item.trim() + "\"! You cannot protect yourself here!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printDoNotUnderstand(GameStatus status, String command) {
        return new GameStatus(false, status.getId(),  "I don't understand \"" + command.trim() + "\"!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printLevelTooLow(GameStatus status, String direction) {
        return new GameStatus(false, status.getId(),  "I can't go \"" +
                direction.trim().toLowerCase() +
                "\"! I cannot protect myself there!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }
}
