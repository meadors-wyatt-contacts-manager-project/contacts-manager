package docrob.cag.framework.menu;

import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.screens.ScreenManager;
import docrob.cag.framework.screens.ScreenState;
import docrob.cag.framework.state.Game;
import docrob.cag.framework.utils.Input;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Menu {
    private int idCounter = 0;

    private ArrayList<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
    }

    public void addItem(String label, MenuItemMethod theMethod) {
        MenuItem item = new FunctionMenuItem(idCounter++, label, theMethod);
        items.add(item);
    }

    public void addItem(String label, MenuItemMethod theMethod, boolean hidden) {
        MenuItem item = new FunctionMenuItem(idCounter++, label, theMethod);
        item.setHidden(hidden);
        item.setHiddenDefault(hidden);
        items.add(item);
    }

    public void addItem(String label, Screen navToScreen) {

        MenuItem item = new NavMenuItem(idCounter++, label, () -> {
            navToScreen.setScreenState(ScreenState.ReadyToQueue);
            ScreenManager.addScreen(navToScreen);
        });
        items.add(item);
    }
    public void addItem(String label, Screen navToScreen, boolean hidden) {

        MenuItem item = new NavMenuItem(idCounter++, label, () -> {
            navToScreen.setScreenState(ScreenState.ReadyToQueue);
            ScreenManager.addScreen(navToScreen);
        });
        item.setHidden(hidden);
        item.setHiddenDefault(hidden);
        items.add(item);
    }

    public MenuItem getSelectedItemFromUser(Input input) {
        int num = Game.getInstance().getInput().getInt("Enter your choice: ");
        // check if num is a valid menu item
        for (MenuItem item : items) {
            if(item.getId() == num) {
                return item;
            }
        }

        // did not select a valid item num so do again
        System.out.print("That was not a valid choice!\n");
        return getSelectedItemFromUser(input);
    }

    public MenuItem getItemFromLabel(String label) throws MenuException {
        for (MenuItem item : items) {
            if(item.getLabel().equals(label)) {
                return item;
            }
        }
        throw new MenuException("Invalid menu item requested: " + label);
    }

    public void resetMenuOnGameStart() throws MenuException {
        for(MenuItem item : items) {
            item.setHidden(item.getHiddenDefault());
        }
    }
}
