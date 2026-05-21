import { useNavigate } from "react-router-dom";
import Button from "../components/common/Button";
import LogoutButton from "../components/common/LogoutButton";
import UserForm from "../components/user/UserForm";

/*
 * ユーザー情報編集画面コンポーネント。
 *
 * useNavigate を利用して画面遷移機能を使用する。
 *
 * UserForm コンポーネントを表示し、
 * ユーザー情報の取得・編集機能を提供する。
 *
 * LogoutButton によりログアウト処理を実行可能。
 *
 * 「社員リスト」ボタン押下時は
 * navigate("/userlist") を実行し、
 * 社員一覧画面へ遷移する。
 *
 * このコンポーネントは
 * 画面レイアウト・画面遷移管理を担当しており、
 * 実際のフォーム処理は UserForm に分離している。
 */

export default function UserEditPage() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>登録内容の編集</h1>

      <UserForm/>
      <LogoutButton />
      <Button text="社員リスト" onClick={() => navigate("/userlist")} />
    </div>
  );
}
