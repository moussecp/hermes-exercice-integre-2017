package com.hermes_ecs.java_exercise.controller;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise.controller.dto.ProductForm;
import com.hermes_ecs.java_exercise.controller.validation.ProductFormValidator;
import com.hermes_ecs.java_exercise.dao.ProductDao;
import com.hermes_ecs.java_exercise.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/products")
@Transactional(readOnly = true)
public class ProductController {

    static final String PRODUCT_MODEL_ATTRIBUTE = "product";
    static final String FORWARD_TO_LIST = "products/listProducts";
    static final String FORWARD_TO_EDIT_FORM = "products/editProduct";
    static final String REDIRECT_TO_LIST = "redirect:/products";
    static final String FORWARD_TO_ADD_FORM = "products/addProduct";
    static final String PRODUCTS_MODEL_ATTRIBUTE = "products";
    private static final String SUCCESS_MESSAGE_ATTRIBUTE = "successMessage";

    @Autowired
    private ProductDao productDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductFormValidator());
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(Throwable e) {
        WattoExceptionHandler wattoExceptionHandler = new WattoExceptionHandler(e);
        String viewName = listProducts(wattoExceptionHandler.getModel());
        wattoExceptionHandler.setViewName(viewName);
        return wattoExceptionHandler.getResponse();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listProducts(ModelMap model) {
        model.put(PRODUCTS_MODEL_ATTRIBUTE, productDao.findAllProductsOrganized());
        return FORWARD_TO_LIST;
    }

    @RequestMapping(value = {"/{productId}/edit"}, method = RequestMethod.GET)
    public String editProductForm(Model model, @PathVariable Long productId) {
        Optional<Product> product = productDao.find(productId);
        if (product.isPresent()) {
            ProductForm productForm = new ProductForm(product.get());
            model.addAttribute(PRODUCT_MODEL_ATTRIBUTE, productForm);
            return FORWARD_TO_EDIT_FORM;
        } else {
            throw new NoSuchElementException("product.unknownid");
        }
    }

    @RequestMapping(value = {"/{productId}"}, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String editProduct(@PathVariable Long productId,
                              @ModelAttribute(PRODUCT_MODEL_ATTRIBUTE) @Valid ProductForm productForm,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return FORWARD_TO_EDIT_FORM;
        } else {
            Optional<Product> product = productDao.find(productId);
            if (product.isPresent()) {
                updateProductWithUserInput(product.get(), productForm);
                redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_ATTRIBUTE, "product.edit.success");
                return REDIRECT_TO_LIST;
            } else {
                throw new NoSuchElementException("product.unknownid");
            }
        }
    }

    private void updateProductWithUserInput(Product product, ProductForm productForm) {
        product.setDescription(productForm.getDescription());
        product.setLabel(productForm.getLabel());
        product.setPrice(productForm.getPriceAsRepublicDactaryAmount());
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addProductForm(Model model) {
        model.addAttribute(PRODUCT_MODEL_ATTRIBUTE, new ProductForm());
        return FORWARD_TO_ADD_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String addProduct(@ModelAttribute(PRODUCT_MODEL_ATTRIBUTE) @Valid ProductForm productForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return FORWARD_TO_ADD_FORM;
        } else {
            Product product = new Product(productForm.getLabel(), null, productForm.getDescription(), productForm.getPriceAsRepublicDactaryAmount());
            productDao.persist(product);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_ATTRIBUTE, "product.add.success");
            return REDIRECT_TO_LIST;
        }
    }

    void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
