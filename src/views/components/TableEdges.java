package views.components;

import domain.Edge;
import domain.Figure;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TableEdges extends JPanel {
    JTable table;
    Color currentColor;
    DefaultTableModel tableModel;

    JTextField inputVertexOrigin, inputVertexDestiny, inputColor;
    JButton btnAdd, btnSelectColor;

    Figure figureOrigin, figureTransformed;

    public TableEdges(Figure figureOrigin, Figure figureTransformed) {

        setLayout(new BorderLayout());

        this.figureOrigin = figureOrigin;
        this.figureTransformed = figureTransformed;

        String[] titles = {"Origen", "Destino", "Color"};
        tableModel = new DefaultTableModel(titles, 0);
        table = new JTable(tableModel);

        table.setEnabled(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        if (figureOrigin != null && figureTransformed != null) {
            updateTable(figureOrigin, figureTransformed);
        }

        currentColor = Color.BLACK;

        JPanel panelOrigin = new JPanel();
        panelOrigin.setLayout(new GridLayout(2,1));
        panelOrigin.add(new JLabel("Vértice origen:"));
        inputVertexOrigin = new JTextField();
        panelOrigin.add(inputVertexOrigin);

        JPanel panelDestiny = new JPanel();
        panelDestiny.setLayout(new GridLayout(2,1));
        panelDestiny.add(new JLabel("Vértice destino:"));
        inputVertexDestiny = new JTextField();
        panelDestiny.add(inputVertexDestiny);

        JPanel panelColor = new JPanel();
        panelColor.setLayout(new GridLayout(2,1));
        panelColor.add(new JLabel("Color:"));
        inputColor = new JTextField();
        inputColor.setBackground(currentColor);
        inputColor.setEditable(false);
        panelColor.add(inputColor);

        btnAdd = new JButton("Agregar arista");
        btnAdd.addActionListener(this::addEdge);

        btnSelectColor = new JButton("Elegir color");
        btnSelectColor.addActionListener(this::selectColor);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(2,1));
        panelButtons.add(btnSelectColor);
        panelButtons.add(btnAdd);

        JPanel control = new JPanel();
        control.setLayout(new GridLayout(1, 4));

        control.add(panelOrigin);
        control.add(panelDestiny);
        control.add(panelColor);
        control.add(panelButtons);

        add(control, BorderLayout.NORTH);

        setVisible(true);
    }

    private void selectColor(ActionEvent actionEvent) {
        Color colorSelected = JColorChooser.showDialog(
            this,
            "Selecciona un color",
            currentColor
        );

        if (colorSelected == null) {
            return;
        }

        currentColor = colorSelected;
        inputColor.setBackground(currentColor);
    }

    public void updateTable(Figure figureOrigin, Figure figureTransformed) {
        this.figureOrigin = figureOrigin;
        this.figureTransformed = figureTransformed;

        List<Edge> edgesOrigin         = figureOrigin.getEdges();

        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        edgesOrigin.forEach(edge -> {
            String color = String.format("%s, %s, %s",
                edge.getColor().getRed(),
                edge.getColor().getGreen(),
                edge.getColor().getBlue()
            );

            Object[] row = {
                "Vértice " + edge.getVertexOrigin().getId(),
                "Vértice " + edge.getVertexDestiny().getId(),
                color
            };
            tableModel.addRow(row);
        });
    }

    private void addEdge(ActionEvent actionEvent) {

        if (
            inputVertexOrigin.getText().isEmpty()
            || inputVertexDestiny.getText().isEmpty()
        ) {
            return;
        }

        int vertexO = Integer.parseInt(inputVertexOrigin.getText());
        int vertexD = Integer.parseInt(inputVertexDestiny.getText());

        figureOrigin.makeEdge(vertexO, vertexD, currentColor);
        figureTransformed.makeEdge(vertexO, vertexD, currentColor);

        updateTable(figureOrigin, figureTransformed);
    }
    
    
}
