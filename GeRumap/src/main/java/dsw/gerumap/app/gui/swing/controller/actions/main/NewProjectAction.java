package dsw.gerumap.app.gui.swing.controller.actions.main;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGeRuMapAction {

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK
        ));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selectedItem = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selectedItem != null) {
            MainFrame.getInstance().getMapTree().addChild(selectedItem);
        }
    }
}
