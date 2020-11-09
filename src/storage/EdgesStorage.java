package storage;


import domain.Edge;
import domain.Vertex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class EdgesStorage extends StorageHandler<VertexEdge> {

    public EdgesStorage(String filename) throws IOException {
        super(filename);
    }

    public List<VertexEdge> getAll() {
        List<VertexEdge> records = new Vector<>();

        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return records;
        }

        reader.nextLine(); // headers
        while (reader.hasNextLine()) {

            String line = reader.nextLine();
            String[] data = line.split(",");

            int id_vertex_origin  = Integer.parseInt(data[0]);
            int id_vertex_destiny = Integer.parseInt(data[1]);

            records.add(new VertexEdge(id_vertex_origin, id_vertex_destiny));

        }

        reader.close();
        return records;
    }
}
