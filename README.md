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

# 5. 리팩토링
<details>
    <summary>제목!</summary>
### 1) 세션기반 인증 → jwt 토큰기반 인증  - 사용자 인증방식 변경

<aside>
💡 **Spring security 와 JWT**를 이용하여 **권한 및 사용자 인증**을 진행

</aside>
</details>
- **기존에는 session 기반**의 사용자 인증 방식을 사용하였다.
session은 서버 또는 DB에 저장해야 하기 때문에 서버 확장시 여러 조치를 취해야 한다.
그래서 **토큰 기반**의 사용자 인증 방식을 **도입**했다. 
두 방식은 상태 저장 위치가 다른데 session은 서버, JWT는 클라이언트에 저장한다.
- **Spring security와 JWT**를 이용하여 권한 및 사용자 인증을 진행하였다.
    - Spring security  - 각종 **보안 체인과 권한** 기능 사용
    - JWT - **인증** 기능 수행

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%208.png)

- Security Config 시큐리티 환경설정

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%209.png)

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2010.png)

- JWT생성

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2011.png)

- usernamePassword 토큰(인증객체) 반환

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2012.png)

- JWT 유효성 검사

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2013.png)

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2014.png)

- JwtFilter - jwt 토큰 유효성 검사 및 SecurityContext에 Authentication객체 저장.

### 2) 파편화된 예외처리, 유효성검사, 로깅(AOP) 집중화

<aside>
💡 1) **예외처리**

**컨트롤러에서** 발생하는 **모든 예외들을** 만들고 **한곳에서 관리**한다.

</aside>

상황에 맞게 직접 Exception을 만들어서 처리한다.

<CustomApiException>, <CustomException>, 
<CustomVallidationException>, <ControllerExceptionHandler>

모든 예외는 **<ControllerExceptionHandler>**에서 받아 처리한다.

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2015.png)

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2016.png)

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2017.png)

Controller는 알람을 띄우고 이전 페이지로 돌아가는  script 코드를 응답한다.

ApiController는 CMRespDto로 커스텀한 에러 데이터와 http응답코드를 리턴한다.

<aside>
💡 2) **로깅 및 유효성 검사
AOP**를 사용해 **기본적인 로깅**과 ****요청 **파라미터 유효성 검사**를 한 곳에서 처리한다.

</aside>

(1) 기본적으로 어떤 클래스의 어떤 메소드가 실행되고 있는지 확인할 수 있는 로그를 작성한다.

(2) 파라미터 유효성 검사 실패시 BindingResult의 fieldError를 errorMap에 저장후  <CustomValidationException>에 담아 던져준다. 그 후 <ControllerExceptionHandler>에서 처리한다.

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2018.png)

### 3) 이미지 로컬 컴퓨터에 저장 → 스토리지 서버 도입

- **이미지 저장 프로세스 버전 업**

<aside>
💡 **<Version 1>** - **Filestream**을 사용해 **이미지** 파일 **로컬 컴퓨터에 저장** 및 전송

</aside>

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2019.png)

<aside>
💡 **<Version 2>** - NCP의 **Object Storage(스토리지 서버)에 이미지 저장**
AWS의 S3 API와 호환되어 S3 클라이언트 사용.

</aside>

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2020.png)

### 4) 글 작성 및  이미지 업로드 → CKEditor(웹 텍스트 에디터)

- **글 작성 에디터 버전 업**

<aside>
💡 **<Version 1>** - **단순 텍스트** 및 썸네일을 위한 **하나의 파일 저장** 가능

</aside>

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2021.png)

<aside>
💡 **<Version 2>** - **ckeditor(텍스트 에디터)** 추가 
썸네일 외 글 내부에 **다수의 파일 업로드** 및 저장, 다양한 **에디터 기능 활용** 가능.

</aside>

ckeditor 라이브러리 추가 및 글 내용 서버로 전송

