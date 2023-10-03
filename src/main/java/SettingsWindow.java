import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;

    private static int mode = 0;

    public static int getSizeX() {
        return sizeX;
    }

    public static int getWinLength() {
        return winLength;
    }

    private static int sizeX = 3;

    private static int sizeY = sizeX;
    // выигрышная длина автоматически устанавливается по максимальному значению поля
        private static int winLength;


    JButton btnStart = new JButton("Start new game");

    public SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setLocation(gameWindow.getWindowPosx(), gameWindow.getWindowPosy());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setLayout(new GridLayout(10, 1));
        add(new JLabel("Choose game mode"));


        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton pvp = new JRadioButton("Person versus person");
        buttonGroup.add(pvp);
        JRadioButton pve = new JRadioButton("Person versus computer");
        buttonGroup.add(pve);
        add(pvp);
        add(pve);

        JLabel labelField = new JLabel("Choose field size");
        add(labelField);
        JLabel labelCurrentField = new JLabel("");
        add(labelCurrentField);
        // ползунок для выбора размера, отображает выбранное значение
        JSlider sliderSize = new JSlider(3, 10, 3);
        sliderSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelCurrentField.setText("Size: " + sliderSize.getValue());
                gameWindow.map.setwLen(sliderSize.getValue());
            }
        });
        add(sliderSize);

        JLabel labelWinLength = new JLabel("Choose win length");
        add(labelWinLength);
        JLabel labelCurrentLength = new JLabel("");
        add(labelCurrentLength);
        // ползунок для выбора размера, отображает выбранное значение
        JSlider sliderLength = new JSlider(3, 10, 3);
        sliderLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelCurrentField.setText("Win length: " + sliderLength.getValue());
            }
        });
        add(sliderLength);

        JLabel labelLength = new JLabel("Choose field size");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pve.isSelected()) {
                    mode = 1;
                }
                sizeX = sliderSize.getValue();
                sizeY = sizeX;  // для квадратного поля, стороны соответственно равны
                winLength = sliderLength.getValue();
                gameWindow.startNewGame(mode, sizeX, sizeY, winLength);
                System.out.printf("Mode: %d;\nSize: x=%d, Win Length: %d \n", mode, sizeX, winLength);
                setVisible(false); // при старте игры окно этого "меню" прячется
            }
        });
        add(btnStart);
        setVisible(true);
    }

}
