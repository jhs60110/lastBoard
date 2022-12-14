# promptBoard

### java :v17
### DB :mysql

#### mysql schema 생성(board) 후 
#### 하단 application.yml datasource 수정 후 실행

    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/board?serverTimezone=Asia/Seoul
        username: root
        password:


### 구현 기능
1. 회원가입
2. 로그인
3. 게시글CRUD (권한)
4. 댓글 CRUD (권한)
5. 첨부파일 업로드, 다운로드
6. 조회수
7. 게시글 제목 검색


