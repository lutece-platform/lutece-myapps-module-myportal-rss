/*
 * Copyright (c) 2002-2012, Mairie de Paris
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

import fr.paris.lutece.portal.business.stylesheet.StyleSheet;
import fr.paris.lutece.portal.business.stylesheet.StyleSheetHome;
import fr.paris.lutece.portal.service.cache.AbstractCacheableService;
import fr.paris.lutece.portal.service.html.XmlTransformerService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import java.util.HashMap;

/**
 * Rss Widget Service
 */
public class RssWidgetService extends AbstractCacheableService
{

    private static final String SERVICE_NAME = "RSS Widget Cache Service";
    private static final String PROPERTY_ERROR_LOADING_FEED = "myportal-rss.message.errorLoadingFeed";
    private static final String PROPERTY_STYLESHEET_ID = "myportal-rss.stylesheet.id";
    private static final int ID_XSL = AppPropertiesService.getPropertyInt( PROPERTY_STYLESHEET_ID , 0 );

    private static RssWidgetService _singleton = new RssWidgetService();

    /** Private constructor */
    private RssWidgetService()
    {
    }

    /**
     * {@inheritDoc }
     */
    public String getName()
    {
        return SERVICE_NAME;
    }

    public static RssWidgetService instance()
    {
        return _singleton;
    }

    public String getRssFeed(String strUrl)
    {
        String strRss = (String) getFromCache(strUrl);
        if (strRss == null)
        {
            String strFeed = fetchRssFeed( strUrl );
            if( strFeed == null )
            {
                AppLogService.error("Error fetching url : " + strUrl);
                return AppPropertiesService.getProperty( PROPERTY_ERROR_LOADING_FEED);
            }
            XmlTransformerService xmlTransformerService = new XmlTransformerService(  );
            strRss = xmlTransformerService.transformBySourceWithXslCache( strFeed, getRssXsl(),  new HashMap<String, String>() );
            putInCache(strUrl, strRss );
        }
        return strRss;
    }

    /**
     * Fetch a RSS feed
     * @param strUrl The RSS feed Id
     * @return The content of the RSS feed
     */
    public static String fetchRssFeed(String strUrl)
    {
        String strContent = null;
        HttpAccess httpAccess = new HttpAccess();

        try
        {
            strContent = httpAccess.doGet(strUrl);
        } catch (HttpAccessException e)
        {
        }
        return strContent;
    }

    StyleSheet getRssXsl()
    {
        StyleSheet xsl = StyleSheetHome.findByPrimaryKey( ID_XSL );
        if( xsl == null )
        {
            AppLogService.error("Error loading Widget XSL stylesheet with the ID : " + ID_XSL);
        }
        return xsl;
    }
}
