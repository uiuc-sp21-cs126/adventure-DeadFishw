package student.adventure;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;
import java.util.List;


public class Character {
    private Room currentRoom;
    private Siebel siebel;
    private List<String> items;

    public Character(Room startingRoom, Siebel setSiebel) {
        siebel = setSiebel;
        currentRoom = startingRoom;
        items = new ArrayList<>();
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public Siebel getSiebel() {
        return siebel;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setSiebel(Siebel siebel) {
        this.siebel = siebel;
    }

    @Override
    public String toString() {
        String toString = currentRoom.getDescriptions() + "\nFrom here, you can go: ";
        for (Direction direction: currentRoom.getDirections()) {
            toString += direction.getDirectionName() + " ";
        }
        if (currentRoom.getItems().size() != 0) {
            toString += "\nItems visible: ";
            for (String item: currentRoom.getItems()) {
                toString += item + ", ";
            }
            toString = toString.substring(0, toString.length() - 2);
        }
        return toString;
    }


    /**
     * Taking the items in the room
     *
     * @param command the command to take item("take xxx")
     */
    public void take(@NotNull String command) {
        String item = command.substring(4);
        if (currentRoom.getItems().contains(item.trim().toLowerCase())) {
            items.add(item.trim().toLowerCase());
            currentRoom.removeItems(item);
        } else {
            System.out.println("There is no \"" + item.trim() + "\" in the room.");
        }
    }

    /**
     * Dropping the items in the room
     *
     * @param command the command to drop item("drop xxx")
     */
    public void drop(@NotNull String command) {
        String item = command.substring(4);
        if (items.contains(item.trim().toLowerCase())) {
            items.remove(item.trim().toLowerCase());
            if (currentRoom.getItems() != null) {
                currentRoom.addItem(item);
            }
        } else {
            System.out.println("You don't have \"" + item.trim() + "\"!");
        }
    }

    /**
     * Moving around in the map
     *
     * @param command the command to go somewhere item("go xxx")
     * @return true for game win and false for game continue.
     */
    public boolean goSomewhere(@NotNull String command) {
        String directionToGo = command.substring(3);
        if (directionToGo.trim().equalsIgnoreCase("log in to zoom") && currentRoom.getName().equalsIgnoreCase("Your room")) {
            System.out.println("Congratulations! You Win! Enjoy your code review!");
            return true;
        }
        for (Direction direction: currentRoom.getDirections()) {
            if (directionToGo.trim().equalsIgnoreCase(direction.getDirectionName())) {
                for (Room room: siebel.getRooms()) {
                    if (direction.getRoom().equalsIgnoreCase(room.getName())) {
                        currentRoom = room;
                        return false;
                    }
                }
                return false;
            }
        }

        System.out.println("I can't go \"" + directionToGo.trim().toLowerCase() + "\"!");
        return false;
    }
}
