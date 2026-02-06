## **🔍 문제 요약**

- 2 ≤ N ≤ 3,000,000 회전 초밥 개수
- 2 ≤ d ≤ 3,000 초밥 가지수
- 2 ≤ k ≤ 3,000 (k ≤ N) 연속해서 먹는 접시의 수
- 1 ≤ c ≤ d 쿠폰번호
- 시간제한 : 1초
- 이때 연속해서 k 접시를 먹을 경우에는 쿠폰 초밥 획득 가능
- 초밥 가짓수의 최대값을 구하자

---

## **💡문제 접근 / 풀이 전략**

- ~~쿠폰 번호에 해당 하는 초밥은 무료로 포함되므로 포인터의 -1, +1 같이 검사~~ → 문제 잘못이해했네; 쿠폰이 앞뒤에 있을필요 x 있기만핟면됨;;
- 연속해서 k개를 먹을때 종류 체크
- ~~슬라이딩 윈도우를 그냥 사용한다쳐도 최악의 경우에 3,000,000번 탐색 괜찮나~~ → 괜찮나보다
- set으로 하려고 했으나 중복 관리를 해줘야해서 map으로 바꿈

1. 초기 윈도우 설정
2. 슬라이딩 윈도우
    - 앞에거 빼고 → 1보다 크면 차감, 작으면 맵에서 제거
    - 뒤에꺼 넣기 → 있으면 개수 + 1 없으면 추가
3. 매 단계마다 현재 윈도우에 쿠폰 있는지 확인해서 갯수 갱신

---

## **✅ 코드 & 소요 시간**

```java
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

```

310504kb / 884ms

---

## **✍️ 회고**

- hashmap 사용했는데 배열로 하면 더 빠를 것 같긴하다

---

## **🧩 다른 풀이 (선택)**

```java

```

---
