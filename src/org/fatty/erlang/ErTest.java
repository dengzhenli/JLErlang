package org.fatty.erlang;

/**
 * Created by dengzhenli on 2019/1/5.
 */
public class ErTest {

    public static void main(String[] args) {
//
        ErlangManager judger =  ErlangManager.getInstance();
        try {
//            System.out.println(judger.enClass(null));
            System.out.println(judger.enClass("{{1,7,[1,[2,3]]},3}"));
            System.out.println(judger.enClass("{player_yaodan,1400100000010154,885,[{yaodan,809,35,1,1600,[]},{yaodan,795,47,1,1600,[]},{yaodan,827,90,1,1600,[]},{yaodan,829,29,1,1600,[]},{yaodan,819,66,1,1600,[]},{yaodan,733,41,1,1600,[]},{yaodan,738,11,1,1600,[]},{yaodan,745,78,1,1600,[]},{yaodan,747,5,1,1600,[]},{yaodan,751,47,1,1600,[]},{yaodan,679,90,1,1600,[]},{yaodan,692,96,1,1600,[]},{yaodan,494,96,1,1600,[]}],[],[],400,[2,5,1],true}"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
