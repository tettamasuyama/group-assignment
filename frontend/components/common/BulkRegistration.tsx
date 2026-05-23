import { useRef, useState } from "react";
import Button from "./Button";
import { csvUpload } from "../../decision/AdminApi";

/*
 * CSVファイルを利用した社員一括登録コンポーネント。
 *
 * file は useState で選択中のCSVファイルを管理する。
 *
 * fileInputRef は非表示input要素を参照するためのref。
 * ボタンクリック時に input の click() を実行し、
 * ファイル選択ダイアログを開く。
 *
 * handleFileChange では選択されたファイルを取得し、
 * 拡張子が.csvかどうかを判定する。
 *
 * CSV以外の場合：
 *   alertでエラーメッセージ表示。
 *
 * CSVの場合：
 *   setFile(selectedFile) によりstate更新。
 *
 * handleRemoveFile では
 * 選択中ファイル情報を削除し、
 * input要素のvalueも初期化する。
 *
 * handleUpload では
 * csvUpload APIを利用してCSVアップロードを行う。
 *
 * アップロード成功時：
 *   完了メッセージ表示。
 *
 * アップロード失敗時：
 *   エラーメッセージ表示。
 *
 * input type="file" は非表示にし、
 * Buttonコンポーネントから操作する構成になっている。
 */

export default function BulkRegistration() {
  const [file, setFile] = useState<File | null>(null);

  const fileInputRef = useRef<HTMLInputElement | null>(null);

  // ファイル選択ボタン
  const handleSelectClick = () => {
    fileInputRef.current?.click();
  };

  // ファイル変更
  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) {
      return;
    }

    const selectedFile = e.target.files[0];

    // CSV判定
    if (!selectedFile.name.endsWith(".csv")) {
      alert("CSVファイルを選択してください。");
      return;
    }

    setFile(selectedFile);
  };

  // ファイル削除
  const handleRemoveFile = () => {
    setFile(null);

    if (fileInputRef.current) {
      fileInputRef.current.value = "";
    }
  };

  // 一括登録
  const handleUpload = async () => {
    if (!file) {
      alert("CSVファイルを選択してください。");
      return;
    }

    try {
      await csvUpload(file);

      alert("読み取りが完了しました。");
    } catch {
      alert(
        "ファイルを読み込めませんでした。\n正しいファイルを選択してください。",
      );
    }
  };

  return (
    <div>
      {/* 非表示input */}
      <input
        type="file"
        ref={fileInputRef}
        onChange={handleFileChange}
        style={{ display: "none" }}
      />

      {/* ファイル選択ボタン */}
      <Button text="CSVファイルを選択" onClick={handleSelectClick} />

      {/* ファイル名表示 */}
      <p>{file ? file.name : "ファイル未選択"}</p>

      {/* 削除 */}
      <Button text="削除" onClick={handleRemoveFile} />

      {/* 一括登録 */}
      <Button text="一括登録" onClick={handleUpload} />
    </div>
  );
}
