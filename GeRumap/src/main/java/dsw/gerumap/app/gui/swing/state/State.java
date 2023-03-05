package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Point;

import javax.swing.*;
import java.awt.event.MouseEvent;

public interface State {
    void mousePressed(MouseEvent mouseEvent, MindMapDiagramView mindMapView);

    void mouseDragged(MouseEvent mouseEvent, MindMapDiagramView mindMapView);

    void mouseReleased(MouseEvent mouseEvent, MindMapDiagramView mindMapView);

    // Coordinates corrected to the position of mapDiagram in relation to scaling factor of the current zoom
    default Point correctCoordinatesToPanelZoom(MouseEvent e, MindMapDiagramView mapDiagramView) {
        SwingUtilities.convertPointFromScreen(e.getPoint(), mapDiagramView);
        double zoomFactor = mapDiagramView.getMindMapView().getDiagramRepresentation().getZoomFactor();
        int myXLocationWithoutZoom = (int) (e.getPoint().getX() * (1 / zoomFactor));
        int myYLocationWithoutZoom = (int) (e.getPoint().getY() * (1 / zoomFactor));
        return new Point(myXLocationWithoutZoom, myYLocationWithoutZoom);
    }
}