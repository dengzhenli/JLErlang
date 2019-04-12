package org.fatty.erlang;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengzhenli on 2019/3/13.
 */
public class ErlangUtil {

    public static List<Object> getList(String str){
        List<Object> list = new ArrayList<>();
        try{
            list = ErlangManager.getInstance().enList(str);
        }catch (ErlangParseException e){
            e.printStackTrace();
//            log.error(e + "异常字符串：" + str);
        }
        return list;
    }
}
