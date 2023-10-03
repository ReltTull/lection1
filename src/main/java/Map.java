import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    private int panelWidth;
    private int panelHeight;
    private int cellHeight = 200;
    private int cellWidth = 200;
    private int wLen;

    Map() {
        panelWidth = 3;
        panelHeight = 3;
        wLen = 3;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }


    private void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.printf("x=%d, y=%d\n", cellX, cellY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {

        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / SettingsWindow.getSizeX();
        cellWidth = panelWidth / SettingsWindow.getSizeX();

        g.setColor(Color.BLACK);
        for (int h = 0; h < SettingsWindow.getSizeX(); h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int w = 0; w < SettingsWindow.getSizeX(); w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        repaint();

    }




    // сеттер для автоматической установки выигрышной длины при выборе режимов
    public void setwLen(int len) {
        this.wLen = len;
    }
    void startNewGame(int mode, int fSzX, int fSzY, int wLength) {
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWin Length: %d \n", mode, fSzX, fSzY, wLen);
        repaint();
    }

}
