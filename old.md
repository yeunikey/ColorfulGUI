<h1 align="center">This is <a target="_blank">ColorfulGUI</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>
<h3 align="center">Comfortable API for creating and managing inventory</h3>

<h5>Note: requires Paper</h5>

[Для Русской документации кликните здесь](https://github.com/xflyiwnl/ColorfulGUI/blob/master/RU.md)

<h3>Installation</h3>

To work with Maven:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.xflyiwnl</groupId>
    <artifactId>ColorfulGUI</artifactId>
    <version>v1.3</version>
</dependency>
```

<h3>Starting work</h3>

Before you can start working with the API, you need to register `ColorfulGUI`:
```java
ColorfulGUI colorfulGUI = new ColorfulGUI(plugin);
```
Put your main class in the `plugin` field and store the registered `ColorfulGUI` in your main class or somewhere else so that we can refer to it when needed.

<h3>Examples</h3>

<h4>Creating Items</h4>

The `ItemBuilder` class is responsible for creating items. Creating a simple functional item goes like this:
```java
GuiItem simpleItem = colorfulGui.item()
                .material(Material.GRASS_BLOCK)
                .name("SimpleItem")
                .lore(Arrays.asList(
                        "1",
                        "2",
                        "3"
                ))
                .amount(1)
                .action(event -> {
                    getPlayer().sendMessage("you clicked a simple item.")
                })
                .build();
```
You can also add other attributes `.model(int)`, `.flags(ItemFlag...)`, `.enchant(Enchantment, int)`, `.unbreakable(boolean)` to the item.

The `.action()` attribute is responsible for clicking the item when `InventoryClickEvent` is called, which checks for item uniqueness by key in advance.

<h5>Note</h5>

All Strings such as names and lore accept color codes, either pre-1.16 ones `&a, &b, &c...`or hex color codes in format `#abc123` (capital letters also work). 
Alternatively, if you put two hex color codes in the front and end of a string like this `#123456Example#abcdef`, a color gradient will be created.

<h4>Creating the provider</h4>

Create a class and name it whatever we want, then inherit `ColorfulProvider<?>`. In the `?` field specify the type of inventory, for example `PaginatedGui` or `Gui`. They differ in that one can have multiple pages and the other is limited to only one page. Let's move on to creation:
```java
public class TestProvider extends ColorfulProvider<Gui>
```
After that, let's create a constructor:
```java
public TestProvider(Player player) {
        super(player);
    }
```
or
```java
public TestProvider(Player player, int updateTime) {
        super(player, updateTime);
    }
```
In the first version of the constructor, the inventory will not be updated and the `update()` method will not be called. In the second constructor, instead of `int updateTime` we write any digit above `0`. If the digit is lower or equal to `0`, the inventory will not be updated. The digit is specified in seconds. If we specify `1`, the inventory will be updated every `1` seconds.

It's time to create a method that will be called as soon as our inventory is created:
```java
 @Override
    public void init() {
      // todo, fill the inventory with items and show the inventory to the player
      show();
    }
```
In the `init()` method, it is recommended that you fill your inventory with items and call the player inventory opener `show()`

Now, in order for the inventory to be opened, let's make a static method `static void showGUI(Player)`:
```java
public static void showGUI(Player player player) {
        colorfulGui.gui()
                .holder(new TestProvider(player))
                .title("Menu")
                .rows(5)
                .build();
    }
```
`.rows(int)` is responsible for the height size of the inventory. `.title(String)` the name of the inventory. `.holder(ColorfulProvider<?>)` the provider of our inventory that the `TestProvider<Gui>` just created.

<h4>Working with a mask</h4>

We have a handy mask system in our API. Here is an example of creating a mask:
```java
public static void showGUI(Player player player) {
        colorfulGui.gui()
                .holder(new TestProvider(player))
                .title("Menu")
                .rows(5)
                .mask(Arrays.asList(
                                "BBBBBBBBB",
                                "BOOOOOOOB",
                                "BOOOSOOOB",
                                "BOOOOOOOB",
                                "BBBBBBBBB"
                        )
                )
                .build();
    }
```
And assigning the item its indicator:
```java
getGui().getMask().addItem("S", simpleItem);
```
<h4>Listeners</h4>

When the inventory updated:
```java
@Override
public void update() {
    getPlayer().sendMessage("update event");
}
```
When the inventory is clicked:
```java
@Override
public void onClick(InventoryClickEvent event) {
    getPlayer().sendMessage("click event");
}
```
When the inventory is opened:
```java
@Override
public void onOpen(InventoryOpenEvent event) {
    getPlayer().sendMessage("open event");
}
```
When the inventory is closed:
```java
@Override
public void onClose(InventoryCloseEvent event) {
    getPlayer().sendMessage("close event");
}
```
When the item was dragged:
```java
@Override
public void onDrag(InventoryDragEvent event) {
    getPlayer().sendMessage("drag event");
}
```
<h4>Summary</h4>

We end up with a class like this:
```java
public class TestProvider extends ColorfulProvider<Gui> {
    
    public TestProvider(Player player) {
        super(player, 1);
    }

    @Override
    public void init() {

        GuiItem borderItem = colorfulGui.item()
                .material(Material.BLACK_STAINED_GLASS_PANE)
                .name("Барьер")
                .lore(Arrays.asList(
                        "1",
                        "2",
                        "3"
                ))
                .amount(1)
                .action(event -> {
                    getPlayer().sendMessage("ты кликнул по барьеру");
                })
                .build();
        getGui().getMask().addItem("B", borderItem);

        GuiItem simpleItem = colorfulGui.item()
                .material(Material.GRASS_BLOCK)
                .name("Просто предмет")
                .lore(Arrays.asList(
                        "1",
                        "2",
                        "3"
                ))
                .amount(1)
                .action(event -> {
                    getPlayer().sendMessage("ты кликнул по простому предмету");
                })
                .build();
        getGui().getMask().addItem("S", simpleItem);

        show();
    }

    @Override
    public void update() {
        getPlayer().sendMessage("update event");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        getPlayer().sendMessage("click event");
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        getPlayer().sendMessage("open event");
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        getPlayer().sendMessage("close event");
    }

    @Override
    public void onDrag(InventoryDragEvent event) {
        getPlayer().sendMessage("drag event");
    }

    public static void showGUI(Player player) {
        colorfulGui.gui()
                .holder(new TestProvider(player))
                .title("Меню")
                .rows(5)
                .mask(Arrays.asList(
                                "BBBBBBBBB",
                                "BOOOOOOOB",
                                "BOOOSOOOB",
                                "BOOOOOOOB",
                                "BBBBBBBBB"
                        )
                )
                .build();
    }

}
```

And we get a menu like this:

![image](https://github.com/xflyiwnl/ColorfulGUI/assets/108489760/33fff71c-adc9-4e9a-b801-c517ca880d3f)