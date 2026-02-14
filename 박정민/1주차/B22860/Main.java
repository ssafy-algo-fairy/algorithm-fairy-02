package week1.B22860;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Node> folder = new ArrayList<>();
		Node main = new Node("main", "1");
		folder.add(main);
		HashMap<String, List<Node>> noP = new HashMap<>();
		
		for (int i = 0; i < n+m; i++) {
			st = new StringTokenizer(br.readLine());
			String p = st.nextToken();
			String name = st.nextToken();
			String type = st.nextToken();
			Node input = new Node(name, type);
			boolean find = false;
			for (Node f : folder) {
				if (f.getName().equals(p)) {				
					f.addChild(input);
					find  = true;
					break;
				}
			}
			if (!find) {
				noP.putIfAbsent(p, new LinkedList<>());
				noP.get(p).add(input);
			}
			if (type.equals("1")) folder.add(input);
		}
		
		for (Map.Entry<String, List<Node>> e : noP.entrySet()) {
			String p = e.getKey();
			List<Node> list = e.getValue();
			for (Node f : folder) {
				if (f.getName().equals(p)) {				
					for (Node node : list) {
						f.addChild(node);
					}
					break;
				}
			}
		}
		
		int q = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < q; i++) {
			String[] input = br.readLine().split("/");
			Node toFind = main;
			for (int j = 1; j < input.length; j++) {
				for (Node next : toFind.getChild()) {
					if (next.getName().equals(input[j])) {
						toFind = next;
						break;
					}
				}
			}
			HashSet<Node> checked = new HashSet<>();
			int answer = toFind.search(checked);
			sb.append(checked.size()).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	static class Node {
		String name;
		String type;
		List<Node> child;
		
		public Node(String name, String type) {
			this.name = name;
			this.type = type;
			if (type.equals("1")) child = new LinkedList<>();
		}
		
		public void addChild(Node n) {
			child.add(n);
		}
		
		public List<Node> getChild() {
			return child;
		}
		
		public String getName() {
			return name;
		}

		public int search(HashSet<Node> checked) {
			int count = 0;
			for (Node f : child) {
				if (f.type.equals("1")) {
					count += f.search(checked);
				} else {
					checked.add(f);
					count++;
				}
			}
			return count;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(name);
		}
		
		@Override
		public boolean equals(Object o) {
			return this.getName().equals(((Node)o).getName());
		}
	}

}
