import { mailDecision, passwordDecision } from "../decision/InputDecision";

/*
 * ログインを行い、JWTを保存
 */

export async function loginApi(email: string, password: string) {
  try {
    if (email === "") {
      alert("メールアドレスが未入力です");
      return;
    }

    if (password === "") {
      alert("パスワードが未入力です");
      return;
    }

    if (mailDecision(email)) {
      if (passwordDecision(password)) {
        const response = await fetch(
          // "/api/auth/login"
          "http://localhost:8080/api/auth/login",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              email,
              password,
            }),
          },
        );

        const data = await response.json();

        // console.log(data);
        // console.log("role =", data.role);

        if (response.ok) {
          // token保存
            localStorage.setItem("token", data.token);

          if (data.role === "ADMIN") {
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
    alert("ログインに失敗しました");
  }
}
