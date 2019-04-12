package org.fatty.erlang.lert;





import org.fatty.erlang.lert.constant.LErtCode;
import org.fatty.erlang.lert.type.LErtDecimal;
import org.fatty.erlang.lert.type.LErtElement;
import org.fatty.erlang.lert.type.LErtList;
import org.fatty.erlang.lert.type.LErtObj;
import org.fatty.erlang.lert.util.ByteArrayUtils;

import java.util.Arrays;
import java.util.zip.DataFormatException;

public class LErt {

	public static LErtElement decode(byte[] data, int offset)
			throws DataFormatException {
		int i = 0;
		byte[] header = new byte[2];

		if (offset > data.length) {
			throw new IndexOutOfBoundsException("Offset " + offset
					+ " > than data lentgh " + data.length);
		}
		header[0] = data[offset];
		header[1] = data[offset + 1];
		if (LErtCode.ERL_DIST_HEADER.equals(header)) {
			throw new DataFormatException(
					"Encountered ERL_DIST_HEADER, at the moment parsing of this structure is not implemented");
		}
		LErtCode code = LErtCode.get(ByteArrayUtils.readInt(data, offset++, 1));
		switch (code) {
		case ERL_VERSION:
			return decode(data, offset);
		case ERL_SMALL_ATOM:
			return decodeAtom(data, offset, 1);
		case ERL_ATOM:
			return decodeAtom(data, offset, 2);
		case ERL_BIN:
			return decodeBin(data, offset);
		case ERL_SMALL_BIGNUM:
			return decodeBigNum(data, offset, 1);
		case ERL_LARGE_BIGNUM:
			return decodeBigNum(data, offset, 4);
		case ERL_SMALL_INT:
			return decodeInt(data, offset, 1);
		case ERL_INT:
			return decodeInt(data, offset, 4);
		case ERL_SMALL_TUPLE:
			return decodeTuple(data, offset, 1);
		case ERL_LARGE_TUPLE:
			return decodeTuple(data, offset, 4);
		case ERL_LIST:
			return decodeList(data, offset);
		case ERL_NIL:
			return decodeNil(offset);// skip null list
		default:
			throw new DataFormatException(
					"Encountered an unrecognized BERT field " + i + " at "
							+ Integer.toHexString(offset));
		}
	}




	public static LErtObj decodeNil(int offset) {
		LErtObj obj = new LErtObj();
		obj.setOffset(offset);
		return obj; // nil already ++ ofted at type check phase
	}

	public static LErtDecimal decodeInt(byte[] data, int offset, int len) {
		LErtDecimal obj = new LErtDecimal();
		obj.setValue(ByteArrayUtils.readInt(data, offset, len));
		obj.setOffset(offset + len);
		return obj;
	}

	public static LErtDecimal decodeBigNum(byte[] data, int offset, int len) {
		LErtDecimal obj = new LErtDecimal();
		int size = ByteArrayUtils.readInt(data, (int) offset, len);
		offset = offset + len;
		obj.setValue(ByteArrayUtils.readLong(data, (int) offset, size));
		obj.setOffset(offset + size + 1);
		return obj;
	}

	public static LErtObj decodeBin(byte[] data, int offset) {
		LErtObj obj = new LErtObj();
		int size = ByteArrayUtils.readInt(data, (int) offset, 4);
		offset = offset + 4;
		obj.setValue(Arrays
				.copyOfRange(data, (int) offset, (int) offset + size));
		if (size > 0) {
			obj.setOffset(offset + size);
		} else {
			obj.setOffset(offset + 1);
		}
		return obj;
	}

	public static LErtObj decodeAtom(byte[] data, int offset, int len) {
		LErtObj obj = new LErtObj();
		int size = ByteArrayUtils.readInt(data, (int) offset, len);
		offset += len;
		obj.setValue(Arrays
				.copyOfRange(data, (int) offset, (int) offset + size));
		obj.setOffset(offset + size);
		return obj;
	}

	public static LErtList decodeTuple(byte[] data, int offset, int len)
			throws DataFormatException {
		LErtList obj = new LErtList();
		int elements = ByteArrayUtils.readInt(data, (int) offset, len);
		obj.setOffset(offset + len);
		for (int i = 0; i < elements; i++) {
			obj.add(decode(data, obj.getOffset()));
		}
		return obj;
	}

	public static LErtElement decodeList(byte[] data, int offset)
			throws DataFormatException {
		LErtList obj = new LErtList();
		int elements = ByteArrayUtils.readInt(data, offset, 4);
		obj.setOffset(offset + 4);

		for (int i = 0; i < elements; i++) {
			obj.add(decode(data, (int) obj.getOffset()));
		}
		return obj;
	}

}