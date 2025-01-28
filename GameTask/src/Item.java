import java.util.Random;

public class Item {

    private String Name;
    private ItemRarity Rarity;
    private int UpgradeCount;
    public int[] ItemRarityDistributions = {50,25,15,8,2};
    public enum ItemRarity {
        // Items by their rarities
        COMMON,
        GREAT,
        RARE,
        EPIC,
        LEGENDARY;

        // method to return the next item by rarity, in circular format - handling legendary case.
        public static ItemRarity getNext(ItemRarity current) {
            ItemRarity[] rarities = ItemRarity.values();
            int nextIndex = (current.ordinal() + 1) % rarities.length;
            if (nextIndex == 0) return null;
            return rarities[nextIndex];
        }
    }


    // Default constructor
    public Item (String name, ItemRarity rarity) {
        setName(name);
        setRarity(rarity);
        setUpgradeCount(0);
    }

    // Copy constructor
    public Item (Item other) {
        setName(other.getName());
        setRarity(other.getRarity());
        setUpgradeCount(other.getUpgradeCount());
    }

    // Getters
    public String getName() {
        return Name;
    }

    public ItemRarity getRarity() {
        return Rarity;
    }

    public int getUpgradeCount() {
        return UpgradeCount;
    }

    // Setters
    public void setName(String name) {
        if (name.equals(null) || name.length() == 0) throw new IllegalArgumentException("Invalid name");
        Name = name;
    }

    public void setRarity(ItemRarity rarity) {
        if (rarity.equals(null)) throw new IllegalArgumentException("Invalid rarity");
        Rarity = rarity;
    }

    public void setUpgradeCount(int upgradeCount) {
        UpgradeCount = upgradeCount;
    }

    // Item with given name and random rarity
    public Item GetRandomItem(String name) {
        // Rnadom module
        Random random = new Random();

        // Range [1;100]
        int randomValue = random.nextInt(100-1)+1;
        int sum = 0;
        int counter = -1;
        for (int weight : ItemRarityDistributions) {
            sum+=weight;
            counter+=1;
            if (sum >= randomValue) break;
        }
        return new Item(name, ItemRarity.values()[counter]);

    }

    public Item UpgradeItem(Item... items) {
        // Don't upgrade legendary
        if (Rarity.equals(ItemRarity.LEGENDARY)) throw new IllegalArgumentException("Legendary item cannot be upgraded.");

        // Either 1 or 2 items in input
        if (items.length > 3 || items.length == 0) throw new IllegalArgumentException("The number of items must be 1 or 2.");

        // First item must always be an item
        if ((items[0].equals(null))) throw new IllegalArgumentException("First item is not provided.");

        // If there is second item, it must also be an item
        if (items.length == 2 && (items[1].equals(null))) throw new IllegalArgumentException("Second item is not provided.");

        // If there were two items
        if (items.length == 2) {

            // Call upgrade for two items, only the upgradables are not epic of level 0 or 1 
            if (!Rarity.equals(ItemRarity.EPIC) || UpgradeCount == 2) return Upgrade((Item)items[0], (Item)items[1]);

            // Otherwise
            throw new IllegalArgumentException("Two items cannot be used to upgrade epic items of level lower than 2.");
        }

        //If there was one item
        if (items.length == 1) {

            // Call upgrade for one item, only if the upgradables are epic of level 0 or 1
            if (Rarity.equals(ItemRarity.EPIC) && UpgradeCount != 2) return Upgrade((Item)items[0]);

            // Otherwise
            throw new IllegalArgumentException("Epic items of level lower than 2 must be upgraded with one item, not two.");
        }

        // If all the rest cases were missed
        throw new IllegalArgumentException("Illegale argument exception.");
    }

    private Item Upgrade(Item item1, Item item2) {
        if (item1.Rarity.equals(item2.Rarity) && item1.Rarity.equals(Rarity)) {
            if (item1.Name.equals(item2.Name) && item1.Name.equals(Name)) {
                Rarity = ItemRarity.getNext(Rarity);
                if (Rarity == ItemRarity.LEGENDARY) setUpgradeCount(0);
                return new Item(this);
            }
            throw new IllegalArgumentException("Item names don't match.");
        }
        throw new IllegalArgumentException("Item rarities don't match.");
    }

    private Item Upgrade(Item item1) {
        if (item1.Rarity == Rarity) {
            setUpgradeCount(UpgradeCount+1);
            return new Item(this);
        }
        throw new IllegalArgumentException("Item rarities don't match.");
    }
}