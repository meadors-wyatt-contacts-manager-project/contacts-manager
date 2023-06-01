package docrob.cag.framework.screens;

import java.util.ArrayList;
import java.util.List;

public class ScreenManager {
    private static List<Screen> screens = new ArrayList<>();

    public static void addScreen(Screen screen) {
        if(screen.getScreenState() != ScreenState.ReadyToQueue) {
            throw new ScreenException(screen.getClass().getSimpleName() + " status is not ready to queue");
        }

        // find the screen in the cache
        Screen cacheScreen = ScreenCache.getCachedScreen(screen);

        cacheScreen.setScreenState(ScreenState.QueuedToShow);
        pushScreen(cacheScreen);
    }

    public static void start() {
        // loop until we run out of screens
        // or are told to exit the app early?
        while(true) {
            if(screens.size() == 0) {
                break;
            }

            Screen screen = popScreen();
            screen.setScreenState(ScreenState.Showing);

            screen.go();
            screen.setScreenState(ScreenState.Exited);
        }
    }

    private static Screen popScreen() {
        return screens.remove(0);
    }

    private static void pushScreen(Screen screen) {
        screens.add(screen);
    }

    public static void emptyCache() {
        ScreenCache.emptyCache();
    }

    public static void resetScreens() {
        // reset menu items on any resettable screen
        for(Screen screen : screens) {
            if(screen instanceof Resettable) {
                screen.getMenu().resetMenuOnGameStart();
            }
        }
    }
}
