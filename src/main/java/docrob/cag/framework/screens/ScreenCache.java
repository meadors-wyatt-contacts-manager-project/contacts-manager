package docrob.cag.framework.screens;

import java.util.HashMap;

public class ScreenCache {
    private static final HashMap<String, Screen> screenCache = new HashMap<>();

    public static void emptyCache() {
        screenCache.clear();
    }

    public static Screen getCachedScreen(Screen screen) {
        String screenClassName = screen.getClass().getSimpleName();
        if(!screenCache.containsKey(screenClassName) || screen instanceof NotCacheable) {
            if(!(screen instanceof NotCacheable)) {
                screenCache.put(screenClassName, screen);
            }
            screen.setup();
//        } else {
//            System.out.println("Found screen in cache: " + screenClassName);
//            System.out.println(screenCache.get(screenClassName).getMenu().getChoices().size());
////            System.out.println(screen.get);
        }
        return screenCache.get(screenClassName);
    }

}
