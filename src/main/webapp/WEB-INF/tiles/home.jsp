<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<sec:authorize access="isAuthenticated()">

	<c:url var="search" value="/search" />

	<div class="row">
		<div class="col-md-8 col-md-offset-2">

			<form action="${search}" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div class="input-group input-group-lg">

					<input type="text" class="form-control" name="s"
						placeHolder="Search People">


					<button id="search-button" class="btn btn-primary" type="submit">
						Find People</button>

				</div>

			</form>


		</div>
	</div>
	<br />
	<div class="row">

		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">


			<div class="panel panel-default">


				<div class="panel-heading">
					<div class="panel-title">
						The last Status was added on
						<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"
							value="${statusUpdate.added}" />
					</div>
				</div>
				<div class="panel-body">


					<div>${statusUpdate.text}</div>

				</div>

			</div>


		</div>


	</div>

</sec:authorize>


<sec:authorize access="!isAuthenticated()">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="homepage-status">
				<h2 align="center" style="color: #FDB827">Goatbook the Social
					Network for the G.O.A.T</h2>
				<img src="/img/goatbook3.jpeg" alt="goatbook" align="middle"
					style="height: 400px; width: auto" />
				<h3 align="center" style="color: #FDB827">No crazy Ex here & no
					stalker Neighbour</h3>
				<h3 align="center" style="color: #FDB827">Just the Best of the
					Best</h3>

			</div>
		</div>
	</div>


</sec:authorize>


