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
 * @author		@yunus-ceyhan, @kengoon, @steve-mir
 * @modified	##date##
 * @version		0.0.1
 */

package netP5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HexFormat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Bytes {

	private Bytes( ) {
	}

	/**
	 * converts an object array into a String that is formatted like a list
	 * 
	 */
	public static String getAsString(Object[] theObject) {
        var s = new StringBuilder();
        for (int i = 0; i < theObject.length; i++) {
            s.append("[").append(i).append("]").append(" ").append(theObject[i]).append("\n");
        }
        return s.toString();
    }

	public static String getAsString(byte[] theBytes) {
        var s = new StringBuilder();
        for (byte theByte : theBytes) {
            s.append((char) theByte);
        }
        return s.toString();
    }

	public static int toInt( byte abyte0[] ) {
		if (abyte0 == null) {
			return 0;
		}
	
		// Check if the length of the array is at least 4
		if (abyte0.length < 4) {
			throw new IllegalArgumentException("Input byte array must contain at least 4 bytes");
		}
	
		return ( abyte0[ 3 ] & 0xff ) + ( ( abyte0[ 2 ] & 0xff ) << 8 ) + ( ( abyte0[ 1 ] & 0xff ) << 16 ) + ( ( abyte0[ 0 ] & 0xff ) << 24 );
	}

	public static long toLong( byte abyte0[] ) {
		return ( ( long ) abyte0[ 7 ] & 255L ) + ( ( ( long ) abyte0[ 6 ] & 255L ) << 8 ) + ( ( ( long ) abyte0[ 5 ] & 255L ) << 16 ) + ( ( ( long ) abyte0[ 4 ] & 255L ) << 24 ) + ( ( ( long ) abyte0[ 3 ] & 255L ) << 32 )
				+ ( ( ( long ) abyte0[ 2 ] & 255L ) << 40 ) + ( ( ( long ) abyte0[ 1 ] & 255L ) << 48 ) + ( ( ( long ) abyte0[ 0 ] & 255L ) << 56 );
	}

	public static float toFloat( byte abyte0[] ) {
		int i = toInt( abyte0 );
		return Float.intBitsToFloat( i );
	}

	public static double toDouble( byte abyte0[] ) {
		long l = toLong( abyte0 );
		return Double.longBitsToDouble( l );
	}

	public static byte[] toBytes( int i ) {
		return toBytes( i , new byte[ 4 ] );
	}

	public static byte[] toBytes( int i , byte abyte0[] ) {
		abyte0[ 3 ] = ( byte ) i;
		i >>>= 8;
		abyte0[ 2 ] = ( byte ) i;
		i >>>= 8;
		abyte0[ 1 ] = ( byte ) i;
		i >>>= 8;
		abyte0[ 0 ] = ( byte ) i;
		return abyte0;
	}

	public static byte[] toBytes( long l ) {
		return toBytes( l , new byte[ 8 ] );
	}

	public static byte[] toBytes( long l , byte abyte0[] ) {
		abyte0[ 7 ] = ( byte ) ( int ) l;
		l >>>= 8;
		abyte0[ 6 ] = ( byte ) ( int ) l;
		l >>>= 8;
		abyte0[ 5 ] = ( byte ) ( int ) l;
		l >>>= 8;
		abyte0[ 4 ] = ( byte ) ( int ) l;
		l >>>= 8;
		abyte0[ 3 ] = ( byte ) ( int ) l;
		l >>>= 8;
		abyte0[ 2 ] = ( byte ) ( int ) l;
		l >>>= 8;
		abyte0[ 1 ] = ( byte ) ( int ) l;
		l >>>= 8;
		abyte0[ 0 ] = ( byte ) ( int ) l;
		return abyte0;
	}

	public static boolean areEqual( byte abyte0[] , byte abyte1[] ) {
		int i = abyte0.length;
		if ( i != abyte1.length ) {
			return false;
		}
		for ( int j = 0 ; j < i ; j++ ) {
			if ( abyte0[ j ] != abyte1[ j ] ) {
				return false;
			}
		}

		return true;
	}

	public static byte[] append( byte abyte0[] , byte abyte1[] ) {
		byte abyte2[] = new byte[ abyte0.length + abyte1.length ];
		System.arraycopy( abyte0 , 0 , abyte2 , 0 , abyte0.length );
		System.arraycopy( abyte1 , 0 , abyte2 , abyte0.length , abyte1.length );
		return abyte2;
	}

	public static byte[] append( byte abyte0[] , byte abyte1[] , byte abyte2[] ) {
		byte abyte3[] = new byte[ abyte0.length + abyte1.length + abyte2.length ];
		System.arraycopy( abyte0 , 0 , abyte3 , 0 , abyte0.length );
		System.arraycopy( abyte1 , 0 , abyte3 , abyte0.length , abyte1.length );
		System.arraycopy( abyte2 , 0 , abyte3 , abyte0.length + abyte1.length , abyte2.length );
		return abyte3;
	}

	public static byte[] copy( byte abyte0[] , int i ) {
		return copy( abyte0 , i , abyte0.length - i );
	}

	public static byte[] copy( byte abyte0[] , int i , int j ) {
		byte abyte1[] = new byte[ j ];
		System.arraycopy( abyte0 , i , abyte1 , 0 , j );
		return abyte1;
	}

	public static void merge( byte abyte0[] , byte abyte1[] , int i , int j , int k ) {
		System.arraycopy( abyte0 , i , abyte1 , j , k );
	}

	public static void merge( byte abyte0[] , byte abyte1[] , int i ) {
		System.arraycopy( abyte0 , 0 , abyte1 , i , abyte0.length );
	}

	public static void merge( byte abyte0[] , byte abyte1[] ) {
		System.arraycopy( abyte0 , 0 , abyte1 , 0 , abyte0.length );
	}

	public static void merge( byte abyte0[] , byte abyte1[] , int i , int j ) {
		System.arraycopy( abyte0 , 0 , abyte1 , i , j );
	}

	public static String toString( byte abyte0[] , int i , int j ) {
		HexFormat hexFormat = HexFormat.of();
    	return hexFormat.formatHex(abyte0, i, i + j);
	}

	public static String toString( byte abyte0[] ) {
		HexFormat hexFormat = HexFormat.of();
    	return hexFormat.formatHex(abyte0);
	}

	public static void printBytes( byte[] byteArray ) {
		for ( int i = 0 ; i < byteArray.length ; i++ ) {
			System.out.print( ( char ) byteArray[ i ] + " (" + hexDigits[ byteArray[ i ] >>> 4 & 0xf ] + "" + hexDigits[ byteArray[ i ] & 0xf ] + ")  " );
			if ( ( i + 1 ) % 4 == 0 ) {
				System.out.print( "\n" );
			}
		}
	}

	public static final char hexDigits[] = { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , 'A' , 'B' , 'C' , 'D' , 'E' , 'F' };

	/**
	 * ByteStream
	 */

	//  Not used locally
	// private static byte[] toByteArray( int in_int ) {
	// 	return ByteBuffer.allocate(4).putInt(in_int).array();
	// }

	private static byte[] toByteArrayBigEndian( int theInt ) {
		return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(theInt).array();
	}

	private static int asInt( byte[] byte_array_4 ) {
		return ByteBuffer.wrap(byte_array_4).order(ByteOrder.LITTLE_ENDIAN).getInt();
	}

	public static int toIntLittleEndian( InputStream theInputStream ) throws java.io.IOException {
		byte[] byte_array_4 = new byte[ 4 ];

		if (theInputStream.read(byte_array_4) != 4) {
        	throw new java.io.IOException("Could not read 4 bytes for an int value");
    	}

		return asInt( byte_array_4 );
	}

	public static int toIntBigEndian( InputStream theInputStream ) throws java.io.IOException {
		byte[] byte_array_4 = new byte[ 4 ];
		/* used to reverse the int32 Big Endian of the tcp header to convert it to an int */
		if (theInputStream.read(byte_array_4) != 4) {
        	throw new java.io.IOException("Could not read 4 bytes for an int value");
    	}
    	ByteBuffer bb = ByteBuffer.wrap(byte_array_4).order(ByteOrder.BIG_ENDIAN);
		return bb.getInt();
	}

	public static String toString( InputStream ins ) throws java.io.IOException {
		int len = toIntLittleEndian( ins );
		return toString( ins , len );
	}

	private static String toString( InputStream ins , int len ) throws java.io.IOException {
		String ret = new String( );
		for ( int i = 0 ; i < len ; i++ ) {
			ret += ( char ) ins.read( );
		}
		return ret;
	}

	public static void toStream( OutputStream os , int i ) throws Exception {
		byte[] byte_array_4 = toByteArrayBigEndian( i );
		os.write( byte_array_4 );
	}

	public static void toStream( OutputStream os , String s ) throws Exception {
		int len_s = s.length( );
		toStream( os , len_s );
		for ( int i = 0 ; i < len_s ; i++ ) {
			os.write( ( byte ) s.charAt( i ) );
		}
		os.flush( );
	}

	public static void toStream( OutputStream os , byte[] theBytes ) throws Exception {
		int myLength = theBytes.length;
		toStream( os , myLength );
		os.write( theBytes );
		os.flush( );
	}

	public static byte[] toByteArray( InputStream ins ) throws java.io.IOException {
		int len = toIntLittleEndian( ins );
		try {
			return toByteArray( ins , len );
		} catch ( Exception e ) {
			return new byte[ 0 ];
		}
	}

	protected static byte[] toByteArray( InputStream ins , int an_int ) throws java.io.IOException , Exception {

		byte[] ret = new byte[ an_int ];

		int offset = 0;
		int numRead = 0;
		int outstanding = an_int;

		while ( ( offset < an_int ) && ( ( numRead = ins.read( ret , offset , outstanding ) ) > 0 ) ) {
			offset += numRead;
			outstanding = an_int - offset;
		}
		if ( offset < ret.length ) {
			throw new Exception( "Could not completely read from stream, numRead=" + numRead + ", ret.length=" + ret.length ); // ???
		}
		return ret;
	}

	private static void toFile( InputStream ins , FileOutputStream fos , int len , int buf_size ) throws java.io.FileNotFoundException , java.io.IOException {

		byte[] buffer = new byte[ buf_size ];

		int len_read = 0;
		int total_len_read = 0;

		while ( total_len_read + buf_size <= len ) {
			len_read = ins.read( buffer );
			total_len_read += len_read;
			fos.write( buffer , 0 , len_read );
		}

		if ( total_len_read < len ) {
			toFile( ins , fos , len - total_len_read , buf_size / 2 );
		}
	}

	private static void toFile( InputStream ins , File file , int len ) throws java.io.FileNotFoundException , java.io.IOException {

		FileOutputStream fos = new FileOutputStream( file );

		toFile( ins , fos , len , 1024 );
	}

	public static void toFile( InputStream ins , File file ) throws java.io.FileNotFoundException , java.io.IOException {

		int len = toIntLittleEndian( ins );
		toFile( ins , file , len );
	}

	public static void toStream( OutputStream os , File file ) throws java.io.FileNotFoundException , Exception {

		toStream( os , ( int ) file.length( ) );

		byte b[] = new byte[ 1024 ];
		try (InputStream is = new FileInputStream( file )) {
			int numRead = 0;

			while ( ( numRead = is.read( b ) ) > 0 ) {
				os.write( b , 0 , numRead );
			}
		}
		os.flush( );
	}

}
