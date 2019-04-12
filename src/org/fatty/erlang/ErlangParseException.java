package org.fatty.erlang;

/**
 * Created by dengzhenli on 2019/1/5.
 * erlang定制异常
 */
public class ErlangParseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErlangParseException(){
        super();
    }

    public ErlangParseException(String message){
        super(message);
    }

    public ErlangParseException(String message, Throwable cause){
        super(message, cause);
    }
}
