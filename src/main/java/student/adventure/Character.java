package student.adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The character to go on to the adventure.
 */
public class Character {
    private room currentRoom;
    private Layout layout;
    private List<Item> items;
    private int levelOfForce;
    private int load;


    public Character(Layout setLayout) {
        layout = setLayout;
        currentRoom = setLayout.getRooms()[0];
        items = new ArrayList<>();
        load = 80;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getLevelOfForce() {
        if (items.size() == 0) {
            return 0;
        }
        int countLevelOfForce = 0;
        for (Item item : items) {
            countLevelOfForce += item.getLevelOfForce();
        }
        return countLevelOfForce;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public Layout getLayout() {
        return layout;
    }

    public room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * Get the names of the items in the inventory.
     *
     * @return the list of all the items in the inventory.
     */
    public List<String> getItemsString() {
        if (items == null || items.size() == 0) {
            return new ArrayList<>();
        }
        List<String> itemStrings = new ArrayList<String>();
        for (Item item : items) {
            itemStrings.add(item.getItemName());
        }
        return itemStrings;
    }

    @Override
    public String toString() {
        String toString = currentRoom.getDescriptions()
                + "\nFrom here, you can go: ";
        for (Direction direction : currentRoom.getDirections()) {
            toString += direction.getDirectionName() + " ";
        }
        if (currentRoom.getItems().size() != 0) {
            toString += "\nItems visible: ";
            for (Item item : currentRoom.getItems()) {
                toString += item.toString() + ", ";
            }
            toString = toString.substring(0, toString.length() - 2);
        }
        return toString;
    }


    /**
     * Get the command options of the character at the point.
     *
     * @return the commands that the character can receive at the point.
     */
    public Map<String, List<String>> getCommandOptions() {
        Map<String, List<String>> commandOptions =  new HashMap<>();
        commandOptions.put("examine", new ArrayList<>());
        commandOptions.put("go", currentRoom.getDirectionStrings());
        commandOptions.put("take", currentRoom.getItemsString());
        commandOptions.put("drop", this.getItemsString());
        List<String> toAdd = new ArrayList<String>();
        return commandOptions;
    }


}
