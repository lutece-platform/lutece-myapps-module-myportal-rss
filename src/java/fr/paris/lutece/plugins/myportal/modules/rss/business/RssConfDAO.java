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
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for RssConf objects
 */
public final class RssConfDAO implements IRssConfDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_rss_conf ) FROM myportal_rss_advanced_conf";
    private static final String SQL_QUERY_SELECT = "SELECT id_rss_conf, attributeUser, attributeValue, url, idCategory FROM myportal_rss_advanced_conf WHERE id_rss_conf = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO myportal_rss_advanced_conf ( id_rss_conf, attributeUser, attributeValue, url, idCategory ) VALUES ( ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM myportal_rss_advanced_conf WHERE id_rss_conf = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE myportal_rss_advanced_conf SET id_rss_conf = ?, attributeUser = ?, attributeValue = ?, url = ?, idCategory = ? WHERE id_rss_conf = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_rss_conf, attributeUser, attributeValue, url, idCategory FROM myportal_rss_advanced_conf";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_rss_conf FROM myportal_rss_advanced_conf";
    private static final String SQL_QUERY_SELECT_BY_CATEGORY = "SELECT id_rss_conf, attributeUser, attributeValue, url, idCategory FROM myportal_rss_advanced_conf WHERE  idCategory = ?";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin)
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
        daoUtil.executeQuery( );
        int nKey = 1;

        if( daoUtil.next( ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free();
        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( RssConf rssConf, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        rssConf.setId( newPrimaryKey( plugin ) );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , rssConf.getId( ) );
        daoUtil.setString( nIndex++ , rssConf.getAttributeUser( ) );
        daoUtil.setString( nIndex++ , rssConf.getAttributeValue( ) );
        daoUtil.setString( nIndex++ , rssConf.getUrl( ) );
        daoUtil.setInt( nIndex++ , rssConf.getIdCategory( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public RssConf load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeQuery( );
        RssConf rssConf = null;

        if ( daoUtil.next( ) )
        {
            rssConf = new RssConf();
            int nIndex = 1;
            
            rssConf.setId( daoUtil.getInt( nIndex++ ) );
            rssConf.setAttributeUser( daoUtil.getString( nIndex++ ) );
            rssConf.setAttributeValue( daoUtil.getString( nIndex++ ) );
            rssConf.setUrl( daoUtil.getString( nIndex++ ) );
            rssConf.setIdCategory( daoUtil.getInt( nIndex++ ) );
        }

        daoUtil.free( );
        return rssConf;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( RssConf rssConf, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , rssConf.getId( ) );
        daoUtil.setString( nIndex++ , rssConf.getAttributeUser( ) );
        daoUtil.setString( nIndex++ , rssConf.getAttributeValue( ) );
        daoUtil.setString( nIndex++ , rssConf.getUrl( ) );
        daoUtil.setInt( nIndex++ , rssConf.getIdCategory( ) );
        daoUtil.setInt( nIndex , rssConf.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<RssConf> selectRssConfsList( Plugin plugin )
    {
        List<RssConf> rssConfList = new ArrayList<RssConf>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            RssConf rssConf = new RssConf(  );
            int nIndex = 1;
            
            rssConf.setId( daoUtil.getInt( nIndex++ ) );
            rssConf.setAttributeUser( daoUtil.getString( nIndex++ ) );
            rssConf.setAttributeValue( daoUtil.getString( nIndex++ ) );
            rssConf.setUrl( daoUtil.getString( nIndex++ ) );
            rssConf.setIdCategory( daoUtil.getInt( nIndex++ ) );

            rssConfList.add( rssConf );
        }

        daoUtil.free( );
        return rssConfList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdRssConfsList( Plugin plugin )
    {
        List<Integer> rssConfList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            rssConfList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return rssConfList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectRssConfsReferenceList( Plugin plugin )
    {
        ReferenceList rssConfList = new ReferenceList();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            rssConfList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return rssConfList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<RssConf> loadByCategory( int nIdCategory, Plugin plugin )
    {
    	
        List<RssConf> rssConfList = new ArrayList<RssConf>(  );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CATEGORY, plugin );
        daoUtil.setInt( 1 , nIdCategory );
        daoUtil.executeQuery( );

        while ( daoUtil.next(  ) )
        {
            RssConf rssConf = new RssConf(  );
            int nIndex = 1;
            
            rssConf.setId( daoUtil.getInt( nIndex++ ) );
            rssConf.setAttributeUser( daoUtil.getString( nIndex++ ) );
            rssConf.setAttributeValue( daoUtil.getString( nIndex++ ) );
            rssConf.setUrl( daoUtil.getString( nIndex++ ) );
            rssConf.setIdCategory( daoUtil.getInt( nIndex++ ) );

            rssConfList.add( rssConf );
        }

        daoUtil.free( );
        return rssConfList;
    }
}