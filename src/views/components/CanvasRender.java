package views.components;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public final class CanvasRender extends Canvas {

    private Graphics currentGraphic;
    private Image offscreen;

    private List<Figure> figures;

    private boolean isLoaded;

    public CanvasRender() {
        figures = new Vector<>();
        isLoaded = false;
        new Timer(100, actionEvent -> repaint());
        setVisible(true);
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public void draw(Graphics2D g) {
        if (!isLoaded) {
            isLoaded = true;
            setNormalCoordinateSystem(g);
        }
        setBackground(g);

        figures.forEach(figure -> figure.getEdges().forEach(edge -> {
            g.setStroke(new BasicStroke(4));
            g.drawLine(
                (int) edge.getVertexOrigin().getX(),
                (int) edge.getVertexOrigin().getY(),
                (int) edge.getVertexDestiny().getX(),
                (int) edge.getVertexDestiny().getY()
            );
        }));

    }

    public void scaleX(double delta) {
        figures.forEach(figure -> transform(figure, new ScalingMatrix(delta, 1, 1)));
    }

    public void scaleY(double delta) {
        figures.forEach(figure -> transform(figure, new ScalingMatrix(1, delta, 1)));
    }

    public void scaleZ(double delta) {
        figures.forEach(figure -> transform(figure, new ScalingMatrix(1, 1, delta)));
    }

    public void translateX(double delta) {
        figures.forEach(figure -> transform(figure, new TranslationMatrix(delta, 0, 0)));
    }

    public void translateY(double delta) {
        figures.forEach(figure -> transform(figure, new TranslationMatrix(0, delta, 0)));
    }

    public void translateZ(double delta) {
        figures.forEach(figure -> transform(figure, new TranslationMatrix(0, 0, delta)));
    }

    public void rotateX(double angle) {
        figures.forEach(figure -> transform(figure, new RotationMatrixX(angle)));
    }

    public void rotateY(double angle) {
        figures.forEach(figure -> transform(figure, new RotationMatrixY(angle)));
    }

    public void rotateZ(double angle) {
        figures.forEach(figure -> transform(figure, new RotationMatrixZ(angle)));
    }

    public void transform(Figure figure, MatrixTransformation matrix) {
        figure.transform(matrix);
    }

    private void setBackground(Graphics2D g) {
        g.setPaint(new GradientPaint(
            getWidth()/2, 0, new Color(100,100,100),
            getWidth()/2, getHeight()+200, new Color(200, 200, 200)
        ));
        g.fillRect(0, 0, getHeight(), getHeight());

        g.setColor(Color.RED);
        g.fillRect(10, 10, 10, 10);
        g.setColor(Color.BLUE);
        g.fillRect(10, 20, 10, 10);
    }

    private void setNormalCoordinateSystem(Graphics2D g) {
        g.translate(0, getHeight());
        g.scale(1, -1);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (offscreen == null) {
            offscreen = createImage(getWidth(), getHeight());
            repaint();
            return;
        }
        if (currentGraphic == null) {
            currentGraphic = offscreen.getGraphics();
        }
        Graphics2D g2d = (Graphics2D) currentGraphic;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paint(g2d);
        draw(g2d);
        g.drawImage(offscreen, 0, 0, getWidth(), getHeight(), this);
    }

}
