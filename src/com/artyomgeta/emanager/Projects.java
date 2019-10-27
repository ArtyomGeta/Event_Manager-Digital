package com.artyomgeta.emanager;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Projects extends JFrame {
    private JPanel panel1;
    private JButton createNewProjectButton;
    private JList<String> list1;
    private JButton button2;
    private JButton importButton;
    private JTextArea textArea1;
    private JButton button3;
    private JButton button4;
    private JPanel toolPanel;
    private JPanel workPanel;
    private JPanel buttonsPanel;
    private JTextField nameField;
    private JTextField textField2;
    private JPanel inputPanel;
    private JLabel eManagerLabel;
    private JButton openButton;

    public Projects() throws Exception {
        setDefault();
    }

    public void run() {
        setTitle("Projects");
        setBounds(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 320, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 240, 640, 480));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(panel1);
        list1.setBorder(BorderFactory.createBevelBorder(1));
        toolPanel.setBorder(BorderFactory.createBevelBorder(1));
        workPanel.setBorder(BorderFactory.createBevelBorder(1));
        buttonsPanel.setBorder(BorderFactory.createBevelBorder(1));
    }
    private void setDefault() {
        AtomicBoolean inputPanelIsVisible = new AtomicBoolean(false);
        inputPanel.setVisible(false);
        inputPanel.setBorder(BorderFactory.createBevelBorder(1));
        createNewProjectButton.addActionListener(e -> {
            if(!inputPanelIsVisible.get()) {
                inputPanel.setVisible(true);
                inputPanelIsVisible.set(true);
            } else {
                inputPanel.setVisible(false);
                inputPanelIsVisible.set(false);
            }
        });
        importButton.addActionListener(e -> {
            try {
                new Editor("Test").run();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occupied!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        eManagerLabel.setText("<html><b>Event</b> Manager</html>");

        list1.addListSelectionListener(e -> {
            inputPanel.setVisible(true);
            nameField.setText(list1.getSelectedValue());
            nameField.setEditable(false);
        });
        openButton.addActionListener(e -> {
            try {
                new Editor(nameField.getText()).run();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occupied!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        list1.setListData(EventManager.returnProjects());
    }

}
