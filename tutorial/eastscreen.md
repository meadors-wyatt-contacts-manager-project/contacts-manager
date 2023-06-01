# Creating the East screen

This screen is the place east of the maze entrance. It has a path leading north and west returns the player back to the entrance.

Note that this screen does NOT need to implement `Resettable`, as it has no menu items that hidden/unhidden. 

We will customize the `show` and `setup` methods.

Below is the `show` method:
```java
@Override
protected void show() {
    System.out.println(ConsoleColors.ANSI_CLEAR);
    System.out.println("You are in the east part of the maze." +
            "\nFrom here, you can go west to return to the entrance, or go north.");

    super.show();

}
```

And here is the `setup` method:
```java
@Override
public void setup() {
    super.setup();
    menu.addItem("Go west", new EntranceScreen());
    menu.addItem("Go north", northStub);
}
```

Lastly, remove the `eastStub` method from `EntranceScreen` and replace the following line in `EntranceScreen`:
```java
menu.addItem("Go east", eastStub);
```
with
```java
menu.addItem("Go east", new EastScreen());
```

Below is the complete `EastScreen` class. You should be able to run the game and go east from the entrance.

`EastScreen.java`
```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.menu.MenuItemMethod;
import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.utils.ConsoleColors;

public class EastScreen extends Screen {
    @Override
    public void setup() {
        super.setup();
        menu.addItem("Go west", new EntranceScreen());
        menu.addItem("Go north", northStub);
    }

    @Override
    protected void show() {
        System.out.println(ConsoleColors.ANSI_CLEAR);
        System.out.println("You are in the east part of the maze." +
                "\nFrom here, you can go west to return to the entrance, or go north.");

        super.show();

    }

    private MenuItemMethod northStub = () -> {
        System.out.println("TODO: go north");
    };
}
```

[Next: the north screen](northscreen.md)