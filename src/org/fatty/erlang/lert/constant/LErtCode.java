package org.fatty.erlang.lert.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * erlang 二进制code
 */
public enum LErtCode {
	
	ERL_BIT_BINARY_EXT (77),
	ERL_SMALL_INT (97),
	//ERL_SMALL_INT_EXT (97),
	//ERL_OFFSET (97),
	ERL_INT (98),
	//ERL_INT_EXT (98),
	ERL_FLOAT (99),
	//ERL_FLOAT_EXIT (99),
	ERL_ATOM (100),
	//ERL_ATOM_EXT (100),
	ERL_REFERENCE_EXT (101),
	NEW_REFERENCE_EXT (114),
	ERL_PORT_EXT (102),
	ERL_PID_EXT (103),
	ERL_ATOM_CACHE_EXT (82),
	ERL_SMALL_ATOM (115),
	//ERL_SMALL_ATOM_EXT (115),
	ERL_SMALL_TUPLE (104),
	//ERL_SMALL_TUPLE_EXT (104),
	ERL_LARGE_TUPLE (105),
	ERL_NIL (106),
	ERL_STRING (107),
	//ERL_STRING_EXT (107),
	ERL_LIST (108),
	//ERL_LIST_EXT (108),
	ERL_BIN (109),
	//ERL_BIN_EXT (109),
	ERL_SMALL_BIGNUM (110),
	ERL_LARGE_BIGNUM (111),
	ERL_VERSION (131),
	ERL_UNKOWN (-1);

	private int code;
	
	private static final Map<Integer, LErtCode> map = new HashMap<Integer, LErtCode>();
	
	static {
		for (LErtCode code : LErtCode.values()){
			map.put(code.toInt(), code);
		}
	}
	
	public final static byte[] ERL_DIST_HEADER = new byte[] {
		LErtCode.ERL_VERSION.toByte(), 68 };
	
	LErtCode(int code){
		this.code = code;
	}
	
	public byte toByte(){
		return (byte) (code & 0xFF);
	}
	
	public int toInt(){
		return code;
	}
	
	public static LErtCode get(int code){
		LErtCode ret = map.get(code);
		if( ret == null){
			return ERL_UNKOWN;
		}
		return ret;
	}
}
