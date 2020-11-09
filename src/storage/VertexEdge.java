package storage;

public class VertexEdge {
    private int idOrigin;
    private int idDestiny;

    public VertexEdge(int idOrigin, int idDestiny) {
        this.idOrigin = idOrigin;
        this.idDestiny = idDestiny;
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
}
