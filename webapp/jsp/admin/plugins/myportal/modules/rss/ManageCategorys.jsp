<jsp:useBean id="managerssCategory" scope="session" class="fr.paris.lutece.plugins.myportal.modules.rss.web.CategoryJspBean" />
<% String strContent = managerssCategory.processController ( request , response ); %>


<%@ page errorPage="../../../../ErrorPage.jsp" %>
<jsp:include page="../../../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../../../AdminFooter.jsp" %>

