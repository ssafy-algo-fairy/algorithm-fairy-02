package B2629;

import java.util.*;
import java.io.*;

public class b2629 {

	static int N, M;

	static Set<Integer> set = new HashSet<>();

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int inputNum = Integer.parseInt(st.nextToken());
			
			Set<Integer> newSet = new HashSet<>(set);
			
			for (int num : set) {
                newSet.add(Math.abs(num - inputNum)); // |(가능 무게) - (새로운 추 무게)|
                newSet.add(num + inputNum); // (가능 무게) + (새로운 추 무게)
            }
            newSet.add(inputNum); // (새로운 추 무게)
            
            set = newSet;
		}

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			int query = Integer.parseInt(st.nextToken());
			
			if (set.contains(query))
				System.out.print("Y ");
			else
				System.out.print("N ");
		}

	}

}