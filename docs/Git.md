# Git ssh 접속 실패

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

# Stash

tracked 파일을 잠깐 빼두고 브랜치를 생성하고 다시 불러올 수 있다. 

# 브랜치 통합하기

## Merge - fast-forward

`dev` 브랜치에서 분기한 `feature/baby` 브랜치에서 작업 후 dev 브랜치에 병합을 할 때 fast-forward merge를 사용하는 경우 baby 브랜치에서 작업한 내용이 dev 브랜치에 완전히 흡수돼서 이력에는 baby 브랜치가 없었던 것처럼 보인다. 말 그대로 baby 브랜치가 먼저 앞서갔고 dev 브랜치는 baby 브랜치를 fast-forward한 것이다. 

fast-forward 옵션을 끄면 baby 브랜치가 dev 브랜치에 병합되는 merge commit이 생기게 된다. 

## Rebase

위와 마찬가지로 dev 브랜치 a 커밋에서 분기한 baby 브랜치가 있다. baby 브랜치에서 기능 개발을 하던 중 다른 팀원이 기능 개발을 마치고 dev 브랜치에 b 커밋으로 merge했다. 이때 baby 브랜치에 dev 브랜치의 b 커밋을 반영하기 위해서 rebase를 사용할 수 있다. a 커밋과 a 커밋 이후 baby 브랜치에 추가된 커밋 사이에 b 커밋이 추가된다. 마치 b 커밋이후 baby 브랜치가 분기한 것 같은 효과가 난다.

- 충돌
    만약 rebase 시 충돌이 나서 충돌을 해결하고 나면 `commit`을 하지 않고 `git rebase --continue`로 rebase를 적용하면 된다.

# 태그

커밋을 참조하기 쉽게 알기 쉬운 이름을 붙이는 방법이다.

- 일반 태그
    - 이름만 붙일 수 있다.

- 주석 태그
    - 일반 태그보다 자세한 설명을 적을 수 있다.

# 커밋 삭제하기

## reset

## revert

