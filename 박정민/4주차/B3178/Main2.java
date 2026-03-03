package week4.B3178;

import java.util.*;
import java.io.*;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		LinkedList<Node> first = new LinkedList<>();
		LinkedList<Node> last = new LinkedList<>();
		
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			
			LinkedList<Node> vertex = first;
			
			for (int j = 0; j < k; j++) {
				boolean find = false;
				
				for (Node v : vertex) {
					if (v.contains(input[j])) {
						vertex = v.getNext();
						find = true;
						break;
					}
				}
				
				if (find) continue;
				
				count += k-j;
				Node next = new Node(input[j]);
				vertex.add(next);
				
				for (int l = j+1; l < k; l++) {
					Node tmp = new Node(input[l]);
					next.addNext(tmp);
					next = tmp;
				}
				break;
			}
			
			vertex = last;
			
			for (int j = 2*k-1; j >= k; j--) {
				boolean find = false;
				
				for (Node v : vertex) {
					if (v.contains(input[j])) {
						vertex = v.getNext();
						find = true;
						break;
					}
				}
				
				if (find) continue;
				
				count += j-k+1;
				Node next = new Node(input[j]);
				vertex.add(next);
				
				for (int l = j-1; l >= k; l--) {
					Node tmp = new Node(input[l]);
					next.addNext(tmp);
					next = tmp;
				}
				break;
			}
		}
		
		System.out.println(count);
	}

	public static class Node {
		char value;
		LinkedList<Node> next;
		
		public Node(char value) {
			this.value = value;
			next = new LinkedList<>();
		}
		
		public void addNext(Node n) {
			next.add(n);
		}
		
		public boolean contains(char value) {
			if (this.value == value) return true;
			return false;
		}
		
		public LinkedList<Node> getNext() {
			return next;
		}
	}
}
