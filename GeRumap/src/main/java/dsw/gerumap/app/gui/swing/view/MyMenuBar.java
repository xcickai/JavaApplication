package dsw.gerumap.app.gui.swing.view;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getEditProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());

        fileMenu.add(MainFrame.getInstance().getActionManager().getInformation());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        this.add(fileMenu);
    }

}
