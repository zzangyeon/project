# [ zzangLog ] 프로젝트

# 1. 개요 및 주제

[zzangLog]는 **블로그계의 SNS**를 꿈꾸며 시작한 프로젝트입니다.

일반적인 블로그는 나만의 공간이라면 [zzangLog]는 모든 **글이 한 공간에 모여있어** 다른 사람의 블로그를 **탐색**하고, 관심 있는 블로그를 **구독**하며, 사람들과 **소통**하며 지낼 수 있는 **공간**입니다.

---

# 2. 기술 스택

### **Back-end**
- Java 11
- Spring Boot 2.5.4
- Spring Data JPA
- Spring security
- Thymeleaf

### Front**-end**
- html, css, Javascript

### Database
- MariaDB

### DevOps
- NCP - server, Object Storage
- Jenkins

---

# 3. ERD
![image](https://user-images.githubusercontent.com/79262461/209148469-b8af88ab-3e8b-4df9-bdb5-83bd9d8364ac.png)

# 4. 핵심 기능

- 자세한 내용을 보시려면 ▶버튼을 펼쳐주세요!

- 토글 모두 열기
- Mac =  cmd + option + t 
- Window =  ctrl + alt + t

### 🎪 메인 페이지

- 모든 글이 모여있는 메인 페이지
![image](https://user-images.githubusercontent.com/79262461/209148954-7e698210-08f4-41de-9526-45fcead839aa.png)
    

### 📃 글 작성

- 글 작성
    
    ![image](https://user-images.githubusercontent.com/79262461/209149164-1d61e28e-f606-4429-a7c6-23d5c65bed6f.png)

    
    웹 텍스트 에디터 **ckeditor** 사용
    

### ✏ 댓글

- 댓글 보기 및 삭제
    1. 로그인된 사용자만 자신의 댓글을 삭제할 수 있다. 
    2. 4개의 최신 댓글을 기본으로 보여준다.
    더보기 버튼 클릭시 4개씩 하단에 추가되어 보여준다.
    
    ![image](https://user-images.githubusercontent.com/79262461/209149292-217f16d5-d29c-4fb4-8146-33b256310340.png)
![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%203.png)
    

### 💖 구독

- 구독 / 구독 리스트
    1. 다른 사용자의 블로그 구독
    2. 구독 정보를 통해 구독한 블로그로 이동 가능
    3. 다른 사용자의 구독 정보 확인 가능
    
    ![image](https://user-images.githubusercontent.com/79262461/209149342-6ea40a3a-047c-4141-ab8d-9a35b3aac8c1.png)
![image](https://user-images.githubusercontent.com/79262461/209149396-97aebbd6-cab7-42f8-a730-293e550b6734.png)
    

### 🧐 내 블로그

- 내가 작성한 글 모아보기
    1. 상단에 블로그 이름, 소개, 아이디, 구독 정보 표시
    2. 하단에 내가 작성한 글 리스트 보기
    
    ![image](https://user-images.githubusercontent.com/79262461/209149553-d35a7979-1e12-4aa0-ac4f-db37c13c9388.png)

    
- 내 정보 수정
    
    아이디, 블로그 이름, 블로그 소개 수정 가능
    
    ![image](https://user-images.githubusercontent.com/79262461/209149611-70a3a8d3-a9af-4a9b-9219-ae1130b51e66.png)

    

### 👩‍🦰 사용자 인증 (회원가입 및 로그인)


더 자세한 내용은
### https://zzangyeon.notion.site/zzangLog-0a05ed93cdd049cfa12584b3a29a9697
