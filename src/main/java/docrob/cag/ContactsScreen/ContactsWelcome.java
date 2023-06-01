package docrob.cag.ContactsScreen;

import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.screens.ScreenManager;
import docrob.cag.framework.state.Game;

public class ContactsWelcome {

    public static void main(String[] args) {

        Screen mainMenu = new MainMenu();
        ScreenManager.addScreen(mainMenu);
        ScreenManager.start();

    }
}


