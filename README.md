
### 지하철 다물어봐!


**개요**

지하철 도착 시간, 주변 버스 정류장 정보와 같이 즉각적인 응답이 요구되는 지하철 관련 정보를 한 채팅방 안에서 자연어를 통해 입력 받고 응답해주는 카카오톡 플러스친구 형태의 챗 봇 시스템

**프로젝트 구성원**

한이음 ICT 멘토링 프로젝트 : 3명의 개발자 중 팀장, 실시간 지하철 위치 정보 제공 기능 및 Retry 구현 

**시스템구조**

![system-architecture](https://github.com/SeoJaeyeon/AllOfSubway/blob/master/img/system_architecture.png?raw=true)

**주요 요구사항 및 구현 내용**

- 카카오톡 플러스친구 형태로 구현: 카카오 플러스친구 API와 Spring과 연동을 통해 구현
- 자연어 형태의 요청 분석: Watson Conversation  API를 연동하여 사용자의 질문 패턴을 예상하여 자연어를 처리할 수 있도록 함
- 서버 다운 시 재요청: 실시간 정보 제공 API 특성 상 서버 다운이 잦은 것을 해결하기 위해 Spring-Retry를 통해 최대 3번까지 재요청 뒤 3번 이후에는 응답 불가 메시지 전송

