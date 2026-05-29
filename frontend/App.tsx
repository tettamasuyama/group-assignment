import { BrowserRouter, Routes, Route } from "react-router-dom";

import LoginPage from "./page/LoginPage";
import UserListPage from "./page/UserListPage";
import UserEditPage from "./page/UserEditPage";
import AdminListPage from "./page/AdminListPage";
import AdminEditPage from "./page/AdminEditPage";

/*
* Appという名前のReactコンポーネントを作成し、
*このファイルのメイン機能として外部公開する。
*(ファイルパスは path="/○○○"となっており、React内では、
* "/○○○" を使って指定のページ(element)へアクセスが可能　)
* 例：onClick={() => navigate("/employeelist")}
* ※ React Router は、
*「URL に応じて、どのコンポーネント（ページ）を表示するか」
* を定義する仕組み。
*/

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />

        <Route path="/UserListPage" element={<UserListPage />} />

        <Route path="/UserEditPage" element={<UserEditPage />} />

        <Route path="/AdminListPage" element={<AdminListPage />} />

        <Route path="/AdminEditPage" element={<AdminEditPage />} />
      </Routes>
    </BrowserRouter>
  );
}
