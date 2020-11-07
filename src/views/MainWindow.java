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

        int v1 = triangle.addVertex(20, 30, 10);
        int v2 = triangle.addVertex(100, 30, 40);
        int v3 = triangle.addVertex(60, 400, 30);

        triangle.makeEdge(v1, v2);
        triangle.makeEdge(v2, v3);
        triangle.makeEdge(v3, v1);

        int vo1 = triangleOriginal.addVertex(20, 30, 10);
        int vo2 = triangleOriginal.addVertex(100, 30, 40);
        int vo3 = triangleOriginal.addVertex(60, 400, 30);

        triangleOriginal.makeEdge(v1, v2);
        triangleOriginal.makeEdge(v2, v3);
        triangleOriginal.makeEdge(v3, v1);



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
