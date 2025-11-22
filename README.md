🔮 오늘의 운세 (AI Fortune Teller)

"AI가 읽어주는 당신의 하루" - OpenAI GPT-3.5 API를 활용한 개인 맞춤형 운세 서비스

📖 Project Overview (프로젝트 개요)

오늘의 운세는 기존의 정형화된 운세 데이터베이스 대신, OpenAI의 생성형 AI를 활용하여 매일 새롭고 창의적인 운세 풀이를 제공하는 웹 애플리케이션입니다.
사용자가 입력한 사주 정보(생년월일, 태어난 시간 등)를 바탕으로 AI가 개인화된 운세를 분석하며, 과거의 운세 기록을 저장하여 흐름을 비교해 볼 수 있습니다.

개발 목적: OpenAI 시대를 맞아 AI API 활용 능력을 기르고, 정형화된 운세 서비스의 한계를 넘어 창의적인 답변을 제공하는 서비스를 구현하고자 함.

1인 개발: 김민우 (기획, 디자인, 프론트엔드, 백엔드, DB 설계 전담)

🛠 Tech Stack (기술 스택)

|Category|Stack|
|------|:---:|
|Language|Java|
|Framework|Jsp|
|Database|MySQL|
|API|OpenAI API|



✨ Key Features (핵심 기능)

1. 🤖 AI 기반 운세 생성 (OpenAI API)

기능: 사용자의 이름, 성별, 생년월일(양력/음력), 태어난 시간을 입력받아 GPT-3.5 Turbo 모델에게 프롬프트를 전송.

특징: 단순 DB 조회가 아니라 AI가 실시간으로 문맥을 생성하므로, 매번 풍부하고 색다른 운세 해설을 제공.

<img src="/images/운세입력.png" width="400">

2. 📝 운세 기록 및 비교 (History)

기능: 한번 조회한 운세는 데이터베이스(FortuneRecord)에 자동 저장.

활용: '기록 보기' 페이지에서 지난 운세들과 오늘의 운세를 비교하며, 나만의 운세 패턴이나 주기성을 파악 가능.

<img src="/images/운세기록.png" width="400">

3. 🔐 회원 가입 시스템 (Authentication)

회원가입: 아이디 중복 체크, 유효성 검사(5자 이상, 영문/숫자 조합), 비밀번호 마스킹 처리.

<img src="/images/회원가입.png" width="400">
<img src="/images/조건1.png" width="400">
<img src="/images/조건2.png" width="400">
<img src="/images/가입완료.png" width="400">

로그인/로그아웃: 세션(Session)을 활용한 사용자 인증 상태 유지.

<img src="/images/로그인.png" width="400">

4. 👑 관리자 모드 (Admin)

사용자 관리: 전체 회원의 정보 조회 및 정보 수정, 강제 탈퇴(삭제) 기능.

<img src="/images/계정관리.png" width="400">

운세 데이터 관리: 모든 사용자의 운세 생성 로그를 모니터링하고 부적절한 데이터 삭제 가능.

<img src="/images/운세관리.png" width="400">

💾 Database Structure (ERD 설계)

<img src="/images/ERD.png" width="400">

📂 Project Structure (패키지 구조)
```bash
src/main/java
└── com.fortune.project
    ├── controller     # 서블릿(Servlet) 요청 처리  
    ├── dao            # DB 접근 객체 (Data Access Object)  
    ├── dto            # 데이터 전송 객체 (Data Transfer Object)   
    └── util           # DB 연결 및 OpenAI API 통신 유틸리티   
webapp
├── assets             # CSS, 이미지, JS 파일
├── views              # JSP 화면 (로그인, 운세입력, 결과창 등)
└── admin              # 관리자 전용 페이지
```
