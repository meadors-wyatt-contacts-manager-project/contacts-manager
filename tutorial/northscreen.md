# Creating the North screen

This screen will kill the player. Sounds awful, but this part of the tutorial will show you how you can force the player to restart the game.

Note that this screen does NOT need to implement `Resettable`, as it has no menu items that hidden/unhidden.

We will customize the `show` and `handleInput` methods.

Below is the `show` method:
```java
@Override
protected void show() {
    System.out.println(ConsoleColors.ANSI_CLEAR);
    System.out.println("You have entered the north room." +
            "\nYou fall into a pit of spikes and die. Sorry! :(");
    super.show();
}
```

And here is the `handleInput` method:
```java
    @Override
    protected void handleInput() {
        Game.getInstance().getInput().getString("Press Enter to continue.");

        MyGame.killPlayer();
        
        ScreenManager.addScreen(new MainScreen());
    }
```

Notice that we are calling a method specific to our game for killing the player. At its simplest, removing the current player object from the game should remove the player object from the `Game`'s state map. We will add this and another method statically to `MyGame` for convenience. Anytime you want to kill the player, call `MyGame.killPlayer()` and add `MainScreen` to the `ScreenManager`. Anytime you want to access the player's information, call `MyGame.getPlayer()`.  

The `getPlayer` method calls `Game`'s `getStateItem` method which fetches an object from the `Game`'s state hashmap. Because any `Object` can be stored in the `Game`'s hashmap, you must tell Java the data type of the object you want from the hashmap. That is the reason for the `Player.class` argument. The call to the `ScreenManager.emptyCache` method clears the screen cache, so that ALL game screens start over with freshly initialized data, including the `MainMenu` screen.

Add these two methods to `MyGame.java`.
```java
public static Player getPlayer() {
    return Game.getInstance().getStateItem("player", Player.class);
}

public static void killPlayer() {
    System.out.println("Removing player " + getPlayer().getName() + "...");
    Game.getInstance().removeStateItem("player");
    // emptyCache() removes ALL screens from the cache,
    // meaning that all screens will start over with a fresh new object
    // (e.g., all hidden menu items that were previously revealed will be hidden again)
    ScreenManager.emptyCache();
}
```

Lastly, remove the `northStub` method from `EastScreen` and replace the following line in `EastScreen`:
```java
menu.addItem("Go north", northStub);
```
with
```java
menu.addItem("Go north", new NorthScreen());
```

Below is the complete `NorthScreen` class. You should be able to run the game and go north from the east room and be killed. Smashing!

`NorthScreen.java`

```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.screens.ScreenManager;
import docrob.cag.framework.state.Game;
import docrob.cag.framework.utils.ConsoleColors;

public class NorthScreen extends Screen {
    @Override
    protected void show() {
        System.out.println(ConsoleColors.ANSI_CLEAR);
        System.out.println("You have entered the north room." +
                "\nYou fall into a pit of spikes and die. Sorry! :(");
        super.show();
    }

    @Override
    protected void handleInput() {
        Game.getInstance().getInput().getString("Press Enter to continue.");

        MyGame.killPlayer();

        ScreenManager.addScreen(new MainScreen());
    }
}
```

[Next: the west screen](westscreen.md)