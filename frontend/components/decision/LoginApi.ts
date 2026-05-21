import { mailDecision, passwordDecision } from "../decision/InputDecision";

/*
* ログインを行い、JWTを保存
*/

export async function loginApi(email: string, password: string) {
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
        const response = await fetch("/api/auth/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email,
            password,
          }),
        });

        const data = await response.json();

        // token保存
        localStorage.setItem("token", data.token);

        if (response.ok) {
          window.location.href = "/UserListPage";
        } else {
          alert("アクセスに失敗しました");
        }
      }
    }
  } catch {
    alert("ログインに失敗しました");
  }
}
