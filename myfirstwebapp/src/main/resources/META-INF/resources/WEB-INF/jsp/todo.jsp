<!-- navigaton bar is common so we create a fragment and just include it syntax is below -->
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h1>Enter Todo Details ${name}</h1>
	<!-- we are mapping this form to todo bean -->
	<form:form method="post" modelAttribute="todo">
		
		<!-- all things that come for one input is fieldset -->
		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description"  required="required"/>
		
		<!-- it is used for showing error 
		we have validated description field if there is error
		below will be printed. It is provided by form tag -->
		
		<!-- this is spring tag so to add class we put cssClass -->
			<form:errors path="description" cssClass="text-warning"></form:errors>
		</fieldset>
		
		<!-- to have a common date time format, we added in application.properties -->
		<fieldset>
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" path="targetDate" required="required"/>
			<form:errors path="targetDate" cssClass="text-warning"></form:errors>
		</fieldset>
		
		<br> 
		<!-- if we submit this alone then error will come beacuse we have'nt submitted id and done -->
		<form:input type="hidden" path="id"/>
		<form:input type="hidden" path="done"/>
		<input type="submit" class="btn btn-success"/>
	</form:form>
		
	
	
</div>

<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
	//targetDate because it is the id of the date field. We hav specified only path but it coverts to id
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>
		
		
