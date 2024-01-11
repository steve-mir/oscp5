package netP5;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class BytesTest {


    // The method 'getAsString(Object[] theObject)' should return a string representation of the input object array, with each element enclosed in square brackets and its index and value displayed.
    @Test
    public void test_getAsString_withObjectArray() {
        Object[] theObject = {1, "two", 3.0};
        String expected = "[0] 1\n[1] two\n[2] 3.0\n";
        String result = Bytes.getAsString(theObject);
        assertEquals(expected, result);
    }

    // The method 'getAsString(byte[] theBytes)' should return a string representation of the input byte array, with each byte converted to its corresponding character.
    @Test
    public void test_getAsString_withByteArray() {
        byte[] theBytes = {65, 66, 67};
        String expected = "ABC";
        String result = Bytes.getAsString(theBytes);
        assertEquals(expected, result);
    }

    // The method 'toInt(byte abyte0[])' should convert a byte array to an integer using the big-endian byte order and return the result.
    @Test
    public void test_toInt_withByteArray() {
        byte[] abyte0 = {0, 0, 0, 1};
        int expected = 1;
        int result = Bytes.toInt(abyte0);
        assertEquals(expected, result);
    }

    // The method 'toInt(byte abyte0[])' should handle a null input byte array and return 0.
    @Test
    public void test_toInt_withNullByteArray() {
        byte []abyte0 = null;
        int expected = 0;
        int result = Bytes.toInt(abyte0);
        assertEquals(expected, result);
    }

    // The method 'toLong(byte abyte0[])' should handle a null input byte array and return 0.
    // @Test
    // public void test_toLong_withNullByteArray() {
    //     byte[] abyte0 = null;
    //     long expected = 0;
    //     long result = Bytes.toLong(abyte0);
    //     assertEquals(expected, result);
    // }

    /**
     * 
     * toBytes( int i , byte abyte0[] ) tests
     */
    // Returns a byte array of length 4 for a given integer input
    @Test
    public void test_returns_byte_array_of_length_4() {
        int input = 123;
        byte[] expected = new byte[]{0, 0, 0, 123};
        byte[] result = Bytes.toBytes(input);
        assertArrayEquals(expected, result);
    }

    // Returns the correct byte array for positive integer inputs
    @Test
    public void test_returns_correct_byte_array_for_positive_integer() {
        int input = 255;
        byte[] expected = new byte[]{0, 0, 0, -1};
        byte[] result = Bytes.toBytes(input);
        assertArrayEquals(expected, result);
    }

    // Returns the correct byte array for negative integer inputs
    @Test
    public void test_returns_correct_byte_array_for_negative_integer() {
        int input = -255;
        byte[] expected = new byte[]{-1, -1, -1, 1};
        byte[] result = Bytes.toBytes(input);
        assertArrayEquals(expected, result);
    }

    // ... Other tests

    // tests for functions edited

     /*
      * toString(byte [], int, int)
      */
      // Returns a string representation of the input byte array in hexadecimal format.
    @Test
    public void test_returnsHexString() {
        byte[] byteArray = {0x12, 0x34, 0x56, 0x78};
        int offset = 0;
        int length = byteArray.length;
    
        String expected = "12345678";
        String actual = Bytes.toString(byteArray, offset, length);
    
        assertEquals(expected, actual);
    }

    // Returns a string representation of a subarray of the input byte array in hexadecimal format.
    @Test
    public void test_returnsSubarrayHexString() {
        byte[] byteArray = {0x12, 0x34, 0x56, 0x78};
        int offset = 1;
        int length = 2;
    
        String expected = "3456";
        String actual = Bytes.toString(byteArray, offset, length);
    
        assertEquals(expected, actual);
    }

    // Returns an empty string when the input byte array is empty.
    @Test
    public void test_returnsEmptyStringForEmptyArray() {
        byte[] byteArray = {};
        int offset = 0;
        int length = 0;
    
        String expected = "";
        String actual = Bytes.toString(byteArray, offset, length);
    
        assertEquals(expected, actual);
    }

    // Throws an IllegalArgumentException when the input byte array length is less than the sum of the input offset and length.
    @Test
    public void test_throwsIllegalArgumentExceptionForInvalidLength() {
        byte[] byteArray = {0x12, 0x34, 0x56, 0x78};
        int offset = 2;
        int length = 3;
    
        assertThrows(IllegalArgumentException.class, () -> {
            Bytes.toString(byteArray, offset, length);
        });
    }

    // Returns a string representation of the entire input byte array when the input offset is zero and the subarray length is equal to the input byte array length.
    @Test
    public void test_returnsFullArrayHexString() {
        byte[] byteArray = {0x12, 0x34, 0x56, 0x78};
        int offset = 0;
        int length = byteArray.length;
    
        String expected = "12345678";
        String actual = Bytes.toString(byteArray, offset, length);
    
        assertEquals(expected, actual);
    }

    /*
     * tests for toString(byte [])
     */
    // Returns a string representation of the input byte array in hexadecimal format.
    @Test
    public void test_returnsHexString2() {
        byte[] input = {0x12, 0x34, 0x56, 0x78};
        String expected = "12345678";
        String result = Bytes.toString(input);
        assertEquals(expected, result);
    }

    // Returns an empty string when the input byte array is empty.
    @Test
    public void test_returnsEmptyStringForEmptyArray2() {
        byte[] input = {};
        String expected = "";
        String result = Bytes.toString(input);
        assertEquals(expected, result);
    }

    // Returns a string with length twice the length of the input byte array.
    @Test
    public void test_returnsStringWithTwiceLength() {
        byte[] input = {0x12, 0x34, 0x56, 0x78};
        String result = Bytes.toString(input);
        assertEquals(input.length * 2, result.length());
    }

    // Returns an empty string when the input byte array is null.
    @Test
    public void test_returnsEmptyStringForNullArray() {
        byte[] input = null;
        String expected = "";
        String result = Bytes.toString(input);
        assertEquals(expected, result);
    }

    // Returns a string with length twice the length of the input byte array when the input byte array is null.
    @Test
    public void test_returnsStringWithTwiceLengthForNullArray() {
        byte[] input = null;
        String result = Bytes.toString(input);
        assertEquals(0, result.length());
    }

    // Returns a string with length twice the length of the input byte array when the input byte array is of length zero.
    @Test
    public void test_returnsStringWithTwiceLengthForEmptyArray() {
        byte[] input = new byte[0];
        String result = Bytes.toString(input);
        assertEquals(0, result.length());
    }

    /*
     * test for asInt
     */
    // Returns the correct integer value when given a byte array of length 4 with little-endian byte order
    // @Test
    // public void test_little_endian() {
    //     byte[] byteArray = {0x01, 0x02, 0x03, 0x04};
    //     int result = bt.asInt(byteArray);
    //     assertEquals(67305985, result);
    // }

    /*
     * tests for toIntLittleEndian
     */
    // Returns the correct integer value when given a valid input stream of 4 bytes in little-endian order
    @Test
    public void test_valid_input_stream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0x01, 0x00, 0x00, 0x00});
        int result = Bytes.toIntLittleEndian(inputStream);
        assertEquals(1, result);
    }

    // Throws an IOException if the input stream does not contain exactly 4 bytes
    @Test
    public void test_less_than_4_bytes() {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0x01, 0x00, 0x00});
        assertThrows(IOException.class, () -> {
            Bytes.toIntLittleEndian(inputStream);
        });
    }

    /*
     * tests for toIntBigEndian
     */
    // Reads 4 bytes from InputStream and returns corresponding int value in Big Endian format
    @Test
    public void test_readBytesAndReturnIntValue() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0x00, 0x00, 0x00, 0x01});
        int result = Bytes.toIntBigEndian(inputStream);
        assertEquals(1, result);
    }

    // Returns correct int value for input stream containing maximum possible int value in Big Endian format
    @Test
    public void test_maxIntValueInBigEndianFormat() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0x7F, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF});
        int result = Bytes.toIntBigEndian(inputStream);
        assertEquals(Integer.MAX_VALUE, result);
    }

    // Returns correct int value for input stream containing minimum possible int value in Big Endian format
    @Test
    public void test_minIntValueInBigEndianFormat() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{(byte) 0x80, 0x00, 0x00, 0x00});
        int result = Bytes.toIntBigEndian(inputStream);
        assertEquals(Integer.MIN_VALUE, result);
    }

    // Throws IOException if InputStream does not contain 4 bytes
    @Test
    public void test_inputStreamWithLessThan4Bytes() {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0x00, 0x00, 0x00});
        assertThrows(IOException.class, () -> {
            Bytes.toIntBigEndian(inputStream);
        });
    }

    // Returns correct int value for input stream containing maximum possible int value in Little Endian format
    @Test
    public void test_maxIntValueInLittleEndianFormat() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, 0x7F});
        int result = Bytes.toIntBigEndian(inputStream);
        assertEquals(Integer.MAX_VALUE, result);
    }

    // Returns correct int value for input stream containing minimum possible int value in Little Endian format
    @Test
    public void test_minIntValueInLittleEndianFormat() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{0x00, 0x00, 0x00, (byte) 0x80});
        int result = Bytes.toIntBigEndian(inputStream);
        assertEquals(Integer.MIN_VALUE, result);
    }

}