package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;

public class ActionsToolBar extends JToolBar {
    public ActionsToolBar() {
        super(VERTICAL);
        add(MainFrame.getInstance().getActionManager().getOpenAction());
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        add(MainFrame.getInstance().getActionManager().getExportDiagramAsImageAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getElementConceptAction());
        add(MainFrame.getInstance().getActionManager().getElementLinkAction());
        add(MainFrame.getInstance().getActionManager().getElementSelectAction());
        add(MainFrame.getInstance().getActionManager().getElementMoveAction());
        add(MainFrame.getInstance().getActionManager().getElementDeleteAction());
        add(MainFrame.getInstance().getActionManager().getMakeCentralConceptAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator();
        add(Box.createGlue());
    }
}
