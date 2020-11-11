package storage;

import java.awt.*;

public class VertexEdge {
    private int idOrigin;
    private int idDestiny;
    private Color color;

    public VertexEdge(int idOrigin, int idDestiny, Color color) {
        this.idOrigin   = idOrigin;
        this.idDestiny  = idDestiny;
        this.color      = color;
    }

    public VertexEdge(int idOrigin, int idDestiny) {
        this(idOrigin, idDestiny, Color.BLACK);
    }

    public int getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(int idOrigin) {
        this.idOrigin = idOrigin;
    }

    public int getIdDestiny() {
        return idDestiny;
    }

    public void setIdDestiny(int idDestiny) {
        this.idDestiny = idDestiny;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
