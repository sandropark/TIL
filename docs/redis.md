# Redis

Remote dictionary server의 줄임말이다. In Memory DB이기 때문에 **메모리 상**에 데이터를 저장한다. Single Thread를 사용한다.

자주 접근하고 자주 바뀌지 않는 데이터를 메모리에 올려두고 데이터에 접근하는 시간을 줄이고자 사용한다.

Redis는 여러 자료구조를 지원한다. 

### In Memory라면 그냥 Java의 Map(자료구조)를 사용해서 관리해도 되는데 왜 Redis를 사용할까?

서버를 여러 대 사용할 경우 서버마다 별도의 메모리를 사용하기 때문에 효율이 떨어지게 된다. Redis는 동기화를 제공한다.

### 왜 사용할까? 

세션처럼 여러 서버에서 공유하는 데이터의 경우 Redis를 사용하면 효율적이다. 

### 주의점

- Single Thread 서버이기 때문에 **시간 복잡도**를 고려해야 한다.
  - O(n)의 명령어의 경우 사용하지 않는 것이 좋다.

- In Memory DB이기 때문에 가상메모리 같은 OS 지식이 필요하다.

## 설치

Homebrew를 이용해서 설치하면 된다.

## 실행

`brew services start redis`
