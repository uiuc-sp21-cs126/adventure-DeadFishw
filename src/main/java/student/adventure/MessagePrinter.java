package student.adventure;

import student.server.GameStatus;

public class MessagePrinter {
    public static GameStatus printNoDirection(GameStatus status, String direction) {
        return new GameStatus(true, status.getId(), "I can't go \"" +
                direction.trim().toLowerCase() +
                "\"!", "","", status.getState(), status.getCommandOptions());
    }

    public static GameStatus printTooMuchLoad(GameStatus status, String item) {
        return new GameStatus(true, status.getId(), "I can't take \"" +
                item.trim().toLowerCase() +
                "\"! It's too heavy for me!", "","", status.getState(), status.getCommandOptions());
    }

    public static GameStatus printWinMessage(GameStatus status) {
        return new GameStatus(false, status.getId(),  "Congratulations! You have slained the dragon",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printCannotTake(GameStatus status, String item) {
        return new GameStatus(true, status.getId(),  "There is no item \"" + item.trim() + "\" in the Room.",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printDoNotHaveItemDrop(GameStatus status, String item) {
        return new GameStatus(true, status.getId(),  "You don't have \"" + item.trim() + "\"!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printLevelTooLowToDrop(GameStatus status, String item) {
        return new GameStatus(true, status.getId(),  "You cannot drop \"" + item.trim() + "\"! You cannot protect yourself here!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printDoNotUnderstand(GameStatus status, String command) {
        return new GameStatus(true, status.getId(),  "I don't understand \"" + command.trim() + "\"!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printLevelTooLow(GameStatus status, String direction) {
        return new GameStatus(true, status.getId(),  "I can't go \"" +
                direction.trim().toLowerCase() +
                "\"! I cannot protect myself there!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }
}
