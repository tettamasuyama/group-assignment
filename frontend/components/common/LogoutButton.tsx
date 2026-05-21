import { useNavigate } from "react-router-dom";

/*
*　ログアウトボタンのコンポーネント。
*/
export default function LogoutButton() {
  //ログアウト時　localStorageからtoken と roleの削除
  localStorage.removeItem("token");
  localStorage.removeItem("role");

  const navigate = useNavigate();
  return (
    <button
      onClick={() => {
        navigate("/");
      }}
    >
      ログアウト
    </button>
  );
}
