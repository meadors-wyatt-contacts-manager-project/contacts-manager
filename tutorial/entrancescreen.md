# Creating the maze Entrance screen

This screen is the start of the adventure: the place where the player begins.

The `EntranceScreen` will have east and west paths. It will also have a menu option to return to the main menu screen. The east path will lead to the `RedScreen`. The west path will lead to the `GreenScreen`. Both screens have not been made so we will stub these out.

Note that one or two of our screens will implement a framework interface called `Resettable`. This is a simple tagging interface that allows the screen implementing it to have their menus reset when the adventure is about to start. This is useful if the screen has a hidden menu item that becomes revealed during the course of the adventure, but you want it to become hidden again when the adventure starts over. 

We will customize the `show` and `setup` methods.

The `show` method is the description for the entrance. Remember to call `super.show()` if you want the superclass to display the menu items in the screen.
```java
@Override
protected void show() {
    System.out.println(ConsoleColors.ANSI_CLEAR);
    System.out.println("You are at the entrance to a scary maze." +
    "\nFrom here, you can run out of the maze, go west, or go east.");

    super.show();
}
```

In the `setup` method, we will add the 3 menu items:
```java
@Override
public void setup() {
    super.setup();
    menu.addItem("Run away!!", new MainScreen());
    menu.addItem("Go west", westStub);
    menu.addItem("Go east", eastStub);
}
```

Next, add an `initGame` method to the `MyGame` class. This method handles everything that is necessary to prepare the maze before a player enters it. For now, the only thing this method will do is to have the `ScreenManager` tell all of the screens (that implement the `Resettable` interface) to reset their menus.
```java
public static void initGame() {
    // reset menus for all resettable screens
    ScreenManager.resetScreens();
}
```

Lastly, modify the `startAdventure` method in the `MainScreen`, replace the entire method with:
```java
private MenuItemMethod startAdventure = () -> {
    MyGame.initGame();
    ScreenManager.addScreen(new EntranceScreen());
    setReadyToExit();
};
```

Below is the complete `EntranceScreen` class. You should be able to run the game and enter the maze.

`EntranceScreen.java`

```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.utils.ConsoleColors;

public class EntranceScreen extends Screen implements Resettable {

    @Override
    public void setup() {
        super.setup();
        menu.addItem("Run away!!", new MainScreen());
        menu.addItem("Go west", westStub);
        menu.addItem("Go east", eastStub);
    }

    @Override
    protected void show() {
        System.out.println(ConsoleColors.ANSI_CLEAR);
        System.out.println("You are at the entrance to a scary maze." +
                "\nFrom here, you can run out of the maze, go west, or go east.");

        super.show();

    }

    private MenuItemMethod westStub = () -> {
        System.out.println("TODO: go west");
    };

    private MenuItemMethod eastStub = () -> {
        System.out.println("TODO: go east");
    };
}
```

[Next: the east screen](eastscreen.md)