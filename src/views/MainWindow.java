package views;

import domain.Edge;
import domain.Figure;
import domain.Vertex;
import views.components.CanvasRender;

import javax.swing.*;
import java.awt.*;

public final class MainWindow extends JFrame {

    private CanvasRender canvasOriginal;
    private CanvasRender canvasTransformed;

    public MainWindow(String title) {
        super(title);
        initComponent();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initComponent() {
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        Figure triangleOriginal = new Figure();
        Figure triangle = new Figure();

        triangleOriginal.addEdge(new Edge(
                new Vertex(20, 30, 10),
                new Vertex(100, 30, 40)
        ));
        triangleOriginal.addEdge(new Edge(
                new Vertex(100, 30, 40),
                new Vertex(60, 400, 30)
        ));
        triangleOriginal.addEdge(new Edge(
                new Vertex(60, 400, 30),
                new Vertex(20, 30, 10)
        ));

        triangle.addEdge(new Edge(
            new Vertex(20, 30, 10),
            new Vertex(100, 30, 40)
        ));
        triangle.addEdge(new Edge(
            new Vertex(100, 30, 40),
            new Vertex(60, 400, 30)
        ));
        triangle.addEdge(new Edge(
            new Vertex(60, 400, 30),
            new Vertex(20, 30, 10)
        ));

        canvasOriginal      = new CanvasRender();
        canvasTransformed   = new CanvasRender();

        canvasOriginal.addFigure(triangleOriginal);
        canvasTransformed.addFigure(triangle);
        canvasTransformed.rotateZ(25);
        //canvasTransformed.rotateX(60);
        //canvasTransformed.rotateY(100);
        canvasTransformed.translateX(150);
        canvasTransformed.translateY(250);
        canvasTransformed.scaleX(2);


        add(canvasOriginal);
        add(canvasTransformed);

        setVisible(true);
    }

}
