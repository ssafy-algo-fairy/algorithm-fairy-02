package com.ssafy.algo.fairy.w4.B19591;

import java.util.*;
import java.io.*;

public class b19591 {

	static int start, end, p1, p2; // 포인터, 임시 포인터
	static long frontNum, backNum, n1, n2; // 숫자, 임시 숫자
	static char frontOper, backOper, o1, o2; // 연산자, 임시 연산자
	static boolean isMinus; // 첫 숫자가 음수인지
	static long t1, t2; // 계산 결과

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] cArr = br.readLine().toCharArray();

		int operCnt = 0;
		for (int i = 1; i < cArr.length; i++) {
			if (isOper(cArr[i]))
				operCnt++;
		}

		start = 0;
		end = cArr.length - 1;

		// 연산자가 없을 때
		if (operCnt == 0) {
			if (cArr[0] == '-')
				isMinus = true;
			else
				n1 = cArr[0] - '0';
			while (start < end) {
				n1 *= 10;
				n1 += (cArr[start + 1] - '0');
				start++;
			}

			if (isMinus)
				System.out.println(-n1);
			else
				System.out.println(n1);
		}
		// 연산자가 있을 때
		else {
			// 앞 숫자
			if (cArr[0] == '-')
				isMinus = true;
			else
				n1 = cArr[0] - '0';
			while (!isOper(cArr[start + 1])) {
				n1 *= 10;
				n1 += (cArr[start + 1] - '0');
				start++;
			}
			if (isMinus)
				frontNum = -n1;
			else
				frontNum = n1;
			n1 = 0;
			// 뒤 숫자
			n1 = cArr[end] - '0';
			int cnt = 1;
			while (!isOper(cArr[end - 1])) {
				n1 += (cArr[end - 1] - '0') * Math.pow(10, cnt++);
				end--;
			}
			backNum = n1;
			n1 = 0;
			// 연산자 두 개 이상일 때
			while (operCnt >= 2) {
				p1 = start;
				o1 = cArr[start + 1];
				start += 2;

				n1 = cArr[start] - '0';
				while (!isOper(cArr[start + 1])) {
					n1 *= 10;
					n1 += (cArr[start + 1] - '0');
					start++;
				}

				p2 = end;
				o2 = cArr[end - 1];
				end -= 2;

				n2 = cArr[end] - '0';
				cnt = 1;
				while (!isOper(cArr[end - 1])) {
					n2 += (cArr[end - 1] - '0') * Math.pow(10, cnt++);
					end--;
				}

				if (operCheck() == 1) {
					frontNum = t1;
					end = p2;
				} else {
					backNum = t2;
					start = p1;
				}

				operCnt--;
			}
			// 연산자가 한 개 남았을 때
			System.out.println(operation(frontNum, backNum, cArr[start + 1]));
		}

	}

	static boolean isOper(char c) {
		if (c == '+' || c == '*' || c == '-' || c == '/')
			return true;
		return false;
	}

	static int operCheck() {
		t1 = operation(frontNum, n1, o1);
		t2 = operation(n2, backNum, o2);

		if ((o1 == '*' || o1 == '/') && (o2 == '+' || o2 == '-'))
			return 1;
		else if ((o1 == '+' || o1 == '-') && (o2 == '*' || o2 == '/'))
			return 2;

		if (t1 >= t2)
			return 1;
		else
			return 2;
	}

	static long operation(long a, long b, char o) {
		if (o == '+')
			return a + b;
		else if (o == '-')
			return a - b;
		else if (o == '*')
			return a * b;
		else
			return a / b;
	}

}
