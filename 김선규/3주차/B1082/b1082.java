package B1082;

import java.util.*;
import java.io.*;

public class b1082 {

	static int N, M, maxSize;

	static int[] price, boughtNum;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		int minNum = 0, minPrice = 51; // 0 제외 가장 작은 숫자, 가격
		price = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		price[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
			if (price[i] < minPrice) {
				minNum = i;
				minPrice = price[i];
			}
		}

		M = Integer.parseInt(br.readLine());

		// 최대 자릿수 구하기
		maxSize = 1;
		if (M >= minPrice)
			maxSize += (M - minPrice) / Math.min(price[0], minPrice);

		boughtNum = new int[maxSize];

		// 최대 자릿수인 최소 숫자 구하기
		for (int i = 0; i < maxSize; i++) {
			boughtNum[i] = (price[0] < minPrice) ? 0 : minNum;
			M -= (price[0] < minPrice) ? price[0] : minPrice;
		}

		// 앞에서부터 가능한 가장 큰 숫자로 바꾸기
		for (int i = 0; i < maxSize; i++) {
			for (int j = N - 1; j > boughtNum[i]; j--) {
				if (price[j] - price[boughtNum[i]] <= M) {
					M -= (price[j] - price[boughtNum[i]]);
					boughtNum[i] = j;
					break;
				}
			}
		}

		for (int i : boughtNum)
			sb.append(i);
		System.out.println(sb);

	}

}