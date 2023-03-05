package dsw.gerumap.app.gui.swing.controller.actions.main;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@Getter
@Setter

public class DeleteAction extends AbstractGeRuMapAction {

    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK
        ));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete the selected item");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selectedItem = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selectedItem != null) {
            MainFrame.getInstance().getMapTree().deleteChild(selectedItem);
        }
    }
}
