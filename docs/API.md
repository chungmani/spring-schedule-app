# API 명세서

## 1. 일정 API (로그인 인증 필요)

### 1-1. 일정 생성

**POST** `/api/schedules`

#### Request

```json
{
  "title": "",
  "content": ""
}
```

#### Response

**201 Created**

```json
{
  "id": 1,
  "title": "",
  "content": "",
  "name": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

---

### 1-2. 일정 전체 조회

**GET** `/api/schedules`

#### Response

**200 OK**

```json
[
  {
    "id": 1,
    "title": "",
    "content": "",
    "name": "",
    "createdAt": "",
    "modifiedAt": ""
  }
]
```

---

### 1-3. 일정 단건 조회

**GET** `/api/schedules/{id}`

#### Response

**200 OK**

```json
{
  "id": 1,
  "title": "",
  "content": "",
  "name": "",
  "createdAt": "",
  "modifiedAt": "",
  "comments": [
    {
      "commentContent": ""
    }
  ]
}
```

> 일정 단건 조회 시 해당 일정에 작성된 댓글 목록을 함께 조회합니다.

---

### 1-4. 일정 수정

**PUT** `/api/schedules/{id}`

#### Request

```json
{
  "title": "",
  "content": ""
}
```

#### Response

**200 OK**

```json
{
  "id": 1,
  "title": "",
  "content": "",
  "name": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

---

### 1-5. 일정 삭제

**DELETE** `/api/schedules/{id}`

#### Response

**204 No Content**

---

# 2. 유저 API

### 2-1. 회원가입

**POST** `/api/auth/signup`

#### Request

```json
{
  "name": "",
  "email": "",
  "password": ""
}
```

#### Response

**201 Created**

```json
{
  "id": 1,
  "name": "",
  "email": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

> 비밀번호는 Response에 포함하지 않습니다.

---

### 2-2. 유저 전체 조회

**GET** `/api/users`

#### Response

**200 OK**

```json
[
  {
    "id": 1,
    "name": "",
    "email": "",
    "createdAt": "",
    "modifiedAt": ""
  }
]
```

---

### 2-3. 유저 단건 조회 (이메일로 조회)

**GET** `/api/users?email={email}`

#### Response

**200 OK**

```json
{
  "id": 1,
  "name": "",
  "email": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

---

### 2-4. 유저 수정

**PATCH** `/api/users/{id}`

#### Request

```json
{
  "name": ""
}
```

#### Response

**200 OK**

```json
{
  "id": 1,
  "name": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

---

### 2-5. 유저 삭제

**DELETE** `/api/users/{id}`

#### Response

**204 No Content**

---

# 3. 인증 API

### 3-1. 로그인

**POST** `/api/auth/login`

#### Request

```json
{
  "email": "",
  "password": ""
}
```

#### Response

**200 OK**

> 로그인 성공 시 Session을 생성하고 Cookie를 통해 Session ID를 관리합니다.

---

### 3-2. 로그아웃

**POST** `/api/auth/logout`

#### Response

**200 OK**

> 로그인된 사용자의 Session을 만료시킵니다.

---

# 4. 댓글 API

### 4-1. 댓글 생성

**POST** `/api/schedules/{id}/comments`

#### Request

```json
{
  "content": ""
}
```

#### Response

**201 Created**

```json
{
  "id": 1,
  "content": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

---

### 4-2. 댓글 수정

**PUT** `/api/schedules/{id}/comments/{commentId}`

#### Request

```json
{
  "content": ""
}
```

#### Response

**200 OK**

```json
{
  "id": 1,
  "content": "",
  "createdAt": "",
  "modifiedAt": ""
}
```

---

### 4-3. 댓글 삭제

**DELETE** `/api/schedules/{id}/comments/{commentId}`

#### Response

**204 No Content**
