package me.jessyan.armscomponent.commonsdk.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by jinwei on 2017/2/6.
 */
public class PreferencesUtils {

    private static SharedPreferences mShareConfig;


    /**
     * 添加公共信息
     */
    public static <T> void addConfigInfo(Context context, String key, T value) {
        if ( value != null && !"".equals(value)) {
            mShareConfig = context.getSharedPreferences( context.getPackageName(), Context.MODE_PRIVATE );
            Editor conEdit = mShareConfig.edit();
            if ( value instanceof String) {
                conEdit.putString( key.trim(), ( (String) value ).trim() );
            } else if ( value instanceof Long) {
                conEdit.putLong( key, (Long) value );
            } else if ( value instanceof Boolean) {
                conEdit.putBoolean( key, (Boolean) value );
            }
            conEdit.commit();
        }
    }

    public static <T> void removeConfigInfo(Context context, String key) {
        if ( key != null && !"".equals(key)) {
            mShareConfig =
                    context.getSharedPreferences( context.getPackageName(), Context.MODE_PRIVATE );
            Editor conEdit = mShareConfig.edit();
            conEdit.remove(key);
            conEdit.commit();
        }
    }

    public static <T> void removeAllInfo(Context context) {
        removeAllInfo(context,null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValueByKey(Context context, String key, Class<T> typeClass ) {
        T value = null;
        if ( key != null && !"".equals(key)) {
            mShareConfig =
                context.getSharedPreferences( context.getPackageName(), Context.MODE_PRIVATE );
            if ( null != mShareConfig ) {
                if ( typeClass.equals( String.class ) ) {
                    value = (T) mShareConfig.getString( key, "" );
                } else if ( typeClass.equals( Long.class ) ) {
                    value = (T) Long.valueOf( mShareConfig.getLong( key, 0 ) );
                } else if ( typeClass.equals( Boolean.class ) ) {
                    value = (T) Boolean.valueOf( mShareConfig.getBoolean( key, true ) );
                } else if ( typeClass.equals( Integer.class ) ) {
                    value = (T) Integer.valueOf( mShareConfig.getInt( key, 0 ) );
                }
            }
        }
        return value;
    }
    
    /**
     * 根据key得到信息
     */
    public static String getStringByKey(Context context, String key) {
        String value = null;
        mShareConfig =
                context.getSharedPreferences( context.getPackageName(), Context.MODE_PRIVATE );
        if (null != mShareConfig) {
            value = mShareConfig.getString(key, "");
        }
        return value;
    }

    public static void removeAllInfo(Context context, String node) {
        if(null == node){
            node = context.getPackageName();
        }
        mShareConfig = context.getSharedPreferences(node, Context.MODE_PRIVATE);
        if(null != mShareConfig){
            mShareConfig.edit().clear().commit();
        }

    }
}
