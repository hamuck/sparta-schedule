# sparta_schedule


---

# API 명세서

| 기능          |Method   | URL                         | request  | response|상태코드  |
|-------------|---|-----------------------------|---|---|---|
| 일정 생성(등록)   |POST| /api/schedules              |요청 body   | 등록정보  | 200: 정상 등록, 400: 등록 실패 |
| 일정 목록 조회    | GET  | /api/schedules              | -  |  다건 응답 정보 |200: 정상 조회 |
| 선택 일정 조회    | GET  | /api/schedules/{scheduleId} |  요청 param | 단건 응답 정보  | 200: 정상 조회, 404: 존재하지 않는 일정|
| 날짜 선택 일정 조회 | GET  | /api/schedules/date/{days}  |  요청 param | 단건 응답 정보  | 200: 정상 조회|
| 선택 일정 수정    | PUT  | /api/schedules/{scheduleId} | 요청 body  | 수정 정보  | 200: 정상 수정, 400: 조회 실패, 404: 존재하지 않는 일정|
| 선택 일정 삭제    | DELETE  | /api/schedules/{scheduleId} | 요청 param  | -  | 200: 정상 삭제|

---

## 일정 생성
일정을 생성합니다.

|메서드|요청 URL|
|---|---|
|POST|http://{SERVER_URL}/api/schedules|

**Request**

```json
{
"password" : "비밀번호",
"username" : "작성자 명",
"title" : "제목",
"content" : "내용"
}
```


|  파라미터 |타입   | 필수여부  | 설명  |
|---|---|---|---|
| password  | String  | 필수  | 일정 비밀 번호  | 
|  username | String  | 필수  |  일정 작성자 이름 |
| title | String | 필수 | 일정 제목|
| content | String | 선택 | 일정 상세 내용|

**Response**
```json
{
  "id": 1,
  "userId": 1,
  "userName": "이름",
  "title": "제목",
  "contents": "내용",
  "createAt": "2024-11-07T09:00:10.349+00:00",
  "updateAt": "2024-11-07T09:00:10.349+00:00"
}
```

| 파라미터     | 타입        | 필수여부 | 설명                        |
|----------|-----------|------|---------------------------|
| id       | Integer   | 필수   | 일정 고유 번호                  |
| userId   | Integer   | 필수   | 사용자 고유 번호                 |
| username | String    | 필수   | 일정 작성자 이름                 |
| title    | String    | 필수   | 일정 제목                     |
| content  | String    | 선택   | 일정 상세 내용                  |
| createAt | Timestamp | 필수   | 일정 작성 시간                  |
| updateAt | Timestamp | 필수   | 일정 작성 시간 (수정시 수정 시간으로 변경) |




---

## 일정 목록 조회
일정 목록을 조회합니다.

|메서드|요청 URL|
|---|---|
|GET|http://{SERVER_URL}/api/schedules|

**Request**
- Request 없음

**Response**
```json
[
    {
        "id": 1,
        "userId": 1,
        "userName": "이름1",
        "title": "제목1",
        "contents": "내용1",
        "createAt": "2024-11-07T07:42:10.000+00:00",
        "updateAt": "2024-11-07T07:42:10.000+00:00"
    },
    {
        "id": 2,
        "userId": 2,
        "userName": "이름2",
        "title": "제목2",
        "contents": "내용2",
        "createAt": "2024-11-07T09:00:10.000+00:00",
        "updateAt": "2024-11-07T09:00:10.000+00:00"
    },
    {
        "id": 3,
        "userId": 3,
        "userName": "이름3",
        "title": "제목3",
        "contents": "내용3",
        "createAt": "2024-11-07T09:02:56.000+00:00",
        "updateAt": "2024-11-07T09:02:56.000+00:00"
    }
]
```
| 파라미터     | 타입        | 필수여부 | 설명                        |
|----------|-----------|------|---------------------------|
| id       | Integer   | 필수   | 일정 고유 번호                  |
| userId   | Integer   | 필수   | 사용자 고유 번호                 |
| username | String    | 필수   | 일정 작성자 이름                 |
| title    | String    | 필수   | 일정 제목                     |
| content  | String    | 선택   | 일정 상세 내용                  |
| createAt | Timestamp | 필수   | 일정 작성 시간                  |
| updateAt | Timestamp | 필수   | 일정 작성 시간 (수정시 수정 시간으로 변경) |



---

