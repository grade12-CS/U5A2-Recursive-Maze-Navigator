/**
 * Node to create connection between points
 */
public class Node implements Comparable<Node>{
    public final Point point; //point of a node
    public double f = -1; //f cost = g + h
    public double g = -1; //g (tentative) cost: distance from this node to a starting node
    public double h = -1; //h (heruistic) cost: distance from this node to goal node
    public Node parent; //adjacent node to connect

    /**
     * initialize with a zero point
     */
    public Node() {
        point = new Point();
    }

    /**
     * initialize with a defined value of point
     * @param x x-coordinate of the point
     * @param y y-coordinate of the point
     */
    public Node(int x, int y) {
        point = new Point(x, y);
    }

    /**
     * initialize with a defined point 
     * @param point point of this node will store
     */
    public Node(Point point) {
        this.point = point;
    }

    /**
     * initialize a node with a defined point and f, g, h values for a* search
     * @param point point of this node
     * @param f summative cost of f (normally f = g + h)
     * @param g tentative cost g
     * @param h heruistic cost h
     */
    public Node(Point point, double f, double g, double h) {
        this.point = point;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    /**
     * connects current node to other node
     * @param node parent node
     */
    public void setParent(Node node) {
        parent = node;
    }


    /**
     * checks if current node is equal to other node 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        Node other = (Node) obj;
        return this.point == other.point && this.f == other.f && this.g == other.g && this.h == other.h;
    }

    /**
     * it is essential to override hashCode() to use Node as a key in java sets
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + point.hashCode();
        return result;
    }

    /**
     * tells to sort Nodes with their f values (because we want to poll the smallest f value of Node when searching a path)
     */
    @Override
    public int compareTo(Node other) {
        return Double.compare(this.f, other.f);
    }
}
