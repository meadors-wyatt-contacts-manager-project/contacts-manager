# Creating the Main Menu Screen

In our game, the main menu screen will present the user with options for:
1. exiting the application
2. creating a player (required in order to start the adventure)
3. starting the adventure (will be hidden until a player is created)


## Making the Main menu screen

The Main menu screen, or `MainScreen`, will display the top-level menu items to the user, prompt the user until an appropriate selection is made, and then load the relevant screen.

The `Screen` superclass provides menu capabilities by default, and `MainScreen` will just use those.

To create the set of menu items, override `setupMenu()`. Call the superclass method first, in case it needs to initialize some things. Next, call `menu.addItem` to add each menu item.

Menu items are typically key/value pairs, where:
- the key is a string that is used as the menu's label.
- the value is a function. If you give `menu.addItem` a `Screen` subclass, it creates a function that will load the given screen.

`menu.addItem` has a 3-argument constructor, where the 3rd option is a boolean where the value `true` will hide the menu item from console output. The following function adds 3 items to the screen's menu. The first 2 items contain references to locally defined functions that will execute when its respective menu item is chosen. The third menu item will EVENUTALLY navigate the player to the `EntranceScreen` when it is selected. For now, let's just point it to a stub function. Note that the third item is hidden initially, but will unhide once the user has created a player (i.e., selected the second menu item).

```java
@Override
public void setup() {
    super.setup();
    menu.addItem("Exit game", exitProgram);
    menu.addItem("Create player", createPlayer);
    menu.addItem("Start the adventure", startAdventure, true);
}
```

The `exitProgram` function prints out a simple message and then calls `Screen`'s `exit()` function. The `exitProgram` function is a functional interface of type `MenuItemMethod` and can be declared as:
```java
private MenuItemMethod exitProgram = () -> {
    System.out.println("Exiting main screen...");
    exit();
};
```

The `createPlayer` function prompts and receives a player name from the user and creates a new `Player` object from the game's `Player` class. The `Player` class is a simple POJO that has the game-specific info that we want for keeping track of the user playing the game. For this tutorial, it will be just the player's `name`.

First, create a package called `characters`. The `Player` class will go in this package.

Next, make the `Player` class using the code below:

`Player.java`
```java
package docrob.cag.mygame.characters;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Player {
    private String name;

}
```

Note that the `Player` class is part of this example game and not provided by the framework. If your game needs a data type to represent the user then feel free to create your own, or use the one provided below as a model.

After creating the `player` object, we save it to the game's global state manager, which is a key/value store:
- the key is a string representing whatever you are storing
- the value is the object that is being stored

Note that the `Game` object is a singleton and must have its `getInstance` function called before you can call any of its methods.

Lastly, after the player is created and stored, we unhide the "Start the adventure" menu item.

```java
private MenuItemMethod createPlayer = () -> {
    String name = Game.getInstance().getInput().getString("\nEnter your name: ");
    Player player = new Player(name);
    Game.getInstance().addStateItem("player", player);

    menu.getItemFromLabel("Start the adventure").setHidden(false);
};
```

The final menu item function is a simple stub:

```java
private MenuItemMethod startAdventure = () -> {
    System.out.println("TODO: start the adventure");
};
```

Next, we need to override the `show` function that outputs the screen's description to the console. If you are using `Screen`'s built-in menu, then you should call `super.show()` before or after your screen's description, depending on whether you want the menu options to print before or after your screen's description. 

For `MainScreen`, we also want to print the player's name to visually confirm that a player has been created. Note that we call `Game`'s `getStateItem` function to give us the player's object if it has been created. We tell `getStateItem` the data type of the object so that it may cast it for us before returning it.

```java
@Override
protected void show() {
    Player player = Game.getInstance().getStateItem("player", Player.class);
    String playerInfo = "";
    if(player != null) {
        playerInfo = "\t\tPlayer: " + player.getName();
    }
    System.out.println(ConsoleColors.ANSI_CLEAR + "\nMain Screen" + playerInfo);

    super.show();
}
```

Finally, we need to hook up the `WelcomeScreen` to transition to then `MainScreen` after the user presses the enter key. Here is the revised `handleInput` function in the `WelcomeScreen`.
`WelcomeScreen`
```java
@Override
protected void handleInput() {
    Game.getInstance().getInput().getString("Press Enter to continue.");

    ScreenManager.addScreen(new MainScreen());
}
```

Below is the complete `MainScreen.java`.

You should now be able to run the game, make a player by providing a player name, select "Start the adventure", and exit the game.

```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.menu.MenuItemMethod;
import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.state.Game;
import docrob.cag.framework.utils.ConsoleColors;

public class MainScreen extends Screen {

    public MainScreen() {
    }

    @Override
    public void setup() {
        super.setup();
        menu.addItem("Exit game", exitProgram);
        menu.addItem("Create player", createPlayer);
        menu.addItem("Start the adventure", startAdventure, true);
    }

    @Override
    protected void show() {
        Player player = Game.getInstance().getStateItem("player", Player.class);
        String playerInfo = "";
        if (player != null) {
            playerInfo = "\t\tPlayer: " + player.getName();
        }
        System.out.println(ConsoleColors.ANSI_CLEAR + "\nMain Screen" + playerInfo);

        super.show();
    }

    private MenuItemMethod createPlayer = () -> {
        String name = Game.getInstance().getInput().getString("\nEnter your name: ");
        Player player = new Player(name);
        Game.getInstance().addStateItem("player", player);

        menu.getItemFromLabel("Start the adventure").setHidden(false);
    };

    private MenuItemMethod exitProgram = () -> {
        System.out.println("Exiting main screen...");
        setReadyToExit();
    };

    private MenuItemMethod startAdventure = () -> {
        System.out.println("TODO: start the adventure");
    };
}
```

[Next: the adventure's starting screen](entrancescreen.md)