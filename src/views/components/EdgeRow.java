package views.components;

import domain.Edge;
import domain.Vertex;

public class EdgeRow {
    private int id;
    private Edge EdgeOriginal, EdgeTransformed;

    public EdgeRow(int id, Edge edgeOriginal, Edge edgeTransformed) {
        this.id         = id;
        EdgeOriginal    = edgeOriginal;
        EdgeTransformed = edgeTransformed;
    }

    public int getId() {
        return id;
    }

    public Edge getEdgeOriginal() {
        return EdgeOriginal;
    }

    public Edge getEdgeTransformed() {
        return EdgeTransformed;
    }
}
