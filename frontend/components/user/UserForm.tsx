import { nameDecision, mailDecision } from "../../decision/InputDecision";
import { useEffect } from "react";
import Button from "../common/Button";
import Input from "../common/Input";
import { userEditApi } from "../../decision/UserApi";
import { useState } from "react";
import UserFormTable from "../../components/user/UserFormTable";
import { Employee } from "../../decision/AdminApi";
import { userOnlyApi } from "../../decision/UserApi";

/*
 * ユーザー自身の情報を取得・編集するフォームコンポーネント。
 *
 * name, email を useState で入力状態として管理する。
 *
 * employee は Employee型またはnullで管理し、
 * APIから取得した現在のユーザー情報を保持する。
 *
 * useEffect により画面初回表示時に loadEmployeeMe を実行し、
 * ログインユーザー情報を取得する。
 *
 * 取得成功時：
 *   - setEmployees(data) でユーザー情報保存
 *   - setName(data.name) でinput初期値設定
 *   - setMail(data.email) でinput初期値設定
 *
 * 取得失敗時：
 *   alertでエラーメッセージ表示
 *
 * handleEdit では入力チェック後、
 * userEditApi を利用してユーザー情報更新を行う。
 *
 * 更新成功時：
 *   - 最新情報再取得
 *   - 更新完了メッセージ表示
 *
 * 更新失敗時：
 *   alertでエラーメッセージ表示
 */

export default function UserForm() {

  const [name, setName] = useState("");
  const [email, setMail] = useState("");

  const [employee, setEmployees] = useState<Employee | null>(null);

  useEffect(() => {
    loadEmplpyeeMe();
  }, []);

  async function loadEmplpyeeMe() {
    try {
      const data = await userOnlyApi();
      setEmployees(data);

      // input初期値
      //　undifindであれば、空文字でバックエンドから取得
      setName(data.name ?? "");
      setMail(data.email ?? "");
    } catch {
      alert("ユーザー情報は見当たりませんでした");
    }
  }

  async function handleEdit() {
    // 名前チェック
    if (!nameDecision(name)) {
      alert("名前が不正です");
      return;
    }

    // メールチェック
    if (!mailDecision(email)) {
      alert("メールアドレスが不正です");
      return;
    }

    try {
      if(name!=="" || email !==""){
      await userEditApi(name, email);
      loadEmplpyeeMe();
      alert("更新しました");
      }else{
      alert("更新は行われませんでした");
      }
    } catch {
      alert("更新に失敗しました");
    }
  }

  return (
    <div>
      {employee && <UserFormTable employee={employee} />}

      <div>
        <Input label="氏名" type="text" placeholder="" onChange={setName} />
        <Input
          label="メールアドレス"
          type="text"
          placeholder="@example.com"
          onChange={setMail}
        />
        <Button text="登録" onClick={handleEdit} />
      </div>
    </div>
  );
}
