/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.myportal.modules.rss.service;

import java.util.List;

import fr.paris.lutece.plugins.myportal.business.Widget;
import fr.paris.lutece.plugins.myportal.modules.rss.business.Category;
import fr.paris.lutece.plugins.myportal.modules.rss.business.CategoryHome;
import fr.paris.lutece.plugins.myportal.modules.rss.business.RssConf;
import fr.paris.lutece.plugins.myportal.modules.rss.business.RssConfHome;
import fr.paris.lutece.plugins.myportal.service.handler.WidgetHandler;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import javax.servlet.http.HttpServletRequest;

/**
 * RSS Widget Handler
 */
public class RssAdvancedWidgetHandler implements WidgetHandler
{
    String NAME = "rss advanced";
    String DESCRIPTION = "RSS Advanced widget";


    /**
     * {@inheritDoc }
     */
    public String getName()
    {
        return NAME;
    }

    /**
     * {@inheritDoc }
     */
    public String getDescription()
    {
        return DESCRIPTION;
    }

    /**
     * {@inheritDoc }
     */
    public String renderWidget( Widget widget, LuteceUser user, HttpServletRequest request)
    {
    	String rssURL= null;
    	int nIdXsl= 0;
    	user = SecurityService.getInstance().getRegisteredUser(request);       

    	if( user!= null){
    		
	    	Category category= CategoryHome.findByTitle(widget.getConfigData()); 	
	    	List<RssConf> rssConfList= RssConfHome.getRssConfsList(category.getId( ));
	    	String strUserInfo = user.getUserInfo(rssConfList.get(0).getAttributeUser());
	    	
	    	for(RssConf rssConf:rssConfList){
	    		
	    		if(rssConf.getAttributeValue( ).equals(strUserInfo) || rssConf.getAttributeUser( ).isEmpty( )){
	    			
	    			rssURL= rssConf.getUrl();
	    			nIdXsl= rssConf.getIdStyleSheet( );
	    			break;
	    		}
	    		
	    	}
	    }
    	if( rssURL == null ||  rssURL.isEmpty( ) ){
    		
    		 AppLogService.error( "Error fetching url : " + rssURL );
             return AppPropertiesService.getProperty( RssWidgetService.PROPERTY_ERROR_LOADING_FEED);
    	}
    	
        return RssWidgetService.instance().getRssFeed( rssURL, nIdXsl );
    }

    /**
     * {@inheritDoc}
     */
	public boolean isCustomizable(  )
	{
		return true;
	}
}
