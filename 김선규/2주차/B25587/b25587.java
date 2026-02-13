package com.ssafy.algo.fairy.w2.B25587;

import java.util.*;
import java.io.*;

public class b25587 {

	static class City {
		int capacity;
		int rain;
		int cityNum;
		int floodNum;
		int parent;

		public City(int capacity, int rain, int cityNum, int floodNum, int parent) {
			this.capacity = capacity; 	// 배수로 용량
			this.rain = rain; 			// 강수량
			this.cityNum = cityNum; 	// union된 도시 개수
			this.floodNum = floodNum; 	// 홍수난 도시 개수
			this.parent = parent; 		// 부모 도시 숫자
		}

	}

	static int N, M, floodCnt;

	static int[] capacity;
	static int[] rain;
	static City[] cityList;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		floodCnt = 0; // 전체 홍수난 도시 개수
		capacity = new int[N];
		rain = new int[N];
		cityList = new City[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			capacity[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			rain[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			cityList[i] = new City(capacity[i], rain[i], 1, (capacity[i] >= rain[i]) ? 0 : 1, i);
			floodCnt += cityList[i].floodNum;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int query = Integer.parseInt(st.nextToken());

			if (query == 1) { // 1 x y : x번 도시와 y번 도시에 공사를 한다.
				int c1 = Integer.parseInt(st.nextToken()) - 1;
				int c2 = Integer.parseInt(st.nextToken()) - 1;

				union(c1, c2);
			} else { // 2 : 현재 상태에서 홍수가 날 도시의 개수를 출력한다.
				System.out.println(floodCnt);
			}
		}

	}

	static void union(int c1, int c2) { // c1, c2의 부모 도시 p1, p2를 찾고 p1으로 합치기
		int p1Idx = find(c1);
		int p2Idx = find(c2);
		
		if (p1Idx == p2Idx) return;
			
		City p1 = cityList[p1Idx];
		City p2 = cityList[p2Idx];

		p1.capacity += p2.capacity;
		p1.rain += p2.rain;
		p1.cityNum += p2.cityNum;
		p2.parent = p1Idx;

		floodCnt -= (p1.floodNum + p2.floodNum);
		p1.floodNum = (p1.capacity >= p1.rain) ? 0 : p1.cityNum;
		floodCnt += p1.floodNum;

	}

	static int find(int c) { // c의 부모 도시 숫자 리턴
	    if (cityList[c].parent == c) return c;
	    
	    return cityList[c].parent = find(cityList[c].parent); // 경로 압축
	}

}