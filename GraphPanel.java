import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Shows the left side of the screen with the polygon
 */
public class GraphPanel extends JPanel implements MouseListener, KeyListener {
    Face polygon;
    ArrayList<Vertex> vertices;
    static int pointerX,pointerY;
    ArrayList<Face> faces;
    int currentVertex;
    public GraphPanel()
    {
        setBackground(Color.white);
        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);
        polygon = new Face(new Vertex[]{});
        vertices = new ArrayList<>();
        repaint();
        pointerX = pointerY = -10;
        faces = new ArrayList<>();
        currentVertex = 0;
    }
    /**
     * Paints the left side of the screen
     * @param g Graphics object used by JPanel
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        polygon.paintFill(g);
        switch (Main.phase)
        {
            case EARS:
                polygon.initializeEars();
                int i = 1;
                g.setColor(Color.BLACK);
                for (Vertex vertex : vertices)
                {
                    g.drawString(""+i++,(int)vertex.x+10,(int)vertex.y+10);
                }
            case DRAW:
                polygon.paint(g);
                currentVertex = 0;
                break;
            case FINAL:
                for (Face face: faces)
                {
                    face.paintFill(g,new Color(250,200,100));
                }
            case TRIANGLE:
                for (Face face : faces)
                    face.paint(g);
                g.setColor(new Color(100,200,100));
                Vertex focus = faces.get(0).vertices.get((currentVertex+faces.get(0).vertices.size())%faces.get(0).vertices.size());
                g.fillOval((int)focus.x-6,(int)focus.y-6,12,12);
                int j = 1;
                g.setColor(Color.BLACK);
                for (Vertex vertex : vertices)
                {
                    g.drawString(""+j++,(int)vertex.x+10,(int)vertex.y+10);
                }
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Determines what to do when a key is pressed
     * @param e KeyEvent containing info on which key was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * Determines what to do when mouse is pressed
     * @param e KeyEvent containing info on which mouse button was pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (Main.phase == Main.PhaseType.DRAW) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                vertices.add(new Vertex(e.getX(), e.getY()));
                polygon = new Face(vertices);
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                Vertex closest = vertices.get(0);
                double dist = Math.hypot(closest.x - e.getX(), closest.y - e.getY());
                for (int i = 1; i < vertices.size(); i++) {
                    Vertex vertex = vertices.get(i);
                    if (Math.hypot(vertex.x - e.getX(), vertex.y - e.getY()) < dist) {
                        dist = Math.hypot(vertex.x - e.getX(), vertex.y - e.getY());
                        closest = vertex;
                    }
                }
                vertices.remove(closest);
                polygon = new Face(vertices);
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}