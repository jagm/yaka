package pl.jagm.kanban.model;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public enum Color {

    BLUE,
    PINK,
    GREEN,
    ORANGE,
    LIGHT_BLUE,
    BEIGE,
    YELLOW;

    public static String getJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(Color.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "[]";
    }
}
