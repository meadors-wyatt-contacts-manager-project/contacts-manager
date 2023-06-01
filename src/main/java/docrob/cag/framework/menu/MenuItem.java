package docrob.cag.framework.menu;

import lombok.*;

/*
a menu item is a label and either a screen or a function
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class MenuItem {
    protected int id;
    protected String label;
    protected boolean hidden;
    protected boolean hiddenDefault;

    public abstract void doIt();

    @Override
    public String toString() {
        return id + ": " + label;
    }

    public boolean getHiddenDefault() {
        return hiddenDefault;
    }
}
