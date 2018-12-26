package me.jessyan.armscomponent.commonsdk.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * utilities of string
 * @author jinwei
 * @version 2.0
 */
public class StringUtils {

	/**
	 * no instance needed
	 */
	private StringUtils() { /*no instance*/ }
	
	/**
	 * null-value String
	 */
	private static final String NULL = "null";
	
	/**
	 * check this target string is NULL or empty; <br/>
	 * if <code>checkContentNull</code> is set true,
	 * it'll also check if it is a "null" string, ignoring Case.
	 * @param target target string to check
	 * @param checkContentNull if is set true,
	 * it'll also check if it is a "null" string, ignoring Case.
	 */
	public static boolean isEmpty(final String target, boolean checkContentNull) {
		// check if target is null, or a len == 0 string
		return (("null"==target)||(null == target)
				|| (target.length() == 0)
				|| (checkContentNull && NULL.equalsIgnoreCase(target)));
	}
	
	/**
	 * check this target string is null or empty;
	 * @param target target string to check
//	 * @see #isEmpty(String, false)
	 */
	public static boolean isEmpty(final String target) {
		return isEmpty(target, false);
	}
	
	/**
	 * check this target string is null or "null" String, ignoring case;
	 * 
	 * @param target target string to check
	 */
	public static boolean isNull(final String target) {
		return null == target || NULL.equalsIgnoreCase(target);
	}

    public static boolean isEmptyOrZero(final String target){
        return (isEmpty(target) || target.equals("0"));
    }

	/**
	 * 得到格式化时间
	 */
	public static String getFormatTimeMsg(int timeInSeconds) {
		int hours, minutes, seconds;
		hours = timeInSeconds / 3600;
		timeInSeconds = timeInSeconds - (hours * 3600);
		minutes = timeInSeconds / 60;
		timeInSeconds = timeInSeconds - (minutes * 60);
		seconds = timeInSeconds;

		String minStr = String.valueOf(minutes);
		String secStr = String.valueOf(seconds);

		if (minStr.length() == 1) {
			minStr = "0" + minStr;
		}
		if (secStr.length() == 1) {
			secStr = "0" + secStr;
		}

		return (minStr + ":" + secStr);
	}


	/**
	 * 判断输入是否是数字或者字母
	 */
	public static boolean isNumOrLetter(String str) {
		String strPattern = "(?!^[0-9]+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]+";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str.trim());
		return m.matches();
	}

	/**
	 * 判断字符长度
	 */
	public static boolean isLength(String str, int maxLen) {
		char[] cs = str.toCharArray();
		int count = 0;
		int last = cs.length;
		for (int i = 0; i < last; i++) {
			if (cs[i] > 255) {
				count += 2;
			} else {
				count++;
			}
		}
		if (count >= maxLen) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否为空
	 */
	public static boolean isNull(Object obj) {
		if (null == obj || "".equals(obj)) {
			return true;
		}
		return false;
	}


	/**
	 * 判断是否为手机号码
	 */
	public static boolean isPhone(String phone) {
		Pattern pattern = Pattern.compile("([1]{1})([0-9]{10})");
		Matcher m = pattern.matcher(phone.trim());
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断给定字符串是否空白串。<br> 空白串是指由空格、制表符、回车符、换行符组成的字符串<br>
	 *
	 * @return boolean
	 */
	public static boolean isBlank(String data) {
		if (data == null || "".equals(data)) {
			return true;
		}

		for (int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static String replace(String str, String oldSubStr, String newSubStr) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j = 0;
		int len = oldSubStr.length();
		while ((i = str.indexOf(oldSubStr, j)) != -1) {
			sb.append(str.substring(j, i));
			sb.append(newSubStr);
			j = i + len;
		}
		sb.append(str.substring(j));
		return sb.toString();
	}

	/**
	 * @param strEmail
	 * @return
	 */
	public static boolean isEmail(String strEmail) {
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param str 只能检查证书，不支持含小数点的数字
	 */
	public static boolean isPureNumber(String str) {
		String strPattern = "^[0-9]*$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 检查是否是数字，支持含小数点的数字
	 */
	public static boolean isValidNumber(String str) {
		String strPattern = "^\\d+\\.{0,1}\\d*$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str);
		return m.matches();

	}


	/**
	 * @param str
	 * @return
	 */
	public static boolean isCellphone(String str) {
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(str.trim());
		return matcher.matches();
	}

	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;
		String expression = "((^(13|15|18|17)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		CharSequence inputStr = phoneNumber;

		Pattern pattern = Pattern.compile(expression);

		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;

	}


	/**
	 * 手机号码前三位与后三位之间用*代替
	 */
	public static String replacePhoneStr(String phoneNum) {
		char[] cs = phoneNum.toCharArray();
		int numLen = phoneNum.length();
		for (int i = 0; i < numLen; i++) {
			if (i > 3 && i < (numLen - 3)) {
				cs[numLen - i - 1] = '*';
			}
		}
		return String.valueOf(cs);
	}


	/**
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		String strPattern = "[\u0391-\uFFE5]*";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str.trim());
		return m.matches();
	}


	/**
	 * 判断输入是否是6-20位数字或字母组合
	 */
	public static boolean isNumAndLetter(String str) {
		String strPattern = "(?!^[0-9]+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{6,18}";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str.trim());
		return m.matches();
	}

	/**
	 * 以字母开头，长度在6~18之间，只能包含字母、数字和下划线
	 */
	public static boolean CheckPsw(String str) {
		String strPattern = "^[a-zA-Z]\\w{5,17}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(str.trim());
		return m.matches();
	}

	/**
	 * 手机号码验证
	 */
	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	public static TextView textShowDifferentcolor(String str, TextView tv) {
		SpannableStringBuilder style = new SpannableStringBuilder(str);
//SpannableStringBuilder实现CharSequence接口
		style.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new ForegroundColorSpan(Color.YELLOW), 2, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new ForegroundColorSpan(Color.GREEN), 7, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(style);
		return tv;
	}

	public static String Stringinsert(String a, char b, int t) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < a.length(); i++) {
			if (i == t) {
				stringBuffer.append(b);
			}
			stringBuffer.append(a.charAt(i));
		}
		return new String(stringBuffer);
	}

	/**
	 * 判断连个str是否相等，都为空是相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean checkStringEquals(String str1, String str2){
		if(StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)){
			return true;
		}
		if(!StringUtils.isEmpty(str1) && !StringUtils.isEmpty(str2) && str1.equals(str2)){
			return true;
		}
		return false;
	}
}
