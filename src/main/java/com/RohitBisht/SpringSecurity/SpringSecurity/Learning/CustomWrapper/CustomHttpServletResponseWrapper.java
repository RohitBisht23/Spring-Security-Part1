package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.CustomWrapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

//public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

//    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    private final PrintWriter writer = new PrintWriter(outputStream);
//    private final ServletOutputStream servletOutputStream = new ServletOutputStream() {
//        @Override
//        public boolean isReady() {
//            return false;
//        }
//
//        @Override
//        public void setWriteListener(WriteListener writeListener) {
//
//        }
//
//        @Override
//        public void write(int b) throws IOException {
//            outputStream.write(b); // Write a byte to the output stream
//        }
//    };

//    public CustomHttpServletResponseWrapper(HttpServletResponse response) {
//        super(response);
//    }
//
//    @Override
//    public PrintWriter getWriter() throws IOException {
//        return writer;  // Capture the response content written through PrintWriter
//    }
//
//    @Override
//    public ServletOutputStream getOutputStream() throws IOException {
//        return servletOutputStream;  // Return a custom ServletOutputStream to capture the response content
//    }
//
//    public String getCapturedResponseBody() throws IOException {
//        writer.flush();
//        return outputStream.toString();  // Return the captured response body as a string
//    }
//}