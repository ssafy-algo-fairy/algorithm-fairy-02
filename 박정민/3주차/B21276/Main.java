package week3.B21276;

import java.io.*;
import java.util.*;

public class Main {
	
	static Map<String, HashSet<String>> child;
	static Map<String, HashSet<String>> graph;
	static Map<String, Integer> indegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		child = new HashMap<>();
		graph = new HashMap<>();
		indegree = new HashMap<>();
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<String> names = new PriorityQueue<>(); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String name = st.nextToken();
			names.add(name);
			child.put(name, new HashSet<>());
			graph.put(name, new HashSet<>());
			indegree.put(name,  0);
		}

		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String c = st.nextToken();
			String p = st.nextToken();
			graph.get(p).add(c);
			indegree.put(c, indegree.get(c) + 1);
		}	
		
		PriorityQueue<String> ansestor = new PriorityQueue<>();
		Queue<String> q = new LinkedList<>();
		int count = 0;
		
		for (Map.Entry<String, Integer> e : indegree.entrySet()) { // 시조 찾기			
			if (e.getValue() == 0) {
				count++;
				ansestor.add(e.getKey());
				q.add(e.getKey());
			}
		}
		
		sb.append(count).append("\n");
		for (int i = 0; i < count; i++) {
			sb.append(ansestor.poll()).append(" ");
		}
		sb.append("\n");
		
		// 위상정렬
		while (!q.isEmpty()) {
			String p = q.poll();
			
			for (String c : graph.get(p)) {
				indegree.put(c,  indegree.get(c) - 1);
				
				if (indegree.get(c) == 0) {
					child.get(p).add(c);
					q.add(c);
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			String name = names.poll();
			sb.append(name).append(" ");
			
			int size = child.get(name).size();
			sb.append(size).append(" ");
			
			PriorityQueue<String> p = new PriorityQueue<>(child.get(name));
			for (int j = 0; j < size; j++) {
				sb.append(p.poll()).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);	
	}
}
