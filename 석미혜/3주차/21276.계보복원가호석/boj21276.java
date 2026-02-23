import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Info {
	List<String> list;
	int degree;

	public Info() {
		list = new ArrayList();
		degree = 0;
	}
}

public class boj21276 {
	static List<String> parent = new ArrayList();
	static Map<String, Info> map = new HashMap();
	static Map<String, Info> result = new HashMap();
	static int N, M;
	static String[] people; // 사람들
	static Queue<String> queue = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		people = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			map.put(people[i], new Info());
			result.put(people[i], new Info());
		}

		M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			String[] input = br.readLine().split(" ");
			// x -> y
			String x = input[0];
			String y = input[1];

			map.get(x).degree++;
			map.get(y).list.add(x);
		}

		int count = 0;
		for (Entry<String, Info> item : map.entrySet()) {
			if (item.getValue().degree == 0) {
				queue.add(item.getKey());
				parent.add(item.getKey());
				count++;
			}
		}

		sb.append(count+"\n");
		Collections.sort(parent);
		for(String s : parent) {
			sb.append(s+" ");
		}
		sb.append("\n");
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				String current = queue.poll();

				for (String child : map.get(current).list) {
					map.get(child).degree--;
					
				    if (map.get(child).degree == 0) {
				        queue.add(child);
				        
				        result.get(current).degree++;
				        result.get(current).list.add(child);
				    }
				}
			}
		}
		
		
		
		Arrays.sort(people);
		for(String p: people) {
			sb.append(p+" ");
			sb.append(result.get(p).degree+" ");
			List<String> childs = result.get(p).list;
			Collections.sort(childs);
			for(String s : childs) {
				sb.append(s+" ");
			}
			sb.append("\n");
		}
		
		
		System.out.println(sb.toString());

	}

}
