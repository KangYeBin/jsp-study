<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // 디스패처에 의해 request 객체가 이쪽으로 전달되었음
    // request에 담겨있는 댄서 리스트를 꺼내는 메서드 필요
    // List<Dancer> dancerList = (List<Dancer>) request.getAttribute("dl");

    // MVC 구조에서는 View 역할을 하는 jsp 쪽에서 자바 코드를 최소화하기로 약속함
    // el을 통해서 request에 있는 dl이라는 이름의 데이터를 꺼내 쓰도록 한다
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
    <style>
        .del-btn {
            padding: 5px 10px;
            outline: none;
            border: none;
            background: red;
            border-radius: 10px;
            color: #fff;
            margin-left: 10px;
            margin-bottom: 10px;
            cursor: pointer;
        }
        .del-btn:hover {
            border: 1px solid orange;
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <h1> mvc 버전 댄서 목록 뷰</h1>
    <ul id="dancer-list">
        <%--
            c:forEach 태그를 이용하여 향상 for문을 구현하는 법.
            제어변수를 선언하고, items 속성에 반복문을 돌리고자 하는 컬렉션 자료형을 세팅
            request 객체 내에 있는 dl이라는 이름의 데이터를 el을 사용하여 꺼낼 수 있다
            ${requestScope.dl} -> Scope를 언급하지 않으면 dl이라는 이름의 데이터를 전달된 모든 객체에서 찾아온다.
        --%>
        <c:forEach var="d" items="${requestScope.dl}">
            <li>
                <%-- el을 사용하여 객체를 지목한 후 메서드를 호출할 때는 get을 제외한 나머지 이름 지목 --%>
                # 이름 : <span class="dancer-name"> ${d.name} </span>
                # 크루명 : ${d.crewName}
                # 레벨 : ${d.danceLevel}
                # 페이 : ${d.danceLevel.payPerEvent}
                <button class="del-btn">"삭제"</button>
            </li>
        </c:forEach>
    </ul>

    <a href="/register.do"> 새로운 댄서 등록하기 </a>

    <script>
        const $dancerList = document.getElementById("dancer-list");
        
        $dancerList.onclick = e => {
            // 버튼 태그가 아니라면 이벤트 강제 종료
            if (!e.target.matches('button')) return;
            
            // 서버로 삭제 요청을 보내면서 댄서 이름을 전달
            const dancerName = e.target.previousElementSibling.textContent;

            // 서버에 링크로 삭제 요청 -> 원칙적으로는 링크로 삭제 요청하면 안 됨
            // 링크로 삭제 요청하면 GET 요청 -> 삭제는 POST로 전달해야함
            location.href="/delete.do?name=" + dancerName
        }
    </script>
</body>
</html>