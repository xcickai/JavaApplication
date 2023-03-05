package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.observer.Observer;
import dsw.gerumap.app.gui.swing.controller.actions.commands.implementation.ElementDeleteCommand;
import dsw.gerumap.app.gui.swing.controller.actions.MouseEventController;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramLinkPainter;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MindMapDiagramView extends JPanel implements Observer {
    private final MindMapView mindMapView;
    private List<DiagramElementPainter> elementPainterList;
    private final MouseEventController mouseEventController;
    AffineTransform at = new AffineTransform();

    // Zoom support
    private double zoomFactor = 1.0;
    private double prevZoomFactor = 1.0;
    private double xOffset = 0;
    private double yOffset = 0;


    public MindMapDiagramView(MindMapView selectedMindMapView) {
        mindMapView = selectedMindMapView;
        elementPainterList = new ArrayList<>();

        // UI Setup
        mouseEventController = new MouseEventController(this);
        this.addMouseListener(mouseEventController);
        this.addMouseMotionListener(mouseEventController);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = new AffineTransform();
        double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
        double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

        double zoomDiv = zoomFactor / prevZoomFactor;
        xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
        yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

        at.scale(zoomFactor, zoomFactor);
        prevZoomFactor = zoomFactor;
        g2.transform(at);

        paintComponent(g);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(DiagramElementPainter painter : elementPainterList){
            painter.draw(g);
        }
    }

    @Override
    public void updateFor(Object obj) {
        if (obj instanceof MouseEvent) {
            this.revalidate();
            this.repaint();
        }
    }

    public ArrayList<DiagramConceptPainter> getSelectedElementPainters() {
        ArrayList<DiagramConceptPainter> selected = new ArrayList<>();
        for (DiagramElementPainter concept : this.getElementPainterList()) {
            if (concept instanceof DiagramConceptPainter) {
                if (((DiagramConceptPainter) concept).getConcept().getIsSelected()) {
                    selected.add((DiagramConceptPainter)concept);
                }
            }
        }
        return selected;
    }

    public List<DiagramConceptPainter> getUnselectedElementPainters() {
        ArrayList<DiagramConceptPainter> unselected = new ArrayList<>();
        for (DiagramElementPainter concept : this.getElementPainterList()) {
            if (concept instanceof DiagramConceptPainter) {
                if (!((DiagramConceptPainter) concept).getConcept().getIsSelected()) {
                    unselected.add((DiagramConceptPainter)concept);
                }
            }
        }
        return unselected;
    }

    public DiagramConceptPainter getPainterFromConcept(MindMapDiagramConcept concept) {
        for (DiagramElementPainter painter : getElementPainterList()) {
            if (painter instanceof DiagramConceptPainter) {
                if (concept == ((DiagramConceptPainter) painter).getConcept()) {
                    return (DiagramConceptPainter) painter;
                }
            }
        }
        return null;
    }

    public DiagramLinkPainter getPainterFromLink(MindMapDiagramLink concept) {
        for (DiagramElementPainter painter : getElementPainterList()) {
            if (painter instanceof DiagramLinkPainter) {
                if (concept == ((DiagramLinkPainter) painter).getLink()) {
                    return (DiagramLinkPainter) painter;
                }
            }
        }
        return null;
    }


    public List<DiagramLinkPainter> getLinkPainters() {
        ArrayList<DiagramLinkPainter> links = new ArrayList<>();
        for (DiagramElementPainter concept : this.getElementPainterList()) {
            if (concept instanceof DiagramLinkPainter) {
                links.add((DiagramLinkPainter)concept);
            }
        }
        return links;
    }

    public DiagramConceptPainter conceptIsOnPoint(Point p) {
        for (DiagramElementPainter concept : this.getElementPainterList()) {
            if (concept instanceof DiagramConceptPainter) {
                if (((DiagramConceptPainter) concept).elementAtPoint(p)){
                    ((DiagramConceptPainter) concept).getConcept().setIsSelected(true);
                    return (DiagramConceptPainter) concept;
                } else {
                    ((DiagramConceptPainter) concept).getConcept().setIsSelected(false);
                }
            }
        }
        return null;
    }
    public void makeSelectionWith(Rectangle r) {
        for (DiagramElementPainter concept : this.getElementPainterList()) {
            if (concept instanceof DiagramConceptPainter) {
                if ((((DiagramConceptPainter) concept).overlaps(r))){
                    ((DiagramConceptPainter) concept).getConcept().setIsSelected(true);
                } else {
                    ((DiagramConceptPainter) concept).getConcept().setIsSelected(false);
                }
            }
        }
    }
    public void makeSingleSelectionAt(Point pressPoint) {
        for (DiagramElementPainter concept : this.getElementPainterList()) {
            if (concept instanceof DiagramConceptPainter) {
                if ((((DiagramConceptPainter) concept).overlaps(pressPoint))){
                    ((DiagramConceptPainter) concept).getConcept().setIsSelected(true);
                } else {
                    ((DiagramConceptPainter) concept).getConcept().setIsSelected(false);
                }
            }
        }
    }

    // Remove after reading as to avoid ConcurrentModificationException
    public void deleteSelectionWith(Rectangle r) {

        List<DiagramElementPainter> conceptPaintersToRemove = new ArrayList<>();
        List<MindMapDiagramConcept> conceptsToRemove = new ArrayList<>();
        for (DiagramElementPainter element : this.getElementPainterList()) {
            if (element instanceof DiagramConceptPainter) {
                DiagramConceptPainter concept = (DiagramConceptPainter)element;
                if (concept.overlaps(r)){
                    conceptPaintersToRemove.add(concept);
                    conceptsToRemove.add(concept.getConcept());
                }
            }
        }

        List<MindMapDiagramLink> linksToRemove = new ArrayList<>();
        for (DiagramElementPainter element : this.getElementPainterList()){
            if (element instanceof DiagramLinkPainter){
                DiagramLinkPainter link = (DiagramLinkPainter)element;
                for (DiagramElementPainter conceptElement : conceptPaintersToRemove) {
                    if (conceptElement instanceof DiagramConceptPainter) {
                        DiagramConceptPainter concept = (DiagramConceptPainter)conceptElement;
                        if (concept.getConcept() == link.getLink().getFrom() ||
                                concept.getConcept() == link.getLink().getTo()) {
                            linksToRemove.add(link.getLink());
                        }
                    }
                }
            }
        }
        if (!conceptPaintersToRemove.isEmpty()) {
            ElementDeleteCommand deleteCommand = new ElementDeleteCommand(conceptsToRemove, linksToRemove, this);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(deleteCommand);
            getMindMapView().setChanged(true);
        }
    }

    public void removeElementFromPainterList(MindMapDiagramElement elementToRemove) {
        DiagramElementPainter painterToRemove = null;
        for (DiagramElementPainter painter : getElementPainterList()) {
            if (painter.getElement() == elementToRemove) {
                painterToRemove = painter;
            }
        }
        if (painterToRemove == null){
            return;
        }
        List<DiagramElementPainter> linksToRemove = new ArrayList<>();
        for (DiagramElementPainter element : this.getElementPainterList()){
            if (element instanceof DiagramLinkPainter){
                DiagramLinkPainter link = (DiagramLinkPainter)element;
                if (painterToRemove.getElement() == link.getLink().getFrom() ||
                        painterToRemove.getElement() == link.getLink().getTo()) {
                    linksToRemove.add(link);
                }
            }
        }
        if (painterToRemove != null) {
            getElementPainterList().remove(painterToRemove);
            getElementPainterList().removeAll(linksToRemove);
        }
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        this.repaint();
        this.revalidate();
    }

    public void removeConceptsFromPainterList(List<MindMapDiagramConcept> conceptsToRemove) {
        for (MindMapDiagramConcept element : conceptsToRemove) {
            removeElementFromPainterList(element);
        }
    }

    public void removeLinksFromPainterList(List<MindMapDiagramLink> linksToRemove) {
        for (MindMapDiagramLink element : linksToRemove) {
            removeElementFromPainterList(element);
        }
    }

    public List<MindMapDiagramElement> getElementsToSerialize() {
        List<MindMapDiagramElement> list = new ArrayList<>();
        for (DiagramElementPainter painter : elementPainterList) {
            list.add(painter.getElement());
        }
        return list;
    }

    public void setElementPainterList(List<DiagramElementPainter> elementPainterList) {
        this.elementPainterList = elementPainterList;
    }

}
