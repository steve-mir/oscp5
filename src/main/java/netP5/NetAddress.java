/**
 * A network library for processing which supports UDP, TCP and Multicast.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		##author##
 * @modified	##date##
 * @version		##version##
 */

package netP5;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

public class NetAddress {

	protected InetAddress inetaddress = null;

	protected String hostAddress;

	public String name = "";

	protected int port = 0;

	protected boolean isValid = false;

	private static final ConcurrentHashMap<String, InetAddress> cache = new ConcurrentHashMap<>();

    public static InetAddress getInetAddress(String host) throws UnknownHostException {
        return cache.computeIfAbsent(host, h -> {
            try {
                return InetAddress.getByName(h);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e); // Rethrow as unchecked exception or handle appropriately
            }
        });
    }

	// private NetAddress( ) {
	// }

	public NetAddress( final int thePort ) {
		this( "127.0.0.1" , thePort );
	}

	public NetAddress( final String theAddress , final int thePort ) {
		hostAddress = theAddress;
		port = thePort;
		if ( thePort > 0 ) {
			try {
				// inetaddress = InetAddress.getByName( theAddress );
				inetaddress = NetAddress.getInetAddress(theAddress);
				isValid = true;
			} catch ( UnknownHostException e ) {
				System.err.println( "no such host " + inetaddress + " address " + theAddress);
			}
		}
	}

	public NetAddress( NetAddress theNetAddress ) {
		this( theNetAddress.address( ) , theNetAddress.port( ) );
	}

	public NetAddress( InetAddress theInetAddress , int thePort ) {
		inetaddress = theInetAddress;
		hostAddress = inetaddress.getHostAddress( );
		port = thePort;
	}

	public InetAddress inetaddress( ) {
		return inetaddress;
	}

	public String address( ) {
		return hostAddress;
	}

	public int port( ) {
		return port;
	}

	public boolean isvalid( ) {
		return isValid;
	}

	public String toString( ) {
		return hostAddress + ":" + port;
	}
}
