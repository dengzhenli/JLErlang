package org.fatty.erlang.lert.type;



import org.fatty.erlang.lert.constant.LErtTypeEnum;
import org.fatty.erlang.lert.util.ByteArrayUtils;

import java.nio.ByteBuffer;

public class LErtDecimal implements LErtElement {

	public int offset;
	public long value;

	public LErtDecimal() {
	}

	public LErtDecimal(int offset, long value) {
		this.offset = offset;
		this.value = value;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}


	@Override
	public LErtTypeEnum getType() {
		return LErtTypeEnum.LErtDecimal;
	}

	@Override
	public String getStringValue() {
		return String.valueOf(value);
	}

	@Override
	public LErtList getAsBertList() {
		throw new IllegalStateException("This is not a lert list");
	}

	@Override
	public LErtObj getAsBertObj() {
		return new LErtObj(0, ByteBuffer.allocate(Long.SIZE / Byte.SIZE)
				.putLong(value).array());
	}

	@Override
	public LErtDecimal getAsBertDecimal() {
		return this;
	}

	@Override
	public byte[] getDates() {
		return ByteArrayUtils.long2Bytes(value);
	}


	@Override
	public String toString() {
		return "LErtDecimal{" +
				"offset=" + offset +
				", value=" + value +
				'}';
	}
}
