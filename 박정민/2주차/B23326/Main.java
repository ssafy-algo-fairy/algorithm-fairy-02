package B23326;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		int[] location = new int[n];
		TreeSet<Integer> s = new TreeSet<>();
		int dohyeon = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			location[i] = Integer.parseInt(st.nextToken());
			if (location[i] == 1) s.add(i); 
		}
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			
			switch (query) {
			case 1:
				int x = Integer.parseInt(st.nextToken());
				if (location[x-1] == 0) {
					location[x-1] = 1;
					s.add(x-1);
				}
				else if (location[x-1] == 1) {
					location[x-1] = 0;
					s.remove(x-1);
				}
				break;
			case 2:
				dohyeon = (dohyeon + Integer.parseInt(st.nextToken())) % n;
				break;
			case 3:
				int output = -1;
				if (!s.isEmpty()) {
					Integer next = s.ceiling(dohyeon);
					if (next == null) {
						next = s.first();
						output = n - dohyeon + next;
					} else {
						output = next - dohyeon;
					}
				}
				
				sb.append(output).append("\n");
			}
		}
		System.out.print(sb);
	}

}
