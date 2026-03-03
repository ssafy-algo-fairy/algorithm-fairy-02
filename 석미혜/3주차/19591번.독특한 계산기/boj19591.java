import java.io.*;
import java.util.*;

public class boj19591 {
	static ArrayDeque<String> operQueue = new ArrayDeque();
	static ArrayDeque<Long> numQueue = new ArrayDeque();
	static long frontA, frontB, backA, backB;
	static String frontOper, backOper;
	static long answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("");

		// 연산자 우선순위 계산을 위한 맵
		Map<String, Integer> priority = new HashMap();
		priority.put("-", 0);
		priority.put("+", 0);
		priority.put("*", 1);
		priority.put("/", 1);

		boolean flag = false; // 처음 숫자가 음수인지 판단하는 flag 변수
		if (input[0].equals("-"))
			flag = true;

		String tmp = "";
		int startIndex = flag ? 1 : 0; // 음수로 시작하면 인덱스 1부터 시작하고 처음 숫자만 음수로 넣어줌
		for (int i = startIndex; i < input.length; i++) {
			String c = input[i];
			if (c.equals("*") || c.equals("-") || c.equals("+") || c.equals("/")) {
				if (tmp.length() > 0) {
					long n = flag ? Long.parseLong(tmp) * -1 : Long.parseLong(tmp);
					flag = false;
					numQueue.add(n);
				}
				operQueue.add(c);
				tmp = "";
			} else {
				tmp += c;
			}
		}
		
		// 음수인지 다시 체크 ex) -100
		long n = flag ? Long.parseLong(tmp) * -1 : Long.parseLong(tmp);
		numQueue.add(n);

		while (true) {
			if (operQueue.size() == 1) {
				answer = calculate(numQueue.poll(), operQueue.poll(), numQueue.poll());
				break;
			} else if (operQueue.size() == 0) {
				answer = numQueue.poll();
				break;
			}

			// 앞의 숫자와 연산
			frontA = numQueue.pollFirst();
			frontOper = operQueue.pollFirst();
			frontB = numQueue.peekFirst();

			// 뒤의 숫자와 연산
			backB = numQueue.pollLast();
			backOper = operQueue.pollLast();
			backA = numQueue.peekLast();

			// 앞의 연산과 뒤의 연산 우선순위
			int frontPriority = priority.get(frontOper);
			int backPriority = priority.get(backOper);

			// 연산자 우선순위가 같을 때
			if (frontPriority == backPriority) {
				long frontResult = calculate(frontA, frontOper, frontB);
				long backResult = calculate(backA, backOper, backB);
				// 앞의 결과값이 더 클 때
				if (frontResult >= backResult) {
					applyFront(frontResult);
				} else {
					applyBack(backResult);
				}
			} else if (frontPriority > backPriority) { // 앞의 연산자 우선순위가 더 클떄
				long frontResult = calculate(frontA, frontOper, frontB);
				applyFront(frontResult);
			} else { // 뒤의 연산자 우선순위가 더 클 때
				long backResult = calculate(backA, backOper, backB);
				applyBack(backResult);
			}
		}

		System.out.println(answer);
	}

	/**
	 * 앞의 연산 계산 a oper b 1. b는 peek해서 가져온것이므로 poll 2. 계산결과 다시 큐 앞에 넣기 
	 * 3. 뒤에서 뺀 연산과 숫자는 다시 넣기 -> while문에서 다시 뽑아서 업데이트시키기 때문
	 */
	public static void applyFront(long result) {
		numQueue.pollFirst();
		numQueue.addFirst(result);

		operQueue.addLast(backOper);
		numQueue.addLast(backB);
	}

	public static void applyBack(long result) {
		numQueue.pollLast();
		numQueue.addLast(result);

		operQueue.addFirst(frontOper);
		numQueue.addFirst(frontA);
	}
	

	public static long calculate(long x, String operator, long y) {
		switch (operator) {
		case "+":
			return x + y;
		case "-":
			return x - y;
		case "*":
			return x * y;
		default:
			return x / y;

		}
	}

}