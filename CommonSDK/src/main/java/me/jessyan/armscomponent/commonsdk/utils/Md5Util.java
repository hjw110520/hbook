package me.jessyan.armscomponent.commonsdk.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     * 生成md5校验码
     *
     * @param srcContent 需要加密的数据
     * @return 加密后的md5校验码。出错则返回null。
     */
    public static String makeMd5Sum(byte[] srcContent ) {
        if ( srcContent == null ) {
            return null;
        }

        String strDes = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance( "MD5" );
            md5.update( srcContent );
            strDes = bytes2Hex( md5.digest() ); // to HexString
        } catch ( NoSuchAlgorithmException e ) {
            return null;
        }
        return strDes;
    }

    /**
     * md5 16位
     * @param srcContent
     * @return
     */
    public static String makeMd5Sum16(byte[] srcContent ) {
        if ( srcContent == null ) {
            return null;
        }

        String strDes = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance( "MD5" );
            md5.update( srcContent );
            strDes = bytes2Hex( md5.digest() ).substring(8,24); // to HexString
        } catch ( NoSuchAlgorithmException e ) {
            return null;
        }
        return strDes;
    }

    /**
     * bytes2Hex方法
     */
    private static String bytes2Hex(byte[] byteArray ) {
        StringBuffer strBuf = new StringBuffer();
        int bytelength = byteArray.length;
        for ( int i = 0; i < bytelength; i++ ) {
            if ( byteArray[i] >= 0 && byteArray[i] < 16 ) {
                strBuf.append( "0" );
            }
            strBuf.append( Integer.toHexString( byteArray[i] & 0xFF ) );
        }
        return strBuf.toString();
    }
}
