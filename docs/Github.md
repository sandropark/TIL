# GitHub SSH key 생성하기

1. 터미널 접속

2. ``ssh-keygen`` 입력

3. 별도의 경로와 파일명을 지정하지 않고 넘어가기. 

4. 완료되었다면 ``cat ~/.ssh/id_rsa.pub`` 입력해서 ssh key 복사하기

5. Github에 ssh key 등록하기

    설정 -> ssh 항목 -> new ssh key -> ssh key 붙여넣기


# GitHub Submodule을 포함하는 저장소 clone하기

Submodule을 포함하는 저장소를 그냥 clone하면 submodule은 clone되지 않는다. 이때는 아래처럼 `--recursive` 옵션을 넣어서 clone하면 저장소에 포함된 모든 submodule을 클론한다.

```bash
git clone --recursive https://github.com/username/repo.git
```

# 한 컴퓨터에서 Github 계정 여러개 사용하기

입사하니 회사 메일로 Github 계정 생성해서 개인 계정과 함께 2개의 계정을 사용하게 되었다. 저장소마다 다른 계정을 사용하기 위해서 설정했다.  

## 1. 계정마다 ssh-key 생성 및 Github에 등록
   먼저 계정마다 ssh-key를 생성한다. 
   ```shell
   $ ssh-keygen -t rsa -C "[email]" -f "[filename]"
   
   $ ssh-keygen -t rsa -C personal_email@naver.com -f personal  # 개인 계정
   $ ssh-keygen -t rsa -C company_email@naver.com -f company    # 회사 계정
   ```
   `~/.ssh/`에 `personal personal.pub company company.pub` 가 생성된 것을 확인할 수 있다. 
   
   Github에서 각 계정으로 로그인하고 key를 등록한다.    

## 2. ~/.ssh/config 설정 

   ssh key가 2개이기 때문에 쉽게 관리하기 위해서 config 파일을 생성해서 설정을 해둔다.
   
   ```
   Host github.com-personal
       HostName github.com
       IdentityFile ~/.ssh/personal

   Host github.com-company
      HostName github.com
      IdentityFile ~/.ssh/company
   ```
   
   - `Host` : 각 key를 식별하기 위한 이름이다.
   - `HostName` : 실제 host로 매핑된다. 
   - `IdentityFile` : key의 경로를 지정한다.

   실제로 어떻게 사용되는지는 뒤에서 살펴본다.

## 3. 전역 설정된 사용자 계정 지우기
   
   만약 하나의 계정만 사용했다면 전역 설정으로 user.name과 user.email을 설정했을 것이다.
   
   이제는 디렉토리 별로 다른 계정으로 설정할 것이기 전역 설정된 이름과 이메일은 삭제한다.

   ```bash
   $ git config --global --unset user.name
   $ git config --global --unset user.email
   ```
   
   추가로 전역 설정된 이름과 이메일이 없기 때문에 혹시 커밋시 임의의 데이터가 정보가 커밋될 수 있기 때문에 

   이를 방지하기 위해서 사용자의 이름과 메일이 없을 때는 커밋을 방지하는 설정을 추가한다.  

   ```bash
   $ git config --global user.useConfigOnly true
   ```
   
## 4. 디렉토리 별로 계정 다르게 설정 `.gitconfig`
   이제 개인과 회사 디렉토리를 생성해서 각 디렉토리 내에서는 다른 계정을 사용하도록 설정한다.
   
   원하는 위치에 개인 디렉토리와 회사 디렉토리를 생성한다. 

   ```bash
   $ mkdir ~/personal
   $ mkdir ~/company
   ```
   
   home 디렉토리에 `.gitconfig` 파일을 생성한다.
 
   ```
   # ~/.gitconfig
   
   [user]
       useConfigOnly = true    # username과 email이 없으면 commit이 안되는 설정

   [includeIf "gitdir:~/personal/"]
       path = ~/personal/.gitconfig
   
   [includeIf "gitdir:~/company/"]
       path = ~/company/.gitconfig
   ```
   
   personal 디렉토리와 company 디렉토리 하위에서는 각 디렉토리에 들어있는 `.gitconfig` 파일을 사용하겠다는 설정이다. 

   이제 각 디렉토리 하위에 `.gitconfig`를 생성한다. 

   ```
   # ~/personal/.gitconfig
   
   [user]
      name = [개인 계정 username]
      email = [개인 계정 email]
   
   # ~/company/.gitconfig
   
   [user]
      name = [회사 계정 username]
      email = [회사 계정 email]
   ```

## 5. 설정 잘 됐는지 확인하기.
   
   설정이 잘 됐는지 확인하기 위해서 원격 저장소를 clone 해본다. 
   
   ```bash
   $ cd ~/personal
   
   $ git clone git@github.com:[개인 계정 username]/[저장소 이름].git 
   ```

   평소에 하던대로 clone하면 에러가 난다. ssh key가 2개인데 clone 시 개인 계정 key를 지정해주지 않았기 때문이다. 

   ```bash
   $ git clone git@github.com-personal:[개인 계정 username]/[저장소 이름].git 
   ```
   
   이렇게 host 명을 수정해야 한다. 위에서 `~/.ssh/config`에 `Host github.com-personal`로 설정했기 때문에 

   이렇게 해야 개인 ssh key로 인증을 거쳐서 clone이 된다.
   
   이제 개인과 회사 계정을 편하게 사용할 수 있다. 