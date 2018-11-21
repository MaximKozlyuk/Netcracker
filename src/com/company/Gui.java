package com.company;

import com.company.buildings.Building;
import com.company.buildings.Buildings;
import com.company.buildings.Floor;
import com.company.buildings.Space;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

public class Gui extends JFrame {

    /**
     * FlowLayout - используется для последовательного отображения элементов.
     * Если элемент не помещается в конкретную строку, он отображается в следующей.
     *
     * GridLayout - отображения элементов в виде таблицы с одинаковыми размерами ячеек.
     *
     * BorderLayout - используется при отображении не более 5 элементов.
     * Эти элементы располагаются по краям фрейма и в ценрте: North, South, East, West, Center.
     *
     * BoxLayout - отображает элементы в виде рядка или колонки.
     *
     * GridBagLayout - позволяет назначать месторасположение и размер каждого виджета.
     * Это самый сложный, но и самый эффективный вид отображения.
     *
     */

//    private JLabel label = new JLabel("text");
//    private JButton button1 = new JButton("start");
//    private JRadioButton radio1 = new JRadioButton("Select this");
//    private JRadioButton radio2 = new JRadioButton("Select that");

    private JMenu fileMenu = new JMenu("File");
    private JMenuBar menuBar = new JMenuBar();
    private JPanel container = new JPanel();
    private JLabel appHelloLabel;

    public Gui() throws HeadlessException {
        super("Buildings app");
        setBounds(new Rectangle(1,1, 250,250));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appHelloLabel = new JLabel("<html>Buildings browser v 1.0<br/>Choose file</html>");
        appHelloLabel.setVerticalAlignment(SwingConstants.CENTER);
        appHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(appHelloLabel);
        this.add(container);

        //menu settings
        JMenuItem chooseFileItem = new JMenuItem("Choose file");
        chooseFileItem.addActionListener((event)->{
            try {
                Building currentBuilding = openFileAndRead();
                showBuildingUI(currentBuilding);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Error file!");
            }
        });
        fileMenu.add(chooseFileItem);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

    }

    private Building openFileAndRead() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        if (fileChooser.getSelectedFile() == null) { return null; }
        return Buildings.readBuilding(new FileReader(fileChooser.getSelectedFile()));
    }

    private void showBuildingUI (Building building) {
        container.removeAll();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        JPanel floor, buildingInfo, floorInfo, infoContainer;
        JLabel floorInfoLabel;
        JButton button;
        infoContainer = new JPanel();
        infoContainer.setLayout(new BoxLayout(infoContainer,BoxLayout.X_AXIS));
        infoContainer.setAlignmentX(1);

        buildingInfo = new JPanel();
        JLabel buildingInfoLabel = new JLabel(getBuildingInfo(building));
        buildingInfo.add(buildingInfoLabel);
        infoContainer.add(buildingInfo);

        floorInfo = new JPanel();
        floorInfoLabel = new JLabel("-");
        floorInfo.add(floorInfoLabel);
        // todo add action of floor on click
        infoContainer.add(floorInfo);

        JLabel spaceInfo = new JLabel("default text");
        infoContainer.add(spaceInfo);

        Floor currentFloor;
        for (int i = 0; i < building.floorsAmount(); i++) {
            floor = new JPanel();
            floor.setBorder(new LineBorder(Color.BLACK,1));
            currentFloor = building.getFloor(i);
            for (Space space : currentFloor) {
                button = new JButton(space.toString());
                button.addActionListener((event)-> spaceInfo.setText(space.toString()));
                floor.add(button);
            }
            container.add(floor, i);
        }
        container.add(infoContainer);
        this.validate();
        this.doLayout();
    }

    private String getBuildingInfo(Building building) {
        StringBuilder str = new StringBuilder();
        String[] splitName = building.getClass().getName().split("\\.");
        str.append("<html>").append(splitName[splitName.length-1]).append("<br/>Floor amount: ").append(building.floorsAmount()).
                append("<br/>Spaces amount: ").append(building.spacesAmount()).append("</html>");
        return str.toString();
    }

    private String getFloorInfo (Floor floor) {
        StringBuilder str = new StringBuilder("<html>");
        String[] splitName = floor.getClass().getName().split("\\.");
        str.append(splitName[splitName.length-1]).append("<br/>Space amount: ").append(floor.amount()).append("</html>");
        return str.toString();
    }

    public static void main (String[] args) {
        if (System.getProperty("os.name").equals("Mac OS X")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        Gui app = new Gui();
        app.setVisible(true);
    }

}
