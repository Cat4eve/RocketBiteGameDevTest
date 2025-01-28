**Overview**

The main file is in the App class. Hence, you must run that file and make your tests there.
The system consists of two classes: Item and Inventory management.

Item class is relatively easy to understand; the class is defined in accordance with OOP principles.

Inventory management is based on two inventories.
The first inventory is for custom grouping based on any string. We assume that the item will not be used in two groups at the same time. Also, it is assumed that the upgrade will be performed on one group, which means that every item of that custom group will be used during the upgrade. The first inventory is absolutely according to the rule of deletion after the upgrade.

The second inventory is based on the rarities. It has only five groups, which are created immediately, and users are not designed to interact with them.
The items remain there even if they will be deleted because of the upgrade operation, as a reminder (history) that such objects ever existed.

The random item generation is based on a random module.

The write and save options are raw - based on file streams, the input must be according to the stringified output of map format, which is somehow similar to JSON format.


**Compilation and Run**

To compile and run the program, do the following steps.

```
javac src/Item.java
javac src/InventorySystem.java
javac src/App.java
```

```
java App
```