```jsx
let editor = CKEDITOR.replace('editor' , {filebrowserUploadUrl:'/edit/file'} );

    <!--텍스트 저장-->
    function saveText(userId){

        let data = editor.getData();
        let title = $('#title').val();

        $.ajax({
            type:"post",
            url:'/edit/text/' + userId,
            data: { 'title' : title, 'data' : data },
            contentType:"application/json; charset=utf-8"
        }).done(res=>{
            alert("성공입니다");
        }).fail(error=>{
            alert("실패입니다");
        });
    }
```

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2022.png)

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2023.png)

### 5) 데이터 전달시 Entity 그대로 사용 → 상황에 맞는 DTO 사용

- 데이터를 담고 전달하는 용도로 Entity를 곳곳에서 사용하고 있었다. 
하지만 **Entity는** **DB의 테이블과 매핑되는 중요한 클래스**이기 때문에 **여러 용도로 사용하는 것은 적절치 않다.**
- 안전하게 데이터를 담고 전달할 수 있도록 Entity대신  **DTO를 만들어 각 상황에 맞는 유효성 검사 어노테이션을 붙여 적절한 곳에서 사용**하도록 수정하였다.

---

# 6. 트러블슈팅

### 1) NCP(네이버 클라우드) 메모리 문제

<aside>
❗ **클라우드 서버의 메모리 부족으로 인한 빌드 실패** - 1GB의 작은 램

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2024.png)

</aside>

### 해결 <1>

- gradle wrapper로 빌드시 **데몬 프로세스가 자동 실행되어 메모리 부하** 발생.
지속적인 빌드를 해야하는 **개발 환경에서 유용한 데몬**은 **CI/CD 환경에서는** 필요치 않고 오히려 **초반 빌드시 메모리를 더 소비함**.
- gradle.properties의 **데몬 stop으로** 설정하여 **자동 실행 중지**.
    
    ![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2025.png)
    

### 해결 <2>

- **swap**으로 인한 사용 가능 메모리 증가 1GB → 2GB

### 2) Swagger(API 자동 문서화, 테스트 툴) 적용시 에러

<aside>
❗ **Swagger 라이브러리 적용시 오류 발생**

</aside>

### 해결 <1>

- Swagger 2.9.x 이후 버전과 Spring 2.6.x 이후의 **버전이 안 맞아서** 오류가 발생했다.
그대로 사용하려면 **application.yml**에 다음을 추가해주면 된다.

```bash
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
```

### 해결 <2>

- 버전 문제이기 때문에 Spring 2.5 이하 버전을 이용하거나 Swagger 2.9 이하 버전을 사용하면 된다.

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2026.png)

### 3) Spring Security 디버깅 - filter chain

<aside>
❗ Spring Security의 기본 인증 **filter chain 동작 과정 및 인증 원리** 무지

</aside>

- 인증 필터 동작 과정 **디버깅**
    
    ![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2027.png)
    

---

- formLogin()시 <UsernamePasswordAuthenticationFilter> 작동
- formLogin()기능 없애고 <JwtFilter>로 인증 기능 대체시 **<PoviderManager>의 authenticate() 메서드가 인증 과정 진행**

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2028.png)

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2029.png)

---

- 실질적 인증 객체인<DaoAuthenticationProvider>의 여러 메소드 중 createSuccessAuthentication() 내부에서 {UsernamePasswordAuthenticationToken()} 의 (isAuthenticated = true) 값을 기본으로 갖는 생성자로 재 생성한 후 반환한다
⇒ 결과적으로 **인증된 사용자**로서 **SecurityContext에 Authentication 객체가 담김**

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2030.png)

### 4) NCP(클라우드) SSH 접속 인증 이슈

<aside>
❗ **ssh** 접속시 **비밀번호, 인증키 방식에 대한** 정확한 **지식 결여**

</aside>

