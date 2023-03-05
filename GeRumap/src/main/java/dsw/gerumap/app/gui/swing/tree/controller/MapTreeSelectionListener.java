package dsw.gerumap.app.gui.swing.tree.controller;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;

public class MapTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem) path.getLastPathComponent();
        MainFrame.getInstance().getProjectView().updateView(treeItemSelected);

        System.out.println("Selektovan cvor " + treeItemSelected.getMapNode().getName());
        System.out.println("Selektovan cvor " + treeItemSelected.getMapNode());
        System.out.println("getPath: "+ e.getPath());
    }
}
