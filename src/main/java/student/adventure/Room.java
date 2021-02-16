package student.adventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The Rooms in the map.
 */
public class Room {
    private String name;
    private String description;
    private Direction[] directions;
    private List<String> items;

    public Room(){}

    public Room(String setName) {
        name = setName;
    }

    public Direction[] getDirections() {
        return directions;
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

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = new ArrayList(Arrays.asList(items));
    }

    public void removeItems(String item) {
        items.remove(item.trim().toLowerCase());
    }

    public void addItem(String item) {
        items.add(item.trim().toLowerCase());
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
}