- AWS에서는 자동으로 비밀키를 사용한 인증 방법을 제공하지만, **NCP는 초기에 비밀번호로 인증**하게 되어있다. 
**ssh**에 **대한** **완전한 이해 없이** **비밀번호와, 비밀키 방식 모두를 사용한다는 착각에** 비밀번호를 **연습하기 편한 쉬운 번호로 적용**했고, ssh 22번 포트에 접근 가능한 **ip 또한 다른 장소에서의 접속을 위해 0.0.0.0/0 으로 모두 열어두었다**.
그러자 다음날 곧바로 중국에서 해킹을 시도했고, 서버가 안전하지 않다는 알림을 받았다.
- **즉시 비대칭키를 통한 인증 방식을 적용했고, 비밀번호 또한 아주 어렵게 변경했다.
비록 혼자 만든 토이 프로젝트이지만 보안에 대한 큰 경각심을 가지게 되었다.**

<aside>
❗ **비대칭키 인증 방식 적용 오류**

</aside>

- key-gen 으로 id_rsa, id_rsa.pub 키 페어를 생성하고 public key를 서버에 저장하는 등 **key pair 인증 방식으로 환경 설정을 했지만 계속해서** 우선 순위가 가장 마지막인 **비밀번호 인증이 실행**됐다.
- 실행 로그를 살펴봐도 정확히 어느 부분에서 어떤 문제가 발생한 것인지 파악하지 못했다.
- 권한 문제가 가장 흔한 적용 실패 원인이다. 그래서 클라이언트의 .ssh 폴더와 id_rsa에 대한 권한을 현재 계정만, 그리고 읽기 및 실행 권한을 주었고, 서버의 .ssh 폴더와 authorized_keys에는 각 700 600 권한을 주었다. 하지만 여전히 증상은 같았다.
- public key를 옮기는 과정에서 문제가 발생했을 가능성이 있어서 다시 옮겨 넣었고 정상 작동했다. **결국 엔터 하나를 넣지 않은 것이 문제가 됐다**. -vvv 옵션을 붙여 인증 성공 **로그를 살펴보면** 이전 실패의 경우, 빨간 상자 속 인증 성공 로그가 발생하지 않았다는 걸 알 수 있다.

![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2031.png)

### 5) 로컬 컴퓨터(Window) 에서 클라우드 서버(Linux)로 DB 백업 후 복구

<aside>
❗ **mysql dump 파일 적용 불가**

</aside>

- mysql dump 파일을 import하는 명령 실행 중 작동하지 않는 문제가 발생했다.
**문제는 window의 powershell이였다**. powershell 은 1.0 부터 현재까지 **‘<’ 연산자를 입력 pipe 로 사용할 수가 없다**. 해결 방법으로는
- (1) **cmd.exe** 는 < 를 입력 파이프로 사용하므로 cmd를 사용해서 import 한다.
(2) git bash를 이용한다.

<aside>
❗ **한글깨짐 - encoding 문제**

</aside>

- 클라우드 서버의 DB에 dump한 파일을 import한 이후에도 문제가 발생했다.
한글이 전부 깨져서 나오는 것이다.
**문제는 encoding이였다.**
- ****mysql의 여러가지 **character 설정을 변경**시켜 줘야한다.
    1. /etc/mysql/my.cnf 파일 변경
    
    ```
    [client]
    default-character-set=utf8
    
    [mysql]
    default-character-set=utf8
    
    [mysqld]
    collation-server = utf8_unicode_ci
    init-connect='SET NAMES utf8'
    character-set-server = utf8
    ```
    
    1. 현재 Character-Set 확인
    
    ```
    mysql> show variables like ‘c%’
    mysql> status
    ```
    
    1. MySQL 재시작
    
    ```
    [root@zzanglog ~]# service mysql restart
    ```
    
    ![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2032.png)
    
    ![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2033.png)
    
    ![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2034.png)
    
    캐릭터 셋이 잘 변경되었고 한글은 깨짐 없이 나오게 됐다.
    

### 6) 스토리지 서버 객체 퍼블릭 엑세스 설정

<aside>
❗ **스토리지 서버 내 Object의 접근 권한 설정 방법 부재**

</aside>

