/*
 * Copyright (c) 2002-2016, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
  package fr.paris.lutece.plugins.myportal.modules.rss.web;

import fr.paris.lutece.plugins.myportal.modules.rss.business.CategoryHome;
import fr.paris.lutece.plugins.myportal.modules.rss.business.RssConf;
import fr.paris.lutece.plugins.myportal.modules.rss.business.RssConfHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage RssConf features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageRssConfs.jsp", controllerPath = "jsp/admin/plugins/myportal/modules/rss/", right = "MYPORTAL_RSS_MANAGEMENT" )
public class RssConfJspBean extends ManageRssJspBean
{
    // Templates
    private static final String TEMPLATE_MANAGE_RSSCONFS = "/admin/plugins/myportal/modules/rss/manage_rssconfs.html";
    private static final String TEMPLATE_CREATE_RSSCONF = "/admin/plugins/myportal/modules/rss/create_rssconf.html";
    private static final String TEMPLATE_MODIFY_RSSCONF = "/admin/plugins/myportal/modules/rss/modify_rssconf.html";

    // Parameters
    private static final String PARAMETER_ID_RSSCONF = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_RSSCONFS = "module.myportal.rss.manage_rssconfs.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_RSSCONF = "module.myportal.rss.modify_rssconf.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_RSSCONF = "module.myportal.rss.create_rssconf.pageTitle";

    // Markers
    private static final String MARK_RSSCONF_LIST = "rssconf_list";
    private static final String MARK_RSSCONF = "rssconf";
    private static final String MARK_CATEGORY_LIST = "category_list";
    
    private static final String JSP_MANAGE_RSSCONFS = "jsp/admin/plugins/myportal/modules/rss/ManageRssConfs.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_RSSCONF = "module.myportal.rss.message.confirmRemoveRssConf";

    // Validations
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "module.myportal.rss.model.entity.rssconf.attribute.";

    // Views
    private static final String VIEW_MANAGE_RSSCONFS = "manageRssConfs";
    private static final String VIEW_CREATE_RSSCONF = "createRssConf";
    private static final String VIEW_MODIFY_RSSCONF = "modifyRssConf";

    // Actions
    private static final String ACTION_CREATE_RSSCONF = "createRssConf";
    private static final String ACTION_MODIFY_RSSCONF = "modifyRssConf";
    private static final String ACTION_REMOVE_RSSCONF = "removeRssConf";
    private static final String ACTION_CONFIRM_REMOVE_RSSCONF = "confirmRemoveRssConf";

    // Infos
    private static final String INFO_RSSCONF_CREATED = "module.myportal.rss.info.rssconf.created";
    private static final String INFO_RSSCONF_UPDATED = "module.myportal.rss.info.rssconf.updated";
    private static final String INFO_RSSCONF_REMOVED = "module.myportal.rss.info.rssconf.removed";
    
    // Session variable to store working values
    private RssConf _rssconf;
    
    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_RSSCONFS, defaultView = true )
    public String getManageRssConfs( HttpServletRequest request )
    {
        _rssconf = null;
        List<RssConf> listRssConfs = RssConfHome.getRssConfsList(  );
        Map<String, Object> model = getPaginatedListModel( request, MARK_RSSCONF_LIST, listRssConfs, JSP_MANAGE_RSSCONFS );
        model.put(MARK_CATEGORY_LIST, CategoryHome.getCategorysReferenceList( ));
        
        return getPage( PROPERTY_PAGE_TITLE_MANAGE_RSSCONFS, TEMPLATE_MANAGE_RSSCONFS, model );
    }

    /**
     * Returns the form to create a rssconf
     *
     * @param request The Http request
     * @return the html code of the rssconf form
     */
    @View( VIEW_CREATE_RSSCONF )
    public String getCreateRssConf( HttpServletRequest request )
    {
        _rssconf = ( _rssconf != null ) ? _rssconf : new RssConf(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_RSSCONF, _rssconf );
        model.put(MARK_CATEGORY_LIST, CategoryHome.getCategorysReferenceList());
        return getPage( PROPERTY_PAGE_TITLE_CREATE_RSSCONF, TEMPLATE_CREATE_RSSCONF, model );
    }

    /**
     * Process the data capture form of a new rssconf
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_RSSCONF )
    public String doCreateRssConf( HttpServletRequest request )
    {
        populate( _rssconf, request );

        // Check constraints
        if ( !validateBean( _rssconf, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_RSSCONF );
        }

        RssConfHome.create( _rssconf );
        addInfo( INFO_RSSCONF_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_RSSCONFS );
    }

    /**
     * Manages the removal form of a rssconf whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_RSSCONF )
    public String getConfirmRemoveRssConf( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_RSSCONF ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_RSSCONF ) );
        url.addParameter( PARAMETER_ID_RSSCONF, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_RSSCONF, url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a rssconf
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage rssconfs
     */
    @Action( ACTION_REMOVE_RSSCONF )
    public String doRemoveRssConf( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_RSSCONF ) );
        RssConfHome.remove( nId );
        addInfo( INFO_RSSCONF_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_RSSCONFS );
    }

    /**
     * Returns the form to update info about a rssconf
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_RSSCONF )
    public String getModifyRssConf( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_RSSCONF ) );

        if ( _rssconf == null || ( _rssconf.getId(  ) != nId ))
        {
            _rssconf = RssConfHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_RSSCONF, _rssconf );
        model.put(MARK_CATEGORY_LIST, CategoryHome.getCategorysReferenceList());

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_RSSCONF, TEMPLATE_MODIFY_RSSCONF, model );
    }

    /**
     * Process the change form of a rssconf
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_RSSCONF )
    public String doModifyRssConf( HttpServletRequest request )
    {
        populate( _rssconf, request );

        // Check constraints
        if ( !validateBean( _rssconf, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_RSSCONF, PARAMETER_ID_RSSCONF, _rssconf.getId( ) );
        }

        RssConfHome.update( _rssconf );
        addInfo( INFO_RSSCONF_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_RSSCONFS );
    }
}