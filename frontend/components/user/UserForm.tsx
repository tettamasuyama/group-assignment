import { nameDecision, mailDecision } from "../../decision/InputDecision";
import { useEffect } from "react";
import Button from "../common/Button";
import Input from "../common/Input";
import { userEditApi } from "../../decision/UserApi";
import { useState } from "react";
import UserFormTable from "../../components/user/UserFormTable";
import { Employee } from "../../decision/AdminApi";
import { userOnlyApi } from "../../decision/UserApi";


export default function UserForm() {

  const [name, setName] = useState("");
  const [email, setMail] = useState("");

  const [employee, setEmployees] = useState<Employee | null>(null);

  useEffect(() => {
    loadEmplpyeeMe();
  }, []);

  async function loadEmplpyeeMe() {
    try {
      const data = await userOnlyApi();
      setEmployees(data);

      // input初期値
      setName(data.name);
      setMail(data.email);
    } catch {
      alert("ユーザー情報は見当たりませんでした");
    }
  }

  async function handleEdit() {
    // 名前チェック
    if (!nameDecision(name)) {
      alert("名前が不正です");
      return;
    }

    // メールチェック
    if (!mailDecision(email)) {
      alert("メールアドレスが不正です");
      return;
    }

    try {
      if(name!=="" || email !==""){
      await userEditApi(name, email);
      loadEmplpyeeMe();
      alert("更新しました");
      }else{
      alert("更新は行われませんでした");
      }
    } catch {
      alert("更新に失敗しました");
    }
  }

  return (
    <div>
      {employee && <UserFormTable employee={employee} />}

      <div>
        <Input label="氏名" type="text" placeholder="" onChange={setName} />
        <Input
          label="メールアドレス"
          type="text"
          placeholder="@example.com"
          onChange={setMail}
        />
        <Button text="登録" onClick={handleEdit} />
      </div>
    </div>
  );
}
