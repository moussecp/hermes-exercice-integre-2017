package com.hermes_ecs.java_exercise.controller;

import com.hermes_ecs.java_exercise.controller.dto.ProductForm;
import com.hermes_ecs.java_exercise.controller.validation.ProductFormValidator;
import com.hermes_ecs.java_exercise.dao.ProductMapDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static com.hermes_ecs.java_exercise.controller.ProductController.*;
import static com.hermes_ecs.java_exercise.domain.ProductMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ProductControllerTest {
    private ProductController controller;
    private ProductMapDao productDao;

    @Before
    public void setUp() throws Exception {
        controller = new ProductController();
        productDao = new ProductMapDao();
        controller.setProductDao(productDao);
    }

    @Test
    public void initBinderAddsValidator() {
        // given
        final ProductForm form = new ProductForm();
        final WebDataBinder binder = new WebDataBinder(form, PRODUCT_MODEL_ATTRIBUTE);

        // when
        controller.initBinder(binder);

        // then
        assertThat(binder.getValidators().get(0), is(instanceOf(ProductFormValidator.class)));
    }

    @Test
    public void listProducts() {
        // given
        final ModelMap model = new ModelMap();

        // when
        final String view = controller.listProducts(model);

        // then
        assertThat(model.get(PRODUCTS_MODEL_ATTRIBUTE), is(notNullValue()));
        assertThat(view, is(equalTo(FORWARD_TO_LIST)));
    }

    @Test
    public void editProductForm() {
        // given
        final Model model = new ExtendedModelMap();
        long buyerId = productDao.addEntity(landSpeeder());

        // when
        final String view = controller.editProductForm(model, buyerId);

        // then
        assertThat(model.asMap().get(PRODUCT_MODEL_ATTRIBUTE), is(notNullValue()));
        assertThat(view, is(equalTo(FORWARD_TO_EDIT_FORM)));
    }

    @Test
    public void editProductWithBindingErrors() {
        // given
        final long buyerId = productDao.addEntity(landSpeeder());
        final ProductForm form = new ProductForm();
        final BindingResult bindingResult = createBindingResultWithError(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.editProduct(buyerId, form, bindingResult, redirectAttributes);

        // then
        assertThat(view, is(equalTo(FORWARD_TO_EDIT_FORM)));
    }

    @Test
    public void editProductWithValidForm() {
        // given
        final long buyerId = productDao.addEntity(landSpeeder());
        final ProductForm form = createValidForm();
        final BindingResult bindingResult = createBindingResult(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.editProduct(buyerId, form, bindingResult, redirectAttributes);

        // then
        assertThat(productDao.contains(t47Airspeeder()), is(true));
        assertThat(view, is(equalTo(REDIRECT_TO_LIST)));
    }

    @Test
    public void addProductForm() {
        // given
        final ExtendedModelMap model = new ExtendedModelMap();

        // when
        final String view = controller.addProductForm(model);

        // then
        assertThatProductModelAttributeIsSet(model);
        assertThat(view, is(equalTo(FORWARD_TO_ADD_FORM)));
    }

    private void assertThatProductModelAttributeIsSet(ExtendedModelMap model) {
        assertThat(model.asMap().containsKey(ProductController.PRODUCT_MODEL_ATTRIBUTE), is(true));
    }

    @Test
    public void addProductWithBindingErrors() {
        // given
        final ProductForm form = new ProductForm();
        final BindingResult bindingResult = createBindingResultWithError(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.addProduct(form, bindingResult, redirectAttributes);

        // then
        assertThat(view, is(equalTo(FORWARD_TO_ADD_FORM)));
    }

    @Test
    public void addProductWithValidForm() {
        // given
        final ProductForm form = createValidForm();
        final BindingResult bindingResult = createBindingResult(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.addProduct(form, bindingResult, redirectAttributes);

        // then
        assertThat(productDao.contains(t47Airspeeder()), is(true));
        assertThat(view, is(equalTo(REDIRECT_TO_LIST)));
    }

    private ProductForm createValidForm() {
        ProductForm form = new ProductForm();
        form.setLabel(T47_AIRSPEEDER);
        form.setDescription(T47_AIRSPEEDER_DESCRIPTION);
        form.setPrice(T47_AIRSPEEDER_PRICE.getValueForDisplay());
        return form;
    }

    private BindingResult createBindingResultWithError(ProductForm form) {
        BindingResult bindingResult = createBindingResult(form);
        bindingResult.reject("error");
        return bindingResult;
    }

    private BindingResult createBindingResult(ProductForm form) {
        return new BeanPropertyBindingResult(form, PRODUCT_MODEL_ATTRIBUTE);
    }

}
