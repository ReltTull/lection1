package ex5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class ChatWindow extends JFrame {
    private static final int CHATWINDOW_POSX = 800;
    private static final int CHATWINDOW_POSY = 300;
    private static final int CHATWINDOW_HEIGHT = 450;
    private static final int CHATWINDOW_WIDTH = 800;

    public ChatWindow() throws IOException {
        setTitle("Chat (ex1.5)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(CHATWINDOW_POSX, CHATWINDOW_POSY);
        setSize(CHATWINDOW_WIDTH, CHATWINDOW_HEIGHT);

        JButton buttonConnect = new JButton("Connect");
        JButton buttonExit = new JButton("Exit");

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel panBottom = new JPanel(new GridLayout(6, 1));
        JTextField fieldLogin = new JTextField("Login");
        JTextField fieldPassword = new JTextField("Password");
        JTextField fieldIP = new JTextField("IP");
        JTextField fieldPort = new JTextField("Port");
        panBottom.add(fieldLogin);
        panBottom.add(fieldPassword);
        panBottom.add(fieldIP);
        panBottom.add(fieldPort);

        panBottom.add(buttonConnect);
        panBottom.add(buttonExit);

        add(panBottom, BorderLayout.NORTH);

        JPanel panMid = new JPanel(new GridLayout(1, 2));
        JTextArea areaChat = new JTextArea();
        areaChat.setVisible(false);
        String[] users = {"Uno", "Dos", "Have to be Tres"};
        JList<String> listUsers = new JList<>(users);
        panMid.add(areaChat);
        panMid.add(areaChat);
        panMid.add(listUsers);
        add(panMid);

        JPanel panChat = new JPanel(new GridLayout(2, 1));
        JTextField fieldMessage = new JTextField("Enter your message");
        JButton buttonSendMessage = new JButton("Send");

        File fileLog = new File("");
        FileWriter fileWriter = new FileWriter(fileLog);

        buttonSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldMessage.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            String message = fieldMessage.getText();
                            areaChat.append(message.toString() + "\n"); // в зоне чата отображается сообщение
                            fieldMessage.setText(""); // очищаем область для ввода
                            try {
                                fileWriter.write(message + "\n");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
            }

        });

        // запись в areaChat данных из истории при нажатии buttonConnect
        FileReader fileReader = new FileReader(fileLog);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String log = bufferedReader.readLine();
                    while (log != null) {
                        areaChat.append(log);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        panChat.add(fieldMessage);
        panChat.add(buttonSendMessage);
        add(panChat, BorderLayout.SOUTH);

        setVisible(true);
    }
}
