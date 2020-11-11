import storage.FiguresStorage;
import views.MainWindow;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            FiguresStorage figuresStorage = new FiguresStorage("figures.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        new MainWindow("3D Viewer");
    }
}
