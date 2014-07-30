<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verify</title>
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
					<dl class="dl-horizontal">
						<dt>Sent MAC</dt>
						<dd>${sent}</dd>

						<dt>Calculated Mac</dt>
						<dd>${calc}</dd>

						<dt>Equal</dt>
						<dd>${equal}</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
</body>
</html>