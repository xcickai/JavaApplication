package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messageGenerator.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.EditDialog;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.factories.NodeFactoryManager;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;


import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Observable;

public class MapTreeImplementation extends Observable implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    public void startObservingProjectView(){
        addObserver(MainFrame.getInstance().getProjectView());
    }

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(MapTreeItem parent) {
        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;

        MapNode child = createChild(parent.getMapNode());
        if (child == null) {return;}
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

        setChanged();
        notifyObservers(parent);
    }

    @Override
    public void editChild(MapTreeItem child) {
        if (!(child.getMapNode() instanceof MapNodeComposite))
            return;
        MapTreeItem selectedItem = MainFrame.getInstance().getMapTree().getSelectedNode();
        new EditDialog(selectedItem, treeModel).getDialog().setVisible(true);

    }

    @Override
    public void deleteChild(MapTreeItem child) {
        if (!(child.getMapNode() instanceof MapNodeComposite))
            return;
        MapNodeComposite parent = (MapNodeComposite) child.getMapNode().getParent();
        if (parent == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.TRYING_TO_DELETE_PROJECT_EXPLORER);
            return;
        }
        parent.deleteChild(child.getMapNode());
        treeModel.removeNodeFromParent(child);

        setChanged();
        notifyObservers();
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void selectNode(MapTreeItem selectedItem) {
        int index = find(selectedItem.getMapNode());
        if (selectedItem.getMapNode() instanceof MindMapView) {index += 1;}
        treeView.setSelectionRow(index);
    }

    private MapNode createChild(MapNode parent) {
        NodeFactoryManager fm = new NodeFactoryManager();
        return fm.getNodeFor(parent);
    }

    // Selection of node from tab pane
    @Override
    public void update(Observable o, Object arg) {
        MapNode selectedMindMap = (MapNode) arg;
        treeView.setSelectionRow(find(selectedMindMap)+1);
    }

    private Integer find(MapNode s) {
        Enumeration<TreeNode> projects = ((MapTreeItem)treeModel.getRoot()).depthFirstEnumeration();
        // Start searching in root already
        int index = 0;
        while (projects.hasMoreElements()) {
            index += 1;
            MapTreeItem node = (MapTreeItem) projects.nextElement();
            if (node.getMapNode().equals(s)) {
                return index;
            }
        }
        return null;
    }


    public String getExpansionState(){
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < treeView.getRowCount(); i++ ){
            if ( treeView.isExpanded(i) ){
                sb.append(i).append(",");
            }
        }
        return sb.toString();
    }
    public void setExpansionState(String s){
        String[] indexes = s.split(",");
        for ( String st : indexes ){
            int row = Integer.parseInt(st);
            this.treeView.expandRow(row);
        }
    }
}
