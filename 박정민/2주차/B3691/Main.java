package B3691;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Map<String, PriorityQueue<Part>> parts = new HashMap<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String type = st.nextToken();
				st.nextToken();
				int price = Integer.parseInt(st.nextToken());
				int quality = Integer.parseInt(st.nextToken());
				Part input = new Part(price, quality);
				if (!parts.containsKey(type)) {
					parts.put(type, new PriorityQueue<>());
				}
				parts.get(type).add(input);
			}

			Map<String, Part> computer = new HashMap<>();
			int minQuality = Integer.MAX_VALUE;
			String minKey = null;
			int sum = 0;

			for (String key : parts.keySet()) {
				Part p = parts.get(key).poll();
				int price = p.getPrice();
				sum += price;

				int quality = p.getQuality();
				if (quality < minQuality) {
					minQuality = quality;
					minKey = key;
				}

				computer.put(key, p);
			}

			while (!parts.get(minKey).isEmpty()) {
				Part p = parts.get(minKey).poll();
				int price = p.getPrice();
				int quality = p.getQuality();
				if (quality <= minQuality) continue;

				if (sum - computer.get(minKey).getPrice() + price > b)
					break;
				
				sum = sum - computer.get(minKey).getPrice() + price;
				computer.put(minKey, p);				
				minQuality = quality;

				for (String key : computer.keySet()) {
					quality = computer.get(key).getQuality();
					if (quality < minQuality) {
						minQuality = quality;
						minKey = key;
					}
				}
			}
			sb.append(minQuality).append("\n");
		}
		System.out.print(sb);
	}

	public static class Part implements Comparable {
		int price, quality;

		public Part(int price, int quality) {
			this.price = price;
			this.quality = quality;
		}

		public int getPrice() {
			return price;
		}

		public int getQuality() {
			return quality;
		}

		@Override
		public int compareTo(Object o) {
			Part other = (Part) o;
			return Integer.compare(price, other.price);
		}
	}

}
