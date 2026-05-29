import { Employee } from "./AdminApi";

/*
*	社員一覧を取得（氏名・メールアドレスのみ）
*/

export async function userApi(): Promise<Employee[]> {

  const token = localStorage.getItem("token");

  const response = await fetch("/api/employees", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      //ログイン済みのユーザー
      Authorization: `Bearer ${token}`,
    },
  });
  if (!response.ok) {
    throw new Error("社員一覧の取得に失敗しました");
  }
  return await response.json();
}



/*
*　自身の情報を取得
*/
export async function userOnlyApi(): Promise<Employee> {

  const token = localStorage.getItem("token");

  const response = await fetch("/api/employees/me", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      //ログイン済みのユーザー
      Authorization: `Bearer ${token}`,
    },
  });
  if (!response.ok) {
    throw new Error("社員情報の取得に失敗しました");
  }
  return await response.json();
}

/*
* 氏名・メールアドレスのみ更新
*/

export async function userEditApi(name: string,email: string){

  const token = localStorage.getItem("token");

  const response = await fetch("/api/employees/me",{
    method: "POST" ,
    headers: {
      "Content-Type":"application/json",
       //ログイン済みのユーザー
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      name,
      email
    })
  });
   if (!response.ok) {
    throw new Error("登録内表の編集に失敗しました");
  }

}

