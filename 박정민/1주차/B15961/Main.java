package B15961;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] visited = new int[d+1];
		visited[c] = 1;
		int count = 1; //현재 윈도우 초밥의 개수
		int max = 1;
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> tmp = new LinkedList<>();
		
		for (int i = 1; i <= n; i++) {
			int in = Integer.parseInt(br.readLine());
			
			if (i <= k) tmp.add(in); // 마지막 초밥에서 이어질 수 있는 경우의수 확인용
			
			if (i > k) { // 윈도우에서 나가는 초밥
				int out = q.poll();
				if (out != c) {
					visited[out]--;
					if (visited[out] == 0) count--;
				}
			}
			
			// 윈도우에 들어가는 초밥
			q.add(in);
			if (in != c) {
				visited[in]++;
				if (visited[in] == 1) {
					count++;
					max = Math.max(max, count);
				}
			}
		}
		
		// 마지막 초밥에서 이어질 수 있는 경우의수 확인
		for (int i = 0; i < k; i++) {
			int in = tmp.poll();
			
			int out = q.poll();
			if (out != c) {
				visited[out]--;
				if (visited[out] == 0) count--;
			}
			
			q.add(in);
			if (in != c) {
				visited[in]++;
				if (visited[in] == 1) {
					count++;
					max = Math.max(max, count);
				}
			}
		}
		
		System.out.println(max);
	}

}
