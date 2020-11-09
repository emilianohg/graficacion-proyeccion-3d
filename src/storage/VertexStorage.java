package storage;


import domain.Vertex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class VertexStorage extends StorageHandler<Vertex> {

    public VertexStorage(String filename) throws IOException {
        super(filename);
    }

    public List<Vertex> getAll() {
        List<Vertex> records = new Vector<>();

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

            int    id   = Integer.parseInt(data[0]);
            double x    = Double.parseDouble(data[1]);
            double y    = Double.parseDouble(data[2]);
            double z    = Double.parseDouble(data[3]);

            records.add(new Vertex(id, x, y, z));

        }

        reader.close();
        return records;
    }
}
