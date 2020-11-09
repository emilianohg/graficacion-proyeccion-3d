package storage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class StorageHandler<E> {

    final private String filename;
    protected File file;

    public StorageHandler(String filename) throws IOException {
        this.filename = filename;
        try {
            URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource(this.filename)).toURI();
            file = new File(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getFilename() {
        return filename;
    }

    public abstract List<E> getAll();

}

