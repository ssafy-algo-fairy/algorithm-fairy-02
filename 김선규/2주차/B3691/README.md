# 🧰 BOJ 3691 - 컴퓨터 조립

- 🔗 문제 링크: https://www.acmicpc.net/problem/3691
- 🏷️ 분류: 이분 탐색(Parametric Search), 그리디(최소 비용 선택), HashMap(타입 압축)

---

## 📌 문제 요약
여러 종류(type)의 부품이 주어진다. 각 부품은

- `type` (부품 종류)
- `price` (가격)
- `quality` (성능)

을 가진다.

각 type에서 **부품을 정확히 1개씩** 선택해서 컴퓨터를 구성해야 하며,
총 가격이 예산 `b` 이하여야 한다.

이때 선택한 부품들 중 **최소 quality(= 병목 성능)** 를 최대화하는 값을 출력한다.

---

## ❗ 시행착오 (시간 초과)
처음에는 "부품을 하나씩 골라가며 조합을 만들어보기" 방식으로 접근했는데,
가능한 조합 수가 너무 많아서 **시간 초과**가 발생했다.

➡️ 해결은 “정답(최대 병목 quality)”을 직접 조합으로 찾지 않고,
**quality 기준으로 이분 탐색(Parametric Search)** 을 하는 방식으로 바꾸는 것이었다.

---

## 💡 풀이 아이디어 (Quality로 이분 탐색)
어떤 quality 값 `Q`가 주어졌다고 하자.

> “모든 type에서 quality ≥ Q 인 부품을 하나씩 고를 수 있고,  
> 그때의 최소 총 비용이 예산 b 이하인가?”

이 판별이 가능하면,
- 가능(Q를 만족 가능) → 더 큰 Q를 시도
- 불가능 → Q를 낮춤

즉, **quality를 기준으로 가능한 최대 값을 이분 탐색**한다.

---

## ✅ 판별 함수 설계: `getPrice(Q)`
`getPrice(Q)`는 다음을 계산한다.

- 각 type마다 `quality >= Q` 를 만족하는 부품들 중 **최소 price**를 선택
- 그 최소 price들을 모두 더한 값이 **Q를 달성하기 위한 최소 비용**

판별 결과:
- 어떤 type에서라도 `quality >= Q` 인 부품이 하나도 없으면 → 실패
- 총합이 `b`를 넘으면 → 실패
- 그 외 → 성공

이 로직은 각 type에서 “최소 비용 1개”만 고르면 되므로 그리디하게 처리된다.

---

## 🗂️ 타입 처리 (HashMap으로 압축)
입력에서 type은 문자열이므로, 배열로 다루기 위해 정수 ID로 변환한다.

- `typeIdx: HashMap<String, Integer>`
- 처음 나온 type이면 새 ID 부여
- 이후부터는 같은 ID 재사용

이를 통해
- `minPrice[typeId]` 형태로 type별 최소 비용을 쉽게 관리한다.

---

## 🔁 전체 알고리즘 흐름
테스트케이스마다:

1. 입력을 읽으며 type을 ID로 변환해 `partList`에 저장  
   - `partList[i] = {typeId, price, quality}`

2. `quality`의 답 범위를 잡고 이분 탐색
   - `left = 0`, `right = 1_000_000_000` (코드 기준)

3. `mid`에 대해 `getPrice(mid)`로 가능 여부 판별
   - `getPrice(mid) <= b` 이면 가능 → `maxQuality = mid`, `left = mid + 1`
   - 불가능이면 `right = mid - 1`

4. 최종 `maxQuality` 출력

---

## ⏱️ 복잡도
- 판별 함수 `getPrice(Q)`:
  - 모든 부품을 한 번 순회하며 type별 최소 가격 갱신 → `O(n)`
  - type 개수만큼 합산 → `O(typeCnt)`
  - 합쳐서 대략 `O(n)`

- 이분 탐색:
  - `log2(1e9)` ≈ 30회

따라서 전체:
- 시간 복잡도: `O(n log 1e9)` ≈ `O(30n)`
- 공간 복잡도: `O(n + typeCnt)`

---

## 📝 구현 포인트(코드 기준)
- `getPrice(Q)`에서 특정 type의 후보가 없으면 즉시 실패 처리:
  - `return b + 1`로 예산 초과처럼 만들어 이분 탐색에서 “불가능”으로 처리
- 비용 합은 커질 수 있어 `long sum` 사용
- 테스트케이스마다 `typeIdx.clear()`로 타입 매핑 초기화

---
