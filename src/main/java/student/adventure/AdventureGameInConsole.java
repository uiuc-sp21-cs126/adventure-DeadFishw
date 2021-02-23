package student.adventure;

import com.sun.istack.internal.NotNull;
import student.server.GameStatus;

public class AdventureGameInConsole {
    Character character;
    Layout layout;

    public AdventureGameInConsole(Character character, Layout map) {
        this.character = character;
        this.layout = map;
    }

    public Character getCharacter() {
        return character;
    }

    public void setMap(Layout map) {
        this.layout = map;
    }

    /**
     * Taking the character.getItems() in the Room
     *
     * @param command the command to take item("take xxx")
     */
    public void take(@NotNull String command) {
        String itemToTake = command.substring(4);
        if (character.getCurrentRoom().getItems() == null) {
            System.out.println("There is no item \"" + itemToTake.trim() + "\" in the Room.");
            return;
        }
        for (Item item: character.getCurrentRoom().getItems()) {
            if (item.getItemName().equalsIgnoreCase(itemToTake.trim())) {
                if (character.getLoad() - item.getLoad() < 0) {
                    System.out.println("I can't take \"" +
                            item.toString().trim().toLowerCase() +
                            "\"! It's too heavy for me!");
                }
                character.getItems().add(item);
                character.getCurrentRoom().getItems().remove(item);
                return;
            }
        }
        System.out.println("There is no \"" +
                itemToTake.trim() +
                "\" in the Room.");
    }

    /**
     * Dropping the character.getItems() in the Room
     *
     * @param command the command to drop item("drop xxx")
     */
    public void drop(@NotNull String command) {
        String itemToDrop = command.substring(4);
        if (character.getCurrentRoom().getItems() == null) {
            System.out.println("You don't have \"" + itemToDrop.trim() + "\"!");
            return;
        }
        for (Item item: character.getItems()) {
            if (item.getItemName().equalsIgnoreCase(itemToDrop.trim())) {
                if (character.getLevelOfForce() - item.getLevelOfForce() < character.getCurrentRoom().getLevelOfDanger()) {
                    System.out.println("You cannot drop \"" + item.toString().trim() + "\"! You cannot protect yourself here!");
                }
                character.getItems().remove(item);
                character.getCurrentRoom().getItems().add(item);
                return;
            }
        }
        System.out.println("You don't have \"" + itemToDrop.trim() + "\"!");
    }


    /**
     * Moving around in the map
     *
     * @param command the command to go somewhere item("go xxx")
     * @return true for game win and false for game continue.
     */
    public void goSomewhere(@NotNull String command) {
        String directionToGo = command.substring(3);
        if (checkWin(directionToGo)) {
            System.out.println("Congratulations! You have slayed the dragon");
            return;
        }
        for (Direction direction: character.getCurrentRoom().getDirections()) {
            if (directionToGo.trim().equalsIgnoreCase(direction.getDirectionName())) {
                for (Room room : layout.getRooms()) {
                    if (direction.getRoom().equalsIgnoreCase(room.getName())) {
                        if (character.getLevelOfForce() >= room.getLevelOfDanger()) {
                            character.setCurrentRoom(room);
                            return;
                        } else {
                            System.out.println("I can't go \"" +
                                    directionToGo.trim().toLowerCase() +
                                    "\"! I cannot protect myself there!");
                        }
                    }
                }
                return;
            }
        }

        System.out.println("I can't go \"" +
                directionToGo.trim().toLowerCase() +
                "\"!");
    }

    public boolean checkWin(String command) {
        return command.trim().equalsIgnoreCase("log in to zoom") &&
                character.getCurrentRoom().getName().equalsIgnoreCase("Your Room");
    }
}
