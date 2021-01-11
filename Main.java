import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static JFrame frame = new JFrame("Ear Clipping Demonstrator");
    public static GraphPanel gpanel;
    public static AlgebraPanel algebraPanel;

    enum PhaseType {DRAW, EARS, TRIANGLE, FINAL}
    public static PhaseType phase = PhaseType.DRAW;

    /**
     * Main method of the program
     * @param args
     */
    public static void main(String[] args)
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(0);
        frame.setLayout(borderLayout);
        frame.setSize(1200,600);
        gpanel = new GraphPanel();
        gpanel.setPreferredSize(new Dimension(700,400));
        frame.add(gpanel, BorderLayout.WEST);
        algebraPanel = new AlgebraPanel();
        algebraPanel.setPreferredSize(new Dimension(500,400));
        frame.add(algebraPanel, BorderLayout.LINE_END);
        InfoPanel infoPanel = new InfoPanel();
        infoPanel.setPreferredSize(new Dimension(1200,100));
        frame.add(infoPanel,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Activated when the CONTINUE button is pressed, advances to the next Stage.
     */
    public static void phaseAdd()
    {
        algebraPanel.repaint();
        if (phase != PhaseType.TRIANGLE||gpanel.faces.get(0).vertices.size()<=3)
        {
            phase = PhaseType.values()[(phase.ordinal()+1)%4];
            if (phase == PhaseType.TRIANGLE) {
                gpanel.faces.add(new Face(gpanel.polygon.vertices));
            }
        }
        switch (phase)
        {
            case DRAW:
                gpanel.faces = new ArrayList<>();
                gpanel.polygon = new Face(Vertex.intsToVertices(Vertex.verticesToInts(gpanel.vertices)));
                break;
            case TRIANGLE:
                if (gpanel.faces.get(0).vertices.size()>3)
                {
                    Face newFace = gpanel.faces.get(0).clipEar(gpanel.currentVertex);
                    if (newFace!= null) {
                        gpanel.faces.add(newFace);
                        gpanel.faces.get(0).checkEar(gpanel.currentVertex);
                        gpanel.faces.get(0).checkEar((gpanel.currentVertex-1)%gpanel.vertices.size());
                    }
                    else
                    {
                        gpanel.currentVertex = (gpanel.currentVertex+1)%gpanel.faces.get(0).vertices.size();
                    }
                }
                break;
            default:
        }
        gpanel.repaint();
    }

    /**
     * Activated when the CLEAR button is pressed, gets rid of the polygon and returns to DRAW Stage.
     */
    public static void phaseClear() {
        gpanel.polygon = new Face(new Vertex[]{});
        gpanel.vertices = new ArrayList<>();
        gpanel.faces = new ArrayList<>();
        gpanel.repaint();
        phase = PhaseType.DRAW;
        algebraPanel.repaint();
    }
}
