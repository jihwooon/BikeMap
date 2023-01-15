# Kotlin-Server

> MacOS 기준으로 작성되었습니다.

## 1-1. Docker 설치 및 DB 컨테이너 실행

Docker 설치되어 있지 않으면 brew 통해 설치해줍니다.
> brew install docker

현재 Mariadb 버전은 10.8.3 입니다.
> docker pull mariadb:[Tag]

도커 실행 명령어는 다음과 같습니다.
```shell
docker run --name bike-map \
-v $(pwd)/db:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root1234 \
-e MYSQL_DATABASE=bikemap \
-d -p 3307:3306 mariadb:10.8.3 \
--character-set-server=utf8mb4 \
--collation-server=utf8mb4_unicode_ci
```
Docker container 접속하기
> docker exec -i [컨테이너명 혹은 컨테이너 ID] mariadb -u root -p

Docker 재실행하기
> docker restart [컨테이너명 혹인 컨테이너 ID]
