export type Employee = {
  employeeNumber: string;
  name: string | undefined;
  email: string | undefined;
  department: string | undefined;
  role: string | undefined;
  joinDate: string | undefined;
  status: string | undefined;
};

/*
 *　社員一覧を取得(全ての情報)
 */

export async function getEmployeeList(): Promise<Employee[]> {
  //ブラウザ保存領域から token を取得
  const token = localStorage.getItem("token");

  const response = await fetch("/api/employees", {
    method: "GET",

    headers: {
      "Content-Type": "application/json",
      //私はログイン済みユーザーの証明
      Authorization: `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    throw new Error("社員一覧取得失敗");
  }

  return await response.json();
}

/*
 *　社員情報を新規登録or更新
 */

export async function employeeRegistration(employeeNumber: Employee) {
  const token = localStorage.getItem("token");

  const response = await fetch(`/api/employees/${employeeNumber}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(employeeNumber),
  });

  if (!response.ok) {
    throw new Error("登録・更新失敗");
  }

  return await response.json();
}

/*
 *　社員情報を削除
 */

export async function employeeDelete(employeeNumber: string) {
  const token = localStorage.getItem("token");

  const response = await fetch(`/api/employees/${employeeNumber}`, {
    method: "DELETE",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    throw new Error("削除失敗");
  }

  return await response.json();
}

/*
 *　CSVで一括登録・更新
 */

export async function csvUpload(file: File) {
  const token = localStorage.getItem("token");

  const formData = new FormData();

  formData.append("file", file);

  const response = await fetch("/api/employees/import", {
    method: "POST",

    headers: {
      Authorization: `Bearer ${token}`,
    },

    body: formData,
  });

  if (!response.ok) {
    throw new Error("CSVアップロード失敗");
  }

  return await response.json();
}

