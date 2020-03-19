import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// NEED TO FIND BOTH A VALID K AND ALSO THE MINIMUM POSSIBLE K
// TO CHECK IF VALID ==> need to simulate this by doing efficient insertion & deletion so PQ is best
public class cowdance {

    private static int N, tMax;
    private static ArrayList<Integer> durations;

    public static boolean determineIfValidK(int K) {
        PriorityQueue<Integer> cows = new PriorityQueue<>();
        int lastTime = 0;

        for (int i = 0; i < N; i++) {
            int currTime = durations.get(i);
            if (cows.size() == K)
                lastTime = cows.poll();

            if (lastTime + currTime > tMax)
                return false;

            cows.add(lastTime + currTime);  // MAKE SURE TO ADD LAST TIME which will help with answer of how much time it takes
        }
        return true;
    }


    public static void main(String[] ARGS) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter out = new PrintWriter(new FileWriter("cowdance.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        tMax = Integer.parseInt(st.nextToken());
        durations = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            durations.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= N; i++) {
            if (determineIfValidK(i)) {
                out.println(i);
                break;
            }
        }

        in.close();
        out.close();

    }
}
