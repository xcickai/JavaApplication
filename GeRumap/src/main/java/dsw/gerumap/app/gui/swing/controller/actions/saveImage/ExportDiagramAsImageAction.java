package dsw.gerumap.app.gui.swing.controller.actions.saveImage;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportDiagramAsImageAction extends AbstractGeRuMapAction {

    public ExportDiagramAsImageAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(NAME, "Export as Image");
        putValue(SHORT_DESCRIPTION, "Export the selected diagram as image");
    }

    public void actionPerformed(ActionEvent e) {

        if (MainFrame.getInstance().getProjectView().getActiveMindmap() == null) return;

        try {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogType(JFileChooser.SAVE_DIALOG);
            jfc.setSelectedFile(new File(MainFrame.getInstance().getProjectView().getActiveMindmap().getName() + "_diagram.png"));
            jfc.setFileFilter(new FileNameExtensionFilter("PNG image file","png"));
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                String filename = jfc.getSelectedFile().toString();
                if (!filename .endsWith(".png"))
                    filename += ".png";

                BufferedImage buffer = ScreenImage.createImage(MainFrame.getInstance().getProjectView().getActiveMindmap().getDiagramRepresentation().getParent());
                ScreenImage.writeImage(buffer, filename);
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (AWTException ex) {
            throw new RuntimeException(ex);
        }

    }
}