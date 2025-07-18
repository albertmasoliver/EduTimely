/*
 * This class downloads the Maven Wrapper JAR from a specified URL if it is not present.
 * It is used to bootstrap Maven builds in projects that use the Maven Wrapper.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class MavenWrapperDownloader
{
    // The version of the Maven Wrapper
    private static final String WRAPPER_VERSION = "3.2.0";

    // Verbose output flag
    private static final boolean VERBOSE = Boolean.parseBoolean( System.getenv( "MVNW_VERBOSE" ) );

    // Main entry point for the downloader
    public static void main( String[] args )
    {
        log( "Apache Maven Wrapper Downloader " + WRAPPER_VERSION );

        if ( args.length != 2 )
        {
            System.err.println( " - ERROR wrapperUrl or wrapperJarPath parameter missing" );
            System.exit( 1 );
        }

        try
        {
            log( " - Downloader started" );
            final URL wrapperUrl = new URL( args[0] );
            final String jarPath = args[1].replace( "..", "" ); // Sanitize path
            final Path wrapperJarPath = Paths.get( jarPath ).toAbsolutePath().normalize();
            downloadFileFromURL( wrapperUrl, wrapperJarPath );
            log( "Done" );
        }
        catch ( IOException e )
        {
            System.err.println( "- Error downloading: " + e.getMessage() );
            if ( VERBOSE )
            {
                e.printStackTrace();
            }
            System.exit( 1 );
        }
    }

    // Downloads the file from the given URL to the specified path
    private static void downloadFileFromURL( URL wrapperUrl, Path wrapperJarPath )
        throws IOException
    {
        log( " - Downloading to: " + wrapperJarPath );
        if ( System.getenv( "MVNW_USERNAME" ) != null && System.getenv( "MVNW_PASSWORD" ) != null )
        {
            final String username = System.getenv( "MVNW_USERNAME" );
            final char[] password = System.getenv( "MVNW_PASSWORD" ).toCharArray();
            Authenticator.setDefault( new Authenticator()
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication( username, password );
                }
            } );
        }
        try ( InputStream inStream = wrapperUrl.openStream() )
        {
            Files.copy( inStream, wrapperJarPath, StandardCopyOption.REPLACE_EXISTING );
        }
        log( " - Downloader complete" );
    }

    // Logs messages if verbose mode is enabled
    private static void log( String msg )
    {
        if ( VERBOSE )
        {
            System.out.println( msg );
        }
    }

}
