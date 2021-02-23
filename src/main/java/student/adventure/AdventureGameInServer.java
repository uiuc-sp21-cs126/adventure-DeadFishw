package student.adventure;

import com.sun.istack.internal.NotNull;
import student.server.AdventureState;
import student.server.GameStatus;

public class AdventureGameInServer {
    private Character character;
    private GameStatus status;
    private Layout map;

    public AdventureGameInServer(Character character, GameStatus gameStatus, Layout map) {
        this.character = character;
        this.status = gameStatus;
        this.map = map;
    }

    public Character getCharacter() {
        return character;
    }

    public GameStatus getGameStatus() {
        return status;
    }

    public void setGameStatus(GameStatus status) {
        this.status = status;
    }

    public void setMap(Layout map) {
        this.map = map;
    }

    public void examine() {
        status.setMessage(character.toString());
    }

    /**
     * Taking the items in the Room
     *
     * @param command the command to take item("take xxx")
     */
    public void take(@NotNull String command) {
        if (character.getCurrentRoom().getItems() == null) {
            status = MessagePrinter.printCannotTake(status, command);
            return;
        }
        for (Item item: character.getCurrentRoom().getItems()) {
            if (item.getItemName().equalsIgnoreCase(command.trim())) {
                if (character.getLoad() - item.getLoad() < 0) {
                    MessagePrinter.printTooMuchLoad(status,command);
                }
                character.getItems().add(item);
                character.setTotalWorthOfItem(character.getTotalWorthOfItem() + item.getWorth());
                character.getCurrentRoom().getItems().remove(item);
                status = new GameStatus(false, status.getId(), character.toString(), "", "https://www.bilibili.com/video/BV1Vo4y1R7dM", new AdventureState(), character.getCommandOptions());
                return;
            }
        }
        status = MessagePrinter.printCannotTake(status, command);
    }

    /**
     * Dropping the character.getItems() in the Room
     *
     * @param command the command to drop item("drop xxx")
     */
    public void drop(@NotNull String command) {
        if (character.getCurrentRoom().getItems() == null) {
            status = MessagePrinter.printDoNotHaveItemDrop(status, command);
            return;
        }
        for (Item item: character.getItems()) {
            if (item.getItemName().equalsIgnoreCase(command.trim())) {
                if (character.getLevelOfForce() - item.getLevelOfForce() < character.getCurrentRoom().getLevelOfDanger()) {
                    status = MessagePrinter.printLevelTooLowToDrop(status, command);
                    return;
                }
                character.getItems().remove(item);
                character.setTotalWorthOfItem(character.getTotalWorthOfItem() - item.getWorth());
                character.getCurrentRoom().getItems().add(item);
                status = new GameStatus(false, status.getId(), character.toString(), "", "", new AdventureState(), character.getCommandOptions());
                return;
            }
        }
        status = MessagePrinter.printDoNotHaveItemDrop(status, command);
    }

    /**
     * Moving around in the map
     *
     * @param command the command to go somewhere item("go xxx")
     * @return true for game win and false for game continue.
     */
    public boolean goSomewhere(@NotNull String command) {
        if (isGameOver(command)) {
            if (character.getLevelOfForce() > map.getLevelOfForceDragon()) {
                status = MessagePrinter.printWinMessage(status);
            } else {
                status = MessagePrinter.printLoseMessage(status);
            }

            return true;
        }
        for (Direction direction: character.getCurrentRoom().getDirections()) {
            if (command.trim().equalsIgnoreCase(direction.getDirectionName())) {
                for (Room room : map.getRooms()) {
                    if (direction.getRoom().equalsIgnoreCase(room.getName())) {
                        if (character.getLevelOfForce() >= room.getLevelOfDanger()) {
                            character.setCurrentRoom(room);
                            status = new GameStatus(false, status.getId(), character.toString(), character.getCurrentRoom().getPhotoURL(), "", new AdventureState(), character.getCommandOptions());
                            return false;
                        } else {
                            status = MessagePrinter.printLevelTooLow(status, command);
                        }
                    }
                }
                return false;
            }
        }

        MessagePrinter.printNoDirection(status, command);
        return false;
    }
    
    public boolean isGameOver(String command) {
        return command.trim().equalsIgnoreCase("log in to zoom") &&
                character.getCurrentRoom().getName().equalsIgnoreCase("Your Room");
    }

    public void sayDoNotUnderstand(String command) {
        status = MessagePrinter.printDoNotUnderstand(status, command);
    }

}