import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		TreeSet<Integer> treeSet = new TreeSet<>(); // 명소인 인덱스들 담은 트리셋
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int famous = Integer.parseInt(st.nextToken());
			if (famous == 1) treeSet.add(i);
		}

		int cur = 1;
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int qNum = Integer.parseInt(st.nextToken());
			
			switch (qNum) {
			case 1: // i번째 명소 변환
				int i = Integer.parseInt(st.nextToken());
				if (treeSet.contains(i)) { // 명소 O -> 명소  X
					treeSet.remove(i);
				} else { // 명소 X -> 명소 O
					treeSet.add(i);
				}
				break;
			case 2:
				int x = Integer.parseInt(st.nextToken());
				cur = (cur - 1 + x) % N + 1; // 원형으로 현재 위치 변경
				break;
			case 3:
				int answer = -1;
				if (!treeSet.isEmpty()) {
					Integer next = treeSet.ceiling(cur);
					if (next == null) { // 반시계방향에 있을 때
						next = treeSet.ceiling(1);
						answer = (N - cur) + next;
					} else { // 시계방향에 있을 때
						answer = next - cur;
					}
				}
				System.out.println(answer);
			}
		}
	}


}
