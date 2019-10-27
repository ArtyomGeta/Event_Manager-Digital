package com.artyomgeta.emanager;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings({"unused", "rawtypes"})
public class Editor extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton closeConsoleButton;
    private JButton toolBarButton1;
    private JTextField textField1;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton toolBarButton2;
    private JButton toolBarButton3;
    private JButton toolBarButton4;
    private JButton toolBarButton5;
    private JButton toolBarButton6;
    private JTextField textField2;
    private JButton button10;
    private JComboBox comboBox1;
    private JButton toolBarButton7;
    private JPanel toolPanel;
    private JPanel mainListPanel;
    private JPanel consolePanel;
    private JButton addToListButton;
    private JButton removeFromList;
    private JButton closeToolButton;
    private JButton closeListButton;
    private JToolBar listToolBar;
    private JToolBar closeListToolBar;
    private JToolBar closeToolToolBar;
    private JToolBar toolBar;
    private JPanel scenarioPanel;
    private JPanel audioPanel;
    private JPanel presentationPanel;
    private JPanel peoplePanel;
    private JList list1;
    private JTree tree1;
    private JList list2;
    private JTree tree2;
    private JList list3;
    private JList list4;
    private JButton saveButton;
    private JToolBar openToolToolBar;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu projectMenu = new JMenu("Project");
    private JMenu viewMenu = new JMenu("View");
    private JMenuItem optionsFileMenu = new JMenuItem("Options");
    private JMenuItem exportFileMenu = new JMenuItem("Export");
    private JMenuItem closeProjectFileMenu = new JMenuItem("Close project");
    private JMenuItem exitFileMenu = new JMenuItem("Exit");
    private JMenuItem informationProjectMenu = new JMenuItem("Information about project");
    private JMenuItem listPanelViewMenu = new JMenuItem("List");
    private JMenuItem toolPanelViewMenu = new JMenuItem("Tools");
    private JMenuItem consolePanelViewMenu = new JMenuItem("Console");

    String scenarioText = "Scenario";
    String projectName;

    public Editor(String projectName) throws Exception {
        this.projectName = projectName;
        setDefaultUI();
        setDefault();
        setJMenuBar(menuBar);
    }

    public void run() {
        setTitle("Editor");
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(panel1);

        setMinimumSize(new Dimension(640, 480));
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public void setDefaultUI() {
        //tabs
        tabbedPane1.addChangeListener(e -> {
            int tab = tabbedPane1.getSelectedIndex();
            switch (tab) {
                case 0:
                    System.out.println(tab);
                    break;
                case 1:
                    System.out.println(tab);
                    break;
            }
            File[] files = new File("Projects/" + projectName + "/Tabs").listFiles();
            assert files != null;
            String[] names = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                names[i] = files[i].getName();
            }
            System.out.println(Arrays.toString(names));
        });
        toolPanel.setBorder(BorderFactory.createBevelBorder(1));
        mainListPanel.setBorder(BorderFactory.createBevelBorder(1));
        consolePanel.setBorder(BorderFactory.createBevelBorder(1));
    }

    @SuppressWarnings("CodeBlock2Expr")
    public void setDefault() {
        //toolPanel.setVisible(false);
        boolean toolsIsVisible = false;
        toolBar.setFloatable(false);
        closeListToolBar.setFloatable(false);
        listToolBar.setFloatable(false);
        closeToolToolBar.setFloatable(false);

        addToListButton.setText("");
        removeFromList.setText("");
        closeListButton.setText(null);
        closeConsoleButton.setText(null);
        closeToolButton.setText(null);
        closeToolButton.setIcon(EventManager.resizeIcon(new ImageIcon("images/x2.png"), 20, 20));
        closeConsoleButton.setIcon(EventManager.resizeIcon(new ImageIcon("images/x2.png"), 20, 20));
        closeListButton.setIcon(EventManager.resizeIcon(new ImageIcon("images/x2.png"), 20, 20));
        addToListButton.setIcon(EventManager.resizeIcon(new ImageIcon("images/plus.png"), 25, 20));
        removeFromList.setIcon(EventManager.resizeIcon(new ImageIcon("images/minus.png"), 25, 20));

        closeToolButton.addActionListener(e -> {
                toolPanel.setVisible(false);
        });
        closeListButton.addActionListener(e -> {
                mainListPanel.setVisible(false);
        });
        closeConsoleButton.addActionListener(e -> {
                consolePanel.setVisible(false);
        });
        tree1.setLeadSelectionPath(new TreePath(Objects.requireNonNull(new File("Projects/" + projectName + "/Tabs/Audio").listFiles())));

        toolPanel.setVisible(false);
        addToListButton.addActionListener(e -> {
            toolPanel.setVisible(true);
        });
        menuBar.add(fileMenu);
        menuBar.add(projectMenu);
        menuBar.add(viewMenu);

        fileMenu.add(optionsFileMenu);
        fileMenu.add(exportFileMenu);
        fileMenu.addSeparator();
        fileMenu.add(closeProjectFileMenu);
        fileMenu.add(exitFileMenu);

        projectMenu.add(informationProjectMenu);
        projectMenu.addSeparator();

        viewMenu.add(listPanelViewMenu);
        viewMenu.add(toolPanelViewMenu);
        viewMenu.add(consolePanelViewMenu);
        viewMenu.addSeparator();

        listPanelViewMenu.addActionListener(e -> mainListPanel.setVisible(true));
        toolPanelViewMenu.addActionListener(e -> toolPanel.setVisible(true));
        consolePanelViewMenu.addActionListener(e -> consolePanel.setVisible(true));

        String[] presentationRoot = new String[] { "Presentation" };
        String[] audioRoot = new String[] { "Audio" };


    }

    // Модель данных дерева
    class SimpleModel implements TreeModel {
        // Список потомков корневой записи
        private ArrayList<String> rootList = new ArrayList<String>();
        // Дочерние узлы первого уровня
        private ArrayList<String>[] tnodes;

        @SuppressWarnings("unchecked")
        public SimpleModel(String[] type) {
            // Заполнение списков данными
            tnodes = (ArrayList<String>[]) new ArrayList<?>[type.length];
            for (int i = 0; i < type.length; i++) {
                rootList.add(type[i]);
                tnodes[i] = new ArrayList<String>();
                /*for (int j = 0; j < leafs[i].length; j++) {
                    tnodes[i].add(leafs[i][j]);
                }*/
            }
        }

        @Override
        public Object getRoot() {
            return null;
        }

        @Override
        public Object getChild(Object parent, int index) {
            return null;
        }

        @Override
        public int getChildCount(Object parent) {
            return 0;
        }

        @Override
        public boolean isLeaf(Object node) {
            return false;
        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {

        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            return 0;
        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {

        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {

        }
    }
}
