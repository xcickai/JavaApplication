package dsw.gerumap.app.gui.swing.controller.actions.tools;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ElementSelectAction extends AbstractGeRuMapAction {
    public ElementSelectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_H, InputEvent.ALT_DOWN_MASK
        ));
        putValue(NAME, "Select");
        putValue(SHORT_DESCRIPTION, "Select an element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getProjectView().startSelectState();
    }
}
