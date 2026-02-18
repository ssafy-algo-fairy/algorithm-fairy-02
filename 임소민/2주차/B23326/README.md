# BOJ 23326 홍익 투어리스트 풀이 요약

## 문제 요약

N개의 구역이 원형으로 배치되어 있고, 일부 구역은 명소이다.  
현재 위치에서 시계 방향으로 이동하며, 가장 가까운 명소까지의 거리를 구하는 쿼리를 처리한다.

---

## 풀이 전략

### 자료 구조

```java
TreeSet<Integer> treeSet
```

- 명소인 구역의 번호만 저장

- 자동으로 정렬 유지

- 다음 명소를 찾는 연산을 빠르게 수행 가능

### 1번 쿼리: 명소 토글

```java
if (treeSet.contains(i)) treeSet.remove(i);
else treeSet.add(i);
```

- 명소 O → 명소 X : `treeSet`에서 `remove`
- 명소 X → 명소 O : `treeSet`에 `add`

### 2번 쿼리: 현재 위치 관리 & 원형 이동

```java
cur = (cur - 1 + x) % N + 1; // 범위: 1~N
```

### 3번 쿼리: 가장 가까운 명소 찾기

```java
Integer next = treeSet.ceiling(cur);
```

- `ceiling(cur)`
  → cur 이상 중 가장 작은 명소 위치

---

## 풀이 중 이슈

### 1. 연결 리스트 풀이 → 시간 초과

수업시간에 배운 `Node class` 써서 해보려 했는데 그냥 복습한 사람 됨...

**문제점**

- 3번 쿼리마다
  - 최악의 경우 N개의 노드를 순회

- 2번 쿼리에서도
  - x번 반복 이동

N ≤ 500,000,
Q ≤ 100,000 이므로

👉 최악의 경우 `O(N × Q)`이 되어 시간 초과가 발생한다.

### 2. ceiling 사용 시 null 처리

`TreeSet.ceiling(x)`는 값이 없으면 null을 반환한다.

따라서 시계 방향에 명소가 없는 경우를 처리하지 않으면
`NullPointerException`이 발생할 수 있어 반드시 `null` 체크가 필요하다.

```java
Integer next = treeSet.ceiling(cur); // ※ int는 null 불가. 기본값 0

if (next == null) { // 시계방향으로 더 이상 명소 없을 때
    next = treeSet.ceiling(1);
    answer = (N - cur) + next;
} else {
    answer = next - cur;
}
```
