import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class InventorySystem {

    // General inventory for any purposes. Custom groups can be created. We assume that one item cannot appear in two groups.
    private HashMap<String, ArrayList<Item>> inventory;

    // Group for rarities, created, filled and displayed automatically, not for manual use.
    private HashMap<String, ArrayList<Item>> rarityGroup;

    public InventorySystem() {

        //Initialization of both inventories according to the rules above
        inventory = new HashMap<>();
        rarityGroup = new HashMap<>();
        for (Item.ItemRarity rarity : Item.ItemRarity.values()) {
            rarityGroup.put(rarity.toString(), new ArrayList<>());
        }
    }

    public void DisplayInventory() {
        // Displaying by rarity, each rarity and their titles will be on new lines.
        // Items in the rarity will be below the title, with tabulation symbol.
        for (String group : rarityGroup.keySet()) {
            ArrayList<Item> items = rarityGroup.get(group);
            if (items.size() == 0) continue;
            System.out.println("Rarity Group - "+group);
            for (Item item : items) {
                System.out.println("\tName - "+item.getName()+", Rarity - "+item.getRarity().toString()+", Upgrade Level - "+item.getUpgradeCount());
            }
        }
    }

    public Item CreateItem(String name, Item.ItemRarity rarity) {
        // Create item and add to rarity inventory automatically. 
        Item item = new Item(name, rarity);
        rarityGroup.get(item.getRarity().toString()).add(item);
        return item;
    }

    public void AssignItemToGroup(Item item, String group) {
        // Custom group creation and assignment in for any item.
        if (inventory.containsKey(group)) {
            inventory.get(group).add(item);
        } else {
            ArrayList<Item> itemList = new ArrayList<>();
            itemList.add(item);
            inventory.put(group, itemList);
        }
    }

    public Item UpgradeItemInGroup(Item item, String group) {
        // If such group exists.
        if (!inventory.containsKey(group)) return null;

        // Get group.
        ArrayList<Item> itemGroup = inventory.get(group);

        // If such item is in the group.
        if (!itemGroup.contains(item)) return null;

        // Try to create new item based on the other items in the same group. All items of group participat to the upgrade.
        Item[] otherItems = new Item[itemGroup.size()-1];
        int counter = 0;
        for (Item otherItem : itemGroup) {
            if (otherItem.equals(item)) continue;
            otherItems[counter++] = otherItem;
        }

        Item newItem = item.UpgradeItem(otherItems);


        // If everything was okay, remove all items from that group, add newly created one.
        if (!newItem.equals(null)) {
            itemGroup.clear();
            itemGroup.add(newItem);
            rarityGroup.get(newItem.getRarity().toString()).add(newItem);
        }

        // Return
        return newItem;
    }

    public void saveInventory(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(inventory);
        }
    }

    // Load inventory from a file
    public void loadInventory(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            inventory = (HashMap<String, ArrayList<Item>>) ois.readObject();
        }
    }

}
