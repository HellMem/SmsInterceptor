package coronadosolutions.smsinterceptor.utils;

public class MsgReader {

    public static double getAmount(String msgBody) {
        int initIndex = msgBody.indexOf('$');

        String stringAmount = "";

        for (int i = initIndex + 1; i < msgBody.length(); i++) {
            if (msgBody.charAt(i) == ' ') break;
            stringAmount += msgBody.charAt(i);
        }

        return Double.parseDouble(stringAmount.replace(",", ""));
    }
}
