# 🌧️ BOJ 25587 - 배수로 공사

- 🔗 문제 링크: https://www.acmicpc.net/problem/25587
- 🏷️ 분류: Union-Find(DSU), 집합 정보 유지(Union with Data)

---

## 📌 문제 요약
N개의 도시가 있고 각 도시는

- 배수로 용량 `capacity[i]`
- 강수량 `rain[i]`

을 가진다.

도시들은 공사(연결)로 묶일 수 있으며, 연결된 도시들은 하나의 “그룹”처럼 동작한다.  
각 그룹에 대해

- 그룹의 총 용량 = 그룹 내 capacity 합
- 그룹의 총 강수량 = 그룹 내 rain 합

이고, **총 강수량이 총 용량보다 크면 그룹 내 모든 도시가 홍수**가 난다.

쿼리:
- `1 x y` : x번 도시와 y번 도시에 공사를 해서 두 그룹을 연결
- `2` : 현재 상태에서 홍수 날 도시의 수 출력

---

## 💡 풀이 아이디어 (Union-Find + 그룹 데이터 유지)
도시 연결/합치기가 계속 발생하므로, 그룹 관리는 **Union-Find(DSU)** 가 적합하다.

각 DSU 루트(대표 도시)에 아래 정보를 저장한다.

- `capacity` : 그룹 총 용량
- `rain` : 그룹 총 강수량
- `cityNum` : 그룹에 속한 도시 수
- `floodNum` : 그룹에서 홍수 나는 도시 수 (0 또는 cityNum)
- `parent` : DSU 부모

그리고 전체 홍수 도시 수 `floodCnt`를 전역으로 유지하면서,
union이 발생할 때마다 **두 그룹의 floodNum을 빼고, 합친 뒤 새 floodNum을 더하는 방식**으로 갱신한다.

---

## ✅ 그룹(루트) 정보 정의
코드의 `City` 클래스(루트에서 의미 있음):

- `capacity` : 그룹 용량 합
- `rain` : 그룹 강수량 합
- `cityNum` : 그룹 크기
- `floodNum` : 홍수 도시 수
  - `capacity >= rain` 이면 `0`
  - 아니면 `cityNum` (그룹 전부 홍수)
- `parent` : DSU 부모 인덱스

초기화 시 각 도시는 자기 자신이 루트이며,
개별 도시가 홍수인지 여부로 `floodCnt`를 초기 세팅한다.

---

## 🔁 Union 처리 로직
### 1) 루트 찾기
`find(x)`로 각 도시의 대표(루트) `p1`, `p2`를 찾는다.  
경로 압축을 사용해 이후 탐색을 빠르게 한다.

### 2) 이미 같은 그룹이면 종료 (중요)
`p1 == p2`면 이미 연결된 도시이므로 union을 하면 안 된다.

- ✅ 이 케이스를 고려하지 않아서 처음에 틀렸던 시행착오가 있었다.
- 이미 연결된 도시 쿼리가 들어오면 **아무 변화가 없어야** 한다.
- 코드에서는 아래처럼 방어한다.

- `if (p1Idx == p2Idx) return;`

### 3) 두 그룹 합치기 + floodCnt 갱신
- 합치기 전:
  - `floodCnt -= (p1.floodNum + p2.floodNum)`
- 그룹 정보 합치기:
  - `p1.capacity += p2.capacity`
  - `p1.rain += p2.rain`
  - `p1.cityNum += p2.cityNum`
  - `p2.parent = p1Idx`
- 합친 뒤 floodNum 재계산:
  - `p1.floodNum = (p1.capacity >= p1.rain) ? 0 : p1.cityNum`
- 반영:
  - `floodCnt += p1.floodNum`

---

## 🖨️ 쿼리 2 처리
전역으로 관리 중인 `floodCnt`를 그대로 출력한다.

- union 때마다 정확히 갱신해두기 때문에 `O(1)`로 응답 가능

---

## ⏱️ 복잡도
- `find/union` : 거의 상수에 가까운 `α(N)` (경로 압축)
- 쿼리 처리:
  - `1 x y` : `O(α(N))`
  - `2` : `O(1)`

- 시간 복잡도: `O((N + M) α(N))`
- 공간 복잡도: `O(N)`

---

## 📝 구현 포인트(코드 기준)
- DSU 루트에만 “그룹 합(capacity/rain/cityNum/floodNum)”이 의미 있게 유지된다.
- `floodCnt`를 전역으로 관리하면 쿼리 2를 빠르게 처리할 수 있다.
- **이미 연결된 도시 쿼리(같은 루트)** 를 반드시 처리해야 한다.  
  (이 부분을 놓쳐서 한 번 틀렸던 것이 핵심 시행착오)

---
