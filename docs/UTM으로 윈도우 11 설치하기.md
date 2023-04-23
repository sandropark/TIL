1. UTM 설치 - Homebrew
2. arm용 windows 11 다운로드
   - [링크](https://www.microsoft.com/en-us/software-download/windowsinsiderpreviewarm64)
    - dev 버전으로 설치한다.
3. UTM 인스턴스 생성
   1. 가상환경
   
        <img width="250" alt="image" src="https://user-images.githubusercontent.com/89520805/233822733-7920a883-579b-4650-9c7b-74e4fad076a8.png">
    2. 다운 받은 윈도우 파일 선택

        <img width="250" alt="image" src="https://user-images.githubusercontent.com/89520805/233823079-59ab8e8d-3c9e-40a7-a128-88f9563ae022.png">
    3. 설치를 진행하면 된다.
4. windows 설치
   - 네트워크 설정 창에서 네트워크 없이 설치하기 위해서 설정을 해야 한다.  
     - `fn + shift + F10` 으로 터미널을 띄우고 `taskmgr` 명령어로 작업관리자를 띄운다. 
     - 프로세스 창에 있는 파일 탐색기를 우클릭해서 `open file location`을 클릭, 윈도우 창이 뜨면 D드라이브에 들어가서 `spice-guest-tools-0.164` 를 실행한다.
     - 설치 완료 후 재부팅하면 네트워크 설정 창을 건너뛰면서 윈도우 설치가 가능해진다.