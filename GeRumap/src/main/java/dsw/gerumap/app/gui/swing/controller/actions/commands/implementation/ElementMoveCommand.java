package dsw.gerumap.app.gui.swing.controller.actions.commands.implementation;

import dsw.gerumap.app.gui.swing.controller.actions.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Point;

import java.util.List;

public class ElementMoveCommand extends AbstractCommand {

    private List<MindMapDiagramConcept> concepts;
    Point start;
    Point end;

    public ElementMoveCommand(List<MindMapDiagramConcept> conceptsToMove, Point start, Point end) {
        this.concepts = conceptsToMove;
        this.start = start;
        this.end = end;
    }

    @Override
    public void doCommand() {
        System.out.println("Redo Move Selection");
        for (int i = 0; i < concepts.size(); i++) {
            int dx = end.X - start.X;
            int dy = end.Y - start.Y;
            Point oldPosition = concepts.get(i).getPosition();
            concepts.get(i).setPosition(new Point((oldPosition.X + dx),
                        oldPosition.Y + dy));
            oldPosition = new Point(concepts.get(i).getSelectionRectangle().getLocation());
            concepts.get(i).getSelectionRectangle().setLocation(new Point((oldPosition.X + dx), oldPosition.Y + dy));
        }
    }


    @Override
    public void undoCommand() {
        System.out.println("Undo Move Selection");
        for (int i = 0; i < concepts.size(); i++) {
            int dx = start.X - end.X;
            int dy = start.Y - end.Y;
            Point oldPosition = concepts.get(i).getPosition();
            concepts.get(i).setPosition(new Point((oldPosition.X + dx),
                    oldPosition.Y + dy));
            oldPosition = new Point(concepts.get(i).getSelectionRectangle().getLocation());
            concepts.get(i).getSelectionRectangle().setLocation(new Point((oldPosition.X + dx), oldPosition.Y + dy));
        }

    }
}
