package B14658;

import java.util.*;
import java.io.*;

public class Main {
	static int[][] star;
	static int n, m, l, k;
	static int max;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		star = new int[k][2];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			star[i][0] = Integer.parseInt(st.nextToken());
			star[i][1] = Integer.parseInt(st.nextToken());
		}
		
		max = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				run(star[i][0], star[j][1]);
			}	
		}
		System.out.print(k - max);
		
		
	}
	
	public static void run(int x, int y) {
		int count = 0;
		
		for (int i = 0; i < k; i++) {			
			if (star[i][0] < x || star[i][0] > x+l ||
					star[i][1] < y || star[i][1] > y+l) continue;
			count++;	
		}
		
		if (count > max) max = count;
	}

}
