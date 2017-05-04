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
 package fr.paris.lutece.plugins.myportal.modules.rss.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for RssConf objects
 */
public final class RssConfHome
{
    // Static variable pointed at the DAO instance
    private static IRssConfDAO _dao = SpringContextService.getBean( "myportal-rss.rssConfDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "myportal-rss" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private RssConfHome(  )
    {
    }

    /**
     * Create an instance of the rssConf class
     * @param rssConf The instance of the RssConf which contains the informations to store
     * @return The  instance of rssConf which has been created with its primary key.
     */
    public static RssConf create( RssConf rssConf )
    {
        _dao.insert( rssConf, _plugin );

        return rssConf;
    }

    /**
     * Update of the rssConf which is specified in parameter
     * @param rssConf The instance of the RssConf which contains the data to store
     * @return The instance of the  rssConf which has been updated
     */
    public static RssConf update( RssConf rssConf )
    {
        _dao.store( rssConf, _plugin );

        return rssConf;
    }

    /**
     * Remove the rssConf whose identifier is specified in parameter
     * @param nKey The rssConf Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of a rssConf whose identifier is specified in parameter
     * @param nKey The rssConf primary key
     * @return an instance of RssConf
     */
    public static RssConf findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin);
    }

    /**
     * Load the data of all the rssConf objects and returns them as a list
     * @return the list which contains the data of all the rssConf objects
     */
    public static List<RssConf> getRssConfsList( )
    {
        return _dao.selectRssConfsList( _plugin );
    }
    
    /**
     * Load the id of all the rssConf objects and returns them as a list
     * @return the list which contains the id of all the rssConf objects
     */
    public static List<Integer> getIdRssConfsList( )
    {
        return _dao.selectIdRssConfsList( _plugin );
    }
    
    /**
     * Load the data of all the rssConf objects and returns them as a referenceList
     * @return the referenceList which contains the data of all the rssConf objects
     */
    public static ReferenceList getRssConfsReferenceList( )
    {
        return _dao.selectRssConfsReferenceList(_plugin );
    }
    
   /**
    * Load the data of  the rssConf objects and returns them as a list
    * @param nIdCategory
    * @return
    */
    public static List<RssConf> getRssConfsList( int nIdCategory)
    {
        return _dao.loadByCategory(nIdCategory,  _plugin );
    }
}

