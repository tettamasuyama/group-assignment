import { Employee } from "../../decision/AdminApi";
type Props = {
  employee: Employee;
};

/*
 * ユーザー情報表示用テーブルコンポーネント。
 *
 * Propsとして Employee型のemployeeを受け取る。
 *
 * 受け取ったemployee情報を利用して、
 * 名前とメールアドレスをテーブル表示する。
 *
 * employee.employeeNumber は
 * Reactが要素を識別するためのkeyとして使用している。
 *
 * このコンポーネント自体は状態管理(useState)を持たず、
 * 渡されたデータを表示するだけの表示専用コンポーネント。
 */

export default function UserFormTable({ employee }: Props) {
  return (
    <table border={1}>
      <thead>
        <tr>
          <th>名前</th>
          <th>メールアドレス</th>
        </tr>
      </thead>
      <tbody>
          <tr key={employee.employeeNumber}>
            <td>{employee.name}</td>
            <td>{employee.email}</td>
          </tr>
      </tbody>
    </table>
  );
}