package week3.B2450;

import java.util.*;
import java.io.*;

public class Main {
	
	static int[] input;
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		input = new int[n];
		num = new int[4]; // 1, 2, 3의 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			num[input[i]]++;
		}
		
		int min = sort(1, 2, 3);
		min = Math.min(sort(1, 3, 2), min);
		min = Math.min(sort(2, 1, 3), min);
		min = Math.min(sort(2, 3, 1), min);
		min = Math.min(sort(3, 1, 2), min);
		min = Math.min(sort(3, 2, 1), min);
		
//		System.out.println(sort(1, 2, 3));
//		System.out.println(sort(1, 3, 2));
//		System.out.println(sort(2, 1, 3));
//		System.out.println(sort(2, 3, 1));
//		System.out.println(sort(3, 1, 2));
//		System.out.println(sort(3, 2, 1));
		System.out.println(min);
	}
	
	public static int sort(int i1, int i2, int i3) {
		int count = 0;
		int count2 = 0;
		
		for (int i = 0; i < num[i1]; i++) {
			if (input[i] == i2) {
				count++;
				count2++;	
			}
			if (input[i] == i3) count++;
		}
		
		for (int i = num[i1]; i < num[i1] + num[i2]; i++) {
			if (input[i] == i1) {
				if (count2 > 0) count2--;
				else count++;
			}
			if (input[i] == i3) count++;
		}
		
		return count;
	}

}
