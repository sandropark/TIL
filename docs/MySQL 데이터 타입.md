- utf8의 경우 한글 한 글짜는 3byte다.

### varchar 와 char의 차이

- char : **고정형**으로 최대 255바이트까지 지정 가능. 고정형이기 때문에 지정된 크기 만큼 메모리를 차지한다. 검색 및 읽기 속도가 varchar에 비해서 빠르다. 데이터가 가변적일 경우 공간 효율이 안 좋을 수 있다.
- varchar : **가변형**으로 varchar(255)로 지정해도 알파벳 한 자(1byte)를 넣으면 1byte만 차지한다. 데이터가 가변적이라면 varchar가 char 보다 공간 효율이 좋다. 

### datetime()

datetime은 괄호안에 숫자를 적으면 마이크로초를 지정할 수 있다. datetime(6)의 경우 마이크로초 6자리까지 표기한다는 뜻이다.