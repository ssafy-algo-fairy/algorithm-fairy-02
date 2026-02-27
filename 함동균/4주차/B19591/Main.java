import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main  {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, "+-*/", true);
		
		Deque<Long> num_q = new ArrayDeque<>();
		Deque<String> op_q = new ArrayDeque<>();
		String first = st.nextToken();
		if (first.equals("-"))
			num_q.add(-Long.parseLong(st.nextToken()));
		else
			num_q.add(Long.parseLong(first));
		while (st.hasMoreTokens()) {
			op_q.add(st.nextToken());
			num_q.add(Long.parseLong(st.nextToken()));
		}
		
		while (op_q.size() > 0) {
			long a = 0;
			long b = 0;
			String op = "";
			if (op_q.size() == 1) {
				a = num_q.pollFirst();
				b = num_q.pollLast();
				op = op_q.poll();
				num_q.add(evaluate(a, op, b));
			}
			else if ((op_q.peekFirst().equals("*") || op_q.peekFirst().equals("/")) && (op_q.peekLast().equals("+") || op_q.peekLast().equals("-"))) {
				a = num_q.pollFirst();
				b = num_q.pollFirst();
				op = op_q.pollFirst();
				num_q.addFirst(evaluate(a, op, b));
			}
			else if ((op_q.peekLast().equals("*") || op_q.peekLast().equals("/")) && (op_q.peekFirst().equals("+") || op_q.peekFirst().equals("-"))) {
				b = num_q.pollLast();
				a = num_q.pollLast();
				op = op_q.pollLast();
				num_q.addLast(evaluate(a, op, b));
			}
			else if (op_q.size() == 2) {
				long c = 0;
				a = num_q.pollFirst();
				c = num_q.pollFirst();
				b = num_q.pollLast();
				if (evaluate(a, op_q.peekFirst(), c) >= evaluate(c, op_q.peekLast(), b)) {
					num_q.addLast(b);
					num_q.addFirst(evaluate(a, op_q.pollFirst(), c));
				}
				else {
					num_q.addFirst(a);
					num_q.addLast(evaluate(c, op_q.pollLast(), b));
				}
			}
			else {
				long c = 0;
				long d = 0;
				a = num_q.pollFirst();
				b = num_q.pollFirst();
				d = num_q.pollLast();
				c = num_q.pollLast();
				if (evaluate(a, op_q.peekFirst(), b) >= evaluate(c, op_q.peekLast(), d)) {
					num_q.addFirst(evaluate(a, op_q.pollFirst(), b));
					num_q.addLast(c);
					num_q.addLast(d);
				}
				else {
					num_q.addLast(evaluate(c, op_q.pollLast(), d));
					num_q.addFirst(b);
					num_q.addFirst(a);
				}
			}
		}
		System.out.println(num_q.poll());
    }
	
	static long evaluate(long a, String op, long b) {
		long res = 0;
		switch(op) {
		case "+":
			res = a + b;
			break;
		case "-":
			res = a - b;
			break;
		case "*":
			res = a * b;
			break;
		case "/":
			res = a / b;
			break;
		}
		return res;
	}
}