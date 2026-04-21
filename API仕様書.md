# API一覧（社員管理アプリ）

## 1. 認証系API

| No | API名 | エンドポイント | メソッド | 認証 | 権限 | 概要 |
|----|------|--------------|----------|------|------|------|
| 1 | registerUser | /api/auth/register | POST | 不要 | 全員 | ユーザーを新規登録 |
| 2 | login | /api/auth/login | POST | 不要 | 全員 | ログインしJWTを取得 |

---

## 2. 管理者専用API（admin）

| No | API名 | エンドポイント | メソッド | 認証 | 権限 | 概要 |
|----|------|--------------|----------|------|------|------|
| 3 | getEmployeesForADMIN | /api/employees | GET | 必要 | 全員 | 社員一覧を取得(全ての情報) |
| 4 | 社員登録 | /api/employees | POST | 必要 | admin | 社員情報を新規登録 |
| 5 | 社員更新 | /api/employees/{id} | PUT | 必要 | admin | 社員情報を更新 |
| 6 | 社員削除 | /api/employees/{id} | DELETE | 必要 | admin | 社員情報を削除 |
| 7 | CSVインポート | /api/employees/import | POST | 必要 | admin | CSVで一括登録・更新 |

---

## 3. 社員専用API（employee）

| No | API名 | エンドポイント | メソッド | 認証 | 権限 | 概要 |
|----|------|--------------|----------|------|------|------|
| 10 | getEmployeesForEMPLOYEE | /api/employees | GET | 必要 | 社員 | 社員一覧を取得（氏名・メールアドレスのみ）
| 11 | 自分の情報取得 | /api/employees/me | GET | 必要 | employee | 自身の情報を取得 |
| 12 | 自分の情報更新 | /api/employees/me | PUT | 必要 | employee | 氏名・メールアドレスのみ更新 |

---

## 補足

### ■ 命名ルール
- エンドポイントは名詞で統一する（例：`/employees`）
- 操作はHTTPメソッドで表現する
  - GET：取得
  - POST：作成
  - PUT：更新
  - DELETE：削除

### ■ 認証
- JWT認証を前提とする
- ヘッダーに付与する
- 認証が必要なAPIは `Authorization: Bearer {token}` を使用する

### ■ 権限
- admin：全操作可能
- employee：閲覧 + 自身の情報更新のみ

## 4. レスポンス形式

### 成功
```json
{
  "status": "SUCCESS",
  "data": {},
  "message": ""
}
```

### エラー
```json
{
  "status": "ERROR",
  "message": "error message"
}
```

### ステータスコード
| コード | 意味 |
|-------|------|
| 200 | 成功(送信・更新) |
| 201 | 成功(作成) |
| 400 | 入力エラー |
| 401 | 認証エラー |
| 404 | データなし |
| 500 | サーバーエラー |

---

# API仕様

### ◼︎POST /api/auth/register (ユーザー登録)

#### リクエスト
```json
{
  "email": "example@example.com",
  "password": "password123",
  "role": "ADMIN",
}
```

#### レスポンス
```json
{
  "status": "SUCCESS"
}
```

### ◼︎POST /api/auth/login (ログイン)

#### リクエスト
```json
{
  "email": "example@exapmle.com",
  "password": "password123",
}
```

#### レスポンス
```json
{
  "status": "SUCCESS",
  "data": {
    "token": "jwt-token"
  }
}
```

### ◼︎GET /api/employees (社員一覧を取得・管理者)

#### レスポンス
```json
{
  "status": "SUCCESS",
  "data": [
    {
      "id": "11111",
      "name": "山田太郎",
      "email": "example@example.com",
      "department": "営業部",
      "position": "部長",
      "joinningDate": "2000/01/01",
      "status": "ACTIVE"
    }
  ]
}
```

### ◼︎POST /api/employees (社員情報を登録)

#### リクエスト
```json
{
  "name": "山田太郎",
  "email": "example@example.com",
  "department": "営業部",
  "position": "部長",
  "joinningDate": "2000/01/01",
  "status": "ACTIVE"
}
```

#### レスポンス
```json
{
  "status": "SUCCESS"
}
```
## ◼︎PUT /api/employees/{id} (社員情報を更新)

#### リクエスト
```json
{
  "name": "山田太郎",
  "email": "example@example.com",
  "department": "営業部",
  "position": "部長",
  "joinningDate": "2000/01/01",
  "status": "ACTIVE"
}
```

#### レスポンス
```json
{
  "status": "SUCCESS"
}
```

## ◼︎DELETE /api/employees/{id} (社員情報を削除)

#### レスポンス
```json
{
  "status": "SUCCESS"
}
```

## ◼︎POST /api/employees/import (CSVで一括登録・更新)

#### リクエスト
・CSVファイル

#### レスポンス
```json
{
  "status": "SUCCESS"
}
```

## ◼︎GET /api/employees(社員一覧を取得・社員)
#### レスポンス
```json
{
  "status": "SUCCESS",
  "data": [
    {
      "name": "山田太郎",
      "email": "example@example.com",
    }
  ]
}
```

## ◼︎GET /api/employees/me (自身の社員情報を取得)
#### レスポンス
```json
{
  "status": "SUCCESS",
  "data":{
      "name": "山田太郎",
      "email": "example@example.com",
  }
}
```
## ◼︎PUT /api/employees/me (自身の社員情報を更新)
#### リクエスト
```json
{
  "name": "山田太郎",
  "email": "example@example.com",
}
```

#### レスポンス
```json
{
  "status": "SUCCESS"
}
```