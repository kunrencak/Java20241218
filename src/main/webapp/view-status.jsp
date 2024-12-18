<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>確認画面</title>
    <link rel="stylesheet" type="text/css" href="view.css">
</head>


<body>
    <h1>有給休暇取得状況</h1>
    <form action="take-leave" method="post">
      <div class="form-group">
        <label>取得日</label>
        <input type="date" name="paidDate" required>
        <button type="submit">休む</button>
      </div> 
    </form>
    <table border="1">
        <tr><th>付与日数</th><th>取得日数</th><th>残日数</th></tr>
        <tr>
            <td>${grantholiday}</td>
            <td>${get}</td>
            <td>${remaining}</td>
        </tr>
    </table>
    <p>残り ${remaining} 日取得できます！</p>
    
    <c:if test="${duty >= 0}">
    <c:if test="${get <= duty}">
        <p style="color:red;">必ず ${duty - get} 日はとってください！</p>
    </c:if>
</c:if>

<button onclick="location.href='home'">戻る</button>
    
</body>
</html>
