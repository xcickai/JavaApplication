package dsw.gerumap.app.gui.swing.tree.controller;


import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;
    private JTextField edit = null;

    public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object renderer, boolean arg2, boolean arg3, boolean arg4, int arg5){
        clickedOn = renderer;
        edit = new JTextField(renderer.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject tree){
        if (tree instanceof MouseEvent){
            if(((MouseEvent)tree).getClickCount()==3){
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent e){
        if(!(clickedOn instanceof MapTreeItem)){
            return;
        }
        MapTreeItem clicked = (MapTreeItem) clickedOn;
        MainFrame.getInstance().getProjectView().updateView(clicked);
        clicked.setName(e.getActionCommand());

    }
}
