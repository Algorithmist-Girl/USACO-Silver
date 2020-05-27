import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SOLVE BY INTUITION
 * Bessie will maximize when Elsie try to earn points when Elsie puts down the smallest of her #s
 * and purposefully lose when Elsie puts bigger numbers
 * <p>
 * Start with Elsie's smallest numbers and see if Bessie's smallest numbers are greater
 * If it's greater ==> give bessie the point
 */
public class highcard {
    private static int n;
    private static boolean[] input; // USING BOOLEAN ARRAY IN THIS CASE IS A LOT MORE EFFIICIENT THAN USING
    // AN INT ARRAY (BOOLEAN ARRAY WILL QUICKLY ACCESS THAT INDEX VS INT ARRAY
    // WOULD TRAVERSE THROUGH ALL INDICES AND CHECK IN EACH ITERATION TO SEE IF
    // IT'S ELSIENUM OR BESSIENUM!!!
    private static ArrayList<Integer> elsieNums;
    private static ArrayList<Integer> bessieNums;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        PrintWriter out = new PrintWriter(new FileWriter("highcard.out"));

        n = Integer.parseInt(st.nextToken());
        input = new boolean[2 * n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            input[Integer.parseInt(st.nextToken())] = true;
        }
//        Collections.sort(input);

        elsieNums = new ArrayList<>();
        bessieNums = new ArrayList<>();
        for (int i = 1; i <= 2 * n; i++) {
            if (input[i])
                elsieNums.add(i);
            else
                bessieNums.add(i);
        }

        in.close();
//        Collections.sort(bessieNums);
//        Collections.sort(elsieNums);

        // 1, 4, 6, 8
        // 2, 3, 5, 7
        // bessieIndex++ in else statement:
        // 2, 5, 3 where 5 is compared with 4 instead
        int bessieIndex = 0;
        int elsieIndex = 0;
        int winCount = 0;
        while (bessieIndex < n && elsieIndex < n) {
            if (bessieNums.get(bessieIndex) > elsieNums.get(elsieIndex)) {
                bessieIndex++;
                elsieIndex++;
                winCount++;
            } else {
                bessieIndex++;
            }
        }

        out.println(winCount);

        out.close();
    }
}
