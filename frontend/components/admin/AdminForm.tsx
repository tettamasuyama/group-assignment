import {
  employeeNumberDecision,
  nameDecision,
  mailDecision,
} from "../../decision/InputDecision";
import { employeeRegistration, employeeDelete } from "../../decision/AdminApi";
import { useState } from "react";
import Input from "../common/Input";
import Date from "../common/Date";
import BulkRegistration from "../common/BulkRegistration";
import Button from "../common/Button";


/*
 * 管理者用社員登録・削除フォームコンポーネント。
 *
 * useState を利用して、
 * 社員情報入力値をstateとして管理する。
 *
 * 管理項目：
 *   - 社員番号
 *   - 氏名
 *   - メールアドレス
 *   - 部署
 *   - 役職
 *   - 在籍状態
 *   - 入社日
 *
 * handleRegister では入力値チェック後、
 * employeeRegistration APIを利用して
 * 社員登録処理を実行する。
 *
 * バリデーション内容：
 *   - 社員番号形式チェック
 *   - 名前形式チェック
 *   - メールアドレス形式チェック
 *
 * handleDelete では入力値チェック後、
 * employeeDelete APIを利用して
 * 社員削除処理を実行する。
 *
 * 削除は employeeNumber を利用して行う。
 *
 * select要素を利用して、
 * 部署・役職・在籍状態を選択式入力にしている。
 *
 * Date コンポーネントでは
 * 入社日入力を管理する。
 *
 * BulkRegistration コンポーネントでは
 * CSVによる一括登録機能を提供する。
 *
 * このコンポーネントは
 * 管理者用の社員登録・削除・一括登録機能をまとめて管理している。
 */

export default function AdmiForm() {
  const [employeeNumber, setEmployeeNumber] = useState("");
  const [employeeName, setEmployeeName] = useState("");
  const [email, setEmail] = useState("");
  const [department, setDepartment] = useState("");
  const [role, setRole] = useState("");
  const [status, setStatus] = useState("");
  const [joinDate, setJoinDate] = useState("");

  // 登録
  const handleRegister = async () => {
    
    // 社員番号チェック
    if (!employeeNumberDecision(employeeNumber)) {
      return;
    }

    // 名前チェック
    if (!nameDecision(employeeName)) {
      return;
    }

    // メールチェック
    if (!mailDecision(email)) {
      return;
    }

    await employeeRegistration({
      employeeNumber,
      name: employeeName,
      email,
      department,
      role,
      status,
      joinDate,
    });
  };

  // 削除（社員番号で削除するのが普通）
  const handleDelete = async () => {

        // 社員番号チェック
    if (!employeeNumberDecision(employeeNumber)) {
      return;
    }

    // 名前チェック
    if (!nameDecision(employeeName)) {
      return;
    }

    // メールチェック
    if (!mailDecision(email)) {
      return;
    }


    await employeeDelete(employeeNumber);
  };

  return (
    <div>
      <Input
        label="社員番号"
        placeholder=""
        type="text"
        onChange={setEmployeeNumber}
      />

      <Input
        label="氏名"
        placeholder=""
        type="text"
        onChange={setEmployeeName}
      />

      <Input
        label="メールアドレス"
        placeholder=""
        type="text"
        onChange={setEmail}
      />

      {/* 部署 */}
      <div>
        <label>部署</label>
        <select
          value={department}
          onChange={(e) => setDepartment(e.target.value)}
        >
          <option value="">部署を選択</option>
          <option value="営業">営業</option>
          <option value="企画">企画</option>
          <option value="総務">総務</option>
          <option value="人事">人事</option>
        </select>
      </div>

      {/* 役職 */}
      <div>
        <label>役職</label>
        <select value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="">役職を選択</option>
          <option value="一般">一般</option>
          <option value="主任">主任</option>
          <option value="課長">課長</option>
          <option value="部長">部長</option>
          <option value="本部長">本部長</option>
          <option value="社長">社長</option>
        </select>
      </div>

      {/* 入社日 */}
      <Date onChange={setJoinDate} />

      {/* 在籍状態 */}
      <div>
        <label>在籍状態</label>
        <select value={status} onChange={(e) => setStatus(e.target.value)}>
          <option value="">在籍状態を選択</option>
          <option value="在籍">在籍</option>
          <option value="退職">退職</option>
        </select>
      </div>

      <div>
        <Button text="登録" onClick={handleRegister} />
        <Button text="削除" onClick={handleDelete} />
      </div>

      {/* 一括登録ページ */}

      <BulkRegistration />
    </div>
  );
}
