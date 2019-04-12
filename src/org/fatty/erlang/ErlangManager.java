package org.fatty.erlang;

import java.util.*;

/**
 * erlang转换工具
 */
public class ErlangManager {
    // pair以右括号为key, 左括号为值
    private Map<Character, Character> pair = null;

    private static ErlangManager erlangManager;

    private ErlangManager()
    {
        pair = new HashMap<Character, Character>();
       // pair.put(')', '(');
        pair.put('}', '{');
        pair.put(']', '[');
    }

    public static ErlangManager getInstance(){
        if (erlangManager==null){
            erlangManager = new ErlangManager();
        }
        return erlangManager;
    }

    /**
     * 检查是否合格字符串
     * @param s
     * @return
     */
    public boolean isMatch(String s)
    {
        Stack<Character> sc = new Stack<Character>();
        for (int i = 0; i < s.length(); i++)
        {
            Character ch = s.charAt(i);
            if (pair.containsValue(ch))// 如果是左括号，放⼊栈中
            {
                sc.push(ch);
            } else if (pair.containsKey(ch)) // 如果是右括号
            {
                if (sc.empty()) // 栈为空，栈头没有字符与右括号匹配
                {
                    return false;
                }
                // 栈不为空，栈头字符与右括号匹配
                if (sc.peek() == pair.get(ch))
                {
                    sc.pop();
                } else //⽹上许多列⼦没有这⾥的else代码块，导致({}[]]])会被判断为true
                { // 栈不为空，栈头字符不与右括号匹配
                    return false;
                }
            }
        }
        return sc.empty() ? true : false;
    }


    /**
     *  转为erlang类
     *  {}
     * @param s
     * @return
     * @throws ErlangParseException
     */
    public ErlangObject enClass(String s) throws ErlangParseException{
        if (s==null) throw new ErlangParseException();
        s = s.replace("\n","").replace("\r","").replace(" ","");
        //不是 {}格式则抛出异常
        if (!s.startsWith("{")||!s.endsWith("}")) throw new ErlangParseException();
        //长度为2则是空内容
        if (s.length()==2) s = "";
        //否则去掉两边括号
        else s = s.substring(1,s.length()-1);
        //用临时栈存储括号
        Stack<Character> sc = new Stack<Character>();
        //定义一个erlangObject节点
        ErlangObject erlangObject = new ErlangObject();
        StringBuilder sb = new StringBuilder();
        int keyindex = 1;  //当前无返回属性名，从1开始做为编号做属性名
        for (int i = 0; i < s.length(); i++)
        {
            Character ch = s.charAt(i);
            //遇到逗号如果栈是空的，就存为一个属性，否则当当前属性的内容
            if (ch==','&&sc.empty()){
                //递归存储erlang对象
                erlangObject.put(keyindex + "",getObject(sb.toString()));
                keyindex++;
                sb.delete( 0, sb.length() );
                continue;
            }
            sb.append(ch);
            if (pair.containsValue(ch))// 如果是左括号，放⼊栈中
            {
                sc.push(ch);
            } else if (pair.containsKey(ch)&&!sc.empty()&&sc.peek() == pair.get(ch)) // 如果匹配到对应的右括号就出栈
            {
                sc.pop();
            }
            //读取完字符串把最后一个数据放入虚拟erlang对象
            if (i == s.length()-1){
                erlangObject.put(keyindex + "",getObject(sb.toString()));
            }
        }
        return erlangObject;
    }

    /**
     * 转为object数组
     * []
     * @param s
     * @return
     * @throws ErlangParseException
     */
    public List<Object> enList(String s) throws ErlangParseException{
        if (s==null) throw new ErlangParseException();
        s = s.replace("\n","").replace("\r","").replace(" ","");
        if (!s.startsWith("[")||!s.endsWith("]")) throw new ErlangParseException();
        if (s.length()==2) s = "";
        else s = s.substring(1,s.length()-1);
        Stack<Character> sc = new Stack<Character>();
        List<Object> erlangObjectList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            Character ch = s.charAt(i);
            if (ch==','&&sc.empty()){

                erlangObjectList.add(getObject(sb.toString()));
                sb.delete( 0, sb.length() );
                continue;
            }
            sb.append(ch);
            if (pair.containsValue(ch))// 如果是左括号，放⼊栈中
            {
                sc.push(ch);
            } else if (pair.containsKey(ch)&&!sc.empty()&&sc.peek() == pair.get(ch)) // 如果是右括号
            {
                sc.pop();
            }
            if (i == s.length()-1){
                erlangObjectList.add(getObject(sb.toString()));
            }
        }
        return erlangObjectList;
    }


    /**
     * 通过字符串前缀获取object
     * @param str
     * @return
     */
    private Object getObject(String str){
        try {
            if (str.startsWith("{")) {
                return enClass(str);
            } else if (str.startsWith("[")) {
                return enList(str);
            } else {
                return str;
            }
        }catch (Exception e){
            return str;
        }
    }
}