<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<style type="text/css">
form {
	position: absolute;
	top: 50%;
	left: 30%;
}

h3 {
	position: absolute;
	top: 30%;
	left: 30%;
}

h5 {
	position: absolute;
	top: 20%;
	left: 33%;
	color: #842029;
	background-color: #f8d7da;
	border-color: #f5c2c7;
	font-size: 15px;
}
</style>
<title>Aprendendo JSP</title>
</head>
<body>
	<h3>Aprendendo JSP</h3>

	<!--  <h1>out.print("JSP - aqui entro do html, pode ser escrito c�digo java");;
	%>
</h1>-->
	<form action="<%= request.getContextPath() %>/ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">

		<div class="mb-3">
			<label class="form-label">Login</label> <input class="form-control"
				name="login" type="text" required>
		</div>
		<div class="invalid-feedback">Obrigat�rio</div>
		<div class="valid-feedback">OK</div>

		<div class="mb-3">
			<label class="form-label">Senha</label> <input class="form-control"
				name="senha" type="password" required>
		</div>
		<div class="invalid-feedback">Obrigat�rio</div>
		<div class="valid-feedback">OK</div>

		<div class="mb-3">
			<input type="submit" class="btn btn-primary" value="Acessar">
		</div>


	</form>
	<!-- pega a mensagem q esta no ServletLogin -->
	<h5>${msg}</h5>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>


	<!-- valida��o com javascript -->
	<script type="text/javascript">
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>

</body>
</html>