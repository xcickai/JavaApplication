package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


@Getter
public class MapViewPanel extends JPanel {
    private MindMapView mindMapView;
    private MindMapDiagramView diagramView;

    public MapViewPanel(MapNode mapNode) {
        mindMapView = (MindMapView) mapNode;
        setUIComponents();
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        repaint();
    }

    private void setUIComponents() {
        this.setLayout(new BorderLayout());
        // Setting header
        JPanel header = new JPanel();
        header.add(new JLabel("Name: " + mindMapView.getName()));
        header.add(new JLabel("Author: " + mindMapView.getAuthor()));
        this.add(header, BorderLayout.NORTH);

        JPanel diagramPanel = new JPanel();
        diagramPanel.setLayout(new BoxLayout(diagramPanel, BoxLayout.Y_AXIS));

        // Setting body
        JScrollPane scrollPane = new JScrollPane(mindMapView.getDiagramRepresentation(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setAutoscrolls(true);
        mindMapView.getDiagramRepresentation().setBackground(Color.WHITE);
        diagramPanel.add(scrollPane);

        // Zoom Buttons
        JPanel bottomOptionPanel = new JPanel();
        JButton zoomInBtn = new JButton("Zoom in");
        JButton zoomOutBtn = new JButton("Zoom out");
        JButton editElementBtn = new JButton("Edit selection");
        zoomInBtn.addActionListener(e -> {
            mindMapView.getDiagramRepresentation().setZoomFactor(mindMapView.getDiagramRepresentation().getZoomFactor() * 1.1);
        });
        zoomOutBtn.addActionListener(e -> {
            mindMapView.getDiagramRepresentation().setZoomFactor(mindMapView.getDiagramRepresentation().getZoomFactor() / 1.1);
        });
        editElementBtn.addActionListener(e -> {
            ArrayList<DiagramConceptPainter> selectedConcepts = diagramView.getSelectedElementPainters();
            if (!selectedConcepts.isEmpty()) {
                ColorChooser colorChooser = new ColorChooser(selectedConcepts);
                colorChooser.createAndShowGUI();
            }
            mindMapView.setChanged(true);
        });
        bottomOptionPanel.add(zoomInBtn);
        bottomOptionPanel.add(zoomOutBtn);
        bottomOptionPanel.add(editElementBtn);
        diagramPanel.add(bottomOptionPanel);

        this.add(diagramPanel, BorderLayout.CENTER);
        diagramView = mindMapView.getDiagramRepresentation();
    }

}
