# Winning the game

A game needs a way for the player to win the game. When the goblin is vanquished, the player will receive THE Gaudy Crown of Victory.

On the `Player` side, we can simply use a boolean to represent whether the player has the crown or not. We will add a getter called `hasCrown()` for readability. Lombok will automatically add a getter and setter for us, but the method name for the getter may not be great.

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

    private int health;

    private boolean hasCrown;

    public Player(String name) {
        this.name = name;
        health = 10;
        hasCrown = false;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean hasCrown() {
        return hasCrown;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health < 0) {
            health = 0;
        }
    }
}
```

Next, let's reward the player with the crown when the goblin dies. In `WestScreen`, add a couple of lines to the `hitGoblin` method.

From this:
```java
...
private void hitGoblin() {
    System.out.println("You hit the goblin!");
    MyGame.getGoblin().setHealth(MyGame.getGoblin().getHealth() - 5);
    if(!MyGame.getGoblin().isAlive()) {
        System.out.println("The goblin has died.");
    }
    Game.getInstance().getInput().getString("Press Enter to continue.");
}
```

to this:
```java
...
private void hitGoblin() {
    System.out.println("You attack the goblin and HIT!");
    MyGame.getGoblin().setHealth(MyGame.getGoblin().getHealth() - 5);
    if(!MyGame.getGoblin().isAlive()) {
        System.out.println("The goblin has died.");
        if(!MyGame.getPlayer().hasCrown()) {
            System.out.println("You retrieve THE Gaudy Crown of Victory from the goblin's twitching corpse.");
            MyGame.getPlayer().setHasCrown(true);
            // goblin is not alive so hide the menu choice
            menu.getItemFromLabel("Smite the goblin").setHidden(true);
        }
    }
    Game.getInstance().getInput().getString("Press Enter to continue.");
}
```

Lastly, let's add a hidden menu option to the `EntranceScreen` that reveals itself if the player has the crown. If the player selects the `Win the game` menu option, then we will print a happy message and end the game. This involves modifying the `setup` and `show` methods, as well as adding a new method `winTheGame`. Here is the fully modified `EntranceScreen.java`:

`EntranceScreen.java`

```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.menu.MenuItemMethod;
import docrob.cag.framework.screens.Resettable;
import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.screens.ScreenManager;
import docrob.cag.framework.state.Game;
import docrob.cag.framework.utils.ConsoleColors;

public class EntranceScreen extends Screen implements Resettable {
    @Override
    public void setup() {
        super.setup();
        menu.addItem("Run away!!", new MainScreen());
        menu.addItem("Go west", new WestScreen());
        menu.addItem("Go east", new EastScreen());
        menu.addItem("Win the game", winTheGame, true);
    }

    @Override
    protected void show() {
        System.out.println(ConsoleColors.ANSI_CLEAR);
        System.out.println("You are at the entrance to a scary maze." +
                "\nFrom here, you can run out of the maze, go west, or go east.");

        // if player has the winning game item, then show the menu
        // else hide it in case they have come back after previously winning
        if (MyGame.getPlayer().hasCrown()) {
            menu.getItemFromLabel("Win the game").setHidden(false);
        } else {
            menu.getItemFromLabel("Win the game").setHidden(true);
        }

        super.show();

    }

    private MenuItemMethod winTheGame = () -> {
        Game.getInstance().getInput().getString("You won the game! Yay...\n\nPress Enter to continue.");

        ScreenManager.addScreen(new MainScreen());
        setReadyToExit();
    };
}
```

Run the game and you will be able to officially "win" the game after defeating the goblin.

[Next: Where to go from here](nextsteps.md)