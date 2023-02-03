
## 문제 상황

코드 수정 사항을 반영하기 위해서 push를 하니 실패하면서 다음과 같은 에러를 뱉었다. 

```
ssh: connect to host github.com port 22: Operation timed out
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
```

## 원인

연결된 네트워크에 따라서 방화벽이 SSH 연결을 차단할 수 있다. 내 경우 도서관 와이파이였다. 


## 해결

~/.ssh/config 에 아래 내용을 추가하면 된다. 

```
Host github.com
    Hostname ssh.github.com
    Port 443
```
