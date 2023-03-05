package dsw.gerumap.app.gui.swing.controller.actions.commands.implementation;
import dsw.gerumap.app.gui.swing.controller.actions.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;

public class ElementConceptCommand extends AbstractCommand {

    private MindMapDiagramConcept concept;
    private MindMapDiagramView currentMindMapView;

    public ElementConceptCommand(MindMapDiagramConcept newConcept, MindMapDiagramView mindmap) {
        concept = newConcept;
        currentMindMapView = mindmap;
    }

    @Override
    public void doCommand() {
        System.out.println("Redo New Concept");
        DiagramConceptPainter newPainter = new DiagramConceptPainter(concept);
        currentMindMapView.getElementPainterList().add(newPainter);
    }

    @Override
    public void undoCommand() {
        System.out.println("Undo New Concept");
        currentMindMapView.removeElementFromPainterList(concept);
    }


}
