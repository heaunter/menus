package com.vota.reservation.common.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

/**
 * 
 */
public class LogHttpInfoServletOutputStream extends ServletOutputStream {

	private ByteArrayOutputStream bos;

	LogHttpInfoServletOutputStream() {
		bos = new ByteArrayOutputStream();
	}

	public void write(int param) throws IOException {
		bos.write(param);
	}

	public void write(byte[] b, int off, int len) throws IOException {
		bos.write(b, off, len);
	}

	protected byte[] getBytes() {
		return bos.toByteArray();
	}

}
