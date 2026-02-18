package B2015;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		long sum = 0;
		long count = 0;
		HashMap<Long, Integer> map = new HashMap<>();
		map.put(0l, 1);
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			sum = sum + Integer.parseInt(st.nextToken());
			long toFind = sum - k;
			count += map.getOrDefault(toFind, 0);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
			
		}
		
		System.out.println(count);
	}

}