## 일정 목록 페이징 조회
일정 목록을 페이징 형식으로 조회합니다.
페이지 사이즈는 5로 설정 되어 있습니다.

|메서드| 요청 URL                                         |
|---|------------------------------------------------|
|GET| http://{SERVER_URL}/api/schedules/page/{pages} |

**Request**
- Request 없음

**Response**
```json
[
    {
        "id": 1,
        "userId": 1,
        "userName": "이름1",
        "title": "제목1",
        "contents": "내용1",
        "createAt": "2024-11-07T07:42:10.000+00:00",
        "updateAt": "2024-11-07T07:42:10.000+00:00"
    },
    {
        "id": 2,
        "userId": 2,
        "userName": "이름2",
        "title": "제목2",
        "contents": "내용2",
        "createAt": "2024-11-07T09:00:10.000+00:00",
        "updateAt": "2024-11-07T09:00:10.000+00:00"
    },
    {
        "id": 3,
        "userId": 3,
        "userName": "이름3",
        "title": "제목3",
        "contents": "내용3",
        "createAt": "2024-11-07T09:02:56.000+00:00",
        "updateAt": "2024-11-07T09:02:56.000+00:00"
    },
  {
    "id": 4,
    "userId": 4,
    "userName": "이름4",
    "title": "제목4",
    "contents": "내용4",
    "createAt": "2024-11-07T09:00:10.000+00:00",
    "updateAt": "2024-11-07T09:00:10.000+00:00"
  },
  {
    "id": 5,
    "userId": 5,
    "userName": "이름5",
    "title": "제목5",
    "contents": "내용5",
    "createAt": "2024-11-07T09:02:56.000+00:00",
    "updateAt": "2024-11-07T09:02:56.000+00:00"
  }
]
```
| 파라미터     | 타입        | 필수여부 | 설명                        |
|----------|-----------|------|---------------------------|
| id       | Integer   | 필수   | 일정 고유 번호                  |
| userId   | Integer   | 필수   | 사용자 고유 번호                 |
| username | String    | 필수   | 일정 작성자 이름                 |
| title    | String    | 필수   | 일정 제목                     |
| content  | String    | 선택   | 일정 상세 내용                  |
| createAt | Timestamp | 필수   | 일정 작성 시간                  |
| updateAt | Timestamp | 필수   | 일정 작성 시간 (수정시 수정 시간으로 변경) |



---

## 선택 일정 조회
선택한 일정의 상세 내역을 조회합니다.

|메서드|요청 URL|
|---|---|
|GET|http://{SERVER_URL}/api/schedules/{scheduleId}|

**Request**

- Request 없음


**Response**

```json
{
  "id": 1,
  "userId": 1,
  "userName": "이름",
  "title": "제목",
  "contents": "내용",
  "createAt": "2024-11-07T09:00:10.349+00:00",
  "updateAt": "2024-11-07T09:00:10.349+00:00"
}
```

| 파라미터     | 타입        | 필수여부 | 설명                        |
|----------|-----------|------|---------------------------|
| id       | Integer   | 필수   | 일정 고유 번호                  |
| userId   | Integer   | 필수   | 사용자 고유 번호                 |
| username | String    | 필수   | 일정 작성자 이름                 |
| title    | String    | 필수   | 일정 제목                     |
| content  | String    | 선택   | 일정 상세 내용                  |
| createAt | Timestamp | 필수   | 일정 작성 시간                  |
| updateAt | Timestamp | 필수   | 일정 작성 시간 (수정시 수정 시간으로 변경) |


---
## 날짜 선택 일정 조회
PathVariable로 받은 숫자 값 전의 날짜들의 일정을 보여줍니다.

예시) /schedules/date/60 일시,
<br>현재 날짜 -60 일 까지의 일정 목록을 Response

|메서드| 요청 URL                                        |
|---|-----------------------------------------------|
|GET| http://{SERVER_URL}/api/schedules/date/{days} |

**Request**

- Request 없음


**Response**
```json
[
    {
        "id": 1,
        "userId": 1,
        "userName": "이름1",
        "title": "제목1",
        "contents": "내용1",
        "createAt": "2024-11-07T07:42:10.000+00:00",
        "updateAt": "2024-11-07T07:42:10.000+00:00"
    },
    {
        "id": 2,
        "userId": 2,
        "userName": "이름2",
        "title": "제목2",
        "contents": "내용2",
        "createAt": "2024-11-07T09:00:10.000+00:00",
        "updateAt": "2024-11-07T09:00:10.000+00:00"
    },
    {
        "id": 3,
        "userId": 3,
        "userName": "이름3",
        "title": "제목3",
        "contents": "내용3",
        "createAt": "2024-11-07T09:02:56.000+00:00",
        "updateAt": "2024-11-07T09:02:56.000+00:00"
    }
]
```

