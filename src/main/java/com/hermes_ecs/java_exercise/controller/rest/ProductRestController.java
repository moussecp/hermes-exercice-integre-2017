package com.hermes_ecs.java_exercise.controller.rest;

import com.hermes_ecs.java_exercise.dao.ProductDao;
import com.hermes_ecs.java_exercise.domain.Product;
import com.hermes_ecs.java_exercise.domain.constant.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Transactional(readOnly = true)
public class ProductRestController {

    private final ProductRestService productRestService;

    @Autowired
    ProductRestController(ProductRestService productRestService) {
        this.productRestService = productRestService;
    }

    // JSON resources

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsInJson(UriComponentsBuilder ucb) {
        return productRestService.getAllProductsResponse(ucb);
    }

    @RequestMapping(value = "/label/{label}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsForLabelNameInJson(@PathVariable("label") String label, UriComponentsBuilder ucb) {
        return productRestService.getAllProductsForLabelLike(label, ucb);
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsForCategoryNameInJson(@PathVariable("category") String category, UriComponentsBuilder ucb) {
        return productRestService.getAllProductsForCategoryNameResponse(category, ucb);
    }

    @RequestMapping(value = "/department/{department}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsForDepartmentInJson(@PathVariable("department") String department, UriComponentsBuilder ucb) {
        return productRestService.getAllProductsForDepartmentResponse(department, ucb);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> singleProductInJson(@PathVariable("id") Long id, UriComponentsBuilder ucb) {
        return productRestService.getSingleProductResponse(id, ucb);
    }

    // XML resources

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsInXml(UriComponentsBuilder ucb) {
        return productRestService.getAllProductsResponse(ucb);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> singleProductInXml(@PathVariable("id") Long id, UriComponentsBuilder ucb) {
        return productRestService.getSingleProductResponse(id, ucb);
    }

    @RequestMapping(value = "/label/{label}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsForLabelNameInXml(@PathVariable("label") String label, UriComponentsBuilder ucb) {
        return productRestService.getAllProductsForLabelLike(label, ucb);
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsForCategoryNameInXml(@PathVariable("category") String category, UriComponentsBuilder ucb) {
        return productRestService.getAllProductsForCategoryNameResponse(category, ucb);
    }

    @RequestMapping(value = "/department/{department}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsForDepartmentInXml(@PathVariable("department") String department, UriComponentsBuilder ucb) {
        return productRestService.getAllProductsForDepartmentResponse(department, ucb);
    }
}
