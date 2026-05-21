import { useState } from "react";

type Props = {
  onChange: (value: string) => void;
};

/*
*　日付選択のコンポーネント。
*/

export default function Date({ onChange }: Props) {
  const [joinYear, setJoinYear] = useState("");
  const [joinMonth, setJoinMonth] = useState("");
  const [joinDay, setJoinDay] = useState("");

  const handleChange = (year: string, month: string, day: string) => {
    if (year && month && day) {
      onChange(`${year}-${month}-${day}`);
    }
  };

  return (
    <div>
      {/* 年 */}
      <select
        value={joinYear}
        onChange={(e) => {
          setJoinYear(e.target.value);
          handleChange(e.target.value, joinMonth, joinDay);
        }}
      >
        <option value="">年</option>
        <option value="2018">2018</option>
        <option value="2019">2019</option>
        <option value="2020">2020</option>
        <option value="2021">2021</option>
        <option value="2022">2022</option>
        <option value="2023">2023</option>
        <option value="2024">2024</option>
        <option value="2025">2025</option>
        <option value="2026">2026</option>
      </select>

      {/* 月 */}
      <select
        value={joinMonth}
        onChange={(e) => {
          setJoinMonth(e.target.value);
          handleChange(joinYear, e.target.value, joinDay);
        }}
      >
        <option value="">月</option>

        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
      </select>

      {/* 日 */}
      <select
        value={joinDay}
        onChange={(e) => {
          setJoinDay(e.target.value);
          handleChange(joinYear, joinMonth, e.target.value);
        }}
      >
        <option value="">日</option>

        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
        <option value="13">13</option>
        <option value="14">14</option>
        <option value="15">15</option>
        <option value="16">16</option>
        <option value="17">17</option>
        <option value="18">18</option>
        <option value="19">19</option>
        <option value="20">20</option>
        <option value="21">21</option>
        <option value="22">22</option>
        <option value="23">23</option>
        <option value="24">24</option>
        <option value="25">25</option>
        <option value="26">26</option>
        <option value="27">27</option>
        <option value="28">28</option>
        <option value="29">29</option>
        <option value="30">30</option>
        <option value="31">31</option>
      </select>
    </div>
  );
}
