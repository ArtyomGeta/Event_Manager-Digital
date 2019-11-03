package com.artyomgeta.emanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class EventManager {
    String eManagerLabelText = "Event Manager";

    public static String[] returnProjects() throws NullPointerException {
        try {
            File[] files = new File("Projects/").listFiles();
            String[] names = new String[files.length];
            if (files.length == 0) {
                return new String[]{"No projects found"};
            }
            for (int i = 0; i < files.length; i++) {
                names[i] = files[i].getName();
            }
            return names;
        } catch (NullPointerException e) {
            new File("Projects/").mkdir();
            return new String[]{"No projects found"};
        }
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void createNewProject(String name, String date, String description) {
        new File("Projects/" + name.replace(" ", "_")).mkdirs();
        new File("Projects/" + name.replace(" ", "_") + "/Tabs").mkdirs();
        new File("Projects/" + name.replace(" ", "_") + "/Tabs/Scenario").mkdirs();
        new File("Projects/" + name.replace(" ", "_") + "/Tabs/Presentations").mkdirs();
        new File("Projects/" + name.replace(" ", "_") + "/Tabs/Audio").mkdirs();
        new File("Projects/" + name.replace(" ", "_") + "/Tabs/People").mkdirs();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("Projects/" + name.replace(" ", "_") + "/Information.json"));
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject.put("name", name);
            jsonObject1.put("date", date);
            jsonObject2.put("description", description);
            jsonArray.put(jsonObject);
            jsonArray.put(jsonObject1);
            jsonArray.put(jsonObject2);
            fileWriter.write(jsonArray.toString());
            fileWriter.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean findProject(String name) {
        if (new File("Projects/" + name).isDirectory()) return true;
        else return false;
    }

    public static void main(String[] args) throws InterruptedException {
        launchMessage();
    }
}
