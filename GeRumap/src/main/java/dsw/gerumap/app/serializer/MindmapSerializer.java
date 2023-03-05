package dsw.gerumap.app.serializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MindmapSerializer implements Serializer {
    @Override
    public List<MindMapDiagramElement> loadMindmap(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            ElementDeserializer elementDeserializer = new ElementDeserializer("elementType");
            elementDeserializer.linkElementType(ElementType.CONCEPT, MindMapDiagramConcept.class);
            elementDeserializer.linkElementType(ElementType.LINK, MindMapDiagramLink.class);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(MindMapDiagramElement.class, elementDeserializer)
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            List<MindMapDiagramElement> elementList = gson.fromJson(fileReader, new TypeToken<List<MindMapDiagramElement>>(){}.getType());
            return elementList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveMindmap(MindMapView mindmap) {
        try (FileWriter writer = new FileWriter(mindmap.getFilePath())) {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            Gson gson = builder
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            gson.toJson(mindmap.getDiagramRepresentation().getElementsToSerialize(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

