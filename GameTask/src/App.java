public class App {
    public static void main(String[] args) throws Exception {
        InventorySystem IS = new InventorySystem();
        Item item1 = IS.CreateItem("Sword", Item.ItemRarity.COMMON);
        Item item2 = IS.CreateItem("Sword", Item.ItemRarity.COMMON);
        Item item3 = IS.CreateItem("Sword", Item.ItemRarity.COMMON);
        IS.DisplayInventory();
        IS.AssignItemToGroup(item1, "A");
        IS.AssignItemToGroup(item2, "A");
        IS.AssignItemToGroup(item3, "A");
        IS.UpgradeItemInGroup(item1, "A");
    }
}
