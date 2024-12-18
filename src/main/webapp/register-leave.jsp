<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登録画面</title>
    <link rel="stylesheet" type="text/css" href="view.css">
</head>
<body>
    <h1>有給休暇の登録</h1>
    <form action="register-leave" method="post">
        <label>年間付与日数: </label>
        <input type="number" name="grantholiday" required><br>
        <label>取得義務日数: </label>
        <input type="number" name="duty" required><br>
        <button type="submit">保存</button>
    </form>
    
    <c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
    <button onclick="location.href='home'">戻る</button>
</body>
</html>


