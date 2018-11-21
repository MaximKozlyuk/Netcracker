package com.company;

import com.company.buildings.Building;
import com.company.buildings.Buildings;
import com.company.buildings.Floor;
import com.company.buildings.Space;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Gui extends JFrame {

    private JMenu fileMenu = new JMenu("File"), lookFeelMenu = new JMenu("Look&Feel");
    private JMenuBar menuBar = new JMenuBar();
    private JPanel container = new JPanel();
    private JLabel appHelloLabel, floorInfoLabel, spaceInfo;

    public Gui() throws HeadlessException {
        super("Buildings app");
        setBounds(new Rectangle(1,1, 250,250));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(250,250));

        appHelloLabel = new JLabel("<html>Buildings browser v 1.0<br/>Choose file</html>");
        appHelloLabel.setVerticalAlignment(SwingConstants.CENTER);
        appHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(appHelloLabel);
        container.setMinimumSize(new Dimension(250,250));
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(container);

        //menu settings
        JMenuItem chooseFileItem = new JMenuItem("Choose file");
        chooseFileItem.addActionListener((event) -> {
            try {
                Building currentBuilding = openFileAndRead();
                showBuildingUI(currentBuilding);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Error file!");
            }
        });

        fileMenu.add(chooseFileItem);
        menuBar.add(fileMenu);

        if (System.getProperty("os.name").equals("Mac OS X")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            addLookAndFeelInMenu(JCheckBoxMenuItem.class);
        } else {
            addLookAndFeelInMenu(JRadioButton.class);
        }
        menuBar.add(lookFeelMenu);

        this.setJMenuBar(menuBar);
        this.validate();
        this.doLayout();
        this.setVisible(true);
    }

    private void addLookAndFeelInMenu (Class<? extends AbstractButton> type) {
        try {
            UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
            ButtonGroup buttonGroup = new ButtonGroup();
            AbstractButton styleSelector;
            for (int i = 0; i < lafInfo.length; i++) {
                Constructor<? extends AbstractButton> ctor = type.getConstructor(String.class);
                styleSelector = ctor.newInstance(lafInfo[i].getName());
                final String s = lafInfo[i].getClassName();
                styleSelector.addActionListener((e) -> changeLookFeel(s));
                buttonGroup.add(styleSelector);
                lookFeelMenu.add(styleSelector);
            }
        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void changeLookFeel(String s){
        try {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this.getContentPane());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    private Building openFileAndRead() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        if (fileChooser.getSelectedFile() == null) { return null; }
        return Buildings.readBuilding(new FileReader(fileChooser.getSelectedFile()));
    }

    private void showBuildingUI (Building building) {
        container.removeAll();
        JPanel floor, buildingInfo,floorInfo, infoContainer;
        JButton button;
        infoContainer = new JPanel();
        infoContainer.setLayout(new BoxLayout(infoContainer,BoxLayout.X_AXIS));
        infoContainer.setBorder(new EmptyBorder(5,10,5,10));

        buildingInfo = new JPanel();
        JLabel buildingInfoLabel = new JLabel(getBuildingInfo(building));
        buildingInfo.add(buildingInfoLabel);
        infoContainer.add(buildingInfo);

        floorInfo = new JPanel();
        floorInfoLabel = new JLabel("");
        floorInfo.add(floorInfoLabel);
        infoContainer.add(floorInfo);

        spaceInfo = new JLabel("");
        infoContainer.add(spaceInfo);

        Floor currentFloor;
        for (int i = 0; i < building.floorsAmount(); i++) {
            floor = new JPanel();
            floor.setMinimumSize(new Dimension(50,10));
            floor.setBorder(new LineBorder(Color.BLACK,1));
            currentFloor = building.getFloor(i);
            floor.addMouseListener(getFloorML(currentFloor));
            for (Space space : currentFloor) {
                button = new JButton(space.toString());
                //button.setBorder(new LineBorder(Color.BLACK,1,true));
                button.addMouseListener(getFloorML(currentFloor));
                button.addMouseListener(getSpaceButtonML(space));
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
                append("<br/>Spaces amount: ").append(building.spacesAmount()).append("<br/>")
                .append("Total area: ").append(building.totalArea()).append("</html>");
        return str.toString();
    }

    private String getFloorInfo (Floor floor) {
        StringBuilder str = new StringBuilder("<html>");
        String[] splitName = floor.getClass().getName().split("\\.");
        str.append(splitName[splitName.length-1]).append("<br/>Space amount: ").append(floor.amount())
                .append("<br/>Floor area: ").append(floor.totalArea()).append("</html>");
        return str.toString();
    }

    private MouseListener getFloorML(Floor floor) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { floorInfoLabel.setText(getFloorInfo(floor)); }

            @Override
            public void mouseExited(MouseEvent e) { floorInfoLabel.setText(""); }
        };
    }

    private MouseListener getSpaceButtonML (Space space) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { spaceInfo.setText(space.toString()); }

            @Override
            public void mouseExited(MouseEvent e) { spaceInfo.setText(""); }
        };
    }

    public static void main (String[] args) {
        Gui app = new Gui();
    }

}
