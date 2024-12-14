package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Filters;

////import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.CustomWrapper.CustomHttpServletResponseWrapper;
////import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import lombok.ToString;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//
//@Slf4j
//@AllArgsConstructor
//@Component
//@ToString
//public class LoggingFilter extends OncePerRequestFilter {
//
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private final HandlerExceptionResolver handlerExceptionResolver;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        try {
//            HttpServletRequest incomingRequest = request;
//
//            // Wrap the response to capture its content
//            CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(response);
//
//            // Log incoming request
//            log.info("Incoming request: Method={}, Url={}, Headers={}",
//                    incomingRequest.getMethod(),
//                    incomingRequest.getRequestURL(),
//                    getHeadersAsString(incomingRequest)
//            );
//
//            filterChain.doFilter(request, responseWrapper);
//
//            // Log outgoing response
//            String responseBody = responseWrapper.getCapturedResponseBody();
//            log.info("Outgoing Response: Status={} Body={}",
//                    responseWrapper.getStatus(),
//                    responseBody
//            );
//
//            // Write the captured response body back
//            response.resetBuffer();
//            response.getWriter().write(responseBody);
//
//        } catch (Exception ex) {
//            handlerExceptionResolver.resolveException(request, response, null, ex);
//        }
//    }
//
//    private String getHeadersAsString(HttpServletRequest request) {
//        StringBuilder headers = new StringBuilder();
//        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
//                headers.append(headerName).append(": ").append(request.getHeader(headerName)).append(", ")
//        );
//        // Remove trailing comma and space
//        if (headers.length() > 0) {
//            headers.setLength(headers.length() - 2);
//        }
//        return headers.toString();
//    }
//}