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

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import java.io.Serializable;

/**
 * This is the business class for the object RssConf
 */ 
public class RssConf implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations 
    private int _nId;
    
    @NotEmpty( message = "#i18n{module.myportal.rss.validation.rssconf.AttributeUser.notEmpty}" )
    @Size( max = 50 , message = "#i18n{module.myportal.rss.validation.rssconf.AttributeUser.size}" ) 
    private String _strAttributeUser;
    
    @NotEmpty( message = "#i18n{module.myportal.rss.validation.rssconf.AttributeValue.notEmpty}" )
    @Size( max = 50 , message = "#i18n{module.myportal.rss.validation.rssconf.AttributeValue.size}" ) 
    private String _strAttributeValue;
    
    @NotEmpty( message = "#i18n{module.myportal.rss.validation.rssconf.Url.notEmpty}" )
    private String _strUrl;
    
    private int _nIdCategory;
    
    private int _nIdStyleSheet;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId( )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */ 
    public void setId( int nId )
    {
        _nId = nId;
    }
    
    /**
     * Returns the AttributeUser
     * @return The AttributeUser
     */
    public String getAttributeUser( )
    {
        return _strAttributeUser;
    }

    /**
     * Sets the AttributeUser
     * @param strAttributeUser The AttributeUser
     */ 
    public void setAttributeUser( String strAttributeUser )
    {
        _strAttributeUser = strAttributeUser;
    }
    
    /**
     * Returns the AttributeValue
     * @return The AttributeValue
     */
    public String getAttributeValue( )
    {
        return _strAttributeValue;
    }

    /**
     * Sets the AttributeValue
     * @param strAttributeValue The AttributeValue
     */ 
    public void setAttributeValue( String strAttributeValue )
    {
        _strAttributeValue = strAttributeValue;
    }
    
    /**
     * Returns the Url
     * @return The Url
     */
    public String getUrl( )
    {
        return _strUrl;
    }

    /**
     * Sets the Url
     * @param strUrl The Url
     */ 
    public void setUrl( String strUrl )
    {
        _strUrl = strUrl;
    }
    
    /**
     * Returns the IdCategory
     * @return The IdCategory
     */
    public int getIdCategory( )
    {
        return _nIdCategory;
    }

    /**
     * Sets the IdCategory
     * @param nIdCategory The IdCategory
     */ 
    public void setIdCategory( int nIdCategory )
    {
        _nIdCategory = nIdCategory;
    }

    /**
     * Returns the IdStyleSheet
     * @return The IdStyleSheet
     */
    public int getIdStyleSheet( )
    {
        return _nIdStyleSheet;
    }

    /**
     * Sets the IdStyleSheet
     * @param nIdStyleSheet The IdStyleSheet
     */ 
    public void setIdStyleSheet( int nIdStyleSheet )
    {
        _nIdStyleSheet = nIdStyleSheet;
    }
}
