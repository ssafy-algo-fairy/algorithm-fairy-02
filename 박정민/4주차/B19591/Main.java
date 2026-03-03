package week4.B19591;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split("");
		
		StringBuilder num = new StringBuilder();
		num.append(st[0]); //-부호일 수도 있으니까 for문 돌리지 않고 따로 처리
		
		Set<String> operator = new HashSet<>();
		operator.add("+");
		operator.add("-");
		operator.add("*");
		operator.add("/");
		
		Deque<Long> nums = new LinkedList<>();
		Deque<String> ops = new LinkedList<>();
		
		for (int i = 1; i < st.length; i++) {
			String input = st[i];
			if (operator.contains(input)) {
				ops.add(input);
				nums.add(Long.parseLong(num.toString()));
				num = new StringBuilder();
			} else {
				num.append(input);
			}
		}
		nums.add(Long.parseLong(num.toString()));
		
		if (ops.isEmpty()) System.out.println(nums.poll());
		
		while (!ops.isEmpty()) {
			if (ops.size() == 1) {
				long answer = calc(nums.poll(), nums.poll(), ops.poll());
				System.out.println(answer);
				break;
			}
			
			boolean frontFirst = true;
			
			long front = nums.pollFirst();
			long last = nums.pollLast();
			
			long frontOutput = calc(front, nums.peekFirst(), ops.peekFirst());
			long lastOutput = calc(nums.peekLast(), last, ops.peekLast());
			
			if (order(ops.peekFirst()) < order(ops.peekLast())) {
				frontFirst = false;
			} 
			else if (order(ops.peekFirst()) == order(ops.peekLast())) {
				if (frontOutput < lastOutput) frontFirst = false;
			}
					
			if (frontFirst) {
				nums.pollFirst();
				nums.addFirst(frontOutput);
				ops.pollFirst();
				nums.addLast(last);
			} else {
				nums.pollLast();
				nums.addLast(lastOutput);
				ops.pollLast();
				nums.addFirst(front);
			}
			
		}

	}
	
	public static long calc(long a, long b, String op) {
		if (op.equals("+")) return a+b;
		else if (op.equals("-")) return a-b;
		else if (op.equals("*")) return a*b;
		return a/b;
	}
	
	public static int order(String op) {
		if (op.equals("*") || op.equals("/")) return 2;
		return 1;
	}

}
