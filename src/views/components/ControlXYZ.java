package views.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyListener;

public class ControlXYZ extends JPanel {

    JTextField inputX, inputY, inputZ;
    private String title;

    public ControlXYZ(String title) {
        this.title = title;
        setPreferredSize(new Dimension(100, 90));
        setLayout(new GridLayout(3, 1));

        JPanel panelX = new JPanel();
        panelX.setSize(300, 30);
        panelX.setLayout(new BorderLayout(5, 2));
        panelX.add(new JLabel("X:"), BorderLayout.WEST);
        inputX = new JTextField();
        panelX.add(inputX, BorderLayout.CENTER);

        JPanel panelY = new JPanel();
        panelY.setLayout(new BorderLayout(5, 2));
        panelY.add(new JLabel("Y:"), BorderLayout.WEST);
        inputY = new JTextField();
        panelY.add(inputY, BorderLayout.CENTER);

        JPanel panelZ = new JPanel();
        panelZ.setLayout(new BorderLayout(5, 2));
        panelZ.add(new JLabel("Z:"), BorderLayout.WEST);
        inputZ = new JTextField();
        panelZ.add(inputZ, BorderLayout.CENTER);

        add(panelX);
        add(panelY);
        add(panelZ);

        Border line = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border titled = BorderFactory.createTitledBorder(
            line,
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            null,
            Color.DARK_GRAY
        );
        setBorder(titled);

        setVisible(true);
    }

    public void setInputX(double value) {
        this.inputX.setText(Double.toString(value));
    }

    public void setInputY(double value) {
        this.inputY.setText(Double.toString(value));
    }

    public void setInputZ(double value) {
        this.inputZ.setText(Double.toString(value));
    }

    public double getInputX() {
        String value = inputX.getText();
        if (inputX.getText().isEmpty()) {
            value = "0";
        }
        return Double.parseDouble(value);
    }

    public double getInputY() {
        String value = inputY.getText();
        if (inputY.getText().isEmpty()) {
            value = "0";
        }
        return Double.parseDouble(value);
    }

    public double getInputZ() {
        String value = inputZ.getText();
        if (inputZ.getText().isEmpty()) {
            value = "0";
        }
        return Double.parseDouble(value);
    }

    public void addKeyListenerInputX(KeyListener act) {
        inputX.addKeyListener(act);
    }

    public void addKeyListenerInputY(KeyListener act) {
        inputY.addKeyListener(act);
    }

    public void addKeyListenerInputZ(KeyListener act) {
        inputZ.addKeyListener(act);
    }

}