# BOJ 25587 풀이 정리

## 📌 문제 유형
- Union-Find (Disjoint Set)
- 시뮬레이션
- 집합 병합

---

## 💡 문제 핵심 아이디어

각 도시는 다음 정보를 가진다.

- 도시 개수
- 물탱크 용량 (tank)
- 강수량 (rain)

어떤 도시(또는 합쳐진 도시 집합)가 침수되는 조건은 다음과 같다.

rain > tank

Union-Find를 이용해 도시들을 하나의 집합으로 관리한다.  
두 도시를 합칠 때는 각각의 물탱크와 강수량을 더해서 하나의 도시처럼 취급한다.

침수된 도시들의 개수 총합을 계속 관리하면서 질의 2번이 들어올 때마다 출력한다.

---

## 🧠 구현 포인트

### 1️⃣ Union-Find 구성

- index 배열로 부모 관리
- check() 함수에서 경로 압축 사용

### 2️⃣ City 클래스

각 집합의 대표 노드가 다음 정보를 관리한다.

- count : 현재 집합의 도시 개수
- tank : 총 물탱크 용량
- rain : 총 강수량

### 3️⃣ merge 로직 핵심

합치기 전
- 기존 두 집합이 침수 상태라면 output에서 각각 제거

합친 후
- 새 집합이 침수 상태라면 output에 전체 도시 수 추가

---

## 📘 배운 점

처음에는 Union-Find를 몰라서 어떻게 집합을 관리해야 할지 감이 오지 않았다.  
정원영이 Union-Find 자료구조를 알려줘서 개념을 배우고 적용할 수 있었다.  

덕분에 도시 합병 문제를 효율적으로 해결할 수 있었고,  
집합을 다루는 문제에서 Union-Find가 얼마나 강력한지 이해하게 되었다.

---

## 🚀 최종 코드

```java
package week2.B25587;

import java.util.*;
import java.io.*;

public class Main {
	
	static int output;
	static int[] index;

	public static void main(String[] args) throws IOException {
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
	
	public static int check(int a) {
		if (a == index[a]) return a;
		index[a] = check(index[a]);
		return index[a];
	}
	
	public static class City {
		int count, num, tank, rain;
		
		public City(int num, int tank, int rain) {
			this.count = 1;
			this.num = num;
			this.tank = tank;
			this.rain = rain;
			if (flood()) output += count;
		}
		
		public boolean flood() {
			return (rain > tank);
		}
 		
		public void merge(City c) {
			if (this.flood()) output -= this.count;
			if (c.flood()) output -= c.count;
			index[c.num] = this.num;
			
			this.count += c.count;
			this.tank += c.tank;
			this.rain += c.rain;
			if (this.flood()) output += this.count;
		}
	}
}
```
