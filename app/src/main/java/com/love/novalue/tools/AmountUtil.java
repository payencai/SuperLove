package com.love.novalue.tools;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

/**
 * 金额工具类
 * Created by Beluga_白鲸.
 */

public class AmountUtil {

    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Long amount) throws Exception {
        if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }

        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return subZeroAndDot(result.toString());
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Context context, String amount) {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            Toast.makeText(context, "金额格式有误", Toast.LENGTH_LONG).show();
        }
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }

    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    public static String changeY2F(Long amount) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }

    /**
     * 限制小数点后两位
     *
     * EditText属性改成如下：
     * numberDecimal表示只能输入一个小数点
     *
     *             <EditText
     *                 android:id="@+id/et_amt"
     *                 android:layout_width="wrap_content"
     *                 android:layout_height="wrap_content"
     *                 android:layout_marginLeft="20dp"
     *                 android:background="@null"
     *                 android:layout_weight="1"
     *                 android:layout_gravity="center_vertical"
     *                 android:textColorHint="@color/font_CCCCCC"
     *                 android:textColor="@color/font_333333"
     *                 android:textSize="14sp"
     *                 android:paddingTop="20dp"
     *                 android:paddingBottom="20dp"
     *                 android:inputType="number|numberDecimal"
     *                 android:hint="需小于或等于本期金额"/>
     *
     */
    public static void limitTwoDecimal(final EditText editText) {
        //采用输入监听的方法
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();

                //0开头没有小数点，自动去0处理
                if (temp.startsWith("0")) {
                    if (temp.length() >= 2) {
                        if (!".".equals(String.valueOf(temp.charAt(1)))) {
                            temp = temp.substring(1, temp.length());
                            editText.setText(temp);
                            editText.setSelection(temp.length());
                        }
                    }
                }

                //小数点后两位处理
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }
}


