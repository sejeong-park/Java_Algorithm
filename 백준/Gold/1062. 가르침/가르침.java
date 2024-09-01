
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N, K;
    static boolean [] visited;
    static String [] words;
    static int answer = 0;

    private static void check() {
        int count = 0;
        for (int idx = 0; idx < N; idx ++) {
            char [] wordList = words[idx].toCharArray();
            boolean read = true;
            for (char c : wordList) {
                if (!visited[c - 'a']) {
                    read = false;
                    break;
                }
            }
            if (read) count ++;
        }
        answer = Math.max(count, answer);
    }
    public static void dfs(int depth, int start) {

        //
        if (K == depth) {
            check();
            return;
        }

        for (int idx = start; idx < 26; idx ++) {
            if (visited[idx]) continue;
            visited[idx] = true;
            dfs(depth + 1, idx + 1);
            visited[idx] = false;
        }
    }

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (K < 5) {
            System.out.println(0);
            return;
        }
        if (K == 26) {
            System.out.println(N);
            return;
        }
        words = new String[N];
        for (int idx = 0; idx < N; idx ++) {
            String word = br.readLine().trim();
            words[idx] = word.replace("anta", "").replace("tica", "");
        }

        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;
        K -= 5;

        dfs(0, 0);
        System.out.println(answer);
    }

}
