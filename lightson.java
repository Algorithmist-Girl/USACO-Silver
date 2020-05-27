import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem understanding-
 * Bessie has to light as many rooms as she can.
 * Bessie can simply light up a room by pushing a toggle. HOWEVER, BESSIE CAN ONLY TRAVEL THROUGH
 * ADJACENT LIT ROOMS!!
 * <p>
 * PSEUDOCODE:
 * TECHNIQUE- RECURSION
 * 1) Start @ (1,1)
 * Visit all of the adjacent blocks and recursively search through them
 * <p>
 * THEN, visit through arraylist which it can toggle some coords on AND turn those pieces on
 * recursively visit the blocks that you turned on which have a VISITED NEIGHBOR
 * WHY VISITED NEIGHBOR? This is because to even continue recursively turning on room lights, even though we
 * turned on the new block....we can ONLY recursively visit it and see if we can turn on any rooms it can toggle
 * if it has a turned on visited neighbor (a neighbor which Bessie can walk to on a path)
 */


public class lightson {

    // INSTEAD OF THIS DS, TRY USING ARRAYLIST IN EACH POSITION OF A 2D ARRAY!
//    private static HashMap<Point, ArrayList<Point>> input;
// THIS NEW DS STORES AN ARRAYLIST consisting of points IN EACH OF THE POSITIONS OF THE 2D ARRAY!
    private static ArrayList<Point>[][] input;
    private static boolean on[][];
    private static boolean bessieCanWalkBCPathExists[][];
    private static int n, m;

//    public static ArrayList<Point> get(int x, int y) {
//        for (Point curr : input.keySet()) {
//            if (curr.getX() == x && curr.getY() == y)
//                return input.get(curr);
//        }
//        return null;
//    }


    public static void explore(int x, int y) {
        if (bessieCanWalkBCPathExists[x][y])
            return;
        else
            bessieCanWalkBCPathExists[x][y] = true;

        // recursively search all adj LIT nodes
        if (isOn(x + 1, y)) {
            explore(x + 1, y);
        }
        if (isOn(x - 1, y)) {
            explore(x - 1, y);
        }
        if (isOn(x, y + 1)) {
            explore(x, y + 1);
        }
        if (isOn(x, y - 1)) {
            explore(x, y - 1);
        }


        ArrayList<Point> points = input[x][y];

        if (points != null) {
            for (int i = 0; i < points.size(); i++) {
                Point curr = points.get(i);
                if (!on[curr.getX()][curr.getY()]) {
                    on[curr.getX()][curr.getY()] = true;

                    // ONLY IF NEED TO TURN ON, explore in all diff directions and see if can toggle on
                    // any other blocks recursively if can visit this node PHYSICALLY by bessie walking!!
                    if (pointHasNeighboringVisitedAndOn(curr.getX(), curr.getY()))
                        explore(curr.getX(), curr.getY());
                }
            }
        }

    }


    public static boolean pointHasNeighboringVisitedAndOn(int x, int y) {
        // if point has a neigboring visited AND ON node so that a path forms and Bessie can physically walk to it
        if (isOn(x - 1, y) && isVisited(x - 1, y)) {
            return true;
        }

        if (isOn(x + 1, y) && isVisited(x + 1, y)) {
            return true;
        }

        if (isOn(x, y - 1) && isVisited(x, y - 1)) {
            return true;
        }

        if (isOn(x, y + 1) && isVisited(x, y + 1)) {
            return true;
        }

        return false;
    }

    public static boolean isOn(int x, int y) {
        return (x >= 0 && y >= 0 && x < n && y < n && on[x][y]);
    }

    public static boolean isVisited(int x, int y) {
        return (x >= 0 && y >= 0 && x < n && y < n && bessieCanWalkBCPathExists[x][y]);
    }

    public static void main(String[] DEEEPANSHA_IS_A_GENIUS) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("lightson.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        on = new boolean[n][n];
        bessieCanWalkBCPathExists = new boolean[n][n];
        input = new ArrayList[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int currX = Integer.parseInt(st.nextToken()) - 1;
            int currY = Integer.parseInt(st.nextToken()) - 1;
            int three = Integer.parseInt(st.nextToken()) - 1;
            int four = Integer.parseInt(st.nextToken()) - 1;

            ArrayList<Point> list = new ArrayList<>();
            if (input[currX][currY] != null)
                list = input[currX][currY];
            list.add(new Point(three, four));
            input[currX][currY] = list;

        }

        on[0][0] = true;
        explore(0, 0);
        int ct = 0;

        for (int i = 0; i < on.length; i++) {
            for (int j = 0; j < on[i].length; j++) {
                if (on[i][j])
                    ct += 1;
            }
        }
        out.println(ct);
        out.close();
    }
}

class Point {
    int xCoord, yCoord;

