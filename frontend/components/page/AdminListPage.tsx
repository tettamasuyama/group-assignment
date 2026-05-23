import { useEffect, useState } from "react";
import AdminTable from "../components/admin/AdminTable";
import { Employee, getEmployeeList } from "../decision/AdminApi";
import { useNavigate } from "react-router-dom";
import Button from "../components/common/Button";
import LogoutButton from "../components/common/LogoutButton";

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

export default function EmployeeListPage() {
  const navigate = useNavigate();

  const [employees, setEmployees] = useState<Employee[]>([]);

  useEffect(() => {
    loadEmployees();
  }, []);

  async function loadEmployees() {
    try {

      const data = await getEmployeeList();

      setEmployees(data);
    } catch {
      alert("ユーザー情報が見つかりませんでした");
    }
  }

  return (
    <div>
      <h1>社員リスト</h1>

      <AdminTable employees={employees} />

      <Button text="編集" onClick={() => navigate("/employeeedit")} />
      <LogoutButton />
    </div>
  );
}
