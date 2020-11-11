package views;

import domain.Figure;
import domain.Transformation;
import storage.FigureSaved;
import storage.FiguresStorage;
import views.components.CanvasRender;
import views.components.ControlXYZ;
import views.components.TableVertices;

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

    TableVertices tableVertices;

    Figure figureOriginal, figureTransformed;

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
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        canvasOriginal      = new CanvasRender();
        canvasTransformed   = new CanvasRender();

        canvasOriginal.setSize(400, 600);
        canvasTransformed.setSize(400, 600);

        figureOriginal = getFigure(ID_FIGURE_DEMO);
        figureTransformed = getFigure(ID_FIGURE_DEMO);

        canvasOriginal.addFigure(figureOriginal);
        canvasTransformed.addFigure(figureTransformed);

        JPanel panelCenter = new JPanel();
        panelCenter.setSize(800, 600);
        panelCenter.add(getPanelRender("Original", canvasOriginal));
        panelCenter.add(getPanelRender("Transformada", canvasTransformed));

        add(panelCenter, BorderLayout.CENTER);

        JPanel panelControls = new JPanel();
        panelControls.setSize(190,100);

        controlScale = new ControlXYZ("Escala");
        panelControls.add(controlScale);

        controlRotation = new ControlXYZ("Rotacion");
        panelControls.add(controlRotation);

        controlTranslation = new ControlXYZ("Traslacion");
        panelControls.add(controlTranslation);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(0,1));

        JButton btnTransform = new JButton("Transformar");
        btnTransform.addActionListener(this::transform);
        panelButtons.add(btnTransform);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(this::resetTransform);
        panelButtons.add(btnReset);

        panelControls.add(panelButtons);

        add(panelControls, BorderLayout.NORTH);

        tableVertices = new TableVertices(figureOriginal, figureTransformed);

        add(tableVertices, BorderLayout.WEST);

        resetTransform(null);

        pack();
        setVisible(true);
    }

    private void resetTransform(ActionEvent actionEvent) {
        controlScale.setInputX(1);
        controlScale.setInputY(1);
        controlScale.setInputZ(1);

        controlRotation.setInputX(0);
        controlRotation.setInputY(0);
        controlRotation.setInputZ(0);

        controlTranslation.setInputX(0);
        controlTranslation.setInputY(0);
        controlTranslation.setInputZ(0);

        transform(actionEvent);
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

            figureTransformed = getFigure(ID_FIGURE_DEMO);
            canvasTransformed.removeAllFigures();
            canvasTransformed.addFigure(figureTransformed);

            canvasTransformed.setTransformation(transformation);

            tableVertices.updateTable(figureOriginal, figureTransformed);
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
