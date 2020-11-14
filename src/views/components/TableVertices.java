package views.components;

import domain.Figure;
import domain.Vertex;
import utils.MathUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class TableVertices extends JPanel {

    JTable table;
    DefaultTableModel tableModel;

    JTextField inputX, inputY, inputZ;
    JButton btnAdd;

    Figure figureOrigin, figureTransformed;
    List<VertexRow> vertices;

    public TableVertices(Figure figureOrigin, Figure figureTransformed) {

        setPreferredSize(new Dimension(400, 400));
        setMaximumSize(new Dimension(400, 400));

        setLayout(new BorderLayout());

        this.figureOrigin = figureOrigin;
        this.figureTransformed = figureTransformed;

        vertices = new Vector<>();

        String[] titles = {"ID", "X", "Y", "Z", "TX", "TY", "TZ"};
        tableModel = new DefaultTableModel(titles, 0);
        table = new JTable(tableModel);

        table.setEnabled(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        if (figureOrigin != null && figureTransformed != null) {
            updateTable(figureOrigin, figureTransformed);
        }

        JPanel panelX = new JPanel();
        panelX.setLayout(new GridLayout(2,1));
        panelX.add(new JLabel("Vértice X:"));
        inputX = new JTextField();
        panelX.add(inputX);

        JPanel panelY = new JPanel();
        panelY.setLayout(new GridLayout(2,1));
        panelY.add(new JLabel("Vértice Y:"));
        inputY = new JTextField();
        panelY.add(inputY);

        JPanel panelZ = new JPanel();
        panelZ.setLayout(new GridLayout(2,1));
        panelZ.add(new JLabel("Vértice Z:"));
        inputZ = new JTextField();
        panelZ.add(inputZ);

        btnAdd = new JButton("Agregar vertice");
        btnAdd.addActionListener(this::addVertex);

        JPanel control = new JPanel();
        control.setLayout(new GridLayout(1, 4));

        control.add(panelX);
        control.add(panelY);
        control.add(panelZ);
        control.add(btnAdd);

        add(control, BorderLayout.NORTH);

        setVisible(true);
    }

    private void addVertex(ActionEvent actionEvent) {
        double x = Double.parseDouble(inputX.getText().isEmpty() ? "0" : inputX.getText());
        double y = Double.parseDouble(inputY.getText().isEmpty() ? "0" : inputY.getText());
        double z = Double.parseDouble(inputZ.getText().isEmpty() ? "0" : inputZ.getText());

        figureOrigin.addVertex(x, y, z);
        figureTransformed.addVertex(x, y, z);

        updateTable(figureOrigin, figureTransformed);
    }

    public TableVertices() {
        this(null, null);
    }

    public void updateTable(Figure figureOrigin, Figure figureTransformed) {

        this.figureOrigin = figureOrigin;
        this.figureTransformed = figureTransformed;

        List<Vertex> verticesOrigin         = figureOrigin.getVertices();
        List<Vertex> verticesTransformed    = figureTransformed.getVertices();

        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        vertices.clear();
        for(int i = 0; i < verticesOrigin.size(); i++) {
            Vertex vertexOrigin         = verticesOrigin.get(i);
            Vertex vertexTransformed    = verticesTransformed.get(i);

            vertices.add(new VertexRow(
                vertexOrigin.getId(),
                vertexOrigin,
                vertexTransformed
            ));
        }

        vertices.forEach(vertexRow -> {
            Object[] row = {
                vertexRow.getVertexOriginal().getId(),
                MathUtils.round(vertexRow.getVertexOriginal().getX(), 2),
                MathUtils.round(vertexRow.getVertexOriginal().getY(), 2),
                MathUtils.round(vertexRow.getVertexOriginal().getZ(), 2),
                MathUtils.round(vertexRow.getVertexTransformed().getX(), 2),
                MathUtils.round(vertexRow.getVertexTransformed().getY(), 2),
                MathUtils.round(vertexRow.getVertexTransformed().getZ(), 2),
            };
            tableModel.addRow(row);
        });
    }

}
