package dsw.gerumap.app.gui.swing.controller.actions.commands.implementation;

import dsw.gerumap.app.gui.swing.controller.actions.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramLinkPainter;

public class ElementLinkCommand extends AbstractCommand {
    private MindMapDiagramLink link;
    private MindMapDiagramView currentMindMapView;

    public ElementLinkCommand(MindMapDiagramLink link, MindMapDiagramView mindmap) {
        this.link = link;
        currentMindMapView = mindmap;
    }
    @Override
    public void doCommand() {
        System.out.println("Redo New Link");
        DiagramLinkPainter newPainter = new DiagramLinkPainter(link);
        currentMindMapView.getElementPainterList().add(newPainter);
    }

    @Override
    public void undoCommand() {
        System.out.println("Undo New Link");
        currentMindMapView.removeElementFromPainterList(link);
    }

}
