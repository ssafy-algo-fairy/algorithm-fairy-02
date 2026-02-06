package boj15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int [] arr;
	static Map<Integer, Integer> map;
	static int c;
	static boolean couponFlag;	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 접시의 수
		int d = Integer.parseInt(st.nextToken());	// 초밥 가짓수
		int k = Integer.parseInt(st.nextToken());	// 연속
		c = Integer.parseInt(st.nextToken());	// 쿠폰 번호
		
		arr = new int[N];
		map = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] == c)	couponFlag = true;
			// 첫번째 탐색
			if(i < k)	map.merge(arr[i], 1, (ov, nv) -> ov + 1);	// 없으면 1, 있으면 기존 값에 1 더함
		}
		int max = map.size() + checkCoupon();
		
		for(int i = 1; i < N; i++) {
			// front pointer
			int frontNum = arr[i-1];	// 초밥 번호
			int frontCnt = map.get(frontNum);	// 개수
			if(frontCnt > 1)	map.replace(frontNum, frontCnt - 1);
			else	map.remove(frontNum);
			
			// end pointer
			int endNum = arr[(i+k-1) % N];
			map.merge(endNum, 1, (ov, nv) -> ov + 1);
			
			max = Math.max(max, map.size() + checkCoupon());
		}
		
		System.out.println(max);                 
	}
	
	// 앞 뒤 쿠폰 체크
	static int checkCoupon() {
		if(!couponFlag)	return 1;	// 쿠폰 초밥이 레일 위에 없는 경우 
		else if(!map.containsKey(c))	return 1;
		else	return 0;
	}
}
