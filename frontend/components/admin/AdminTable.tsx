// src/components/admin/AdminTable.tsx
import { Employee } from "../../decision/AdminApi";


/*
 * 管理者用社員一覧テーブルコンポーネント。
 *
 * Propsとして Employee型配列(employees)を受け取る。
 *
 * employees.map() を利用して、
 * 社員情報を1件ずつテーブル行(tr)として表示する。
 *
 * 表示項目：
 *   - 社員番号
 *   - 氏名
 *   - メールアドレス
 *   - 部署
 *   - 役職
 *   - 入社日
 *   - 在籍状態
 *
 * employee.employeeNumber は
 * Reactが各行を識別するためのkeyとして利用している。
 *
 * table border={1} は
 * テーブル枠線を1pxで表示する設定。
 *
 * このコンポーネントは表示専用であり、
 * state管理やAPI通信は行わない。
 *
 * 管理者画面で社員情報一覧を表示する役割を持つ。
 */

type Props = {
  employees: Employee[];
};

export default function AdminTable({ employees }: Props) {
  return (
    //表の枠線を1pxで表示
    <table border={1}>
      <thead>
        <tr>
          <th>社員番号</th>
          <th>氏名</th>
          <th>メール</th>
          <th>部署</th>
          <th>役職</th>
          <th>入社日</th>
          <th>在籍状態</th>
        </tr>
      </thead>

      <tbody>
        {employees.map((employee) => (
          <tr key={employee.employeeNumber}>
            <td>{employee.employeeNumber}</td>

            <td>{employee.name}</td>

            <td>{employee.email}</td>

            <td>{employee.department}</td>

            <td>{employee.role}</td>

            <td>{employee.joinDate}</td>

            <td>{employee.status}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
