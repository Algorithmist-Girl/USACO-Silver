import java.io.*;
import java.util.StringTokenizer;

public class moobuzz {

    // private variables
    private static int N;
    private static int[] hardCodedArr = new int[]{1, 2, 4, 7, 8, 11, 13, 14};

    private static int compute() {
        if (N < 8)
            return hardCodedArr[N - 1];
        else {
            int rowNum = N / 8;
            int extraLeft = N % 8;
            return (rowNum * 15) + hardCodedArr[extraLeft - 1];
        }
    }

    public static void main(String[] args) throws IOException {

        // input and output
        BufferedReader in = new BufferedReader(new FileReader("moobuzz.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        PrintWriter out = new PrintWriter(new File("moobuzz.out"));

        N = Integer.parseInt(st.nextToken());
        out.println(compute());

        in.close();
        out.close();


    }
}
