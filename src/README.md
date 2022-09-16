# MetaChat

# 개발 동기

---

처음엔 단순히 카카오톡, 페이스북 메신저, 인스타그램 디엠처럼 실시간 통신이 신기한 마음에 “우와~ 대단하다~” 정도로만 생각했었다.

어느날 친구가 대학교 커뮤니티인 에브리타임에서 거래를 하기로 했는데 캠퍼스간 오해가 생기는 일이 발생했다. 불현듯 사용자의 위치가 에타에도 나오면 좋겠다는 생각을 했고, 에브리타임처럼 게시판 구조가 아닌 실시간 채팅으로 빠른 피드백을 적용해보면 어떨까? 에서 MetaChat이 시작되었다. 😄

[상황 예시]

<aside>
▶️ 학생A : 지금 학식 먹으러 가면 많이 기다려야하나요??

</aside>

<aside>
▶️ 학생B : 사람 완전 많아요..! 차라리 다른거 드세요..!

</aside>

이 때 학생 B의 말이 진짜인지 아닌지는 순전히 믿음의 영역에 달렸다. 어쩌면 식당에 있지도 않은 친구들이 장난삼아 하는 말일지 고민되는 상황에서, 채팅의 신뢰도를 높이기 위해 학생 B가 식당 건물 내부에 있지 않다면 채팅을 조금 다르게 보여주는 방법을 택했다.

# 프로젝트 설명

---

처음 지도 화면에서 사용자가 원하는 채팅방을 클릭한다 ( 건물 )

해당 채팅방에 입장할 때, 사용자의 위치정보를 가져와 저장되어있는 건물의 위치 사이 거리를 계산하여 반경 45미터 이내의 경우, 건물 내부에 사용자가 있는 것으로 생각한다.

건물 외부에서 채팅방에 입장할 경우, 사용자의 닉네임 앞에 천사 이모티콘이 (😇) 등장하여 사용자의 현재 위치를 간접적으로 알 수 있도록 한다.

사용자의 위치를 지속적으로 얻기 위해서 사용자의 위치가 아닌, 채팅이 발송되는 위치를 체크했다.

처음 시도 때, 사용자가 위치 서비스를 켰다가 그 뒤로 끄는 경우나 getCurrentPosition이 아닌 watchPosition을 사용할까도 고민했지만, watchPosition의 경우 기기의 배터리나 기타 리소스를 많이 차지하여 오히려 좋지 않을 수 있다고 느껴져서 

 → 채팅을 보낼 때 마다 발송된 위치를 getCurrentPosition을 통해 가져오도록 하였다.

---

![처음 채팅방 입장 시 보여주는 학교 지도](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled.png)

처음 채팅방 입장 시 보여주는 학교 지도

---

![기숙사 채팅방에서 아직 도착하지 않은 “승현”이, 먼저 도착한 “정훈”에게 학교 기숙사 소감을 물어보는 상황이다.](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%201.png)

기숙사 채팅방에서 아직 도착하지 않은 “승현”이, 먼저 도착한 “정훈”에게 학교 기숙사 소감을 물어보는 상황이다.

왼쪽 그림과 같이 특정 건물에서 사용자가 채팅을 보낼 때 해당 채팅이 전송된 위치 정보를 통해서 

건물의 중심 위치와 사용자의 위치 사이의 거리를 통해 건물 내 외부를 판별한다.

(학교 건물의 크기를 평균으로 대략 반지름 45미터로 반경을 계산하였다.)

## 파일 구조

## 작동 시퀀스

![Untitled](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%202.png)

![Untitled](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%203.png)

---

# 프로젝트 상세

## API

- GET method http://localhost/chat/rooms
    
     → return : ModelAndView (모든 채팅방을 “list” 명으로 `/chat/roomList`로 전달)
    
    ![Untitled](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%204.png)
    

- GET method http://localhost/chat/room?(roomId)
    
    파라미터로 들어온 채팅방 아이디를 통해 해당 채팅방 검색, 채팅방 DTO를 “room” 이름으로 전달
    → return : 페이지를 보여줄 html 파일 명 (template 폴더 내부)
    
    ![Untitled](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%205.png)
    

---

## Websocket

<aside>
▶️ Websocket 헨드셰이크를 위한 엔드포인트 지정 ( ”/university” )

[StompConfig]
    prefix : ( “/pub” )
    broker : ( “/sub” )

</aside>

- STOMP broker   ws://localhost/university/pub/chat/enter
    
    입장시 로직 처리 → ~~/sub/chat/room/{roomId} 로 입장 안내 메시지 전달
    
    직접 환영 인사를 메시지에 set
    
    ![Untitled](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%206.png)
    
- STOMP broker   ws://localhost/university/pub/chat/message
    
    사용자가 전송한 메시지 전달 → ~~/sub/chat/room/{roomId} 로 입장 안내 메시지 전달
    
    ![Untitled](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%207.png)
    

---

## DTO

- 채팅방 DTO
    
    ```java
    public class ChatRoomDTO {
        private String roomId;            //채팅방 ID
        private String name;              //채팅방 이름
        private double roomLatitude;      //채팅방 위도
        private double roomLongitude;     //채팅방 경도
    }
    ```
    
- 채팅 DTO
    
    ```java
    public class ChatMessageDTO {
        private String roomId;            //채팅방 ID
        private String writer;            //채팅 작성자
        private String message;           //채팅 메시지
        private double chatLatitude;      //채팅 위도
        private double chatLongitude;     //채팅 경도
    }
    ```
    

---

## 위치 정보

![사용자의 위치 정보 수집](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%208.png)

사용자의 위치 정보 수집

![위도와 경도를 통한 거리 계산](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%209.png)

위도와 경도를 통한 거리 계산

Geolocation API를 활용한 사용자 위치 정보 수집  →  수집된 위치 정보와 입장한 건물의 위도 | 경도 정보를 통해 거리 계산

## 채팅 시각화

![Untitled](MetaChat%202e9f2e59accb4126bd09ebb6cf95898c/Untitled%2010.png)

- 들어온 채팅 DTO를 바탕으로 채팅 작성자와 현재 사용자의 닉네임 일치 여부를 바탕으로 채팅의 좌 우측 표시를 결정
- 거리 계산을 통해 구한 값을 바탕으로 건물 내부 외부를 결정하여 사용자 닉네임 앞에 이모티콘 (😇) 표시
    - 건물 내/외부 판별은 학교 건물 크기를 평균 → 건물 중심부터 반경 45m 를 기준으로 구분함

---

# 오류 해결

- favicon → href=’#’ (때문에 조회 2번씩 발생) → 무조건 존재하는 현재 폴더 위치 다시 조회해서
    - 수 십번 코드 진행에 따라 살펴봄 → 스프링 부트 코드에는 문제 없음을 확인
        
        → 자바스크립트 단위별로 끊어서 확인 (주석처리로)
        
        → 모든 경우에서 두번씩 실행
        
        → 아이에 새로운 파일을 생성해서 실행 → 오류 해결
        
    - 해결 : 알고보니 맨 위에 favicon 오류 뜨는게 보기 싫어서 넣었던 href=’#’를 인지하지 못함
        
        ---
        
        이미지 파일 하나 넣어서 해결
        
# 개선 사항

- db 연결하기
  - 사용자 아이디 중복 처리 불가
  - 파라미터 검증 필요 (유저 데이터 관점)
  
  -> 회원가입 / 로그인 기능 제작중 (구조 재구성 하여 확장 계획중)
- https 통신 사용하기