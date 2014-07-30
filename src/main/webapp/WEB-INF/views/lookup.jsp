<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LookUp</title>
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
					<dl class="dl-horizontal">
						<dt>IP</dt>
						<dd>${data.ip}</dd>

						<dt>Domain Name</dt>
						<dd>${data.domainName}</dd>

						<dt>Signature</dt>
						<dd>${data.signature}</dd>

					</dl>

					<table>
						<tr>
							<td>
								<form action="<%=request.getContextPath()%>/dhkey"
									class="form-inline" method="post">
									<input type="submit" value="Generate Key" class="btn"><br>
								</form>
							</td>
							<td>
								<form action="<%=request.getContextPath()%>/rmkey"
									class="form-inline" method="post">
									<input type="submit" value="Forget Key" class="btn"><br>
								</form>
							</td>
							<c:if>
							<td test="${hasSign}">
								<form action="<%=request.getContextPath()%>/verify"
									class="form-inline" method="post">
										<input type="submit" value="Verify" class="btn"><br>
										<input id="ip" name="ip" type="hidden" value="${data.ip}"/>
										<input id="domainName" name="domainName" type="hidden" value="${data.domainName}"/>
										<input id="signature" name="signature" type="hidden" value="${data.signature}"/>
								</form>
							</td>
							</c:if>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>