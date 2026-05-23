import AdmiForm from "../components/admin/AdminForm";
import Button from "../components/common/Button";
import LogoutButton from "../components/common/LogoutButton";
import { useNavigate } from "react-router-dom";

/*
 * 管理者用社員編集画面コンポーネント。
 *
 * useNavigate を利用して画面遷移機能を使用する。
 *
 * AdmiForm コンポーネントを表示し、
 * 管理者による社員情報編集機能を提供する。
 *
 * 「社員リスト」ボタン押下時は
 * navigate("/employeelist") を実行し、
 * 管理者用社員一覧画面へ遷移する。
 *
 * LogoutButton によりログアウト処理を実行可能。
 *
 * このコンポーネントは
 * 画面構成と画面遷移管理を担当し、
 * 実際の編集処理は AdmiForm に分離している。
 */

export default function EmployeeEditPage() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>編集ページ</h1>

      <AdmiForm />

      <Button text="社員リスト" onClick={() => navigate("/employeelist")} />
      <LogoutButton />
    </div>
  );
}