- **NCP(네이버 클라우드) 스토리지 서버인 Object Storage를 사용**했다. **AWS S3 api와 호환**이 되었기 때문에 제공되는 SDK를 이용했다.
- **NCP**는 버킷과 객체(파일)에 대한 접근권한을 따로 설정해야하는데
**객체에 대한 퍼블릭 엑세스 권한은 콘솔에서 하나하나 직접 설정해주는 방법 밖에 없었다.**
- 그래서 **S3 api를 이용해 권한 설정하는 방법을 AWS 공식 문서에서 찾아보아도** 정확한 방법이 **나와있지 않았고**, (IDE를 이용해) **ACL(오브젝트 접근권한리스트) 설정 메서드인 setObjectAcl()의 마지막 파라미터가 ACL관련 설정임을 찾았고**, 관련 키워드를 통해 **IDE에서 검색되는 파라미터를 적용시켜 보면서 마침내  [ CannedAccessControlList.PublicRead ] 를 발견**했다.
    
    ![Untitled](%5B%20zzangLog%20%5D%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200a05ed93cdd049cfa12584b3a29a9697/Untitled%2035.png)
    
    접근 권한은 잘 적용되었다.
    

### 7) ****Jenkins 플러그인 설치 실패****

<aside>
❗ JVM의 certificate 문제

</aside>

  [ **Caused by: javax.naming.CommunicationException: simple bind failed ]**

- 위의 에러가 발생되면서 **Jenkins 플러그인들이 설치 되지 않는 현상**이 발생했다.
**원인**으로는 여러가지가 있는데 그 중 **연결하려는 remote site의 SSL인증서가 java에서 신뢰하는 인증기관의 인증서 목록(keystore)에 없는 경우** 였다.
- 무료 SSL 인증서를 발급해 주는 Let's encrypt 의 CA 인증서는 Java VM 에 포함되어 있지 않아 Let's encrypt 에서 발급 받은 SSL 인증서를 Java에서사용할 경우에 이런 에러가 발생한다.

### 해결<1>

- 아래 코드를 서버에서 입력한다. 단, 이 방식은 JDK가 변경될 때 마다 적용해줘야 된다.

```
$ keytool -trustcacerts \
    -keystore $JAVA_HOME/jre/lib/security/cacerts \
    -storepass changeit \
    -noprompt \
    -importcert \
    -file /etc/letsencrypt/live/hostname.com/chain.pem
```

위와 같은 방법으로 대부분의 플러그인들이 설치됐지만 몇몇의 플러그인은 설치가 되지 않았다.

그래서 추가 조치를 취했다.

### 해결<2>

- 젠킨스 **[ skip-certificate-check ] 플러그인** 이용
- [ skip-certificate-check ] ****은 **인증 검사를 스킵**해 주는 젠킨스 **플러그인**이다.
- 물론 **실제 프로덕트 환경에서는** 사용하면 ****절대 **안되지만**, **토이 프로젝트에서** 인증서 오류로 인해 진행이 안 될때 **간단하게 문제를 해결할 수 있는** 방법이다.

---

# 8. 회고 및 느낀점

zzangLog는 혼자의 힘으로 처음부터 끝까지 만들어본 첫 프로젝트이다.
혼자서 기획, 설계, 개발, 배포의 과정을 모두 진행했다.
특별한 기능이나 창의적인 아이디어가 깃든 프로젝트는 아니였지만 혼자의 힘으로 프로젝트 전체 사이클을 돌아보는 매우 값진 경험을 했다. 
하지만 프로젝트를 진행하며 부족함이 많다는 것을 느꼈다. 아래 두가지에 —————--

지금 사용하는 기술 스텍을 더 견고히 만들기 위해 공부를 계속해서 이어나갈 것이다.———-

- 클린코드 및 좋은 아키틱처에
- 안정적이고 변화에 유연한 서비스

앞으로 성장해 나갈 수 있는 부분이 무궁무진하기 때문에 기대가 된다.
다음 프로젝트는 기발한 아이디어로 많은 사람들이 재밌게 사용할 수 있는서비스를 만들어 볼 것이다.

---
