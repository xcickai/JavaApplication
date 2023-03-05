package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ElementDeserializer implements JsonDeserializer<MindMapDiagramElement> {

    private String elementTypeNameField;
    private Gson gson;
    private Map<String, Class<? extends MindMapDiagramElement>> elementTypeRegistry;

    public ElementDeserializer(String elementTypeNameField) {
        this.elementTypeNameField = elementTypeNameField;
        this.gson = new Gson();
        this.elementTypeRegistry = new HashMap<>();
    }

    public void linkElementType(ElementType elementTypeName, Class<? extends MindMapDiagramElement> elementType) {
        elementTypeRegistry.put(elementTypeName.toString(), elementType);
    }

    @Override
    public MindMapDiagramElement deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject elementObject = jsonElement.getAsJsonObject();

        JsonElement elementTypeObject = elementObject.get(elementTypeNameField.toString());
        Class<? extends MindMapDiagramElement> elementType = elementTypeRegistry.get(elementTypeObject.getAsString());
        return gson.fromJson(elementObject, elementType);
    }

}
