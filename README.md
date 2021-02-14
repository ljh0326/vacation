
# 기능 명세

* 사용자 모델과 로그인 시스템이 있습니다
* 사용자에게는 매년 15일의 연차가 부여됩니다.
* 사용자는 연차/반차(0.5일)/반반차(0.25일)에 해당하는 휴가를 신청할 수 있습니다.
* 휴가 신청시 시작일, 종료일(반차/반반차의 경우는 필요없음), 사용 일수, 코멘트(선택 항목)를 입력합니다.
* 휴가 신청시 남은 연차를 표시합니다.
* 연차를 모두 사용한 경우 휴가를 신청할 수 없습니다.
* 추가 기능: 사용 일수를 입력하는 대신 시작일, 종료일을 가지고 공휴일을 제외하고 계산해도 됩니다.
* 아직 시작하지 않은 휴가는 취소할 수 있습니다.

---
# 시나리오 호출 스크립트
1. 로그인 api를 호출합니다.
![image](https://user-images.githubusercontent.com/17944004/107321761-769daf80-6ae6-11eb-9aaa-71ddb50f1b8b.png)

2. 로그인 api 반환값인 토근을 x-auth-token 헤터에 등록해주신후 휴가조회 api를 호출합니다.
![image](https://user-images.githubusercontent.com/17944004/107321849-a3ea5d80-6ae6-11eb-9d67-a39b6af21ae5.png)

3. 마찬가지로 토큰을 헤더에 넣고 휴가등록록api를 호출합니다.
![image](https://user-images.githubusercontent.com/17944004/107322110-2e32c180-6ae7-11eb-82bf-18dcb8463fa0.png)

4. 당일이나, 사용한 휴가가 아니면 취소할 수 있습니다.
![image](https://user-images.githubusercontent.com/17944004/107322201-57ebe880-6ae7-11eb-9f5c-21ec21dbe3d5.png)

포스트맨 스크립트는 메일로 따로 첨부하겠습니다.

---
## TODO
1. 에러 처리를 다 하지 못함 - 년도 넘어갈때, 또 이미 등록된 날짜 또 등록하는 예외처리 등등.
2. 패스워드 암호화 - 마무리 못함
3. 리프레시토큰 - 지금은 로그인하면 토큰이 15분만 유지되고 따로 리프레시해주는 로직이 없음
4. lint 적용
