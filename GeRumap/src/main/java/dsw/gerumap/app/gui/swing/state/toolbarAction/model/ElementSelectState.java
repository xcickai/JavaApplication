package dsw.gerumap.app.gui.swing.state.toolbarAction.model;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramSelectionRectangle;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramSelectionRectanglePainter;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ElementSelectState implements State {
    private Point starting;
    private Point ending;
    private MindMapDiagramSelectionRectangle selectionRectangle;
    private DiagramSelectionRectanglePainter tmpPainter;

    @Override
    public void mousePressed(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        starting = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);

        // Start selection rectangle and add it to painters
        selectionRectangle = new MindMapDiagramSelectionRectangle(new Rectangle(starting));
        tmpPainter = new DiagramSelectionRectanglePainter(selectionRectangle);
        mindMapView.getElementPainterList().add(tmpPainter);
        // Make single selection
        mindMapView.makeSelectionWith(selectionRectangle.getSelectionRectangle());
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        ending = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);

        // Expand diagonal from starting point selecting elements
        selectionRectangle.getSelectionRectangle().setFrameFromDiagonal(starting, ending);
        mindMapView.makeSelectionWith(selectionRectangle.getSelectionRectangle());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        starting = null;
        ending = null;
        // Remove selection rectangle from painters
        mindMapView.getElementPainterList().remove(tmpPainter);
        selectionRectangle = null;
    }
}
