package student.adventure;

import java.util.*;


public class Character {
    private room currentRoom;
    private Layout layout;
    private List<Item> items;
    private int levelOfForce;

    public Character(Layout setLayout) {
        layout = setLayout;
        currentRoom = setLayout.getRooms()[0];
        items = new ArrayList<>();
    }

    public int getLevelOfForce() {
        if (items.size() == 0) {
            return 0;
        }
        int countLevelOfForce = 0;
        for (Item item: items) {
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

    public List<String> getItemsString() {
        if (items == null || items.size() == 0) {
            return new ArrayList<>();
        }
        List<String> itemStrings = new ArrayList<String>();
        for (Item item: items) {
            itemStrings.add(item.getItemName());
        }
        return itemStrings;
    }

    @Override
    public String toString() {
        String toString = currentRoom.getDescriptions() +
                "\nFrom here, you can go: ";
        for (Direction direction: currentRoom.getDirections()) {
            toString += direction.getDirectionName() + " ";
        }
        if (currentRoom.getItems().size() != 0) {
            toString += "\nItems visible: ";
            for (Item item: currentRoom.getItems()) {
                toString += item.toString() + ", ";
            }
            toString = toString.substring(0, toString.length() - 2);
        }
        return toString;
    }

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
