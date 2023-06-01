package docrob.cag.framework.screens;

import docrob.cag.framework.menu.Menu;
import docrob.cag.framework.menu.MenuItem;
import docrob.cag.framework.menu.NavMenuItem;
import docrob.cag.framework.state.Game;
import lombok.*;

@Getter
@Setter
public abstract class Screen {

    protected Menu menu;

    protected ScreenState screenState;

    protected boolean showMenuEachIteration;

    public Screen() {
        menu = new Menu();
        screenState = ScreenState.ReadyToQueue;
        showMenuEachIteration = true;
    }

    public void setReadyToExit() {
        if(screenState != ScreenState.Exited) {
            screenState = ScreenState.ReadyToExit;
        }
    }

    // override to have a screen reset its stuff on a cache hit
    public void reset() {
    }

    // override to setup a screen's stuff on the first cache write
    public void setup() {
    }

    // sets all menuitem.hidden to menuitem.defaultHidden
    public void resetMenuOnGameStart() {
        menu.resetMenuOnGameStart();
    }

    public void go() {
        show();
        handleInput();
    }

    protected void show() {
        System.out.println();
        for (MenuItem item : menu.getItems()) {
            if(!item.isHidden()) {
                System.out.println(item);
            }
        }
    }

    protected void handleInput() {
        while(screenState == ScreenState.Showing) {
            MenuItem selected = menu.getSelectedItemFromUser(Game.getInstance().getInput());

            if(selected.isHidden()) {
                System.out.println("That was not a valid choice!");
                continue;
            }

            selected.doIt();
            // if selected item was a nav menu item then this screen will exit to move to the next screen
            if(selected instanceof NavMenuItem) {
                this.setReadyToExit();
            }

            if(screenState == ScreenState.Showing && showMenuEachIteration) {
                show();
            }

        }
    }
}
