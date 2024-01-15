package p1;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamingPanel extends JPanel {
    private JSlider sliderBoku;

    public GamingPanel() {
        setBorder(new LineBorder(new Color(157, 146, 97), 4, true));
        setBackground(Color.WHITE);
        setBounds(50, 50, 600, 700);
    }

    public void setSliderReference(JSlider sliderBoku) {
        this.sliderBoku = sliderBoku;
    }

    void addSquare() {
        // Tworzenie wątku dla nowego kwadratu
        Thread squareThread = new Thread(new Runnable() {
            @Override
            public void run() {
                moveSquare();
            }
        });

        squareThread.start();
    }

    private void moveSquare() {
        Graphics g = this.getGraphics();
        int x = (int) (Math.random() * (this.getWidth() - 30));
        int y = 0;
        int size = 30;

        // Rysowanie kwadratu na górze panelu
        g.setColor(Color.blue);
        g.fillRect(x, y, size, size);

        // Poruszanie kwadratu w dół
        while (y < this.getHeight() - size) {
            try {
                Thread.sleep(50); // Kontrola prędkości poruszania się kwadratu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            g.setColor(Color.blue);
            g.fillRect(x, y, size, size);



            y += 5; // Zmiana pozycji w dół

            g.setColor(Color.blue);
            g.fillRect(x, y, size, size);
        }
    }
}
