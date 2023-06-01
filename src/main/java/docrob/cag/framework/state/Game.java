package docrob.cag.framework.state;

import docrob.cag.framework.utils.Input;

import java.util.HashMap;

// manages global game state info
// and input
// uses singleton since there should only be one of these per game
public class Game {
    private static Game game = null;

    private HashMap<String, Object> stateItems;

    private Game() {
        stateItems = new HashMap<>();
        stateItems.put("input", new Input());
    }

    public Input getInput() {
        return getStateItem("input", Input.class);
    }

    public void addStateItem(String key, Object item) {
        stateItems.put(key, item);
    }

    public <T> T getStateItem(String key, Class<T> className) {
        Object o = stateItems.get(key);
        return (T) o;
    }

    public static Game getInstance() {
        if(game == null) {
            game = new Game();
        }
        return game;
    }

    // return a random number between min and max (both inclusive)
    public int getRandomInt(int min, int max) {
        return (int) (Math.floor(Math.random() * max) + min);
    }

    public static void main(String[] args) {
        String x = "howdy";
        Game.getInstance().addStateItem("1", x);
        String y = Game.getInstance().getStateItem("1", String.class);
        System.out.println(y);
    }

    public void removeStateItem(String key) {
        stateItems.remove(key);
    }
}