    public Point(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

//    public boolean equals(Object other) {
//        Point hi = (Point) other;
//        return (this.getX() == hi.getX() && this.getY() == hi.getY());
//    }

//    public int hashCode() {
//        return xCoord * yCoord;
//    }

    public String toString() {
        return "(" + xCoord + ", " + yCoord + ")";
    }
}


//import java.io.*;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
///**
// * APPROACH: recursive technique traverse unvisited neighboring places
// */
//public class lightson {
//
//    private static int n, m;
//    private static boolean[][] input;
//    private static ArrayList<ArrayList<Coord>> list;
//    private static ArrayList<Coord> litRooms = new ArrayList<>();
//
//
//    public static boolean CONTAINS(ArrayList<Coord> list, Coord one) {
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getX() == one.getX() && list.get(i).getY() == one.getY())
//                return true;
//        }
//        return false;
//    }
//
//    public static boolean canVisit(ArrayList<Coord> litRooms, Coord tryingToReachCoord) {
//        for (int i = 0; i < litRooms.size(); i++) {
//            Coord currLitRoom = litRooms.get(i);
//            int index = contains(currLitRoom);
//            if (index != -1) {
////                System.out.println(list.get(index));
//                if (CONTAINS(list.get(index), tryingToReachCoord))
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    public static void compute(Coord startCoord) {
//        if (!CONTAINS(litRooms, startCoord))
//            litRooms.add(startCoord);
//        System.out.println(litRooms);
//
//        if (startCoord.getX() + 1 <= input.length - 1) {
//            Coord newCoord = new Coord(startCoord.getX() + 1, startCoord.getY());
//
//            if (canVisit(litRooms, newCoord)) {
//                input[startCoord.getX() + 1][startCoord.getY()] = true;
//                if (!litRooms.contains(newCoord))
//                    litRooms.add(newCoord);
//                compute(new Coord(startCoord.getX() + 1, startCoord.getY()));
//            }
//        }
//        if (startCoord.getX() - 1 >= 0) {
//            Coord newCoord = new Coord(startCoord.getX() - 1, startCoord.getY());
//
//            if (canVisit(litRooms, newCoord)) {
//                input[startCoord.getX() - 1][startCoord.getY()] = true;
//                if (!litRooms.contains(newCoord))
//                    litRooms.add(newCoord);
//                compute(new Coord(startCoord.getX() - 1, startCoord.getY()));
//            }
//        }
//
//        if (startCoord.getY() + 1 <= input.length - 1) {
//            Coord newCoord = new Coord(startCoord.getX(), startCoord.getY() + 1);
//
//            if (canVisit(litRooms, newCoord)) {
//                input[startCoord.getX()][startCoord.getY() + 1] = true;
//                if (!litRooms.contains(newCoord))
//                    litRooms.add(newCoord);
//                compute(new Coord(startCoord.getX(), startCoord.getY() + 1));
//            }
//        }
//
//        if (startCoord.getY() - 1 >= 0) {
//            Coord newCoord = new Coord(startCoord.getX(), startCoord.getY() - 1);
//
//            if (canVisit(litRooms, newCoord)) { // !input[startCoord.getX()][startCoord.getY() - 1] &&
//                input[startCoord.getX()][startCoord.getY() - 1] = true;
//                if (!litRooms.contains(newCoord))
//                    litRooms.add(newCoord);
//                compute(new Coord(startCoord.getX(), startCoord.getY() - 1));
//            }
//        }
//
//    }
//
//
//    public static int contains(Coord one) {
//        // list of lists
//        // at index 0 ==> will have the currcord
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).get(0).getX() == one.getX() && list.get(i).get(0).getY() == one.getY())
//                return i;
//        }
//        return -1;
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
//        PrintWriter out = new PrintWriter(new FileWriter("lightson.out"));
//        StringTokenizer st = new StringTokenizer(in.readLine());
//
//
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        input = new boolean[n + 1][n + 1];
//        list = new ArrayList<>();
//        list = new ArrayList<>();
//
//
//        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(in.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//
//            Coord newCoord = new Coord(x, y);
//            Coord toCoord = new Coord(a, b);
//
//            ArrayList<Coord> temp = new ArrayList<>();
//            int index = contains(newCoord);
//            if (index != -1) {
//                list.get(index).add(toCoord);
//            } else {
//                temp.add(newCoord);
//                temp.add(toCoord);
//                list.add(temp);
//            }
//        }
//
//        System.out.println(list);
//
//        input[1][1] = true;
//
//
////        ArrayList<Coord> TEST = new ArrayList<>();
////        TEST.add(new Coord(1, 1));
////        System.out.println(canVisit(TEST, new Coord(1, 2)));
////        System.out.println(canVisit(TEST, new Coord(1, 3)));
////        System.out.println(canVisit(TEST, new Coord(1, 0)));
//
//
//        compute(new Coord(1, 1));
//        for (int i = 0; i < input.length; i++) {
//            for (int j = 0; j < input[i].length; j++) {
//                System.out.print(input[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        out.close();
//    }
//}
//
//class Coord {
//    int x, y;
//
//    public Coord(int xCoord, int yCoord) {
//        x = xCoord;
//        y = yCoord;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public String toString() {
//        return "(" + getX() + ", " + getY() + ")";
//    }
//
//}
