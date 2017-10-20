package com.hermes_ecs.java_exercise.controller;

import com.hermes_ecs.java_exercise.controller.dto.BuyerForm;
import com.hermes_ecs.java_exercise.controller.validation.BuyerFormValidator;
import com.hermes_ecs.java_exercise.dao.BuyerMapDao;
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

import static com.hermes_ecs.java_exercise.controller.BuyerController.*;
import static com.hermes_ecs.java_exercise.domain.BuyerMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BuyerControllerTest {

    private BuyerController controller;
    private BuyerMapDao buyerDao;

    @Before
    public void setUp() throws Exception {
        controller = new BuyerController();
        buyerDao = new BuyerMapDao();
        controller.setBuyerDao(buyerDao);
    }

    @Test
    public void initBinderAddsValidator() {
        // given
        final BuyerForm form = new BuyerForm();
        final WebDataBinder binder = new WebDataBinder(form, BUYER_MODEL_ATTRIBUTE);

        // when
        controller.initBinder(binder);

        // then
        assertThat(binder.getValidators().get(0), is(instanceOf(BuyerFormValidator.class)));
    }

    @Test
    public void listBuyers() {
        // given
        final ModelMap model = new ModelMap();

        // when
        final String view = controller.listBuyers(model);

        // then
        assertThat(model.get("buyers"), is(notNullValue()));
        assertThat(view, is(equalTo(FORWARD_TO_LIST)));
    }

    @Test
    public void editBuyerForm() {
        // given
        final Model model = new ExtendedModelMap();
        long buyerId = buyerDao.addEntity(lukeSkywalker());

        // when
        final String view = controller.editBuyerForm(model, buyerId);

        // then
        assertThat(model.asMap().get(BuyerController.BUYER_MODEL_ATTRIBUTE), is(notNullValue()));
        assertThat(view, is(equalTo(FORWARD_TO_EDIT_FORM)));
    }

    @Test
    public void editBuyerWithBindingErrors() {
        // given
        final long buyerId = buyerDao.addEntity(lukeSkywalker());
        final BuyerForm form = new BuyerForm();
        final BindingResult bindingResult = createBindingResultWithError(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.editBuyer(buyerId, form, bindingResult, redirectAttributes);

        // then
        assertThat(view, is(equalTo(FORWARD_TO_EDIT_FORM)));
    }

    @Test
    public void editBuyerWithValidForm() {
        // given
        final long buyerId = buyerDao.addEntity(lukeSkywalker());
        final BuyerForm form = createValidForm();
        final BindingResult bindingResult = createBindingResult(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.editBuyer(buyerId, form, bindingResult, redirectAttributes);

        // then
        assertThat(buyerDao.contains(balonGreyjoy()), is(true));
        assertThat(view, is(equalTo(REDIRECT_TO_LIST)));
    }

    @Test
    public void addBuyerForm() {
        // given
        final ExtendedModelMap model = new ExtendedModelMap();

        // when
        final String view = controller.addBuyerForm(model);

        // then
        assertThatBuyerModelAttributeIsSet(model);
        assertThat(view, is(equalTo(FORWARD_TO_ADD_FORM)));
    }

    private void assertThatBuyerModelAttributeIsSet(ExtendedModelMap model) {
        assertThat(model.asMap().containsKey(BuyerController.BUYER_MODEL_ATTRIBUTE), is(true));
    }

    @Test
    public void addBuyerWithBindingErrors() {
        // given
        final BuyerForm form = new BuyerForm();
        final BindingResult bindingResult = createBindingResultWithError(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.addBuyer(form, bindingResult, redirectAttributes);

        // then
        assertThat(view, is(equalTo(FORWARD_TO_ADD_FORM)));
    }

    @Test
    public void addBuyerWithValidForm() {
        // given
        final BuyerForm form = createValidForm();
        final BindingResult bindingResult = createBindingResult(form);
        final RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // when
        final String view = controller.addBuyer(form, bindingResult, redirectAttributes);

        // then
        assertThat(buyerDao.contains(balonGreyjoy()), is(true));
        assertThat(view, is(equalTo(REDIRECT_TO_LIST)));
    }

    private BuyerForm createValidForm() {
        BuyerForm form = new BuyerForm();
        form.setFirstName(BALON);
        form.setLastName(GREYJOY);
        form.setBirthLocation(PYK);
        return form;
    }

    private BindingResult createBindingResultWithError(BuyerForm form) {
        BindingResult bindingResult = createBindingResult(form);
        bindingResult.reject("error");
        return bindingResult;
    }

    private BindingResult createBindingResult(BuyerForm form) {
        return new BeanPropertyBindingResult(form, BUYER_MODEL_ATTRIBUTE);
    }

}
