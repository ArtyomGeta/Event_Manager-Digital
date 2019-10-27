package com.artyomgeta.emanager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EventManager {
    String eManagerLabelText = "Event Manager";

    public static String[] returnProjects() {
        File[] files = new File("Projects/").listFiles();
        String[] names = {"No projects found"};
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        return names;
    }

    public static void launchMessage() throws InterruptedException {
        JDialog dialog = new JDialog();
        JLabel label = new JLabel("Event Manager");
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setUndecorated(true);
        dialog.setBounds(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 320, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 240, 640, 480));
        dialog.setVisible(true);
        dialog.setLayout(new BorderLayout());
        dialog.add(label);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        dialog.setAlwaysOnTop(true);
        label.setLabelFor(dialog);
        label.setHorizontalAlignment(JLabel.CENTER);
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("Configuration/Run.txt"));
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//            while (line != null) {
//                sb.append(line);
//                line = br.readLine();
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Thread.sleep(0);
        try {
            new Projects().run();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occupied!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Thread.sleep(0);
        dialog.dispose();
    }

    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static void main(String[] args) throws InterruptedException {
        launchMessage();
    }
}
