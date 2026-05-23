import { useState } from "react";
import Button from "../components/common/Button";
import Input from "../components/common/Input";
import { loginApi } from "../decision/LoginApi";
import { newRegistrationApi } from "../decision/NewRegistrationApi";


/*
*　ログインページのコンポーネント
*  staiteにより入力された内容を、
*  ログイン専用のApi関数か、新規登録のApi関数
*  の引数として処理を渡し、バックエンドの通信を可能とする。
*  実行形式はボタンの押下。(ログインボタン、新規登録ボタン)
* 
*   state利用 ･･･ email, password ,isAdmin
*  『管理者として登録』のチェックボックスにチェックすることで
* 　isAdminのstateを実行。
* 
*  loginApi･･･ログイン専用のApi関数
*  newRegistrationApi･･･新規登録専用のApi関数
* 
*/

export default function LoginPage() {
  // const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);


  return (
    <div>
      <h1>社員管理システム</h1>
      <Input
        label="メールアドレス"
        type="text"
        placeholder="@example.com"
        onChange={setEmail}
      />
      <Input
        label="パスワード"
        type="password"
        placeholder=""
        onChange={setPassword}
      />
      <Button text="ログイン" onClick={() => loginApi(email, password)} />
      <Button
        text="新規登録"
        onClick={() => newRegistrationApi(email, password, isAdmin)}
      />

      <input
        type="checkbox"
        checked={isAdmin}
        onChange={(e) => setIsAdmin(e.target.checked)}
      />
      <label>管理者として登録</label>
    </div>
  );
}
