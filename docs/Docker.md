웹이 발전하면서 웹 서버의 트래픽이 늘어나게 되었다. 트래픽을 감당하기 위해서는 서버를 증설해야 하는데 서버를 늘리는 작업은 꽤나 품이 많이든다. 그래서 Docker처럼 Container를 사용해서 필요한 환경을 한 번 세팅해두고 재사용할 수 있는 기술이 등장했다. 

# 명령어

- 실행중인 컨테이너 확인
`docker ps`

- image 확인하기
`docker images`

- image 삭제하기
`docker rmi [이미지id]`

## Docke로 Mysql 설치하기

`docker pull mysql:oracle`