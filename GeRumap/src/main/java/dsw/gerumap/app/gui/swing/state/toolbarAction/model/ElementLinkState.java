package dsw.gerumap.app.gui.swing.state.toolbarAction.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.actions.commands.implementation.ElementLinkCommand;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLine;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Point;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramLinePainter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ElementLinkState extends MouseAdapter implements State {
    Point starting;
    Point ending;
    DiagramConceptPainter fromConcept;
    DiagramConceptPainter toConcept;
    DiagramLinePainter tmpLine;

    @Override
    public void mousePressed(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        starting = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);
        tmpLine = new DiagramLinePainter(new MindMapDiagramLine(starting, starting));
        mindMapView.getElementPainterList().add(tmpLine);
        try {
            fromConcept = mindMapView.conceptIsOnPoint(starting);
        } catch (Exception ignore) { System.out.println("Didn't find a concept at starting location! " + starting.toString()); }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        ending = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);
        tmpLine.getLine().setTo(ending);
        try {
            toConcept = mindMapView.conceptIsOnPoint(ending);
        } catch (Exception ignore) {}
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent, MindMapDiagramView mindMapView) {
        ending = correctCoordinatesToPanelZoom(mouseEvent, mindMapView);
        mindMapView.getElementPainterList().remove(tmpLine);
        try {
            toConcept = mindMapView.conceptIsOnPoint(ending);
        } catch (Exception ignore) { System.out.println("Didn't find a concept at ending location! " + ending.toString()); }

        if (starting != null && ending != null && fromConcept != null && toConcept != null && fromConcept != toConcept) {
            MindMapDiagramLink newLink = new MindMapDiagramLink(fromConcept.getConcept(), toConcept.getConcept());
            ElementLinkCommand addLinkCommand = new ElementLinkCommand(newLink, mindMapView);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(addLinkCommand);
            mindMapView.getMindMapView().setChanged(true);
        }
        resetState();
    }

    private void resetState() {
        starting = null;
        ending = null;
        if (fromConcept != null) {fromConcept.getConcept().setIsSelected(false);}
        if (toConcept != null) {toConcept.getConcept().setIsSelected(false);}
        fromConcept = null;
        toConcept = null;
    }
}
