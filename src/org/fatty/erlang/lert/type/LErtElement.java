package org.fatty.erlang.lert.type;


import org.fatty.erlang.lert.constant.LErtTypeEnum;

/**
 * 元素
 */
public interface LErtElement {

	public void setOffset(int offset);

	public int getOffset();

	public LErtList getAsBertList();

	public LErtObj getAsBertObj();

	public LErtDecimal getAsBertDecimal();

	public LErtTypeEnum getType();

	public String getStringValue();

	public byte[] getDates();
}
