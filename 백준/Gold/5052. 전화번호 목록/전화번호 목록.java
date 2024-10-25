import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;

    static class Node {
        Map<Character, Node> child = new HashMap<>();
        boolean isEnd;
    }

    static class Trie {
        Node root = new Node();

        // insert
        boolean insert(String str) {
            Node now = this.root;
            boolean isNew = false;  // 새로운 노드가 삽입되는지 여부 체크

            for (Character c : str.toCharArray()) {
                if (!now.child.containsKey(c)) {
                    now.child.put(c, new Node());
                    isNew = true;  // 새 노드가 추가됨
                }
                now = now.child.get(c);

                // 중간에 다른 번호가 이미 끝나는 지점이 있으면 접두어 관계 발생
                if (now.isEnd) {
                    return false;  // 접두어 관계가 있음
                }
            }

            // 새 번호가 기존 번호의 접두어인지 확인 (기존 번호들이 새 번호보다 짧은 경우)
            if (!isNew) {
                return false;  // 이미 다른 번호가 이 번호의 접두어임
            }

            now.isEnd = true;
            return true;  // 정상 삽입됨
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < testCase; idx++) {
            int telSize = Integer.parseInt(br.readLine().trim());
            Trie trie = new Trie();
            boolean isConsistent = true;  // 일관성 체크

            for (int telIdx = 0; telIdx < telSize; telIdx++) {
                String telNum = br.readLine().trim();

                // 새 번호를 삽입하기 전에 접두어 검사
                if (!trie.insert(telNum)) {
                    isConsistent = false;
 
                }
            }

            if (isConsistent) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
