package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;
import dsw.gerumap.app.mapRepository.implementation.Project;

import java.io.File;
import java.util.List;

public interface Serializer {
    List<MindMapDiagramElement> loadMindmap(File file);
    void saveMindmap(MindMapView node);

}
