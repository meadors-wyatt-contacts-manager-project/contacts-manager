# Attacking the goblin

We need to add a hidden menu option to the `WestScreen` that will unhide IF the goblin is still alive. To do that, we need to change two things:
1. add a hidden menu option in the `setup()` method for the screen
2. reveal the hidden menu option in the `show()` method IF the goblin is alive

Lastly, we must add a method in `WestScreen` to attack the goblin. The method will have a 75% of hitting the goblin. If the goblin is hit, we will use its getters and setters to adjust its health. We won't bother adjusting our health yet if we are hit.

Note that we use separate methods for hitting the goblin and being hit by the goblin. Don't put a LOT of code in a single method. A good rule of thumb is that every method should be <= 20 lines of code. It is ok for a class to have a lot of methods. It is not ok for a single method to have a lot of code.

Below is the modified `WestScreen`:

`WestScreen.java`

```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.menu.MenuItemMethod;
import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.state.Game;
import docrob.cag.framework.utils.ConsoleColors;

public class WestScreen extends Screen {
    @Override
    public void setup() {
        super.setup();
        menu.addItem("Go east", new EntranceScreen());
        menu.addItem("Smite the goblin", attackGoblin, true);
    }

    @Override
    protected void show() {
        System.out.println(ConsoleColors.ANSI_CLEAR);
        System.out.println("You are in the west part of the maze." +
                "\nFrom here, you can go east to return to the entrance.");

        if (MyGame.getGoblin().isAlive()) {
            System.out.println("\nThere is a goblin here. " + MyGame.getGoblin().toString());
            menu.getItemFromLabel("Smite the goblin").setHidden(false);
        } else {
            // goblin is not alive so hide the menu item, just in case it was unhidden previously
            menu.getItemFromLabel("Smite the goblin").setHidden(true);
        }
        super.show();

    }

    private MenuItemMethod attackGoblin = () -> {
        if (Game.getInstance().getRandomInt(1, 100) <= 75) {
            hitGoblin();
        } else {
            hitByGoblin();
        }
    };

    private void hitGoblin() {
        System.out.println("You hit the goblin!");
        MyGame.getGoblin().setHealth(MyGame.getGoblin().getHealth() - 5);
        if (!MyGame.getGoblin().isAlive()) {
            System.out.println("The goblin has died.");
        }
        Game.getInstance().getInput().getString("Press Enter to continue.");
    }

    private void hitByGoblin() {
        System.out.println("The goblin hits you!");
        Game.getInstance().getInput().getString("Press Enter to continue.");
    }
}
```

Now, run the game and test attacking the goblin. Once the goblin dies, it should only respawn if the `Player` leaves the `EntranceScreen`.

[Next: Taking damage from the goblin](goblindamage.md)