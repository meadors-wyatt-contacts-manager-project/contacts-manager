# Taking damage and possibly dying

Let's enhance the `Player` object to track health. Just like the goblin, let's start with 10 health, and if the goblin hits us, we will lose 5 health. If the `Player` is hit twice (unlikely, but possible), then the `Player` will die and be sent back to the `MainScreen`.

Let's also add a convenient function to apply damage to the `Player`'s health. The modified `Player` class looks like this:

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

    public Player(String name) {
        this.name = name;
        health = 10;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if(health < 0) {
            health = 0;
        }
    }
}
```

Next, let's modify the `WestScreen`'s `hitByGoblin` method to damage the `Player`.

```java
private void hitByGoblin() {
    System.out.println("You attack the goblin and MISS! The goblin punches you.");
    MyGame.getPlayer().takeDamage(5);
    if(!MyGame.getPlayer().isAlive()) {
        Game.getInstance().getInput().getString("You have died.\nPress Enter to continue.");
        MyGame.killPlayer();
        ScreenManager.addScreen(new MainScreen());
        setReadyToExit();
        return;
    }
    Game.getInstance().getInput().getString("Press Enter to continue.");
}
```

Run and test the game. You might change the value in `attackGoblin` from `75` to a smaller number like `10`. This will increase the likelihood that you will miss the goblin and be damaged. Once you feel the code is working, be sure to change that number back to `75`, unless you like restarting the game.

[Next: winning the game](wingame.md)