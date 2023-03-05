package dsw.gerumap.app.gui.swing.tree.model;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;
import java.util.Observable;

public class TabChangeListener extends Observable implements ChangeListener {
    List<MapNode> selectedNode;

    public TabChangeListener(List<MapNode> selectedNode){
        this.selectedNode = selectedNode;
        addObserver(MainFrame.getInstance().getMapTree());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        setChanged();
        int index = ((JTabbedPane)e.getSource()).getSelectedIndex();
        notifyObservers(selectedNode.get(index));
    }
}