| 파라미터     | 타입        | 필수여부 | 설명                        |
|----------|-----------|------|---------------------------|
| id       | Integer   | 필수   | 일정 고유 번호                  |
| userId   | Integer   | 필수   | 사용자 고유 번호                 |
| username | String    | 필수   | 일정 작성자 이름                 |
| title    | String    | 필수   | 일정 제목                     |
| content  | String    | 선택   | 일정 상세 내용                  |
| createAt | Timestamp | 필수   | 일정 작성 시간                  |
| updateAt | Timestamp | 필수   | 일정 작성 시간 (수정시 수정 시간으로 변경) |

---
## 선택 일정 수정
선택한 일정을 수정합니다.

**Request**

```json
{
"password" : "비밀번호",
"title" : "수정된 제목",
"content" : "수정된 내용"
}
```

|메서드|요청 URL|
|---|---|
|PUT|http://{SERVER_URL}/api/schedules/{scheduleId}|


|  파라미터 |타입   | 필수여부  | 설명  |
|---|---|---|---|
| password  | String  | 필수  | 일정 비밀 번호  | 
| title | String | 필수 | 일정 제목|
| content | String | 선택 | 일정 상세 내용|

**Response**

```json
{
  "id": 1,
  "userId": 1,
  "userName": "이름",
  "title": "수정된 제목",
  "contents": "수정된 내용",
  "createAt": "2024-11-07T09:00:10.349+00:00",
  "updateAt": "2024-11-08T09:00:10.349+00:00"  //수정한 날짜로 변경
}
```

| 파라미터     | 타입        | 필수여부 | 설명                        |
|----------|-----------|------|---------------------------|
| id       | Integer   | 필수   | 일정 고유 번호                  |
| userId   | Integer   | 필수   | 사용자 고유 번호                 |
| username | String    | 필수   | 일정 작성자 이름                 |
| title    | String    | 필수   | 일정 제목                     |
| content  | String    | 선택   | 일정 상세 내용                  |
| createAt | Timestamp | 필수   | 일정 작성 시간                  |
| updateAt | Timestamp | 필수   | 일정 작성 시간 (수정시 수정 시간으로 변경) |

---

## 선택 일정 삭제
선택한 일정을 삭제합니다.

**Request**

```json
{
"password" : "비밀번호"
}
```

|메서드|요청 URL|
|---|---|
|DELETE|http://{SERVER_URL}/api/schedules/{scheduleId}|


|  파라미터 |타입   | 필수여부  | 설명  |
|---|---|---|---|
| password  | String  | 필수  | 일정 비밀 번호  | 

---

## 사용자 생성
사용자를 생성합니다.

|메서드| 요청 URL                                  |
|---|-----------------------------------------|
|POST| http://{SERVER_URL}/api/schedules/users |

**Request**

```json
{
"name" : "사용자 명",
"mail" : "사용자 메일"
}
```


| 파라미터 |타입   | 필수여부 | 설명     |
|------|---|------|--------|
| name | String  | 필수   | 사용자 이름 | 
| mail | String  | 선택   | 사용자 메일 |

**Response**
```json
{
  "id": 1,
  "name": "이름",
  "mail": "mail@mail",
  "createAt": "2024-11-07T11:00:44.477+00:00",
  "updateAt": "2024-11-07T11:00:44.477+00:00"
}
```

| 파라미터    | 타입        | 필수여부 | 설명                        |
|---------|-----------|------|---------------------------|
| id      | Integer | 필수 | 사용자 고유 번호                 |
| name    | String    | 필수   | 사용자 이름                    |
| mail    | String    | 선택   | 사용자 메일                    |
| createAt | Timestamp | 필수   | 일정 작성 시간                  |
| updateAt | Timestamp | 필수   | 일정 작성 시간 (수정시 수정 시간으로 변경) |


---

# ERD

![스크린샷 2024-11-08 오전 10 40 14](https://github.com/user-attachments/assets/1d2becb9-a8f9-4799-9b3f-67c3dd2e9433)




