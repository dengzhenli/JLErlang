package org.fatty.erlang.lert.type;


import org.fatty.erlang.lert.constant.LErtTypeEnum;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LErtObj implements LErtElement {

	private int offset = 0;
	private byte[] value = null;

	public LErtObj() {
	}

	public LErtObj(int offset, byte[] val) {
		this.offset = offset;
		this.value = val;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public byte[] getValue() {
		return value;
	}

	@Override
	public LErtList getAsBertList() {
		throw new IllegalStateException("This is not a lert list");
	}

	@Override
	public LErtObj getAsBertObj() {
		return this;
	}

	@Override
	public LErtDecimal getAsBertDecimal() {
		throw new IllegalStateException("This is not a lert decimal");
		// TODO i can convert it if the byte[] is not longer than 'long' type
	}

	@Override
	public LErtTypeEnum getType() {
		return LErtTypeEnum.LErtObj;
	}


	@Override
	public String getStringValue() {
		if (value == null ) return "------------";
		try{
			return new String(value,"utf-8");
		}catch (UnsupportedEncodingException e){
			return "++++++++++++";
		}
	}

	@Override
	public byte[] getDates() {
		return value;
	}

	public byte[] getAsByteArray() {
		return value;
	}


	@Override
	public String toString() {
		return "LErtObj{" +
				"offset=" + offset +
				", value=" + Arrays.toString(value) +
				'}';
	}
}
