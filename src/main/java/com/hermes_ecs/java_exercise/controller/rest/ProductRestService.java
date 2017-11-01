package com.hermes_ecs.java_exercise.controller.rest;

import com.hermes_ecs.java_exercise.dao.ProductDao;
import com.hermes_ecs.java_exercise.domain.Product;
import com.hermes_ecs.java_exercise.domain.constant.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.hermes_ecs.java_exercise.controller.rest.ProductHateoasService.getDefaultHeader;

@Service
public class ProductRestService {

    public static final String PRODUCT_NOT_FOUND_FOR_ID_ERROR = "could not find product for id: ";
    public static final String PRODUCT_NOT_FOUND_FOR_LABEL_NAME = "could not find product for label: ";
    public static final String PRODUCT_NOT_FOUND_FOR_CATEGORY_ERROR = "could not find product for category: ";
    public static final String PRODUCT_NOT_FOUND_FOR_DEPARTMENT_ERROR = "could not find product for department: ";

    private final ProductDao productDao;
    private final ProductHateoasService productHateoasService;

    @Autowired
    public ProductRestService(ProductDao productDao, ProductHateoasService productHateoasService) {
        this.productDao = productDao;
        this.productHateoasService = productHateoasService;
    }

    protected ResponseEntity<List<Product>> getAllProductsResponse(UriComponentsBuilder ucb) {
        return new ResponseEntity<>(productDao.findAll(), getDefaultHeader(ucb), HttpStatus.OK);
    }

    protected ResponseEntity<?> getSingleProductResponse(Long id, UriComponentsBuilder ucb) {
        try {
            return new ResponseEntity<>(productDao.find(id).get(), getDefaultHeader(ucb), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError(PRODUCT_NOT_FOUND_FOR_ID_ERROR + id), getDefaultHeader(ucb), HttpStatus.NOT_FOUND);
        }
    }

    protected ResponseEntity<?> getAllProductsForLabelLike(String labelName, UriComponentsBuilder ucb) {
        try {
            return new ResponseEntity<>(productDao.getProductsWithLabelLike(labelName), getDefaultHeader(ucb), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError(PRODUCT_NOT_FOUND_FOR_LABEL_NAME + labelName), getDefaultHeader(ucb), HttpStatus.NOT_FOUND);
        }
    }

    protected ResponseEntity<?> getAllProductsForCategoryNameResponse(String categoryName, UriComponentsBuilder ucb) {
        try {
            return new ResponseEntity<>(productDao.getProductsWithCategoryName(categoryName), getDefaultHeader(ucb), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError(PRODUCT_NOT_FOUND_FOR_CATEGORY_ERROR + categoryName), getDefaultHeader(ucb), HttpStatus.NOT_FOUND);
        }
    }

    protected ResponseEntity<?> getAllProductsForDepartmentResponse(String departmentName, UriComponentsBuilder ucb) {
        try {
            Department department = Department.valueOf(departmentName);
            return new ResponseEntity<>(productDao.getProductsWithCategoryDepartment(department), getDefaultHeader(ucb), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError(PRODUCT_NOT_FOUND_FOR_DEPARTMENT_ERROR + departmentName), getDefaultHeader(ucb), HttpStatus.NOT_FOUND);
        }
    }
}
