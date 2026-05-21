type InputProps = {
  label: string;
  type: string;
  placeholder: string;
  onChange: (value: string) => void;
};

/*
*　入力項目のコンポーネント。
*/

export default function Input({
  label,
  type,
  placeholder,
  onChange,
}: InputProps) {
  return (
    <div>
      <label>{label}</label>
      <input
        type={type}
        placeholder={placeholder}
        onChange={(e) => onChange(e.target.value)}
      />
    </div>
  );
}
