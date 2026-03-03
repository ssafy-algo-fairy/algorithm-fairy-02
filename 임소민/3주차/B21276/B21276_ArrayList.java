import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static String[] names;
    static HashMap<String, Integer> nameToNum;
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static ArrayList<String> roots;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder sb = new StringBuilder();
        
        // -- 입력 --
        N = Integer.parseInt(br.readLine());
        
        names = new String[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            names[i] = st.nextToken();
        }

        // 사람들 정렬해서 이름->숫자 치환
        Arrays.sort(names); 
        nameToNum = new HashMap<>();
        for (int i = 0; i < N; i++) {
            nameToNum.put(names[i], i);
        }
        
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        indegree = new int[N];
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int child = nameToNum.get(st.nextToken());
            int ancestor = nameToNum.get(st.nextToken());
            
            // 조상 -> 자손 방향으로 그래프에 넣기
            graph[ancestor].add(child);
            
            // child로 들어오는 간선 수 갱신
            indegree[child]++;
        }
        
        // -- 직계 부모로 이어지도록 조정 --
        roots = new ArrayList<>();
        adjustParent();
        
        // -- 출력 --
        sb.append(roots.size()).append('\n'); // 시조 수
        for (String root : roots) sb.append(root).append(' '); // 시조들
        sb.append('\n');
        for (int i = 0; i < N; i++) { // 직계 자식들
        	sb.append(names[i]).append(' ');

        	ArrayList<Integer> children = graph[i];
        	sb.append(children.size()).append(' ');

        	Collections.sort(children);
            for (int j = 0; j < children.size(); j++) {
            	sb.append(names[graph[i].get(j)]).append(' ');
            }
            sb.append('\n');
        }
        
        
        bw.write(sb.toString());
        bw.flush();
    }
    
    // 위상 정렬
    static void adjustParent() { // 직계 부모 건너 뛰고 조상으로 이어진 간선들 지우는 작업
        Queue<Integer> queue = new ArrayDeque<>();
    
        // 큐에 간선수(indegree)가 0개인 노드 담기
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                roots.add(names[i]); // 초기-> 시조
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty()) {
            int parent = queue.poll();
            
            for (int i = graph[parent].size() - 1; i >= 0; i--) {
                int descendant = graph[parent].get(i);
                
                indegree[descendant]--;
                
                if (indegree[descendant] != 0) {
                    graph[parent].remove(i);
                } else {
                    queue.add(descendant);
                }
            }

        }
    }
}
