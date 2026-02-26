import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, P;
	static PriorityQueue<int[]> stations;
	static PriorityQueue<Integer> fuel;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		stations = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			stations.add(new int[] {a, b});
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		fuel = new PriorityQueue<>(Collections.reverseOrder());
		
		int answer = 0;
		
		int curDist = P; // 현재 연료 다 써서 최대한 전진
		while (curDist < L) {
			while (!stations.isEmpty()) {
				int dist = stations.peek()[0];
				if (dist <= curDist) {
					int[] now = stations.poll();
					fuel.add(now[1]);
				} else break;
			}
			
			if (!fuel.isEmpty()) {
				curDist += fuel.poll(); // 지나온 주유소 중 가장 많은 연료 써서 전진
				answer++;
			} else {
				answer = -1;
				break;
			}
		}
		
		System.out.println(answer);
	}
}
