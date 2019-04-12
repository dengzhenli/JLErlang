package org.fatty.erlang.lert;

import org.fatty.erlang.lert.type.LErtElement;

/**
 * Created by dengzhenli on 2019/4/12.
 */
public class LErtTest {

    public static void main(String[] args)throws Exception{
        LErtElement LErtElement = new LErt().decode(new byte[]{(byte) 0x83,(byte) 0x61,(byte) 0x01},0); //1
        LErtElement LErtElement1 = new LErt().decode(new byte[]{(byte) 0x83,100,0,1,97},0); //a
        LErtElement LErtElement2 = new LErt().decode(new byte[]{(byte) 0x83,104,3,100,0,1,97,100,0,1,98,100,0,1,99},0); //abc
        System.out.println(LErtElement.getStringValue());
        System.out.println(LErtElement1.getStringValue());
        System.out.println(LErtElement2.getStringValue());
    }
}
