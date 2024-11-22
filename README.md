# Schedule management app
## API 명세서
### 1. 일정 생성

| Method | URL | Description |
| --- | --- | --- |
| POST | /schedules | 할일, 작성자명, 비밀번호, 작성/ 수정일을 넘겨받아서 저장한다. |

__Request__
```json
{
  "task": "요리",
  "author": "스파르타",
  "password": "sdfaf123"
}
```
__Response__
```json
{
  "id": 1,
  "task": "요리",
  "author": "스파르타",
  "createAt": "2024-11-04 10:06:06"
}
```

### 2. 전체 일정 조회

| Method | URL | Description | 상태코드 |
| --- | --- | --- | --- |
| GET | /schedules | 수정일이나 작성자명 조건을 충족하는 목록을 조회한다.  | 200 : 정상조회 |

__Request__
```
1. 작성자명
/schedules?author=eunyoung
2. 수정일('-'을 넣는 요구사항이 있음)
/schedules?revisionDate=2024-11-04
3. 작성자명과 수정일
/schedules?author=eunyoung&revisionDate=2024-11-04
```
__Response__
```json
[
  {
    "id": 1,
    "task": "요리",
    "author": "eunyoung",
    "createAt": "2024-11-04"
  },
  {
    "id": 2,
    "task": "운동",
    "author": "eun",
    "createAt": "2024-11-05"
  }
]
```

### 3. 선택 일정 조회

| Method | URL | Description | 상태코드 |
| --- | --- | --- | --- |
| GET | /schedules/{id} | 선택한 일정 단건의 정보를 조회 | 200 : 정상조회 |

__Request__
```
1. 작성자명
/schedules/{1}?author=eunyoung
2. 수정일('-'을 넣는 요구사항이 있음)
/schedules/{1}?revisionDate=2024-11-04
3. 작성자명과 수정일
/schedules/{1}?author=eunyoung&revisionDate=2024-11-04
```
__Response__
```json
{
  "id": 1,
  "task": "요리",
  "author": "eunyoung"
  "createAt": "2024-11-04"
}
```

### 4. 선택 일정 수정

| Method | URL | Description | 상태코드 |
| --- | --- | --- | --- |
| PATCH | /schedules/{id} | 선택한 일정 내용 중 할일이나 작성자명을 수정한다. | 200 : 정상수정 |

__Request__
```json
{
  "task": "운동",
  "author": "eun",
  "password": "sdfaf123"
}
```
__Response__
```json
{
  "id": 1,
  "task": "운동",
  "author": "eun",
  "revisionAt": "2024-11-04 10:33:01"
}
```

### 5. 선택 일정 삭제

| Method | URL | Description | 상태코드 |
| --- | --- | --- | --- |
| DELETE | /schedules/{id} | 선택한 일정을 삭제한다. | 200 : 정상삭제 |

__Request__
```json
{
  "task": "운동",
  "author": "eun",
  "password": "sdfaf123"
}
```
__Response__
```json
{
  state: "200"
}
```

## ERD
![image](https://github.com/user-attachments/assets/b295f76d-f8ed-49cb-9384-7f51eeece34e)

## SQL
### 1. 테이블 생성 (Create)
```sql
CREATE DATABASE sparta;

CREATE TABLE schedule(
	id VARCHAR(50) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	task VARCHAR(100) NULL,
  name VARCHAR(50) NULL,
  password VARCHAR(100) NULL,
  created_at DATETIME NULL,
  modified_at DATETIME NULL
);
```
### 2. 일정 생성 (Insert)
```sql
INSERT INTO schedule (task, name, password, created_at, modified_at)
VALUES('공부', 'asdf123', 'fdsf554', now(), now());
```
### 3. 전체 일정 조회 (Select)
```sql
SELECT s.task
FROM schedule s;
```
### 4. 선택 일정 조회 (Select)
```sql
SELECT *
FROM schedule s
WHERE s.id = '1';
```
### 5. 선택 일정 수정 (Update)
```sql
UPDATE schedule s
SET s.task = "공부", s.modified_at = "2024-11-1"
WHERE s.name = "asdf123"
  AND s.password = "fdsf554"
```
### 6. 선택 일정 삭제 (Delete)
```sql
DELETE FROM schedule s
WHERE s.id = '1' AND s.password = 'fdsf554';
```
