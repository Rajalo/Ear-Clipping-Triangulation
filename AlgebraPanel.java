import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Shows information about the process of the algorithm
 */
public class AlgebraPanel extends JPanel{
    public AlgebraPanel()
    {
        setBackground(new Color(250,210,250));
    }

    /**
     * Paints the info about the next clip on the right side of the screen
     * @param g Graphics object used by JPanel
     */
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        if (Main.phase == Main.PhaseType.FINAL)
        {
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.8F);
            g.setFont(newFont);
            g.drawString("Now we have a triangulation!",20,40);
            g.setFont(currentFont);
        }
        if (Main.phase == Main.PhaseType.TRIANGLE)
        {
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.8F);
            g.setFont(newFont);
            Face leftover = Main.gpanel.faces.get(0);
            ArrayList<Vertex> vertices = Main.gpanel.vertices;
            Vertex v0 = leftover.vertices.get((Main.gpanel.currentVertex+leftover.vertices.size())%leftover.vertices.size());
            String str = "Vertex " + (vertices.indexOf(v0)+1);
            if (v0.ear)
            {
                str+= " is an ear vertex, so we will clip it.";
                Vertex next = leftover.vertices.get((Main.gpanel.currentVertex+1+leftover.vertices.size())%leftover.vertices.size());
                Vertex prev = leftover.vertices.get((Main.gpanel.currentVertex+-1+leftover.vertices.size())%leftover.vertices.size());
                g.drawString("Update: Vertex " + (vertices.indexOf(prev)+1) +((prev.ear)?" is an ear":" is not an ear"),20,80 );
                g.drawString("Update: Vertex " + (vertices.indexOf(next)+1) +((next.ear)?" is an ear":" is not an ear"),20,120 );
            }
            else
            {
                str += " is not an ear vertex, so we will continue.";
            }
            g.drawString(str, 20,40);
            g.setFont(currentFont);
        }
    }
}
