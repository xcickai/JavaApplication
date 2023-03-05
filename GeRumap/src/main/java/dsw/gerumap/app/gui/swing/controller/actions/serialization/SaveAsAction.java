package dsw.gerumap.app.gui.swing.controller.actions.serialization;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAsAction extends AbstractGeRuMapAction {

    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Save Mindmap As");
        putValue(SHORT_DESCRIPTION, "Save a mindmap template to storage.");
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (MainFrame.getInstance().getProjectView().getActiveMindmap() == null) return;

        MindMapView mindmapTemplate = MainFrame.getInstance().getProjectView().getActiveMindmap();
        File mindmapFile = null;
        
        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            mindmapFile = jfc.getSelectedFile();
            mindmapTemplate.setFilePath(mindmapFile.getPath());
        } else {
            return;
        }

        mindmapTemplate.setChanged(false);
        ApplicationFramework.getInstance().getSerializer().saveMindmap(mindmapTemplate);
    }
}