package p1;

import javax.swing.*;
import java.util.List;

class SquareWorker extends SwingWorker<Void, SquareData> {

    public static final int SQUARE_SIZE = 30;
    private static final int FALLING_SPEED = 5;
    private static final int MOVE_SPEED = 5;

    private DrawingPanel drawingPanel;
    private int x;
    private int y;

    public SquareWorker(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        this.x = (int) (Math.random() * (drawingPanel.getWidth() - SQUARE_SIZE));
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (y + SQUARE_SIZE < drawingPanel.getHeight()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Sprawdź kolizję z innymi kwadratami
            if (checkCollision()) {
                return null;
            }

            y += FALLING_SPEED;
            publish(new SquareData(x, y));
        }

        y = drawingPanel.getHeight() - SQUARE_SIZE;
        publish(new SquareData(x, y));
        return null;
    }

    // Sprawdź kolizję z innymi kwadratami
    private boolean checkCollision() {
        for (SquareWorker otherSquare : drawingPanel.getSquareWorkers()) {
            if (otherSquare != this) {
                // Sprawdź czy kwadrat jest nad innym kwadratem
                if (x < otherSquare.getX() + SQUARE_SIZE &&
                        x + SQUARE_SIZE > otherSquare.getX() &&
                        y - FALLING_SPEED < otherSquare.getY() + SQUARE_SIZE &&
                        y + SQUARE_SIZE > otherSquare.getY()) {
                    return true; // Koliduje z innym kwadratem
                }
            }
        }
        return false; // Brak kolizji
    }

    public void moveLeft() {
        x -= MOVE_SPEED;
        if (x < 0) {
            x = 0;
        }
        publish(new SquareData(x, y));
        drawingPanel.repaint();
    }

    // wydaje mi się, że przesuwanie nie działa, ponieważ należałoby dodać metodę wait,
    // która czekałaby na przesunięcie i dopiero kontynuwała przesuwanie w dół
    public void moveRight() {
        x += MOVE_SPEED;
        if (x + SQUARE_SIZE > drawingPanel.getWidth()) {
            x = drawingPanel.getWidth() - SQUARE_SIZE;
        }
        publish(new SquareData(x, y));
        drawingPanel.repaint();
    }

    @Override
    protected void process(List<SquareData> chunks) {
        for (SquareData squareData : chunks) {
            drawingPanel.repaint();
        }
    }
}
