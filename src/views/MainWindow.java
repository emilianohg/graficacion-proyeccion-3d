package views;

import domain.Figure;
import domain.Transformation;
import storage.FigureSaved;
import storage.FiguresStorage;
import views.components.CanvasRender;
import views.components.ControlXYZ;
import views.components.TableEdges;
import views.components.TableVertices;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;

public final class MainWindow extends JFrame {

    private FiguresStorage figuresStorage;

    private CanvasRender canvasOriginal;
    private CanvasRender canvasTransformed;

    private ControlXYZ controlScale;
    private ControlXYZ controlRotation;
    private ControlXYZ controlTranslation;

    TableVertices tableVertices;
    TableEdges tableEdges;

    private JComboBox<FigureSaved> selectFigure;

    Figure figureOriginal, figureTransformed;

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
        setMaximumSize(new Dimension(1300, 800));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        figureOriginal = new Figure();
        figureTransformed = new Figure();

        canvasOriginal      = new CanvasRender();
        canvasTransformed   = new CanvasRender();

        canvasOriginal.setSize(400, 600);
        canvasTransformed.setSize(400, 600);

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

        controlRotation = new ControlXYZ("Rotacion");

        controlTranslation = new ControlXYZ("Traslacion");

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(0,1));

        JButton btnTransform = new JButton("Transformar");
        btnTransform.addActionListener(this::transform);
        panelButtons.add(btnTransform);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(this::resetTransform);
        panelButtons.add(btnReset);

        JPanel panelTables = new JPanel();
        panelTables.setLayout(new GridLayout(2,1));
        panelTables.setSize(600, 300);
        panelTables.setPreferredSize(new Dimension(600, 300));
        panelTables.setMaximumSize(new Dimension(600, 300));

        tableVertices   = new TableVertices(figureOriginal, figureTransformed);
        tableEdges      = new TableEdges(figureOriginal, figureTransformed);

        panelTables.add(tableVertices);
        panelTables.add(tableEdges);

        add(panelTables, BorderLayout.WEST);

        JPanel panelSelectFigure = new JPanel();
        panelSelectFigure.setLayout(new GridLayout(2, 1));
        selectFigure        = new JComboBox<>();
        selectFigure.addItemListener(this::changeFigure);
        selectFigure.addItem(new FigureSaved(-1, "Vacio", "", "", ""));
        figuresStorage.getAll().forEach(selectFigure::addItem);
        panelSelectFigure.add(new JLabel("Figura inicial"));
        panelSelectFigure.add(selectFigure);

        panelControls.add(panelSelectFigure);
        panelControls.add(controlScale);
        panelControls.add(controlRotation);
        panelControls.add(controlTranslation);
        panelControls.add(panelButtons);

        add(panelControls, BorderLayout.NORTH);

        resetTransform(null);

        pack();
        setVisible(true);
    }

    private void changeFigure(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
            FigureSaved figureSaved = (FigureSaved) itemEvent.getItem();

            canvasOriginal.removeAllFigures();
            canvasTransformed.removeAllFigures();

            if (figureSaved.getId() == -1) {
                figureOriginal = new Figure();
                figureTransformed = new Figure();
            } else {
                figureOriginal = figureSaved.getFigure();
                figureTransformed = figureOriginal.clone();
            }


            canvasOriginal.addFigure(figureOriginal);
            canvasTransformed.addFigure(figureTransformed);

            resetTransform(null);
        }
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

            figureTransformed = figureOriginal.clone();
            canvasTransformed.removeAllFigures();
            canvasTransformed.addFigure(figureTransformed);

            canvasTransformed.setTransformation(transformation);

            tableVertices.updateTable(figureOriginal, figureTransformed);
            tableEdges.updateTable(figureOriginal, figureTransformed);
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

}
