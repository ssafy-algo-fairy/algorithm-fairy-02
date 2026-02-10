package com.ssafy.algo.fairy.w2.B3691;

import java.util.*;
import java.io.*;

public class b3691 {

	static int n, b, typeCnt, left, right, mid, maxQuality;

	static HashMap<String, Integer> typeIdx = new HashMap<>();

	static int[][] partList;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int testCase = 0; testCase < T; testCase++) {
			typeIdx.clear();

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 부품 개수
			b = Integer.parseInt(st.nextToken()); // 예산

			partList = new int[n][3]; // typeId | price | quality

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String typeStr = st.nextToken();
				partList[i][0] = getTypeId(typeStr); // typeId
				st.nextToken();
				partList[i][1] = Integer.parseInt(st.nextToken()); // price
				partList[i][2] = Integer.parseInt(st.nextToken()); // quality
			}
			typeCnt = typeIdx.size();

			left = 0;
			right = 1000000000;
			while (right >= left) { // quality로 이분 탐색
				mid = (left + right) / 2;
				if (getPrice(mid) <= b) {
					maxQuality = mid;
					left = mid + 1;
				} else
					right = mid - 1;
			}

			System.out.println(maxQuality);

		}

	}

	static int getTypeId(String s) {
		if (typeIdx.containsKey(s))
			return typeIdx.get(s);

		typeIdx.put(s, typeIdx.size());
		return typeIdx.size() - 1;
	}

	static long getPrice(int quality) { // 성능이 quality 이상인 부품들의 가격 총합
		int[] minPrice = new int[typeCnt];
		Arrays.fill(minPrice, -1);
		for (int i = 0; i < n; i++) {
			if (partList[i][2] < quality)
				continue;
			if (minPrice[partList[i][0]] == -1)
				minPrice[partList[i][0]] = partList[i][1];
			else
				minPrice[partList[i][0]] = Math.min(minPrice[partList[i][0]], partList[i][1]);
		}
		long sum = 0;
		for (int price : minPrice) {
			if (price == -1) // 특정 부품이 없는 경우
				return b + 1;
			sum += price;
		}
		return sum;
	}

}
