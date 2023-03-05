package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

import java.util.Observable;
import java.util.Observer;

public interface MapTree extends Observer {

    MapTreeView generateTree(ProjectExplorer projectExplorer);

    void addChild(MapTreeItem parent);
    void editChild(MapTreeItem parent);
    void deleteChild(MapTreeItem parent);

    MapTreeItem getSelectedNode();
    void selectNode(MapTreeItem selectedItem);

    public String getExpansionState();
    public void setExpansionState(String s);

    public void startObservingProjectView();
}
