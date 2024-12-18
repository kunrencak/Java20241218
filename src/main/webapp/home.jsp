<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css" />
<title>トップ画面</title>
<link rel="stylesheet" href="holiday.css" type="text/css" />

<style>
	body { text-align: center; font-family: Arial, sans-serif; }
    button { margin: 20px; padding: 10px 20px; }
</style>

</head>

<body>
<h1>有給休暇管理システム</h1>
    <button onclick="location.href='view-status'">入力・確認</button>
    <button onclick="location.href='register-leave'">登録</button>
    <form action="reset-data" method="post">
    <button type="submit">リセット</button>
</form>
    
    

<p>令和6年度終了まで･･･</p>
<p class="timer">残り
   <span id="day"></span>日と
   <span id="hour"></span>時間
   <span id="minute"></span>分
   <span id="second"></span>秒
</p>
    


<script>

  //残り時間を取得する関数
  function showRestTime() {
  //現在の時刻を取得する
  const now = new Date();
　//指定した年月日の情報を取得する（※月は「実際の月の-1」にする）
  const goal = new Date(2025, 3, 1);
　
  //goal.getTime( )：1970年1月1日0時～2050年1月1日までの経過時間をミリ秒で取得
　//now.getTime( )：1970年1月1日0時～現在までの経過時間をミリ秒で取得
  //指定した日付までの残り時間をミリ秒で取得する
  const restMillisecond = goal.getTime() - now.getTime();
  const day = Math.floor(restMillisecond / 1000 / 60 / 60 / 24); //日数
  const hour = Math.floor(restMillisecond / 1000 / 60 / 60) % 24; //時間
  const minute = Math.floor(restMillisecond / 1000 / 60) % 60;//分
  const second = Math.floor(restMillisecond / 1000) % 60; //秒

  document.getElementById('day').textContent = day;
  document.getElementById('hour').textContent = hour;
  document.getElementById('minute').textContent = String(minute).padStart(2, '0');
  document.getElementById('second').textContent = String(second).padStart(2, '0');
}

  setInterval(showRestTime, 1000);

</script>

</body>
</html>