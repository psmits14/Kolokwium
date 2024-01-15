
package p1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame {
    DrawingPanel drawingPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow("Aplikacja");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public MainWindow() throws HeadlessException {
        this("untitled");
    }

    public MainWindow(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new DrawingPanel();
        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.addSquare();
            }
        });

        setLayout(new BorderLayout());
        add(drawingPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        drawingPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Obsługa naciśnięcia klawisza
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    drawingPanel.moveSquaresLeft();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    drawingPanel.moveSquaresRight();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Ustaw focus na panel, aby mógł reagować na zdarzenia klawiatury
        drawingPanel.setFocusable(true);
        drawingPanel.requestFocusInWindow();
    }
}


