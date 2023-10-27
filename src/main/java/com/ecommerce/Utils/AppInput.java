package com.ecommerce.Utils;

import static com.ecommerce.Utils.AppScanner.getScanner;
import static com.ecommerce.Utils.Utils.print;

public class AppInput {
    public String enterString(String msg){
        print(msg);
        return getScanner().nextLine();
    }
    public static int enterInt(String msg) throws AppException{
        print(msg);
        int input;
        try{
            input = Integer.parseInt(getScanner().nextLine());
        }catch(Exception exception){
            throw new AppException(StringUtils.INVALID_CHOICE);

        }

        return input;
    }

}
