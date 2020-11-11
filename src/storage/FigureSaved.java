package storage;

import domain.Figure;
import domain.Vertex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FigureSaved {

    private int     id;
    private String  name;
    private String  image_url;
    private String  file_vertex_url;
    private String  file_edges_url;
    private Image   image;
    private Figure  figure;

    public FigureSaved(
        int id,
        String name,
        String image_url,
        String file_vertex_url,
        String file_edges_url
    ) {
        this.id             = id;
        this.name           = name;
        this.image_url      = image_url;
        this.file_vertex_url= file_vertex_url;
        this.file_edges_url = file_edges_url;

        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(image_url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getFile_vertex_url() {
        return file_vertex_url;
    }

    public String getFile_edges_url() {
        return file_edges_url;
    }

    public Image getImage() {
        return image;
    }

    public Figure getFigure() {
        EdgesStorage edgesStorage;
        VertexStorage vertexStorage;

        try {
            edgesStorage = new EdgesStorage(file_edges_url);
            vertexStorage = new VertexStorage(file_vertex_url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        List<Vertex> vertices = vertexStorage.getAll();
        List<VertexEdge> vertexEdges = edgesStorage.getAll();

        Figure figure = new Figure();
        vertices.forEach(figure::addVertex);
        vertexEdges.forEach(vertexEdge -> figure.makeEdge(vertexEdge.getIdOrigin(), vertexEdge.getIdDestiny()));

        return figure;
    }

    @Override
    public String toString() {
        return "FigureSaved{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", file_vertex_url='" + file_vertex_url + '\'' +
                ", file_edges_url='" + file_edges_url + '\'' +
                '}';
    }
}
