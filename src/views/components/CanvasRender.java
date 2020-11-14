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
        new Timer(100, actionEvent -> {
            repaint();
        }).start();
        setVisible(true);
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public void removeAllFigures() {
        figures.clear();
    }

    public void draw(Graphics2D g) {
        if (!isLoaded) {
            isLoaded = true;
            setNormalCoordinateSystem(g);
        }
        setBackground(g);
        setGuideLines(g);

        figures.forEach(figure -> figure.getEdges().forEach(edge -> {
            g.setColor(edge.getColor());
            g.setStroke(new BasicStroke(4));
            g.drawLine(
                (int) edge.getVertexOrigin().getX(),
                (int) edge.getVertexOrigin().getY(),
                (int) edge.getVertexDestiny().getX(),
                (int) edge.getVertexDestiny().getY()
            );
        }));

    }

    public void setTransformation(Transformation transformation) {
        scaleX(transformation.getScaleX());
        scaleY(transformation.getScaleY());
        scaleZ(transformation.getScaleZ());
        translateX(transformation.getTranslateX());
        translateY(transformation.getTranslateY());
        translateZ(transformation.getTranslateZ());
        rotateX(transformation.getRotateX());
        rotateY(transformation.getRotateY());
        rotateZ(transformation.getRotateZ());
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

    private void setGuideLines(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(1));
        g.drawLine(-getWidth(), 0, getWidth(), 0);
        g.drawLine(0, -getHeight(), 0, getHeight());

        int heightMark = 3;

        for(int i = -getWidth(); i < getWidth(); i+=10) {
            g.drawLine(i, -heightMark, i, heightMark);
        }

        for(int i = -getHeight(); i < getHeight(); i+=10) {
            g.drawLine(-heightMark, i, heightMark, i);
        }
    }

    private void setBackground(Graphics2D g) {
//        g.setPaint(new GradientPaint(
//            getWidth()/2, 0, new Color(100,100,100),
//            getWidth()/2, getHeight()+200, new Color(200, 200, 200)
//        ));

        g.setColor(Color.WHITE);
        g.fillRect(-getWidth()/2, -getHeight()/2, getWidth(), getHeight());

    }

    private void setNormalCoordinateSystem(Graphics2D g) {
        g.translate(getWidth()/2, getHeight()/2);
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

        int x = 0;
        int y = 0;

        g.drawImage(offscreen, x, y, getWidth(), getHeight(), this);
    }

}
