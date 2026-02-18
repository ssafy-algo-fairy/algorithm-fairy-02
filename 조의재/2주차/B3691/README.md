# [BOJ] 3691 - Computer Components

### 📌 문제 정보
* **문제 링크**: [백준 3691번](https://www.acmicpc.net/problem/3691)
* **난이도**: Gold 3
* **분류**: 이분 탐색(Binary Search), 매개 변수 탐색(Parametric Search), 자료 구조(TreeMap)

---

### 💡 풀이 전략
이 문제는 주어진 예산 내에서 여러 종류의 부품을 하나씩 골라 컴퓨터를 조립할 때, **가장 낮은 성능을 가진 부품의 성능(최소 성능)을 최대화**하는 전형적인 **최소의 최대화** 문제입니다.

1. **매개 변수 탐색(Parametric Search)**: '성능 $X$ 이상으로 컴퓨터를 조립할 수 있는가?'라는 결정 문제로 변환하여 이분 탐색을 수행합니다. 성능의 범위가 크므로 $O(\log(\text{max\_quality}))$의 탐색이 필요합니다.
2. **자료구조 최적화 (TreeMap)**: 각 부품 종류별로 성능과 가격 정보를 저장할 때 `TreeMap`을 활용합니다.
    * **Pruning(가지치기)**: 성능은 더 낮은데 가격이 더 비싼 '가성비가 떨어지는' 부품은 입력 단계에서 미리 제거하여 탐색 효율을 높입니다.
    * **Efficient Search**: 특정 성능 $X$ 이상인 부품 중 가장 저렴한 것을 찾을 때 `ceilingEntry(X)`를 사용하여 $O(\log N)$만에 조회합니다.
3. **오버플로우 방지**: 부품 성능의 합을 계산할 때 `int` 범위를 초과할 수 있으므로 `totalPrice`를 `long`으로 관리하고, 이분 탐색 시 $mid$ 계산에 주의합니다.

---

### 💡 핵심 풀이 로직

이 문제의 핵심은 **결정 함수(check)**를 얼마나 빠르게 수행하느냐와 **불필요한 부품 데이터를 사전에 필터링**하는 데 있습니다.

#### 1. 효율적인 데이터 전처리 (TreeMap Pruning)
부품을 추가할 때, 현재 부품보다 성능이 좋으면서 가격이 싸거나 같은 게 이미 있다면 무시하고, 반대로 현재 부품보다 성능은 나쁜데 가격이 비싼 것들은 삭제하여 최적의 부품 리스트만 유지합니다.

```java
TreeMap<Integer, Integer> tm = part.computeIfAbsent(type, k -> new TreeMap<>());

// 1. 더 좋은 성능에 가격까지 싼 부품이 이미 있다면 현재 부품 무시
Map.Entry<Integer, Integer> higherEntry = tm.ceilingEntry(quality);
if(higherEntry != null && higherEntry.getValue() <= price) continue; 

tm.put(quality, price);

// 2. 현재 부품보다 성능은 낮은데 가격은 더 비싼 부품들은 모두 제거
while(true) {
    Map.Entry<Integer, Integer> lowerEntry = tm.lowerEntry(quality);
    if(lowerEntry != null && lowerEntry.getValue() >= price) {
        tm.remove(lowerEntry.getKey());
    } else {
        break;
    }
}

2. 결정 함수 (Check Function)
설정한 성능 targetQual을 만족하는 부품들 중 가장 저렴한 조합의 합이 예산 b 이내인지 확인합니다.
```
static boolean check(int targetQual) {
    long totalPrice = 0; // 합산 시 오버플로우 방지

    for(TreeMap<Integer, Integer> tm : part.values()) {
        // targetQual 이상의 성능 중 가장 낮은 성능(즉, 가장 싼 가격)의 엔트리 조회
        Map.Entry<Integer, Integer> entry = tm.ceilingEntry(targetQual);
        if(entry == null) return false; // 만족하는 부품이 해당 종류에 없음

        totalPrice += entry.getValue();
        if(totalPrice > b) return false; // 중간에 예산 초과 시 즉시 탈출
    }
    return true;
}
```

3. 이분 탐색 (Binary Search)
중간값 mid를 구할 때 오버플로우를 방지하는 식을 사용하여 안전하게 탐색합니다.
```
// (left + right) >>> 1 방식도 오버플로우에 안전함
int mid = left + (right - left) / 2; 

if(check(mid)) {
    ans = mid;    // 가능하면 성능을 더 높여봄
    left = mid + 1;
} else {
    right = mid - 1;
}
```

✅ 복기 및 핵심 포인트
TreeMap의 활용: ceilingEntry를 통해 "특정 값 이상의 최솟값"을 찾는 로직을 $O(\log N)$으로 해결했습니다.

데이터 정제: 입력 단계에서 유효하지 않은(가성비 낮은) 데이터를 쳐냄으로써 check 함수가 순회해야 할 데이터 양을 대폭 줄였습니다.

타입 안정성: 여러 종류의 부품 합계를 구할 때 long을 사용하여 오버플로우를 방어했고, 매 테스트 케이스마다 HashMap을 초기화하여 독립성을 보장했습니다.