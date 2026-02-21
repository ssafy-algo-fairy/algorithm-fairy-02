import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class BOJ_21276 {
    static Map<String, Set<String>> children;
    static Map<String, Integer> indegrees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        children = new TreeMap<>();
        indegrees = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();
            children.put(name, new TreeSet<>());
            indegrees.put(name, 0);
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent = st.nextToken();

            children.get(parent).add(child);
            indegrees.put(child, indegrees.get(child) + 1);
        }

        int rootCount = 0;
        List<String> roots = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : indegrees.entrySet()) {
            if (entry.getValue() == 0) {
                rootCount++;
                roots.add(entry.getKey());
            }
        }

        sb.append(rootCount).append("\n");
        for (String root : roots) sb.append(root).append(" ");
        sb.append("\n");

        for (String name : children.keySet()) {
            sb.append(name).append(" ");

            int indegree = indegrees.get(name);
            int directCount = 0;
            List<String> directChildren = new ArrayList<>();

            for (String child : children.get(name)) { //직계 찾기
                if (indegrees.get(child) == indegree + 1) {
                    directCount++;
                    directChildren.add(child);
                }
            }

            sb.append(directCount).append(" ");
            for (String child : directChildren) sb.append(child).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
