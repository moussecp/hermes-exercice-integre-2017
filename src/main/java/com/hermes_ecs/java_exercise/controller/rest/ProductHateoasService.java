package com.hermes_ecs.java_exercise.controller.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Method;


@Service
public class ProductHateoasService {

    protected static HttpHeaders getDefaultHeader(UriComponentsBuilder ucb) {
//        Method allProductsForCategoryNameInJson = getMethodForName("allProductsForCategoryNameInJson", String.class, UriComponentsBuilder.class);
//        Method allProductsForCategoryNameInXml = getMethodForName("allProductsForCategoryNameInXml", String.class, UriComponentsBuilder.class);
//        Method allProductsForDepartmentInJson = getMethodForName("allProductsForDepartmentInJson", String.class, UriComponentsBuilder.class);
//        Method allProductsForDepartmentInXml = getMethodForName("allProductsForDepartmentInXml", String.class, UriComponentsBuilder.class);
//        Method allProductsForLabelNameInJson = getMethodForName("allProductsForLabelNameInJson", String.class, UriComponentsBuilder.class);
//        Method allProductsForLabelNameInXml = getMethodForName("allProductsForLabelNameInXml", String.class, UriComponentsBuilder.class);
//        Method allProductsInJson = getMethodForName("allProductsInJson", UriComponentsBuilder.class);
//        Method allProductsInXml = getMethodForName("allProductsInXml", UriComponentsBuilder.class);
//        Method singleProductInJson = getMethodForName("singleProductInJson", Long.class, UriComponentsBuilder.class);
//        Method singleProductInXml = getMethodForName("singleProductInXml", Long.class, UriComponentsBuilder.class);

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("allProductsInJson", linkTo(allProductsInJson).toUri().toASCIIString());
        httpHeaders.setLocation(ucb.path("/products/")
                .build()
                .toUri());
//        httpHeaders.setLocation(linkTo(ProductController.class).toUri());
//        httpHeaders.setLocation(linkTo(allProductsForCategoryNameInJson).toUri());
        return httpHeaders;
    }

    private static Method getMethodForName(String methodName, Class<?>... parameterTypes) {
        try {
            return ProductRestController.class.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
