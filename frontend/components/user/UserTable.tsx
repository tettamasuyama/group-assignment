import { Employee } from "../../decision/AdminApi";

type Props = {
  employee: Employee[];
};

/*
 * 社員一覧表示用テーブルコンポーネント。
 *
 * Propsとして Employee型配列(employee)を受け取る。
 *
 * employee.map() を利用して、
 * 配列内の社員情報を1件ずつ取り出し、
 * テーブルの行(tr)として一覧表示する。
 *
 * 表示項目：
 *   - 名前
 *   - メールアドレス
 *
 * employee.employeeNumber は
 * Reactが各行を識別するためのkeyとして使用している。
 *
 * このコンポーネントは表示専用であり、
 * state管理やAPI通信は行わない。
 */

export default function UserTable({ employee }: Props) {
  return (
    <table border={1}>
      <thead>
        <tr>
          <th>名前</th>
          <th>メールアドレス</th>
        </tr>
      </thead>
      <tbody>
        {employee.map((employee) => (
          <tr key={employee.employeeNumber}>
            <td>{employee.name}</td>
            <td>{employee.email}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
