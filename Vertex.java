import java.util.ArrayList;

/**
 * Stores info about a vertex of the polygon
 */
public class Vertex {
    double x,y;
    double[] coordsArr;
    boolean ear;
    public Vertex(double x, double y)
    {
        this.x = x;
        this.y = y;
        ear = false;
    }

    /**
     * Returns a string representation of the Vertex
     * @return string representation of the Vertex
     */
    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    /**
     * Converts a list of vertices to array of integers conducive towards painting the Polygon
     * @param vertices vertices being converted
     * @return array of integer coordinates
     */
    public static int[][] verticesToInts(ArrayList<Vertex> vertices)
    {
        int[] xs = new int[vertices.size()];
        int[] ys = new int[vertices.size()];
        for (int i = 0; i < vertices.size();i++) {
            xs[i] = (int) (vertices.get(i).x);
            ys[i] = (int) (vertices.get(i).y);
        }
        return new int[][]{xs,ys};
    }
    /**
     * Converts a array of integers to list of vertices conducive towards painting the Polygon
     * @param array integer coordinates being converted
     * @return list of vertices from coordinates
     */
    public static ArrayList<Vertex> intsToVertices(int[][] array)
    {
        ArrayList<Vertex> lst = new ArrayList<>();
        if (array.length!=2||array[0].length!=array[1].length)
        {
            return lst;
        }
        for (int i = 0; i < array[0].length;i++)
        {
            lst.add(new Vertex(array[0][i],array[1][i]));
        }
        return lst;
    }

    /**
     * Returns coordinates of this vertex
     * @return coordinates of this vertex
     */
    public double[] getCoordsArr()
    {
        if (coordsArr==null)
            return coordsArr = new double[]{x,y};
        return coordsArr;
    }
}
