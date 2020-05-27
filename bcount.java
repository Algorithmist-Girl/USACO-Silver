import java.io.*;
import java.util.StringTokenizer;

/**
 * CONCEPT USED: PREFIX SUMS
 * To figure out how many cows are in an interval [a, b]
 * output number cows [a+1, b] - [0, a-1]
 */
public class bcount {

    private static int N, Q;
    private static int[][] numCows;

    public static void main(String[] DEEPANSHA_IS_THE_BEST) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter out = new PrintWriter(new FileWriter("bcount.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());


        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        numCows = new int[4][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            int currNo = Integer.parseInt(st.nextToken());

            if (i != 0) {
                for (int row = 1; row < 4; row++) {
                    numCows[row][i] = numCows[row][i - 1];
                }
            }

            numCows[currNo][i] += 1;

        }

//        // PRINTING OUT arr
//        for (int i = 0; i < numCows.length; i++) {
//            for (int j = 0; j < numCows[i].length; j++) {
//                System.out.print(numCows[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());


            int numOnes = numCows[1][end] - numCows[1][start - 1];
            int numTwos = numCows[2][end] - numCows[2][start - 1];
            int numThrees = numCows[3][end] - numCows[3][start - 1];

            out.println(numOnes + " " + numTwos + " " + numThrees);
        }


        out.close();
    }
}
