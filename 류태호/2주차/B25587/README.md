### 📌 문제 정보

* **번호:** 25587
* **제목:** 배수로
* **난이도:** Gold 5
* **분류:** 자료 구조, 분리 집합 (Union-Find)

### 💡 접근 방식

> 두 도시가 연결될 때 전체 배수 용량과 강수량의 합을 비교해야 하므로, 대표에게 모든 데이터를 모아 관리하는 방식을 사용했습니다.

* **실시간 홍수 체크:** `totalFlood` 변수를 두고, 공사를 진행할 때마다 변동 사항만 갱신하여 결과를 출력했습니다.
* **데이터 구조:** `cities[N][2]` 2차원 배열을 활용하여 0번 인덱스에는 부모 정보를, 1번 인덱스에는 해당 집합의 도시 개수를 저장했습니다.

### 💻 핵심 코드

```java
static void construction(int city1, int city2) {
    int root1 = find(city1);
    int root2 = find(city2);

    if (root1 != root2) {
        // 합치기 전, 기존 상태 확인하여 제외
        if (rainFall[root1] > gutter[root1]) totalFlood -= cities[root1][1];
        if (rainFall[root2] > gutter[root2]) totalFlood -= cities[root2][1];

        // 합치기
        cities[root2][0] = root1;
        cities[root1][1] += cities[root2][1];
        gutter[root1] += gutter[root2];
        rainFall[root1] += rainFall[root2];

        // 합쳐진 후 재판단
        if (rainFall[root1] > gutter[root1]) totalFlood += cities[root1][1];
    }
}

```

### ⏳ 복잡도 계산
**시간 복잡도: O(M)**
* **경로 압축**을 통해 합치기 연산을 상수 시간(O(1))에 가깝게 처리하고, 홍수 카운트를 루프 없이 실시간 변수(`totalFlood`)로 관리하여 최적화했습니다.

**공간 복잡도: O(N)**
* 도시 정보를 저장하는 배열들(`gutter`, `rainFall`, `cities`)이 모두 도시 수 에 비례하는 크기만 가집니다.


### ⚠️ 고민 지점

* 초기에는 2번 쿼리가 들어올 때마다 모든 도시를 직접 순회하며 홍수 여부를 확인하도록 구현했으나 시간 초과가 났습니다. 이를 해결하기 위해 공사할 때 미리 결과를 업데이트하도록 로직을 전환했습니다.
* 유니온 파인드를 오랜만에 접하다 보니 합치는 것 외에 계산을 누적하고 관리하는 로직을 떠올리는 데 시간이 조금 걸렸습니다.
---