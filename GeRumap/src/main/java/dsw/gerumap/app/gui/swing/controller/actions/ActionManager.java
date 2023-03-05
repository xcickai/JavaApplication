package dsw.gerumap.app.gui.swing.controller.actions;

import dsw.gerumap.app.gui.swing.controller.actions.main.*;
import dsw.gerumap.app.gui.swing.controller.actions.saveImage.ExportDiagramAsImageAction;
import dsw.gerumap.app.gui.swing.controller.actions.serialization.OpenAction;
import dsw.gerumap.app.gui.swing.controller.actions.serialization.SaveAction;
import dsw.gerumap.app.gui.swing.controller.actions.serialization.SaveAsAction;
import dsw.gerumap.app.gui.swing.controller.actions.tools.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ActionManager {
    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private EditProjectAction editProjectAction;
    private InformationAboutCreators information;
    private DeleteAction deleteAction;

    private ElementConceptAction elementConceptAction;
    private ElementDeleteAction elementDeleteAction;
    private ElementLinkAction elementLinkAction;
    private ElementSelectAction elementSelectAction;
    private ElementMoveAction elementMoveAction;
    private MakeCentralConceptAction makeCentralConceptAction;

    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenAction openAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private ExportDiagramAsImageAction exportDiagramAsImageAction;

    public ActionManager(){
        initializeActions();
    }

    private void initializeActions() {
        // Main actions
        newProjectAction = new NewProjectAction();
        exitAction = new ExitAction();
        information = new InformationAboutCreators();
        editProjectAction = new EditProjectAction();
        deleteAction = new DeleteAction();

        // Toolbar tools actions
        elementConceptAction = new ElementConceptAction();
        elementLinkAction = new ElementLinkAction();
        elementDeleteAction = new ElementDeleteAction();
        elementSelectAction = new ElementSelectAction();
        elementMoveAction = new ElementMoveAction();
        makeCentralConceptAction = new MakeCentralConceptAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openAction = new OpenAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        exportDiagramAsImageAction = new ExportDiagramAsImageAction();
    }
}
