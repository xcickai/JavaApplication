package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messageGenerator.EventType;
import dsw.gerumap.app.core.messageGenerator.MessageGenerator;
import dsw.gerumap.app.gui.swing.controller.actions.commands.CommandManager;
import dsw.gerumap.app.gui.swing.state.toolbarAction.ActionStateManager;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.model.TabChangeListener;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static dsw.gerumap.app.core.messageGenerator.EventType.CONCEPT_NOT_SELECTED;
import static dsw.gerumap.app.core.messageGenerator.EventType.MINDMAP_NOT_SELECTED;

@Getter
@Setter
public class ProjectViewPanel extends JPanel implements Observer {
    private ActionStateManager actionStateManager;
    private MapNodeComposite activeItem;
    private MindMapView activeMindmap;

    public void updateView(MapTreeItem activeProject) {
        if (activeProject == null && activeItem == null) return;
        repaint();
        updateUI(activeProject);
        updateUI();
    }
    public void deleteProject(){
        repaint();
        removeAll();
    }

    public ProjectViewPanel() {
        setLayout(new BorderLayout());
        actionStateManager = new ActionStateManager();
        if (activeItem == null) {
            add(new JLabel("Please select a project"));
            return;
        }
    }

    private void updateUI(MapTreeItem activeProjectOrMindmap) {
        if (activeProjectOrMindmap.getMapNode() instanceof ProjectExplorer){
            return;
        }
        this.activeItem = (MapNodeComposite) activeProjectOrMindmap.getMapNode();

        if (activeProjectOrMindmap.getMapNode() instanceof MindMapView) {
            this.activeItem = (MapNodeComposite) activeProjectOrMindmap.getMapNode().getParent();
            activeMindmap = ((MindMapView) activeProjectOrMindmap.getMapNode());
        }

        this.removeAll();
        JTabbedPane tabs = new JTabbedPane();
        tabs.setSize(this.getSize());

        for (MapNode child: this.activeItem.getChildren()){
            Component newComponent = new MapViewPanel(child);
            tabs.addTab(child.getName(), newComponent);
            if (child.equals(activeProjectOrMindmap.getMapNode())) {
                tabs.setSelectedComponent(newComponent);
            } else if (activeItem.getChildren().size() == 1) {
                activeMindmap = ((MindMapView) activeItem.getChildren().get(0));
            }
        }

        tabs.addChangeListener(new TabChangeListener(this.activeItem.getChildren()));
        this.add(tabs);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            deleteProject();
            return;
        }
        updateView((MapTreeItem) arg);
    }

    public void startConceptState(){ actionStateManager.setConceptState(); }
    public void startDeleteState(){ actionStateManager.setDeleteState(); }
    public void startLinkState(){ actionStateManager.setLinkState(); }
    public void startSelectState(){ actionStateManager.setSelectState(); }
    public void startMoveState(){ actionStateManager.setMoveState(); }

    public void startCentralTopicState() { actionStateManager.setCentralTopicState(); }

    public void loadMindmap(List<MindMapDiagramElement> mindmapTemplate) {
        if(mindmapTemplate == null) {
            return;
        }
        if(activeMindmap == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MINDMAP_NOT_SELECTED);
            return;
        }


        activeMindmap.getDiagramRepresentation().setElementPainterList(new ArrayList<>());
        for(MindMapDiagramElement element : mindmapTemplate) {
            System.out.println(element);
            activeMindmap.getDiagramRepresentation().getElementPainterList().add(element.createPainter(activeMindmap.getDiagramRepresentation()));
        }
        repaint();
        updateUI();
    }
}