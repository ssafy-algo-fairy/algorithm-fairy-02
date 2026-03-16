package B21276;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    // 이름과 인덱스를 매핑하기 위한 그래프
    static Map<String, Integer> graph = new HashMap<>();
    // 위상정렬을 위한 진입차수 배열
    static int[] indegree;
    // 가계도를 저장하기 위한 맵
    // (자식 -> 부모) 로 진행하니까 위상정렬이 안돼서 (부모 -> 자식)로 저장
    static Map<String, ArrayList<String>> family = new HashMap<>();
    // 직계 자식을 저장하기 위한 맵
    static Map<String, ArrayList<String>> direct = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        String[] names = new String[N];
        for (int i = 0; i < N; i++) {
            names[i] = st.nextToken();
        }
        // 문제 조건에 사전순으로 출력하라고 되어있어서 미리 정렬
        Arrays.sort(names);

        for (int i = 0; i < N; i++) {
            // 이름과 인덱스 매핑
            graph.put(names[i], i);
        }
        for (int i = 0; i < N; i++) {
            // 가계도 초기화
            family.put(names[i], new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            // 직계 자식 초기화
            direct.put(names[i], new ArrayList<>());
        }

        M = Integer.parseInt(br.readLine());

        indegree = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent = st.nextToken();
            // 가계도에 자식 추가
            family.get(parent).add(child);
            int idx = graph.get(child);
            // 자식의 진입차수 증가
            indegree[idx]++;
        }

        ArrayList<String> roots = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        int[] tmpIndegree = indegree.clone();

        for (int i = 0; i < N; i++) {
            String name = names[i];
            if (tmpIndegree[graph.get(name)] == 0) {
                // 진입차수가 0 == 루트
                roots.add(name);
                queue.add(name);
            }
        }

        // 위상정렬을 통해 직계 자식 구하기
        while (!queue.isEmpty()) {
            String parent = queue.poll();
            // 부모의 자식들 탐색
            for (String child : family.get(parent)) {
                int idx = graph.get(child);
                tmpIndegree[idx]--;
                // 진입차수가 0이 되면 직계 자식이므로 direct에 추가
                if (tmpIndegree[idx] == 0) {
                    direct.get(parent).add(child);
                    queue.add(child);
                }
            }
        }

        sb.append(roots.size()).append("\n");

        for (int i = 0; i < roots.size(); i++) {
            // 1. 루트 먼저 입력
            sb.append(roots.get(i)).append(" ");
        }
        sb.append("\n");

        for (String name : names) {
            // 2. 이름, 직계자식 수, 직계자식 이름 입력
            ArrayList<String> children = direct.get(name);
            // 자식이 정렬되었다는 보장이 없으므로 정렬
            Collections.sort(children);
            sb.append(name).append(" ").append(children.size()).append(" ");
            for (String child : children) {
                sb.append(child).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}