package dsw.gerumap.app.gui.swing.controller.actions.serialization;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class OpenAction extends AbstractGeRuMapAction {

    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(NAME, "Open Mindmap");
        putValue(SHORT_DESCRIPTION, "Open a saved mindmap template from storage.");
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                List<MindMapDiagramElement> m = ApplicationFramework.getInstance().getSerializer().loadMindmap(file);
                MainFrame.getInstance().getProjectView().loadMindmap(m);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
