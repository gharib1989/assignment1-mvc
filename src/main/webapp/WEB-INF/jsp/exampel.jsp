<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
   
<body class="container">
    <div class="container" >
    <div class="row text-center">
        <div class="col-sm-3">
            
        </div>
        <div class="col-sm-6">
            <div class="panel panel-heading">
              <div class="panel-heading">
 

            <c:url var="getDomain" value="/check" ></c:url>
            <form:form method="get" action="${getDomain}" modelAttribute="domain" name="myForm" onsubmit="return validateForm()" >    
                <div class="form-group">
                    <form:label path="domain">Enter Url</form:label>
                    <form:input path="domain" class="form-control" name="fname" />
                    
                 </div>
                    
                    <input type="submit" value="Get Domain" class="btn btn-primary"/>    
                </div>    
           
            </form:form> 
            </div>
            <br>
           <div class="panel-body">
                <div class="row text-center">
            
	             <c:forEach items="${contactForm}" var="contactMap" varStatus="status">
                         <h4>    <a href="#">${contactMap.key} <span class="badge">${contactMap.value}</span></a></h4><br>
	             </c:forEach>
                </div>
           </div>
         </div>  
      </div>
        
   <div class="col-sm-3"> 
  </div>
 </div>
<script>
function validateForm() {
    var x = document.forms["myForm"]["fname"].value;
    if (x == "") {
        alert("Name must be filled out");
        return false;
    }
}
</script>
</body>
</html>
