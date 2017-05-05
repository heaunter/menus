package com.vota.reservation.common.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class LogHttpInfoResponseWrapper extends HttpServletResponseWrapper {

	private LogHttpInfoServletOutputStream outStream;
	private ServletOutputStream stream;
	private PrintWriter writer;

	private StringBuffer responseHeaders;

	public LogHttpInfoResponseWrapper(HttpServletResponse original) {
		super(original);
		responseHeaders = new StringBuffer();
	}

	protected ServletOutputStream createOutputStream() throws IOException {
		outStream = new LogHttpInfoServletOutputStream();
		return outStream;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (stream != null) {
			return stream;
		}
		if (writer != null) {
			throw new IOException("Writer already in use");
		}
		stream = createOutputStream();
		return stream;
	}

	public PrintWriter getWriter() throws IOException {
		if (writer != null) {
			return writer;
		}
		if (stream != null) {
			throw new IOException("OutputStream already in use");
		}
		writer = new PrintWriter(new OutputStreamWriter(createOutputStream()));
		return writer;
	}

	protected byte[] getBytes() throws IOException {
		if (outStream != null) {
			return outStream.getBytes();
		}
		return null;
	}

	@Override
	public void addHeader(String name, String value) {
		super.addHeader(name, value);
		responseHeaders.append(name).append("=").append(value).append(";");
	}

	public StringBuffer getResponseHeaders() {
		return responseHeaders;
	}

}
