package dsw.gerumap.app.gui.swing.state.toolbarAction.model;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Point;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramLinkPainter;


import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CentralTopicState implements State {

    @Override
    public void mousePressed(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        // Make single selection
        java.awt.Point pressPoint = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);
        mindMapView.makeSingleSelectionAt(pressPoint);

        List<DiagramConceptPainter> selectedConcepts = mindMapView.getSelectedElementPainters();
        if (selectedConcepts.size() == 1) {
            // Set central topic in the middle
            DiagramConceptPainter centralConcept = selectedConcepts.get(0);
            centralConcept.setPosition(new Point(300, 300));

            // Get all links in mindmap and while visiting any concept via these links we will remove them one by one
            List<DiagramLinkPainter> allLinks = new ArrayList<>();
            allLinks.addAll(mindMapView.getLinkPainters());

            Queue<DiagramConceptPainter> conceptsToVisit = new ArrayDeque<>();
            conceptsToVisit.add(centralConcept);

            // While there are concepts to visit, visit them
            while(!conceptsToVisit.isEmpty()) {
                DiagramConceptPainter currentConcept = conceptsToVisit.remove();
                // Links to visit for current concept
                List<MindMapDiagramLink> linksToVisit = currentConcept.getConcept().getConnectedLinks(allLinks);

                // Get neighbouring concepts to visit and remove their link (mark them as visited)
                int offsetXTimes = 0;
                for(MindMapDiagramLink link : linksToVisit) {
                    // Add neighbour to the list to visit
                    DiagramConceptPainter connectedConcept;
                    if (currentConcept.getConcept() == link.getFrom()) {
                        connectedConcept = mindMapView.getPainterFromConcept(link.getTo());
                    } else {
                        connectedConcept = mindMapView.getPainterFromConcept(link.getFrom());
                    }

                    //Set new position
                    connectedConcept.setPosition(new Point(
                            (int) currentConcept.getConcept().getPosition().getX() + (400 * offsetXTimes),
                            (int) currentConcept.getConcept().getPosition().getY() + 200));
                    offsetXTimes += 1;

                    // Add new concept and remove the visited link
                    conceptsToVisit.add(connectedConcept);
                    allLinks.remove(mindMapView.getPainterFromLink(link));
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {

    }

}