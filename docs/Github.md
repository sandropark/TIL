# Github

## GitHub SSH key 생성하기

1. 터미널 접속

2. ``ssh-keygen`` 입력

3. 별도의 경로와 파일명을 지정하지 않고 넘어가기. 

4. 완료되었다면 ``cat ~/.ssh/id_rsa.pub`` 입력해서 ssh key 복사하기

5. Github에 ssh key 등록하기

    설정 -> ssh 항목 -> new ssh key -> ssh key 붙여넣기


## GitHub Submodule을 포함하는 저장소 clone하기

Submodule을 포함하는 저장소를 그냥 clone하면 submodule은 clone되지 않는다. 이때는 아래처럼 `--recursive` 옵션을 넣어서 clone하면 저장소에 포함된 모든 submodule을 클론한다.

```bash
git clone --recursive https://github.com/username/repo.git
```