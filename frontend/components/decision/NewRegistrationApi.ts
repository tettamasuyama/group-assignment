import { mailDecision, passwordDecision } from "../decision/InputDecision";

/*
* ユーザーを新規登録
*/

export async function newRegistrationApi(
  email: string,
  password: string,
  isAdmin: boolean,
) {
  //enumのroleフィールドへ
  const role = isAdmin ? "ROLE_ADMIN" : "ROLE_USER";
  try {

    if(email===""){
    alert("メールアドレスが未入力です");
    return
    }

    if(password===""){
    alert("パスワードが未入力です");
    return
    }

    if (mailDecision(email)) {
      if (passwordDecision(password)) {
        const response = await fetch("/api/auth/register", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email,
            password,
            role,
          }),
        });

        // レスポンスbody取得
        const data = await response.json();

        // token保存
        localStorage.setItem("token", data.token);

        // role保存
        localStorage.setItem("role", role);

        if (response.ok) {
          if (role === "ROLE_ADMIN") {
            window.location.href = "/AdminListPage";
          } else {
            window.location.href = "/UserListPage";
          }
        } else {
          alert("アクセスに失敗しました");
        }
      }
    }
  } catch {
    alert("新規登録に失敗しました");
  }
}
