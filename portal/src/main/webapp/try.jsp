<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <script>

            <c:forEach var="i" begin="1" end="5">
                Item <c:out value="${i}"/> < p >
                        alert(${i});
            </c:forEach>  
        </script>
<script>
  
        <c:forEach items="${cTagBean.tagList}" var="ctag" varStatus="loop">
            '${ctag.name}': ${ctag.age}${!loop.last ? ',' : ''}
     
        </c:forEach>
  
</script>
    </head>
    <body >

    </body>
</html>