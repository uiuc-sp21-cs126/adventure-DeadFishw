package student.adventure;

import java.util.Objects;

public class Item {
    private String itemName;
    private int itemID;

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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
