## 📝 문제 요약
### 배수로
N개의 도시가 있을때 각 i번 도시의 배수로 용량은 Ai, 강수량은 Bi 이다.
이때 배수로를 공사하여 도시들간 강수량의 합과 홍수의 합을 더할 수도 있다.
(Bi 합이 Ai 합을 넘지 않으면 모든 도시에서 홍수가 나지 않는다)

## 💡 접근 방법

- **알고리즘/자료구조:** `UnionFind`
    
- **핵심 아이디어:**
도시 배수로 공사 -> union()
### union()
1. x와 y의 부모 값 찾기
2. 부모 x,y의 홍수 count 초기화
3. 작은 값으로 몰기 , 홍수 count 증가
---

## ✅ 풀이 과정

⚠️ 핵심 문제

도시가 연결되면:

두 집합이 하나로 합쳐짐

집합 전체의 배수로 용량 = A 합

집합 전체의 강수량 = B 합

집합 단위로 홍수 여부 판단

즉, 도시 개별 판단이 아니라 집합 단위 판단이 필요함.

2. 해결 전략 수립
🔥 핵심 아이디어: Union-Find 활용

각 집합에 대해 다음 정보를 유지:

size[] → 집합에 속한 도시 수

A[] → 집합의 배수로 용량 총합

B[] → 집합의 강수량 총합

🧠 홍수 도시 수 관리 전략

홍수 조건:

A[대표노드] < B[대표노드]


union 시 처리 순서:

기존 두 집합이 홍수 상태였다면 → count에서 제거

두 집합 merge

새 집합이 홍수 상태이면 → count에 추가

### Union-Find 알고리즘

- 그래프/트리의 대표적 알고리즘
- 서로소인 부분집합의 표현
- 여러 노드가 있을때, 두 노드가 같은 그래프에 속해있는지 알 수 있음
- `union(x,y)` : x와 y그래프를 합친다.
- `find(x)`: x가 어느 그래프에 속하는지 연산한다.

예제
``` java
public class Main {
static int[] parent;

	// union 연산
	public static boolean union(int x, int y) {
		// 부모 노드 찾기
		x = find(x); 
		y = find(y);

		// 이미 같은 그래프에 속해있을때 false 반환
		if(x == y) return false;

		if(x <= y) parent[y] = x;
		else parent[x] = y;
		return true;
	}

    // find 연산
	public static int find(int x) {
		// 마지막 노드인지(여기서는 조건이 x랑 같은지) 확인하면 마지막 노드 리턴
		if(parent[x] == x) return x; 
		// 아니면 부모 노드 찾아가기
		return find(parent[x]);
	}

     // parent 출력
	public static void parentPrint() {
		System.out.print("[ ");
		for (int i = 1; i < parent.length; i++) {
			System.out.print(parent[i]+ " ");
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		int n = 5;
		parent = new int[n + 1];
    // 부모 노드 초기화
		for (int i = 0; i < parent.length; i++) parent[i] = i;

    //위의 예제 실행
		union(1, 2);
		parentPrint();
		union(3, 4);
		parentPrint();
		union(3, 5);
		parentPrint();
		System.out.println("find(2): " + find(2));
		System.out.println("find(4): " + find(4));
		union(2, 4);
		parentPrint();
		System.out.println("find(4): " + find(4));
		
		/**
			출력 결과
			[ 1 1 3 4 5 ]
			[ 1 1 3 3 5 ]
			[ 1 1 3 3 3 ]
			find(4): 3
			[ 1 1 1 3 3 ]
			find(4): 1
		*/
	}
}
```