package student.adventure;

import java.util.Objects;

public class Item {
    private String itemName;
    private int levelOfForce;

    public int getLevelOfForce() {
        return levelOfForce;
    }

    public String getItemName() {
        return itemName;
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
}
