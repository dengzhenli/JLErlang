package org.fatty.erlang;

import java.util.HashMap;
import java.util.Map;

/**
 * erlang虚拟对象
 */
public class ErlangObject {
    Map<String,Object> map ;

    public ErlangObject(){
        map = new HashMap<>();
    }

    public void putString(String key,String data){
        map.put(key,data);
    }

    public String getString(String key,String data){
        if (map.containsKey(key)){
            return map.get(key).toString();
        }else {
            return data;
        }

    }

    public void put(String key,Object data){
        map.put(key,data);
    }

    public Object get(String key){
        if (map.containsKey(key)){
            return map.get(key);
        }else {
            return null;
        }

    }

    @Override
    public String toString() {
        return map.toString();
    }
}
