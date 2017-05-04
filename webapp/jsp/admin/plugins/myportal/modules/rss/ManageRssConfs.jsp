<jsp:useBean id="managerssRssConf" scope="session" class="fr.paris.lutece.plugins.myportal.modules.rss.web.RssConfJspBean" />
<% String strContent = managerssRssConf.processController ( request , response ); %>


<%@ page errorPage="../../../../ErrorPage.jsp" %>
<jsp:include page="../../../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../../../AdminFooter.jsp" %>

