<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%pageContext.setAttribute("currentMenu", "user");%>
<%pageContext.setAttribute("HEADER_MODEL", "bpm-admin");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑</title>
    <%@include file="/common/s.jsp"%>
  </head>

  <body>
    <%@include file="/header/bpm-console.jsp"%>

    <div class="row-fluid">
	<%@include file="/menu/bpm-console.jsp"%>

	<!-- start of main -->
    <section id="m-main" class="span10" style="float:right">

      <article class="m-widget">
        <header class="header">
		  <h4 class="title">编辑</h4>
		</header>

		<div class="content content-inner">

<form id="demoForm" method="post" action="identity!saveUser.do?operationMode=STORE" class="form-horizontal">
  <div class="control-group">
    <label class="control-label">编号</label>
	<div class="controls">
	  <input type="text" name="userId" value="${user.id}">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">名</label>
	<div class="controls">
	  <input type="text" name="firstName" value="${user.firstName}">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">姓</label>
	<div class="controls">
	  <input type="text" name="lastName" value="${user.lastName}">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">邮箱</label>
	<div class="controls">
	  <input type="text" name="email" value="${user.email}">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button id="submitButton" type="submit" class="btn">保存</button>
	  &nbsp;
      <button type="button" onclick="history.back();" class="btn">返回</button>
    </div>
  </div>
</form>
        </div>
      </article>

    </section>
	<!-- end of main -->
	</div>

  </body>

</html>
