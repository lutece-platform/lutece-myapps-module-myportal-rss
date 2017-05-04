<%@ page errorPage="/../../../../ErrorPage.jsp" %>
<jsp:include page="../../../../AdminHeader.jsp" />
<jsp:useBean id="managerss" scope="session" class="fr.paris.lutece.plugins.myportal.modules.rss.web.ManageRssJspBean" />

<% managerss.init( request, managerss.RIGHT_MANAGERSS ); %>
<%= managerss.getManageRssHome ( request ) %>
<%@ include file="../../../../AdminFooter.jsp" %>
