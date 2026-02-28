package boj1826;

import java.io.*;
import java.util.*;

public class Main {

	static int N, L, P;
	static ArrayList<Node> gasStation = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			gasStation.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 마을까지 거리
		P = Integer.parseInt(st.nextToken()); // 현재 연료
		gasStation.add(new Node(L, 0));

		// 거리순 정렬
		gasStation.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.loc - o2.loc;
			}
		});

		System.out.println(drive());
	}

	static int drive() {
		int curDist = 0;
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i <= N; i++) {

			// 도착 계산
			if (curDist + P >= L) {
				return answer;
			}

			int move = gasStation.get(i).loc - curDist;

			if (P >= move) { // 다음 위치까지 이동 가능
				pq.offer(gasStation.get(i).oil);
				curDist += move;
				P -= move;
			} else { // 다음 위치까지 이동 불가능
				while (!pq.isEmpty()) {
					P += pq.poll();
					answer++;

					if (P >= move) {
						pq.offer(gasStation.get(i).oil);
						curDist += move;
						P -= move;
						break;
					}
				}

				if (pq.isEmpty() && P < move)
					return -1; // 다 넣어도 이동 불가
			}
		}
		return answer;
	}
}

class Node {
	int loc;
	int oil;

	Node(int loc, int oil) {
		this.loc = loc;
		this.oil = oil;
	}
}
