
package p1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    GamingPanel gamingPanel;
    JSlider sliderBoku;
    JButton addButton;

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
        setSize(800, 800);
        JPanel contentPane = new JPanel(); // Ustawienie layoutu na BorderLayout
        contentPane.setLayout(null);
        gamingPanel = new GamingPanel();
        contentPane.add(gamingPanel, BorderLayout.CENTER); // Dodanie panelu do środka
        add(contentPane);
        initComponents();
    }

    public void initComponents() {
        JPanel buttonPanel = new JPanel(new FlowLayout());


        addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamingPanel.addSquare();
            }
        });

        buttonPanel.add(addButton);
        add(buttonPanel,  BorderLayout.NORTH);
    }
}
