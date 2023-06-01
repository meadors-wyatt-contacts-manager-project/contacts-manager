package docrob.cag.ContactsScreen;

import docrob.cag.framework.menu.Menu;
import docrob.cag.framework.menu.MenuItemMethod;
import docrob.cag.framework.screens.Screen;

public class MainMenu extends Screen {
    @Override
    public void setup() {
        super.setup();
        menu.addItem("Exit Application", exitProgram);
        menu.addItem("Hi", sayHello);
        menu.addItem("Do something else", doSomethingElse, true);
    }
    @Override
    public void show (){
        System.out.println("This is the Main Menu");
        super.show();
    }

    private MenuItemMethod exitProgram = ()->{
        System.out.println("Exiting Main Screen");
        super.setReadyToExit();
    };

    private MenuItemMethod sayHello = () -> {
        System.out.println("Hi");
    };

    private MenuItemMethod doSomethingElse = () -> {
        System.out.println("Gotcha");
    };

}
