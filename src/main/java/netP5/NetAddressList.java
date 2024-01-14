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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class NetAddressList {

	protected List< NetAddress > _myList = new ArrayList<>( );

	public void add( NetAddress theNetAddress ) {
		if ( theNetAddress.isValid ) {
			_myList.add( theNetAddress );
		}
	}

	public void add( String theAddress , int thePort ) {
		NetAddress myOscHost = new NetAddress( theAddress , thePort );
		if ( myOscHost.isValid ) {
			_myList.add( myOscHost );
		}
	}

	public void remove(String theAddress, int thePort) {
        _myList.removeIf(myHost -> myHost.hostAddress.equals(theAddress) && myHost.port == thePort);
    }

	public void remove( NetAddress theNetAddress ) {
		_myList.remove( theNetAddress );
	}

	public NetAddress get( String theIPaddress , int thePort ) {
		for (NetAddress myHost : _myList) {
			if ( myHost.hostAddress.equals( theIPaddress ) && myHost.port == thePort ) {
				return myHost;
			}
		}
		return null;

	}

	public boolean contains(NetAddress theNetAddress) {
        return _myList.contains(theNetAddress);
    }

	public boolean contains( String theIPaddress , int thePort ) {
		for (NetAddress myHost : _myList) {
			if ( myHost.hostAddress.equals( theIPaddress ) && myHost.port == thePort ) {
				return true;
			}
		}
		return false;
	}

	public int size( ) {
		return _myList.size( );
	}

	public void set( NetAddress ... theList ) {
		_myList = new ArrayList<>( Arrays.asList( theList ) );
	}

	public List<NetAddress> list( ) {
		return _myList;
	}

	public NetAddress get( int theIndex ) {
		return _myList.get( theIndex );
	}

}
