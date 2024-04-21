package com.example.coupon.advice;

import com.example.coupon.annotation.IgnoreResponseAdvice;
import com.example.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {

        // If the @IgnoreResponseAdvice annotation is marked for the class in which the current method resides, it doesn't need to be handled.
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }

        // If the current method is marked with the @IgnoreResponseAdvice annotation, it doesn't need to be handled.
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }

        // Process the response, executing the beforeBodyWrite method.
        return true;
    }

    /**
     * Processing before response returns
     * */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        // Define the final return object
        CommonResponse<Object> response = new CommonResponse<>(
                0, ""
        );

        // If o is null, the response does not need to set the data
        if (null == o) {
            return response;
            // If o is already a CommonResponse, it doesn't need to be processed again.
        } else if (o instanceof CommonResponse) {
            response = (CommonResponse<Object>) o;
            // Otherwise, use the response object as the data part of the CommonResponse.
        } else {
            response.setData(o);
        }

        return response;
    }
}
