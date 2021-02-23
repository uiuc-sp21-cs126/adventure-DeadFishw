package student.adventure;

import java.util.*;

/**
 * The Rooms in the map.
 */
public class Room {
    private String name;
    private String description;
    private Direction[] directions;
    private List<Item> items;
    private Map<String, List<String>> commandOptions;
    private int levelOfDanger;

    public Map<String, List<String>> getCommandOptions() {
        return commandOptions;
    }

    public Room(){}

    public Room(String setName) {
        name = setName;
    }

    public Direction[] getDirections() {
        return directions;
    }

    public int getLevelOfDanger() {
        return levelOfDanger;
    }

    public String getDescriptions() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return name.equals(room.name);
    }

    public List<Item> getItems() {
        return items;
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

    public void setItems(Item[] items) {
        this.items = new ArrayList(Arrays.asList(items));
    }

    public void removeItems(String item) {
        items.remove(item.trim().toLowerCase());
    }


    public void setLevelOfDanger(int levelOfDanger) {
        this.levelOfDanger = levelOfDanger;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setDescription(String descriptions) {
        this.description = descriptions;
    }

    public void setDirections(Direction[] directions) {
        this.directions = directions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDirectionStrings() {
        List<String> result = new ArrayList<String>();
        if (directions == null || directions.length == 0) {
            return result;
        }
        for(Direction direction: directions) {
            result.add(direction.toString());
        }
        return result;
    }
}
