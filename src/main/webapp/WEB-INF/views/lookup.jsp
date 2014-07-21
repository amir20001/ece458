<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<!-- <link type="text/css" href="resources/css/bootstrap.css" rel="stylesheet" /> -->
<link type="text/css" href="css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"
	rel="stylesheet" />

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-6 col-md-6">
				<div class="thumbnail tile tile-double tile-orange">
					<a href="#">

						<form action="<%=request.getContextPath()%>/lookup"
							class="form-inline" method="post">
							<input type="text" name="domainName" class="form-control"
								placeholder="example.com"> <input type="submit"
								value="Look Up" class="btn btn-primary"><br>
						</form>
					</a>
					<c:if test="${not empty data}">
						<dl class="dl-horizontal">
							<dt>IP</dt>
							<dd>${data.ip}</dd>

							<dt>Domain Name</dt>
							<dd>${data.domainName}</dd>

							<dt>Signature</dt>
							<dd>${data.signature}</dd>
						</dl>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>