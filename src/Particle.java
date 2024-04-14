import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class Particle extends JComponent{
    private double x_coord;
    private double y_coord;
    private double velocity;
    private double angle;

    public Particle(double x, double y, double velocity, double angle){
        this.x_coord = x;
        this.y_coord = y;
        this.angle = angle;
        this.velocity = velocity;
        repaint();
    }

    public void updatePosition(double time, List<Wall> walls){
        double x2 = x_coord + getVelocityX() * time;
        double y2 = y_coord + getVelocityY() * time;
        if (x2 <= 0 || x2 >= 1280){
            this.angle = 180 - angle; 
        }
        if (y2 <= 0 || y2 >= 720){
            this.angle = - angle;
        }
        if (!walls.isEmpty()){
            for (Wall wall : walls){
                if (checkCollision(wall)){
                    double xNorm = Math.cos(wall.getAngle() + Math.PI / 2); 
                    double yNorm = Math.sin(wall.getAngle() + Math.PI / 2);
                
                    double dotPr = getVelocityX() * xNorm + getVelocityY() * yNorm;
                
                    double refX = getVelocityX() - 2 * dotPr * xNorm;
                    double refY = getVelocityY() - 2 * dotPr * yNorm;
                
                    this.angle = Math.toDegrees(Math.atan2(refY, refX));
                }
            }
        }
        x_coord += getVelocityX() * time;
        y_coord += getVelocityY() * time;
        repaint();
    }

    public boolean checkCollision(Wall wall){
        double wx1 = wall.getX1();
        double wy1 = wall.getY1();
        double wx2 = wall.getX2();
        double wy2 = wall.getY2();
        double x2 = x_coord + getVelocityX();
        double y2 = y_coord + getVelocityY();

        double det = ((x_coord - x2) * (wy1 - wy2)) - ((y_coord - y2) * (wx1 - wx2));

        double t = ((x_coord - wx1) * (wy1 - wy2) - (y_coord - wy1) * (wx1 - wx2)) / det;
        double u = -((x_coord - x2) * (y_coord - wy1) - (y_coord - y2) * (x_coord - wx1)) / det;
        
        return t >= 0 && t <= 1 && u >= 0 && u <= 1;
    }

    public double getXCoord(){
        return x_coord;
    }

    public double getYCoord() {
        return y_coord;
    }

    public double getAngle() {
        return angle;
    }

    public double getVelocity() {
        return velocity;
    }
    public double getVelocityX() {
        return velocity * Math.cos(Math.toRadians(angle));
    }
    public double getVelocityY() {
        return velocity * Math.sin(Math.toRadians(angle));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval((int) x_coord - 5, 720 - (int)y_coord - 5, 10, 10);
    }
}
