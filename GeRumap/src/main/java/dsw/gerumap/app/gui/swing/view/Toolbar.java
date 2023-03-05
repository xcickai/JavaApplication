package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar {
    public Toolbar() {
        super(HORIZONTAL);
        setFloatable(false);
        Dimension size = new Dimension(32, 32);
        add(MainFrame.getInstance().getActionManager().getNewProjectAction()).setPreferredSize(size);
        add(MainFrame.getInstance().getActionManager().getEditProjectAction()).setPreferredSize(size);
        add(MainFrame.getInstance().getActionManager().getDeleteAction()).setPreferredSize(size);

        add(MainFrame.getInstance().getActionManager().getInformation()).setPreferredSize(size);
        add(MainFrame.getInstance().getActionManager().getExitAction()).setPreferredSize(size);
    }
}
