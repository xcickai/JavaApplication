package dsw.gerumap.app.gui.swing.controller.actions;

import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseEventController extends MouseAdapter implements MouseListener, MouseMotionListener {
    private JPanel mapView;

    public MouseEventController(JPanel mapView){
        this.mapView = mapView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        MainFrame.getInstance().getProjectView().getActionStateManager().getCurrentState().mousePressed(e, ((MindMapDiagramView)mapView));
        ((MindMapDiagramView)mapView).updateFor(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getActionStateManager().getCurrentState().mouseDragged(e, ((MindMapDiagramView)mapView));
        ((MindMapDiagramView)mapView).updateFor(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getActionStateManager().getCurrentState().mouseReleased(e, ((MindMapDiagramView)mapView));
        ((MindMapDiagramView)mapView).updateFor(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}
