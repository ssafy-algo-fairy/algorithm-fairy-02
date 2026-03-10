package week3.B1826;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] oil = new int[1000001];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			oil[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int l = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
		
		int count = 0;
		
		for (int i = 0; i <= p; i++) {
			if (i == l) break;
			
			if (oil[i] != 0) q.add(oil[i]);
			
			if (i == p) {
				if (q.isEmpty()) {
					System.out.println(-1);
					return;
				}
				else {
					p += q.poll();
					count++;
				}
			}
		}
		
		System.out.println(count);

	}

}
