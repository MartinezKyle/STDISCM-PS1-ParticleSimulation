import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class Wall extends JComponent{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double angle; //in radians already
    
    public Wall(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        this.angle = angle;
    }

    public double getX1() {
        return x1;
    }
    public double getY1() {
        return y1;
    }
    public double getX2() {
        return x2;
    }
    public double getY2() {
        return y2;
    }
    public double getAngle() {
        return angle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable antialiasing for smoother lines

        // Draw each wall with appropriate color and thickness
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2.0f)); // Set wall thickness
        //System.out.println("(" + wall.getStart().x + ", " + wall.getStart().y + "), (" + wall.getEnd().x + ", " + wall.getEnd().y + ")");
        g2d.drawLine((int) x1, (int) (720 - y1),(int) x2, (int) (720 - y2));
    }
}
