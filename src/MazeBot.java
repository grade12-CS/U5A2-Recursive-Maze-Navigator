import becker.robots.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MazeBot extends DaveSoftware{
    private Thing treasure;
    private Point thingPoint;
    private final Point home;
    public boolean shouldGoHome = false;

    private final int streets, avenues;
    
    public MazeBot(City city, int y, int x, int streets, int avenues) {
        super(city, y, x);
        this.streets = streets;
        this.avenues = avenues;
        home = new Point(x, y);
        thingPoint = Point.createRandomPoint(0, 10, 0, 10);
        treasure = new Thing(city, thingPoint.y, thingPoint.x);
    }

    /**
     * directions to place adjacent nodes
     */
    HashMap<Point, Direction> directions = new HashMap<>(){{
        put(new Point(0, 1), Direction.SOUTH);
        put(new Point(0, -1), Direction.NORTH);
        put(new Point(-1, 0), Direction.WEST);
        put(new Point(1, 0), Direction.EAST);
    }};

    public void infiniteSearching() {
        if (shouldGoHome) {
            goHome();
            treasure = null;
            return;
        }
        if (treasure == null) {
            return;
        }
        Stack<Point> path = new Stack<>();
        PriorityQueue<Node>toVisit = new PriorityQueue<>();
        HashSet<Point> visited = new HashSet<>();
        Stack<Point> pathToTreasure = findShortestPath(true, getCurrent(), thingPoint, path, toVisit, visited); 
        solve(pathToTreasure);
        createThingRandomly();
        invisiblizeDummyBots();
        infiniteSearching();
    }

    public void invisiblizeDummyBots() {
        if (dummyBag.isEmpty()) {
            return;
        }
        var bot = dummyBag.getLast();
        bot.setTransparency(1);
        dummyBag.removeLast();
        invisiblizeDummyBots();
    }

    ArrayList<RobotSE> dummyBag = new ArrayList<>();
    /**
     * find shortest path using fancy A* algorithm with recursion
     */
    public Stack<Point> findShortestPath(boolean isFirst, Point startPoint, Point goalPoint, Stack<Point> path, PriorityQueue<Node>toVisit, HashSet<Point> visited) {
        if (isFirst) {
            double start_g = 0;
            double start_h = startPoint.distanceTo(goalPoint);
            double start_f = start_g + start_h;
            Node startNode = new Node(startPoint, start_f, start_g, start_h);
            startNode.parent = null;
    
            toVisit.offer(startNode);
        }

        if (!isFirst && toVisit.isEmpty()) {
            //no path found. returns an empty stack
            return path;
        }

        Node lastNode = null;
        //get the node with smallest F value
        Node minNode = toVisit.poll();
        lastNode = minNode;
        visited.add(minNode.point);
        
        //if the adjacent point == thingPoint, stop searching and solve the maze
        if (minNode.point.equals(goalPoint)) {
            path = createPath(minNode);
            path.addFirst(minNode.point);
            return path;
        }
        
        //check for 4 adjacent points in 4 directions 
        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                Direction dir = directions.get(new Point(dx, dy)); //get directions to check walls in 4 directions
                if (dir == null) continue; // we don't check adjacent points diagonally or current point 
                
                //adjacent point of minNode
                Point adjacentPoint = new Point(minNode.point.x + dx, minNode.point.y + dy);
                if (!valid(adjacentPoint) || visited.contains(adjacentPoint)) continue; //checks if the point is within the maze
                
                //creates dummy bot to check for wall in front of it
                RobotSE dummy = new RobotSE(getCity(), minNode.point.y, minNode.point.x, dir);
                dummy.setTransparency(0.8);
                dummy.setColor(Color.blue);
                dummyBag.add(dummy);
                if (!dummy.frontIsClear()) continue; //if there is wall in front, don't check that adjacent point in that direction
                
                //tentative G = minNode.g + distance between minNode and adjacent Node which is always 1
                double G = minNode.g + 1;
                double H = adjacentPoint.distanceTo(goalPoint);
                double F = G + H;
                
                //get or create node instance for the adjacent point
                Node adjacentNode = new Node(adjacentPoint);
                if (!toVisit.contains(adjacentNode)) {
                    toVisit.offer(adjacentNode);
                }
                
                if (adjacentNode.f >= F) continue; //it's not the shortest path, so skip
                
                //This node is closer to thingPoint. So we connect this to current node
                adjacentNode.setParent(minNode); 
                adjacentNode.g = G;
                adjacentNode.h = H; 
                adjacentNode.h = F;
            }
        } 
        
        return findShortestPath(false, lastNode.point, goalPoint, path, toVisit, visited);
    }
    
    /**
     * checks if a point is within a maze
     * @param p point to check
     * @return result of point in a maze or not
     */
    private boolean valid(Point p) {
        return p.x >= 0 && p.x < avenues && p.y >= 0 && p.y < streets;
    }

    /**
     * generate a path by iterating through node parents
     * @param node last node the path ends (usually thingPoint)
     * @return path
     */
    public Stack<Point> createPath(Node node) {
        Stack<Point> path = new Stack<>();
        while (node.parent != null) {
            path.add(node.parent.point);
            node = node.parent;
        }
        return path;
    }

    /**
     * go pick the thing using a recursion 
     */
    public void solve(Stack<Point> path) {
        if (path.isEmpty()) {
            pickAllThings();
            System.out.println("found the treasure!");
            return;
        } 
        Point p = path.getLast();
        path.pop();
        goTo(p);
        solve(path);
    }

    /**
     * go back to startPoint by reversely iterating through path stack
     */
    public void goHome() {
        System.out.println("GOING HOME!");
        Stack<Point> path = new Stack<>();
        PriorityQueue<Node>toVisit = new PriorityQueue<>();
        HashSet<Point> visited = new HashSet<>();
        Stack<Point> pathToHome = findShortestPath(true, getCurrent(), home, path, toVisit, visited);
        solve(pathToHome);
        System.out.println("I'm at Home!");
        shouldGoHome = false;
    }

    public void createThingRandomly() {
        thingPoint = Point.createRandomPoint(0, streets-1, 0, avenues-1);
        treasure = new Thing(getCity(), thingPoint.y, thingPoint.x);
    }
}