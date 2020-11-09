package storage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class FiguresStorage extends StorageHandler<FigureSaved> {

    public FiguresStorage(String filename) throws IOException {
        super(filename);
    }

    public List<FigureSaved> getAll() {
        List<FigureSaved> records = new Vector<>();

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

            int    id               = Integer.parseInt(data[0]);
            String name             = data[1];
            String image_url        = data[2];
            String file_vertex_url  = data[3];
            String file_edges_url   = data[4];

            records.add(new FigureSaved(
                id,
                name,
                image_url,
                file_vertex_url,
                file_edges_url
            ));

        }

        reader.close();
        return records;
    }
}
