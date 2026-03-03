package com.ssafy.algo.fairy.w3.B21276;

import java.util.*;
import java.io.*;

public class b21276 {

	static int N, M;

	static List<String> nameList = new ArrayList<>(); // 전체 이름 목록
	static List<String> anc = new ArrayList<>(); // 시조 목록
	static List<List<String>> edge = new ArrayList<>(); // 부모의 자식 후보 목록

	static HashMap<String, Integer> sToNum = new HashMap<>(); // 이름 -> 인덱스 매핑
	static HashMap<String, Integer> outCnt = new HashMap<>(); // 사람별 부모 수 (진입차수)

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = 0;
		for (int i = 0; i < N; i++) {
			String name = st.nextToken();
			nameList.add(name);
			edge.add(new ArrayList<>());
			sToNum.put(name, num++);
			outCnt.put(name, 0);
		}

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			edge.get(sToNum.get(parent)).add(child);
			outCnt.replace(child, outCnt.get(child) + 1);
		}

		// 시조 찾기
		for (String name : nameList)
			if (outCnt.get(name) == 0)
				anc.add(name);
		Collections.sort(anc);

		System.out.println(anc.size());
		for (String name : anc)
			System.out.print(name + " ");
		System.out.println();

		// 직계 자식 찾기
		for (String name : anc)
			findC(name);

		Collections.sort(nameList);
		for (int i = 0; i < N; i++)
			Collections.sort(edge.get(i));

		for (String name : nameList) {
			System.out.print(name + " ");
			System.out.print(edge.get(sToNum.get(name)).size() + " ");
			for (String cName : edge.get(sToNum.get(name)))
				System.out.print(cName + " ");
			System.out.println();
		}

	}

	static void findC(String parent) {
		List<String> newList = new ArrayList<>();
		
		for (String name : edge.get(sToNum.get(parent))) {
			outCnt.put(name, outCnt.get(name) - 1);
			if (outCnt.get(name) == 0)
				newList.add(name);
		}
		edge.set(sToNum.get(parent), newList);
		
		for (String name : edge.get(sToNum.get(parent))) {
			findC(name);
		}
	}

}
// edge
//
// da - ho
// sa - ho da
// yu -
// ho -
// mi - yu do ha
// do - ha
// ha -
//
// outCnt
// da sa yu ho mi do ha
//  1  0  1  2  0  1  2