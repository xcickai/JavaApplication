package dsw.gerumap.app.gui.swing.controller.actions.main;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class InformationAboutCreators extends AbstractGeRuMapAction {

    public InformationAboutCreators() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK
        ));
        putValue(SMALL_ICON, loadIcon("/images/information.png"));
        putValue(NAME, "Information");
        putValue(SHORT_DESCRIPTION, "Information");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            JDialog d = new JDialog();
            d.setTitle("Information");
            JLabel l = new JLabel("<html>Ivan Cicka RN79 2022<br/>Luka Korica RN81 2022</html>");
            d.add(l);
            d.setSize(150, 100);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
    }
}
