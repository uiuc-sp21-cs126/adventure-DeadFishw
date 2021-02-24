package student.adventure;

import com.sun.istack.internal.NotNull;
import student.server.AdventureState;
import student.server.GameStatus;

import java.util.Locale;
/**
 * AdventureGame instance for playing in console
 */
public class AdventureGameInConsole {
    Character character;
    Layout layout;

    public AdventureGameInConsole(Character character, Layout layout) {
        this.character = character;
        this.layout = layout;
    }

    public Character getCharacter() {
        return character;
    }

    /**
     * Taking the items in the Room
     *
     * @param command the command to take item("take xxx")
     */
    public void take(@NotNull String command) {
        String itemToTake = command.substring(4).trim();
        if (character.getCurrentRoom().getItems() == null) {
            System.out.println("There is no item \"" + itemToTake.trim() + "\" in the room.");
            System.out.println(1);
            return;
        }
        for (Item item: character.getCurrentRoom().getItems()) {
            if (item.getItemName().equalsIgnoreCase(itemToTake.trim())) {
                if (character.getCurrentLoad() + item.getLoad() > character.getMaximumLoad()) {
                    System.out.println("I can't take \"" +
                            itemToTake.trim().toLowerCase() +
                            "\"! It's too heavy for me!");
                    return;
                }
                character.getItems().add(item);
                character.setTotalWorthOfItem(character.getTotalWorthOfItem() + item.getWorth());
                character.getCurrentRoom().getItems().remove(item);
                return;
            }
        }
        System.out.println("There is no item \"" + itemToTake.trim() + "\" in the room.");
    }

    /**
     * Dropping the character.getItems() in the Room
     *
     * @param command the command to drop item("drop xxx")
     */
    public void drop(@NotNull String command) {
        String itemToDrop = command.substring(4).trim();
        if (character.getItems() == null) {
            System.out.println("You don't have \"" + itemToDrop.trim() + "\"!");
            return;
        }
        for (Item item: character.getItems()) {
            if (item.getItemName().equalsIgnoreCase(itemToDrop.trim())) {
                if (character.getLevelOfForce() - item.getLevelOfForce() < character.getCurrentRoom().getLevelOfDanger()) {
                    System.out.println("You cannot drop \"" + itemToDrop.trim() + "\"! You cannot protect yourself here!");
                    return;
                }
                character.getItems().remove(item);
                character.setTotalWorthOfItem(character.getTotalWorthOfItem() - item.getWorth());
                character.getCurrentRoom().getItems().add(item);
                return;
            }
        }
        System.out.println("You don't have \"" + itemToDrop.trim() + "\"!");
    }

    /**
     * Moving around in the layout
     *
     * @param command the command to go somewhere item("go xxx")
     * @return true for game win and false for game continue.
     */
    public boolean goSomewhere(@NotNull String command) {
        String directionToGo = command.substring(3).trim();
        if (isGameOver(directionToGo)) {
            if (character.getLevelOfForce() > layout.getLevelOfForceDragon()) {
                System.out.println("Congratulations! You have slayed the dragon! You are the hero of the town!");
            } else {
                System.out.println("You meet your own death. Try challenge the dragon with more force next time.");
            }
            return true;
        }
        for (Direction direction: character.getCurrentRoom().getDirections()) {
            if (directionToGo.trim().equalsIgnoreCase(direction.getDirectionName())) {
                for (Room room : layout.getRooms()) {
                    if (direction.getRoom().equalsIgnoreCase(room.getName())) {
                        if (character.getLevelOfForce() >= room.getLevelOfDanger()) {
                            character.setCurrentRoom(room);
                            return false;
                        } else {
                            System.out.println("I can't go \"" +
                                    direction.getDirectionName().trim().toLowerCase() +
                                    "\"! I cannot protect myself there!");
                            return false;
                        }
                    }
                }
                return false;
            }
        }

        System.out.println("I can't go \"" +
                directionToGo.toString().trim().toLowerCase() +
                "\"!");
        return false;
    }

    public void checkWorth() {
        System.out.println("Your current worth of inventory is: " + character.getTotalWorthOfItem());
    }

    public void checkLoad() {
        System.out.println("Your current load is: " + character.getCurrentLoad());
    }

    public boolean isGameOver(String command) {
        return command.trim().equalsIgnoreCase("Fight the dragon") &&
                character.getCurrentRoom().getName().equalsIgnoreCase("Nest of the Dragon");
    }

    public void sayDoNotUnderstand(String command) {
        System.out.println("I don't understand \"" + command.trim() + "\"!");
    }
}
