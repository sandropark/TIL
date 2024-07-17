# <테스트 주도 개발> - 켄트 백

## Money 예제

### TODO
* [x] $5 + 10CHF = $10(환율이 2:1인 경우)
* [x] $5 + $5 = $10
* [x] $5 + $5에서 Money 반환하기
* [x] Bank.reduce(Money)
* [x] Money에 대한 통화 변환을 수행하는 Reduce
* [x] Reduce(Bank, String)
* [x] Sum.plus
* [x] Expression.times
* [x] $5 x 2 = $10
* [x] amount를 private로 만들기
* [x] Dollar 부작용(side effect?)
* [ ] Money 반올림?
* [x] equals()
* [ ] hashCode()
* [ ] Equal null
* [ ] Equal object
* [x] 5CHF x 2 = 10 CHF
* [x] Dollar/Franc 중복
* [x] 공용 equals
* [x] 공용 times
* [x] Franc와 Dollar 비교하기
* [x] 통화?
* [x] testFrancMultiplication 제거

## 삼각형 예제

- 정삼각형이면 1
- 이등변삼각형이면 2
- 부등변삼각형이면 3
- 삼각형이 아니면 예외를 던진다.

### TODO 
- ~~세 가지 숫자를 받아서 삼각형인지 확인한다.~~
  - ~~숫자는 0보다 커야한다.~~
  - ~~한 변의 길이는 나머지 두 변의 길이의 합보다 작아야 한다.~~
- ~~세 숫자가 모두 같다면 1을 반환한다.~~
- ~~두 숫자가 같다면 2를 반환한다.~~
- ~~세 숫자 모두 다르다면 3을 반환한다.~~