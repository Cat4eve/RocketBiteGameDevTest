# RocketBiteGameDevTest

The main file is in App class, hence, you must run that file and make your tests there.
The system consists of two classes, Item and Inventory management.

Item class is relatively easy for understanding, the class is defined in accordance with OOP principles.

Inventory management is based on two ineventories.
First inventory for custom grouping, based on any string. We assume that item will not be used in two groups at the same time. Also, it is assumed that the upgrade will be performed on one group, which means that every item of that custom group will be used during the upgrade. The first inventory is absolutely according the rule of deletion after upgrade.

Second inventory is based on the rarities. It has only five groups, which are created immediately and user are not designed to interact with them.
The items remain there even if they will be deleted because of the upgrade operation, as a remineder (history) that such object ever existed.

The random item generateion is based on random module.

The write and save options are raw - based on file streams, the input must be according to the stringified output of map format, which is somehow similar to json format.
