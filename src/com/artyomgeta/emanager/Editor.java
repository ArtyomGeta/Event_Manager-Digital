package com.artyomgeta.emanager;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
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
    private JList<String> list1;
    private JTree tree1;
    private JList list2;
    private JTree tree2;
    private JList list3;
    private JList list4;
    private JButton saveButton;
    private JButton button1;
    private JTextArea textArea2;
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

    DefaultListModel<String> scenarioModel = new DefaultListModel<>();

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

    @SuppressWarnings({"CodeBlock2Expr", "MagicConstant"})
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
        closeToolButton.setText(null);
        closeToolButton.setIcon(EventManager.resizeIcon(new ImageIcon("images/x2.png"), 20, 20));
        closeListButton.setIcon(EventManager.resizeIcon(new ImageIcon("images/x2.png"), 20, 20));
        addToListButton.setIcon(EventManager.resizeIcon(new ImageIcon("images/plus.png"), 25, 20));
        removeFromList.setIcon(EventManager.resizeIcon(new ImageIcon("images/minus.png"), 25, 20));

        closeToolButton.addActionListener(e -> {
            toolPanel.setVisible(false);
        });
        closeListButton.addActionListener(e -> {
            mainListPanel.setVisible(false);
        });
        //tree1.setLeadSelectionPath(new TreePath(Objects.requireNonNull(new File("Projects/" + projectName + "/Tabs/Audio").listFiles())));

        list1.setModel(scenarioModel);

        toolPanel.setVisible(false);
        addToListButton.addActionListener(e -> {
            toolPanel.setVisible(true);
            int tab = tabbedPane1.getSelectedIndex();
            if (tab == 0) {
                scenarioModel.addElement(("New element" + countElements(scenarioModel)).replace("t0", "t"));
                list1.setSelectedIndex(countElements(scenarioModel) - 1);
            }
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

        consolePanel.setVisible(false);

        removeFromList.addActionListener(e ->

        {
            int tab = tabbedPane1.getSelectedIndex();

            if (tab == 0 && !list1.isSelectionEmpty()) {
                removeListElement(list1.getSelectedValue().toString());
            } else if (tab == 1 && !list2.isSelectionEmpty()) {
                removeListElement(list2.getSelectedValue().toString());
            } else if (tab == 2 && !list3.isSelectionEmpty()) {
                removeListElement(list3.getSelectedValue().toString());
            } else if (tab == 3 && !list4.isSelectionEmpty()) {
                removeListElement(list4.getSelectedValue().toString());
            }
        });
        removeFromList.setEnabled(false);

        closeProjectFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        toolPanelViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
        listPanelViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
        consolePanelViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));

        closeProjectFileMenu.addActionListener(e -> {
            try {
                new Projects().run();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            dispose();
        });

        String[] presentationRoot = new String[]{"Presentation"};
        String[] audioRoot = new String[]{"Audio"};

        String[] data = new String[]{Arrays.toString(presentationRoot)};
    }

    private void removeListElement(String name) {

    }

    // Модель данных дерева
    /*class SimpleModel implements TreeModel
    {
        // Список потомков корневой записи
        private ArrayList<String> rootList = new ArrayList<String>();
        // Дочерние узлы первого уровня
        private ArrayList<String>[] tnodes;

        @SuppressWarnings("unchecked")
        public SimpleModel()
        {
            // Заполнение списков данными
            tnodes = (ArrayList<String>[]) new ArrayList<?>[tnodes.length];
            for (int i = 0; i < tnodes.length; i++) {
                rootList.add(nodes[i]);
                tnodes[i] = new ArrayList<String>();
                for (int j = 0; j < leafs[i].length; j++) {
                    tnodes[i].add(leafs[i][j]);
                }
            }
        }
        // Функция получения корневого узла дерева
        @Override
        public Object getRoot() {
            return root;
        }
        // Функция получения потомка корневого узла
        private final int getRootChild(Object node)
        {
            int idx = -1;
            for (int i = 0; i < rootList.size(); i++){
                if (rootList.get(i) == node) {
                    idx = i;
                    break;
                }
            }
            return idx;
        }
        // Функция получения количество потомков узла
        @Override
        public int getChildCount(Object node)
        {
            int idx = getRootChild(node);
            if ( node == root )
                return rootList.size();
            else if ( node == rootList.get(idx))
                return tnodes[idx].size();
            return 0;
        }
        // Функция получения потомка узла по порядковому номеру
        @Override
        public Object getChild(Object node, int index)
        {
            int idx = getRootChild(node);
            if ( node == root )
                return rootList.get(index);
            else if ( node == rootList.get(idx))
                return tnodes[idx].get(index);
            return null;
        }
        // Функция получения порядкового номера потомка
        @Override
        public int getIndexOfChild(Object node, Object child)
        {
            int idx = getRootChild(node);
            if ( node == root )
                return rootList.indexOf(child);
            else if ( node == rootList.get(idx))
                return tnodes[idx].indexOf(child);
            return 0;
        }
        // Функция определения, является ли узел листом
        @Override
        public boolean isLeaf(Object node)
        {
            int idx = getRootChild(node);
            if ((idx >= 0) && tnodes[idx].contains(node))
                return true;
            else
                return false;
        }
        // Функция вызывается при изменении значения некоторого узла
        @Override
        public void valueForPathChanged(TreePath path, Object value) {}
        // Метод присоединения слушателя
        @Override
        public void addTreeModelListener(TreeModelListener tml) {}
        // Методы удаления слушателя
        @Override
        public void removeTreeModelListener(TreeModelListener tml) {}
    }*/

    private int countElements(DefaultListModel defaultListModel) {
        return defaultListModel.getSize();
    }


    private boolean listElementSelected(JList list) {
        System.out.println("Works" + !list.isSelectionEmpty());
        return !list.isSelectionEmpty();
    }

}
