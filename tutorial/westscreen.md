# Creating the West screen

In this screen, the player will fight with a monster (a goblin). The goblin does not provide much of a challenge. If the player defeats the goblin, the player receives the treasure needed to win the game. If the player is particularly unlucky, then the player dies.

Note that this screen DOES implement `Resettable`.

First, let's make the basic WestScreen class, plug it into the game, and test it. 

Copy `EastScreen.java` and name it `WestScreen.java`, and modify it appropriately. Here is a basic `WestScreen`.

`WestScreen.java`

```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.utils.ConsoleColors;

public class WestScreen extends Screen implements Resettable {
    @Override
    public void setup() {
        super.setup();
        menu.addItem("Go east", new EntranceScreen());
    }

    @Override
    protected void show() {
        System.out.println(ConsoleColors.ANSI_CLEAR);
        System.out.println("You are in the west part of the maze." +
                "\nFrom here, you can go east to return to the entrance.");

        super.show();

    }

}
```

Also, make the following change to `EntranceScreen`. Change:

```java
menu.addItem("Go west", westStub);
```

to 

```java
menu.addItem("Go west", new WestScreen());
```

and remove the `westStub` method. Test your changes by running the game. You should be able to go west from the entrance, and 

---

### Making the goblin

Create a Goblin class in the `characters` package. Let's think through the specification for the goblin:

- The goblin can take two hits before dying
- The player can take two hits before dying
- The goblin will persist even if you leave the screen
- Once the goblin is dead, it does not return while you stay in the maze
- If you leave the game (i.e., choose "0" from the entrance) then the goblin will respawn
- If the player attacks the goblin, there is a 75% chance that the player hits the goblin, and a 25% chance that the goblin hits the player
- When the goblin dies, the player will automatically receive the treasure needed to win the game.

The goblin class will be a simple POJO with an int field for its health. It will start with 10 health. If it is hit by the player, its health will drop to 5. If it is hit again, its health drops to 0 and the goblin dies. Put the following class in your `charcters` package.

`Goblin.java`
```java
package docrob.cag.mygame.characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Goblin {
    private int health;

    public Goblin() {
        health = 10;
    }

    @Override
    public String toString() {
        if(health > 5) {
            return "The goblin looks uninjured.";
        } else {
            return "The goblin looks close to death.";
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}
```

We also need to change the `initGame` method in `MyGame.java` so that it creates a new goblin each time the game starts. Change the `initGame` method to this:
```java
public static void initGame() {
    Game.getInstance().addStateItem("goblin", new Goblin());
    // reset menus for all resettable screens
    ScreenManager.resetScreens();
}
```

Let's add the following convenience getter for the goblin in `MyGame.java`:
```java
public static Goblin getGoblin() {
    return Game.getInstance().getStateItem("goblin", Goblin.class);
}
```


Now, let's reveal the goblin in the `WestScreen`, if it is alive. Change your `show()` method in the `WestScreen` to this:
```java
protected void show() {
    System.out.println(ConsoleColors.ANSI_CLEAR);
    System.out.println("You are in the west part of the maze." +
            "\nFrom here, you can go east to return to the entrance.");

    if(MyGame.getGoblin().isAlive()) {
        System.out.println("\nThere is a goblin here. " + MyGame.getGoblin().toString());
    }
    super.show();

}
```

Run your game and you will see the goblin in the `WestScreen`. Yikes!

[Next: Attacking the goblin](attackgoblin.md)