package dsw.gerumap.app.gui.swing.controller.actions.main;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditProjectAction extends AbstractGeRuMapAction {
    public EditProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Edit the selected item");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selectedItem = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selectedItem != null) {
            MainFrame.getInstance().getMapTree().editChild(selectedItem);
            MainFrame.getInstance().getMapTree().selectNode(selectedItem);
        }
    }

}