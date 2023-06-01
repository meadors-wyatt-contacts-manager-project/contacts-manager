package docrob.cag.framework.menu;

public class FunctionMenuItem extends MenuItem {
    private MenuItemMethod theMethod;

    public FunctionMenuItem(int id, String label, MenuItemMethod theMethod) {
        super(id, label, false, false);
        this.theMethod = theMethod;
    }

    @Override
    public void doIt() {
        theMethod.doIt();
    }
}
