package views.components;

import domain.Figure;
import domain.Vertex;
import utils.MathUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class TableVertices extends JPanel {

    JTable table;
    DefaultTableModel tableModel;

    Figure figureOrigin, figureTransformed;
    List<VertexRow> vertices;

    public TableVertices(Figure figureOrigin, Figure figureTransformed) {

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
        setVisible(true);
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
