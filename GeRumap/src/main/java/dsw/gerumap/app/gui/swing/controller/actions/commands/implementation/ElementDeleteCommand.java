package dsw.gerumap.app.gui.swing.controller.actions.commands.implementation;

import dsw.gerumap.app.gui.swing.controller.actions.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramLinkPainter;

import java.util.List;

public class ElementDeleteCommand extends AbstractCommand {

    private List<MindMapDiagramConcept> conceptsToRemove;
    private List<MindMapDiagramLink> linksToRemove;
    private MindMapDiagramView currentMindMapView;

    public ElementDeleteCommand(List<MindMapDiagramConcept> conceptsToRemove, List<MindMapDiagramLink> linksToRemove, MindMapDiagramView mindMapDiagramView) {
        currentMindMapView = mindMapDiagramView;
        this.conceptsToRemove = conceptsToRemove;
        this.linksToRemove = linksToRemove;

    }

    @Override
    public void doCommand() {
        System.out.println("Redo Delete Concept");
        currentMindMapView.removeConceptsFromPainterList(conceptsToRemove);
        currentMindMapView.removeLinksFromPainterList(linksToRemove);

    }

    @Override
    public void undoCommand() {
        System.out.println("Undo Delete Concept");

        for(MindMapDiagramConcept concept : conceptsToRemove) {
            DiagramConceptPainter conceptPainter = new DiagramConceptPainter(concept);
            currentMindMapView.getElementPainterList().add(conceptPainter);
        }
        for(MindMapDiagramLink link : linksToRemove) {
            DiagramLinkPainter linkPainter = new DiagramLinkPainter(link);
            currentMindMapView.getElementPainterList().add(linkPainter);

        }
    }

}