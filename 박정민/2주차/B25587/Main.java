package week2.B25587;

import java.util.*;
import java.io.*;

public class Main {
	
	static int output;
	static int[] index;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] tank = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] rain = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		HashMap<Integer, City> cities = new HashMap<>();
		output = 0;
		index = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			cities.put(i, new City(i, tank[i-1], rain[i-1]));
			index[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "1": 
				int x = check(Integer.parseInt(st.nextToken()));
				int y = check(Integer.parseInt(st.nextToken()));
				City a = cities.get(x);
				City b = cities.get(y);
				if (a == b) break;
				a.merge(b);
				break;
			case "2":
				sb.append(output).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}
	
	public static int check(int a) { //union-find
		if (a == index[a]) return a;
		index[a] = check(index[a]);
		return index[a];
	}
	
	public static class City {
		int count, num, tank, rain;
		
		public City(int num, int tank, int rain) {
			this.count = 1; // 도시개수
			this.num = num;
			this.tank = tank;
			this.rain = rain;
			if (flood()) output += count;
		}
		
		public boolean flood() {
			return (rain > tank);
		}
 		
		public void merge(City c) { // 도시 배수로 합병(하나의 도시로 취급)
			if (this.flood()) output -= this.count; // 이전 도시 없애기
			if (c.flood()) output -= c.count;
			index[c.num] = this.num;
			
			this.count += c.count;
			this.tank += c.tank;
			this.rain += c.rain;
			if (this.flood()) output += this.count;
		}
	}

}
