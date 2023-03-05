package dsw.gerumap.app.gui.swing.state.toolbarAction.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.actions.commands.implementation.ElementMoveCommand;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Point;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ElementMoveState implements State {
    private List<DiagramConceptPainter> selectedConcepts;
    private List<Point> startingPoints;
    private Point starting;
    private Point midPoint;
    private Point ending;

    @Override
    public void mousePressed(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        selectedConcepts = mindMapView.getSelectedElementPainters();
        startingPoints = new ArrayList<>();
        for (DiagramConceptPainter concept : selectedConcepts) {
            startingPoints.add(concept.getConcept().getPosition());
        }
        starting = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);
        midPoint = starting;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        ending = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);
        updatePosition();
        midPoint = ending;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        // Fake reset back so that command can move it to the translated space once
        resetPosition();

        // Add command to undo, redo
        List<MindMapDiagramConcept> concepts = new ArrayList<>();
        for (DiagramElementPainter painter : selectedConcepts) {
            concepts.add((MindMapDiagramConcept) painter.getElement());
        }
        ElementMoveCommand addMoveCommand = new ElementMoveCommand(concepts, starting, ending);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(addMoveCommand);
        mindMapView.getMindMapView().setChanged(true);

        // Reset state
        startingPoints = null;
        selectedConcepts = null;
    }


    private void resetPosition() {
        for (int i = 0; i < selectedConcepts.size(); i++) {
            DiagramConceptPainter concept = selectedConcepts.get(i);
            int dx = starting.X - ending.X;
            int dy = starting.Y - ending.Y;
            Point oldPosition = (selectedConcepts.get(i)).getConcept().getPosition();
            concept.setPosition(new Point((oldPosition.X + dx),
                        oldPosition.Y + dy));

        }
    }

    private void updatePosition() {
        for (int i = 0; i < selectedConcepts.size(); i++) {
            DiagramConceptPainter concept = selectedConcepts.get(i);
            int dx = ending.X - midPoint.X;
            int dy = ending.Y - midPoint.Y;
            Point oldPosition = (selectedConcepts.get(i)).getConcept().getPosition();
            concept.setPosition(new Point((oldPosition.X + dx),
                        oldPosition.Y + dy));

        }
    }

}
