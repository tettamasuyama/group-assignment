import { Employee } from "../../decision/AdminApi";
type Props = {
  employee: Employee;
};
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