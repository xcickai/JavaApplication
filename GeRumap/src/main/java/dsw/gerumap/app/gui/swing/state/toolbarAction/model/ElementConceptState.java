package dsw.gerumap.app.gui.swing.state.toolbarAction.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.actions.commands.implementation.ElementConceptCommand;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Point;

import java.awt.event.MouseEvent;

public class ElementConceptState implements State {

    @Override
    public void mousePressed(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        Point panelPoint = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);
        MindMapDiagramConcept newConcept = new MindMapDiagramConcept("New Concept", panelPoint);
        ElementConceptCommand addCommand = new ElementConceptCommand(newConcept, mindMapView);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(addCommand);
        mindMapView.getMindMapView().setChanged(true);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {

    }
}
