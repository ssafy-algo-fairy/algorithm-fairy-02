package week1.B20955;

import java.util.*;
import java.io.*;

public class Main {
	
	static int[] index;
	static int count;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		index = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			index[i] = i;
		}
		
		count = 0;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		boolean first = true;
		for (int i = 1; i <= n; i++) {
			if (index[i] == i) {
				if (first) first = false;
				else count++;
			}
		}
		
		System.out.print(count);
		
	}
	
	public static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		
		if (p1 == p2) {
			count++;
			return;
		}
		
		index[p2] = p1;
	}
	
	public static int find(int a) {
		if (index[a] == a) return a;
		
		return index[a] = find(index[a]);
	}

}
