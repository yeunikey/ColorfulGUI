<h1 align="center">Это - <a target="_blank">ColorfulGUI</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>
<h3 align="center">Удобный API для создания и управления инвентарями</h3>

<h5>Примечание: требует Paper</h5>

[For English 🇬🇧 translation click here](https://github.com/xflyiwnl/ColorfulGUI/blob/master/ENG.md)

<h3>Установка</h3>

Для работы с Maven:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.xflyiwnl</groupId>
    <artifactId>ColorfulGUI</artifactId>
    <version>v1.1</version>
</dependency>
```

<h3>Начало работы</h3>

Перед началом работы с API, вам необходимо зарегестрировать `ColorfulGUI`:
```java
ColorfulGUI colorfulGUI = new ColorfulGUI(plugin);
```
В поле `plugin` вставляем ваш главный класс и храним зарегестрированный `ColorfulGUI` в вашем главном классе или где-нибудь ещё, чтобы когда нужно мы обращались к нему.

<h3>Примеры</h3>

<h4>Создание предметов</h4>

Для создания предметов отвечает класс `ItemBuilder`. Создание простого функционального предмета происходит так:
```java
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
```
Также, вы сможете добавить предмету и другие аттрибуты `.model(int)`, `.flags(ItemFlag...)`, `.enchant(Enchantment, int)`, `.unbreakable(boolean)`.

Аттрибут `.action()` отвечает за клик предмета при вызове `InventoryClickEvent`, в котором заранее проверяется на уникальность предмета по ключам.

<h4>Создание провайдера</h4>

Создаём класс и называем его как хотим, после наследуем `ColorfulProvider<?>`. В поле `?` указываем тип инвентаря, к примеру `PaginatedGui` или `Gui`. Отличаются они тем, что один может иметь несколько страниц, а другой ограничен лишь одной страницей. Перейдём к созданию:
```java
public class TestProvider extends ColorfulProvider<Gui>
```
После, создаём конструктор:
```java
public TestProvider(Player player) {
        super(player);
    }
```
или
```java
public TestProvider(Player player, int updateTime) {
        super(player, updateTime);
    }
```
В первом варианте конструктора инвентарь не будет обновляться и метод `update()` не будет вызываться. Во втором конструкторе вместо `int updateTime` записываем любую цифру выше `0`. Если цифра будет ниже или равна `0`, то инвентарь обновляться не будет. Цифра указывается в секундах. Если укажем `1`, то инвентарь будет обновляться каждые `1` секунд.

Пришло время создать метод, который будет вызываться как только наш инвентарь будет создан:
```java
 @Override
    public void init() {
      // todo, заполнение инвентаря предметами и показ инвентаря игроку
      show();
    }
```
В методе `init()` рекомендуется заполнять ваш инвентарь предметами и вызывать открыватель инвентаря для игрока `show()`

Теперь, чтобы инвентарь можно было открыть, сделаем статичный метод `static void showGUI(Player)`:
```java
public static void showGUI(Player player) {
        colorfulGui.gui()
                .holder(new TestProvider(player))
                .title("Меню")
                .rows(5)
                .build();
    }
```
`.rows(int)` отвечает за размер инвентаря по высоте. `.title(String)` название инвентаря. `.holder(ColorfulProvider<?>)` провайдер нашего инвентаря, которого только что создали `TestProvider<Gui>`.

<h4>Работа с маской</h4>

В нашем API есть удобная система маски. Пример создания маски:
```java
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
```
И присвоение предмету его индикатор:
```java
getGui().getMask().addItem("S", simpleItem);
```

<h4>Слушатели</h4>

Когда инвентарь обновился:
```java
@Override
public void update() {
    getPlayer().sendMessage("update event");
}
```
Когда кликнули по инвентарю:
```java
@Override
public void onClick(InventoryClickEvent event) {
    getPlayer().sendMessage("click event");
}
```
Когда открыли инвентарь:
```java
@Override
public void onOpen(InventoryOpenEvent event) {
    getPlayer().sendMessage("open event");
}
```
Когда закрыли инвентарь:
```java
@Override
public void onClose(InventoryCloseEvent event) {
    getPlayer().sendMessage("close event");
}
```
Когда перетаскивали предмет:
```java
@Override
public void onDrag(InventoryDragEvent event) {
    getPlayer().sendMessage("drag event");
}
```

<h4>Итог</h4>

В итоге у нас получится такой класс:
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

И такое меню:

![image](https://github.com/xflyiwnl/ColorfulGUI/assets/108489760/33fff71c-adc9-4e9a-b801-c517ca880d3f)
