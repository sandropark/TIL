# 클린 코더스 TDD

- [클린 코더스 강의 7. TDD 1](https://youtu.be/wmHV6L0e1sU?list=PLeQ0NTYUDTmMM71Jn1scbEYdLFHz5ZqFA)

## 볼링 게임

- [클린 코더스 강의 7. TDD 3 - bowling game](https://youtu.be/fFwDMzML7hI?list=PLeQ0NTYUDTmMM71Jn1scbEYdLFHz5ZqFA)

### 룰
- 한 게임은 10개의 프레임이다.
- 각 프레임은 보통 2개의 롤을 갖는다. 즉, 공을 2번 굴릴 수 있다.
- Strike : 첫 번째 롤에서 핀 10개를 쓰러트린 경우. 다음 두 번의 roll의 점수를 추가로 얻는다. 
- Spare : 두 번째 롤에서 핀 10개를 쓰러트린 경우. 다음 1번의 roll의 점수를 추가로 얻는다.
- 마지막 프레임에서는 Strike인 경우 2번, Spare인 경우 1번의 추가 roll을 얻는다.
- 퍼펙트 게임(전부 스트라이크)은 총 12롤을 갖는다.

### 느낀점

스페어 테스트 케이스를 추가한 다음 리팩토링하는 과정이 매우 흥미로웠다.

`roll()`은 넘어뜨린 핀의 갯수만 기록하고 점수는 `score()`에서 계산해야 한다. 
`roll()`에 점수를 계산하는 로직이 추가되려고 하니까 
메서드 명과 실제 메서드의 동작에 괴리가 생기기 시작하는 것을 파악하고 구조를 변경했다.

이런식으로 아주 간단한 기능부터 구현하면서 구조를 잡아가는 방법을 많이 훈련해야겠다.


## Prime factor and wordwrap

- [클린 코더스 강의 9. TDD 4 - primefactors and wordwrap](https://youtu.be/X4JtF2BfA0U?list=PLeQ0NTYUDTmMM71Jn1scbEYdLFHz5ZqFA)

## name inverter

- [클린 코더스 강의 16.1 advanced tdd 1 - name inverter](https://youtu.be/czjWpmy3rkM?list=PLeQ0NTYUDTmMM71Jn1scbEYdLFHz5ZqFA)

first name과 last name을 입력받아 뒤집어서 반환하는 메서드를 구현한다.

