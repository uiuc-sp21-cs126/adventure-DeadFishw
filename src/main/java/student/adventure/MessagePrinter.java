package student.adventure;

import student.server.GameStatus;

public class MessagePrinter {
    public static GameStatus printCannotGo(GameStatus status, String direction) {
        return new GameStatus(true, status.getId(), "I can't go \"" +
                direction.trim().toLowerCase() +
                "\"!", null,null, status.getState(), status.getCommandOptions());
    }

    public static GameStatus printWinMessage(GameStatus status) {
        return new GameStatus(false, status.getId(),  "Congratulations! You have slained the dragon",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printCannotTake(GameStatus status, String item) {
        return new GameStatus(true, status.getId(),  "There is no item \"" + item.trim() + "\" in the room.",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }

    public static GameStatus printCannotDrop(GameStatus status, String item) {
        return new GameStatus(true, status.getId(),  "You don't have \"" + item.trim() + "\"!",
                status.getImageUrl(), status.getVideoUrl(), status.getState(), status.getCommandOptions());
    }
}