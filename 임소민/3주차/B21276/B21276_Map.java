import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N, M;
	static TreeMap<String, ArrayList<String>> graph;
	static TreeMap<String, Integer> indegree;
	static ArrayList<String> roots;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		
		// -- 입력 --
		N = Integer.parseInt(br.readLine());
		
		graph = new TreeMap<>();
		indegree = new TreeMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { // 초기세팅: 마을 사람들 그래프에 넣기
			String name = st.nextToken();
			graph.put(name, new ArrayList<>());
			indegree.put(name, 0);
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String ancestor = st.nextToken();
			
			// 조상 -> 자손 방향으로 그래프에 넣기
			ArrayList<String> list = graph.get(ancestor);
			list.add(child);
			graph.put(ancestor, list);
			
			// child로 들어오는 간선 수 갱신
			indegree.put(child, indegree.get(child) + 1);
		}
		
		// -- 직계 부모로 이어지도록 조정 --
		roots = new ArrayList<>();
		adjustParent();
		
		// -- 출력 --
		sb.append(roots.size()).append('\n'); // 시조 수
		for (String root : roots) sb.append(root).append(' '); // 시조들
		sb.append('\n');
		for (Map.Entry<String, ArrayList<String>> entry : graph.entrySet()) { // 직계 자식들
			String parent = entry.getKey();
			ArrayList<String> children = entry.getValue();
			
			sb.append(parent).append(' ');
			sb.append(children.size()).append(' ');
			Collections.sort(children); // 자식들 정렬
			for (String child : children) {
				sb.append(child).append(' ');
			}
			sb.append('\n');
		}
		
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	// 위상 정렬
	static void adjustParent() { // 직계 부모 건너 뛰고 조상으로 이어진 간선들 지우는 작업
		Queue<String> queue = new ArrayDeque<>();
	
		// 큐에 간선수(indegree)가 0개인 노드 담기
		for (Map.Entry<String, Integer> entry : indegree.entrySet()) {
			if (entry.getValue() == 0) {
				roots.add(entry.getKey()); // 초기-> 시조
				queue.add(entry.getKey());
			}
		}
		
		while(!queue.isEmpty()) {
			String parent = queue.poll();
			
			ArrayList<String> descendants = graph.get(parent);
			ArrayList<String> children = new ArrayList<>();
			for (String descendant : descendants) {
				indegree.put(descendant, indegree.get(descendant) - 1);
				
				if (indegree.get(descendant) == 0) { // 간선수 0인 것들만 직계자손
					children.add(descendant);
					queue.add(descendant);
				}
			}
			
			graph.put(parent, children);
		}
	}
}
