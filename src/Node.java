/**
 * Node to create connection between points
 */
public class Node implements Comparable<Node>{
    public final Point point;
    public double f = -1; //f cost = g + h
    public double g = -1; //g (tentative) cost: distance from this node to a starting node
    public double h = -1; //h (heruistic) cost: distance from this node to goal node
    public Node parent; //adjacent node to connect

    public Node() {
        point = new Point();
    }

    public Node(int x, int y) {
        point = new Point(x, y);
    }

    public Node(Point point) {
        this.point = point;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        Node other = (Node) obj;
        return this.point == other.point && this.f == other.f && this.g == other.g && this.h == other.h;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + point.hashCode();
        return result;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.f, other.f);
    }
}
