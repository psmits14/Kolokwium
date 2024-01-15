package p1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class DrawingPanel extends JPanel {

    private List<SquareWorker> squareWorkers;
    public List<SquareWorker> getSquareWorkers() {
        return squareWorkers;
    }
    public DrawingPanel() {
        squareWorkers = new ArrayList<>();
    }

    public void addSquare() {
        SquareWorker squareWorker = new SquareWorker(this);
        squareWorkers.add(squareWorker);
        squareWorker.execute();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (SquareWorker squareWorker : squareWorkers) {
            int x = squareWorker.getX();
            int y = squareWorker.getY();
            g.drawRect(x, y, SquareWorker.SQUARE_SIZE, SquareWorker.SQUARE_SIZE);
        }
    }

    public void moveSquaresLeft() {
        for (SquareWorker squareWorker : squareWorkers) {
            squareWorker.moveLeft();
        }
        repaint();
    }

    // Metoda do przesuwania wszystkich kwadratów w prawo
    public void moveSquaresRight() {
        for (SquareWorker squareWorker : squareWorkers) {
            squareWorker.moveRight();
        }
        repaint();
    }

}