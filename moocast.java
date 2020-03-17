import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// max # cows a broadcast from a single cow can reach ==> reachability problem = BFS/DFS
// DFS: take an unexplored node u and traverse recursively through all adjacent unexplored nodes u
public class moocast {

    private static int N;
    private static boolean[] visited;
    private static boolean[][] isNeighbors;
    private static ArrayList<Cow> input;

    public static void fillNeighborArr() {

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
//                if (i != j) {
                int x1 = input.get(i).getX();
                int y1 = input.get(i).getY();
                int p1 = input.get(i).getP();

                int x2 = input.get(j).getX();
                int y2 = input.get(j).getY();

                int dist = (int) (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
//              int dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                if (dist <= (p1 * p1))
                    isNeighbors[i][j] = true;
//                }
            }
        }
    }


    // # cows reachable from source currCow
    public static int dfs(int currCow, boolean[] visited, boolean[][] isNeighbors) {
        if (visited[currCow])
            return 0;

        visited[currCow] = true;
        int ct = 1;
        for (int i = 0; i < isNeighbors[currCow].length; i++) {
            if (isNeighbors[currCow][i]) {
                ct += dfs(i, visited, isNeighbors);
            }
        }
        return ct;

    }


    public static void main(String[] DEEPANSHA_IS_THE_BEST) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter out = new PrintWriter(new FileWriter("moocast.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        isNeighbors = new boolean[N][N];
        visited = new boolean[N];
        input = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            input.add(new Cow(x, y, p));
        }

        fillNeighborArr();

        int max = 1;
        for (int i = 0; i < input.size(); i++) {
            max = Math.max(max, dfs(i, visited, isNeighbors));
            visited = new boolean[N];   // make sure to clear visited array each time!!
        }

        out.println(max);
        in.close();
        out.close();
    }
}

class Cow {
    private int x, y, p;

    Cow(int xCoord, int yCoord, int power) {
        x = xCoord;
        y = yCoord;
        p = power;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getP() {
        return p;
    }
}
