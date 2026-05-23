/*
 *　loginApi.ts,NewRegistrationApi.ts,adminApi.ts,userApi.tsにて、
 *　true判定の場合、成功、
 *　false判定の場合、再入力を促す。
 *　重複しているかいなかはDB側(バックエンド側)で判定を行うとする。
 */

/*
 * 社員番号判定
 */

export function employeeNumberDecision(number: string): boolean {
  if (number === "") {
    alert("社員番号は必ずご入力ください");
    return false;
  }

  if (number.length !== 5) {
    alert("社員番号は5桁でご入力ください");
    return false;
  }
  return true;
}

/*
 * 名前判定
 */

export function nameDecision(name: string): boolean {
  //未入力可
   if (name === "") {
    return true;
  }
  
  //前後空白除去
  name = name.trim();

  //文字数制限
  if (name.length > 50) {
    alert("名前を50文字以内で入力してください");
    return false;
  }

  //危険文字禁止
  const dangerousRegex = /[<>"';&/\\]/;

  if (dangerousRegex.test(name)) {
    alert("使用できない文字が含まれています");
    return false;
  }

  // 使用可能文字チェック
  // 漢字
  // ひらがな
  // カタカナ
  // 英字
  // 数字
  // 空白
  const nameRegex = /^[ぁ-んァ-ヶ一-龯A-Za-z\s]+$/;

  if (!nameRegex.test(name)) {
    alert("使用できない文字が含まれています");
    return false;
  }

  //先頭空白禁止
  if (/^\s/.test(name)) {
    alert("先頭に空白は入力できません");
    return false;
  }

  return true;
}

/*
 *メールアドレス判定
 */

export function mailDecision(mail: string): boolean {

   //未入力可
   if (mail === "") {
    return true;
  }

  if (mail.length > 254) {
    alert("メールアドレスを245文字以内で入力を行ってください");
    return false;
  }

  if (mail === "") {
    alert("メールアドレスの入力を行ってください");
    return false;
  }

  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

  if (!emailRegex.test(mail)) {
    alert("メールアドレス形式が不正です");
    return false;
  }

  const domainCheck =
    mail.endsWith("@gmail.com") ||
    mail.endsWith("@yahoo.com") ||
    mail.endsWith("@ezweb.co.jp") ||
    mail.endsWith("@softbank.co.jp") ||
    mail.endsWith("@docomo.ne.jp") ||
    mail.endsWith("@rakuten.jp");

  // 指定ドメイン確認
  if (!domainCheck) {
    alert("利用可能なドメインを入力してください");
    return false;
  }

  return true;
}

/*
 *  パスワード判定
 */

export function passwordDecision(password: string): boolean {
  if (password.length < 8 || password.length > 64) {
    alert("パスワードは8文字以上64文字以内で入力を行ってください");
    return false;
  }
  //  半角判定

  const halfWidthRegex = /^[A-Za-z0-9]+$/;

  if (!halfWidthRegex.test(password)) {
    alert("パスワードは半角英数字で入力してください");
    return false;
  }

  return true;
}
