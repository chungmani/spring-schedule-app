# 📋 API 명세서

## 1. 일정 생성

### 기본 정보

| 항목 | 내용 |
|---|---|
| API명 | 일정 생성 |
| Method | POST |
| URL | `/api/schedules` |

### Request

#### Header

| 이름 | 타입 | 필수 | 설명 |
|---|---|---|---|
| Content-Type | String | O | application/json |

#### Body

```json
{
  "title": "",
  "content": "",
  "author": "",
  "password": ""
}
```

### Response

| Status | 성공 | 201 Created |

```json
{
  "id": "1",
  "title": "",
  "content": "",
  "author": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

*!비밀번호는 응답에 포함하지 않습니다.*


## 2. 일정 조회 (단건조회)

### 기본 정보

| 항목 | 내용                    |
|---|-----------------------|
| API명 | 일정 조회                 |
| Method | GET                   |
| URL | `/api/schedules/{Id}` |

### Request

#### Path Valiable

| 이름 | 타입   | 필수 | 설명        |
|----|------|---|-----------|
| id | Long | O | 일정 고유 식별자 |

### Response

| Status | 성공 | 200 OK|

```json
{
  "id": "1",
  "title": "",
  "content": "",
  "author": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

## 3. 일정 조회 (전체조회 / 조건 조회)

### 기본 정보

| 항목 | 내용               |
|---|------------------|
| API명 | 일정 조회            |
| Method | GET              |
| URL | `/api/schedules` |

### Response

| Status | 성공 | 200 OK|

```json
[
  {
    "id": "1",
    "title": "",
    "content": "",
    "author": "",
    "createdAt": "",
    "modifiedAt": ""
  }
]
```

## 4. 일정 수정

### 기본 정보

| 항목 | 내용                    |
|---|-----------------------|
| API명 | 일정 수정                 |
| Method | PUT                   |
| URL | `/api/schedules/{Id}` |

### Request

#### Path Valiable

| 이름 | 타입   | 필수 | 설명        |
|----|------|---|-----------|
| id | Long | O | 일정 고유 식별자 |

#### Body

```json
{
  "title": "",
  "author": ""
}
```

### Response

| Status | 성공 | 200 OK|

```json
{
  "id": "1",
  "title": "",
  "author": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

## 5. 일정 삭제

### 기본 정보

| 항목 | 내용                    |
|---|-----------------------|
| API명 | 일정 삭제                 |
| Method | DELETE                |
| URL | `/api/schedules/{Id}` |

### Request

#### Path Valiable

| 이름 | 타입   | 필수 | 설명        |
|----|------|---|-----------|
| id | Long | O | 일정 고유 식별자 |

#### Body

```json
{
  "password": ""
}
```

### Response

| Status | 성공 | 200 OK|

```json
{
  "message": "일정이 삭제되엇습니다."
}
```
