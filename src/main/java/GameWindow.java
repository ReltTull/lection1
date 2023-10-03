import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;

    // использую геттеры, чтобы установить локацию settingsWindow
    public static int getWindowPosx() {
        return WINDOW_POSX;
    }
    public static int getWindowPosy() {
        return WINDOW_POSY;
    }

    Map map;
    SettingsWindow settingsWindow;



    JButton btnStart = new JButton("New Game");
    JButton btnExit = new JButton("Exit");

    public GameWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("KnockKnock");
        setResizable(false);


        settingsWindow = new SettingsWindow(this);
        map = new Map();

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        });



        JPanel panBottom = new JPanel(new GridLayout(1,2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int winLength) {
        map = new Map();
        map.startNewGame(mode, fSzX, fSzY, winLength);
    }
}
