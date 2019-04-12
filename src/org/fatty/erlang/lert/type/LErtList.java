package org.fatty.erlang.lert.type;



import org.fatty.erlang.lert.constant.LErtTypeEnum;
import org.fatty.erlang.lert.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class LErtList implements LErtElement {

	private int offset = 0;
	private List<LErtElement> values = new ArrayList<LErtElement>();

	public LErtList(int offset, List<LErtElement> values) {
		this.offset = offset;
		this.values = values;
	}

	public LErtList() {

	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	public LErtElement get(int index) {
		return values.get(index);
	}

	@Override
	public LErtList getAsBertList() {
		return this;
	}

	@Override
	public LErtObj getAsBertObj() {
		throw new IllegalStateException("This is not a lert object");
	}

	@Override
	public LErtDecimal getAsBertDecimal() {
		throw new IllegalStateException("This is not a lert decimal type");
	}

	@Override
	public LErtTypeEnum getType() {
		return LErtTypeEnum.LErtList;
	}

	@Override
	public String getStringValue() {
		StringBuffer sb = new StringBuffer();
		values.forEach(value -> sb.append(value.getStringValue()));
		return sb.toString();
//		try{
//			return new String(getDates(),"utf-8");
//		}catch (UnsupportedEncodingException e){
//			return "";
//		}
	}

	@Override
	public byte[] getDates() {
		byte[] bytes = new byte[0];
		values.forEach(value -> {
			if (value != null) {
				ArrayUtils.addAll(bytes,value.getDates());
			}
		});
		return bytes;
	}

	public void setValue(LErtElement value) {
		offset += value.getOffset();
		values.add(value);
	}

	public void add(LErtElement e) {
		offset = e.getOffset();
		values.add(e);
	}

}
