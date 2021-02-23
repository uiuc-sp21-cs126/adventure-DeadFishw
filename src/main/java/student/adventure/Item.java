package student.adventure;

import java.util.Objects;

public class Item {
    private String itemName;
    private int levelOfForce;
    private int load;
    private int worth;

    public int getLevelOfForce() {
        return levelOfForce;
    }

    public String getItemName() {
        return itemName;
    }

    public int getLoad() {
        return load;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public void levelOfForce(int levelOfForce) {
        this.levelOfForce = levelOfForce;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return Objects.equals(itemName, item.itemName);
    }

    public int getWorth() {
        return worth;
    }

    @Override
    public String toString() {
        return itemName;
    }
}
