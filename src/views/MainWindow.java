package views;

import domain.Figure;
import domain.Transformation;
import storage.FigureSaved;
import storage.FiguresStorage;
import views.components.CanvasRender;
import views.components.ControlXYZ;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public final class MainWindow extends JFrame {

    private FiguresStorage figuresStorage;

    private CanvasRender canvasOriginal;
    private CanvasRender canvasTransformed;

    private ControlXYZ controlScale;
    private ControlXYZ controlRotation;
    private ControlXYZ controlTranslation;

    private final int ID_FIGURE_DEMO = 3;

    public MainWindow(String title) {
        super(title);
        try {
            figuresStorage = new FiguresStorage("figures.csv");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        initComponent();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initComponent() {
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        canvasOriginal      = new CanvasRender();
        canvasTransformed   = new CanvasRender();

        canvasOriginal.setSize(400, 600);
        canvasTransformed.setSize(400, 600);

        canvasOriginal.addFigure(getFigure(ID_FIGURE_DEMO));
        canvasTransformed.addFigure(getFigure(ID_FIGURE_DEMO));

        JPanel panelCenter = new JPanel();
        panelCenter.setSize(800, 600);
        panelCenter.add(getPanelRender("Original", canvasOriginal));
        panelCenter.add(getPanelRender("Transformada", canvasTransformed));

        add(panelCenter, BorderLayout.CENTER);

        JPanel panelControls = new JPanel();
        panelControls.setSize(190,100);

        controlScale = new ControlXYZ("Escala");
        controlScale.setInputX(1);
        controlScale.setInputY(1);
        controlScale.setInputZ(1);
        panelControls.add(controlScale);

        controlRotation = new ControlXYZ("Rotacion");
        panelControls.add(controlRotation);

        controlTranslation = new ControlXYZ("Traslacion");
        panelControls.add(controlTranslation);

        JButton btnTransform = new JButton("Transformar");
        btnTransform.addActionListener(this::transform);
        panelControls.add(btnTransform);

        add(panelControls, BorderLayout.NORTH);

        setVisible(true);
    }

        private void transform(ActionEvent actionEvent) {
            Transformation transformation = new Transformation(
                controlScale.getInputX(),
                controlScale.getInputY(),
                controlScale.getInputZ(),
                controlRotation.getInputX(),
                controlRotation.getInputY(),
                controlRotation.getInputZ(),
                controlTranslation.getInputX(),
                controlTranslation.getInputY(),
                controlTranslation.getInputZ()
            );

            canvasTransformed.removeAllFigures();
            canvasTransformed.addFigure(getFigure(ID_FIGURE_DEMO));
            canvasTransformed.setTransformation(transformation);

        }

    public JPanel getPanelRender(String title, CanvasRender canvas) {
        Border line = BorderFactory.createLineBorder(Color.GRAY, 2);
        Border titled = BorderFactory.createTitledBorder(
                line,
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                null,
                Color.DARK_GRAY
        );

        JPanel panel = new JPanel();
        panel.setBorder(titled);
        panel.add(canvas);

        return panel;
    }

    public Figure getFigure(int id) {
        List<FigureSaved> figureSavedList = figuresStorage.getAll();
        Figure triangleOriginal = figureSavedList.get(id).getFigure();
        return figureSavedList.get(id).getFigure();
    }

}
