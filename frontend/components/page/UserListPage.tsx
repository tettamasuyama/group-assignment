import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Employee, getEmployeeList } from "../decision/AdminApi";
import Button from "../components/common/Button";
import LogoutButton from "../components/common/LogoutButton";
import UserTable from "../components/user/UserTable";

/*
 * Employee型の配列をstateとして管理する。
 * 初期値は空配列([])。
 *
 * useEffectによって画面初回表示時にloadEmployeeを実行し、
 * APIから社員一覧情報を取得する。
 *
 * 取得成功時：
 *   setEmployees(data) によりstateを更新。
 *
 * 取得失敗時：
 *   alert("取得失敗") を表示する。
 */

export default function UserListPage() {
  
  const navigate = useNavigate();
  const [employee, setEmployees] = useState<Employee[]>([]);

  useEffect(() => {
    loadEmplpyee();
  }, []);

  async function loadEmplpyee() {
    try {
      const data = await getEmployeeList();
      setEmployees(data);
    } catch {
      alert("取得失敗");
    }
  }

  return (
    <div>
      <h1>社員一覧画面</h1>

      <UserTable employee={employee} />

      <Button text="編集" onClick={() => navigate("/useredit")} />
      <LogoutButton />
    </div>
  );
}
