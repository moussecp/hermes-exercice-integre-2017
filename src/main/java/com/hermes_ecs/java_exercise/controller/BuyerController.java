package com.hermes_ecs.java_exercise.controller;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise.controller.dto.BuyerForm;
import com.hermes_ecs.java_exercise.controller.validation.BuyerFormValidator;
import com.hermes_ecs.java_exercise.dao.BuyerDao;
import com.hermes_ecs.java_exercise.domain.Buyer;
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
@RequestMapping("/buyers")
@Transactional(readOnly = true)
public class BuyerController {

    static final String BUYER_MODEL_ATTRIBUTE = "buyer";
    static final String SUCCESS_MESSAGE_ATTRIBUTE = "successMessage";
    static final String REDIRECT_TO_LIST = "redirect:/buyers";
    static final String FORWARD_TO_ADD_FORM = "buyers/addBuyer";
    static final String FORWARD_TO_EDIT_FORM = "buyers/editBuyer";
    static final String FORWARD_TO_LIST = "buyers/listBuyers";

    @Autowired
    private BuyerDao buyerDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new BuyerFormValidator());
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(Throwable e) {
        WattoExceptionHandler wattoExceptionHandler = new WattoExceptionHandler(e);
        String viewName = listBuyers(wattoExceptionHandler.getModel());
        wattoExceptionHandler.setViewName(viewName);
        return wattoExceptionHandler.getResponse();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listBuyers(ModelMap model) {
        model.addAttribute("buyers", buyerDao.findAll());
        return FORWARD_TO_LIST;
    }

    @RequestMapping(value = {"/{buyerId}/edit"}, method = RequestMethod.GET)
    public String editBuyerForm(Model model, @PathVariable Long buyerId) {
        Optional<Buyer> buyer = buyerDao.find(buyerId);

        if (buyer.isPresent()) {
            BuyerForm buyerForm = new BuyerForm(buyer.get());
            model.addAttribute(BUYER_MODEL_ATTRIBUTE, buyerForm);
            return FORWARD_TO_EDIT_FORM;
        } else {
            throw new NoSuchElementException("buyer.unknownid");
        }
    }

    @RequestMapping(value = {"/{buyerId}"}, method = RequestMethod.PUT)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String editBuyer(@PathVariable Long buyerId,
                            @ModelAttribute(BUYER_MODEL_ATTRIBUTE) @Valid BuyerForm buyerForm,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return FORWARD_TO_EDIT_FORM;
        }

        Optional<Buyer> buyer = buyerDao.find(buyerId);

        if (buyer.isPresent()) {
            updateBuyerWithUserInput(buyer.get(), buyerForm);
            setSuccessMessage("buyer.edit.success", redirectAttributes);
            return REDIRECT_TO_LIST;
        } else {
            throw new NoSuchElementException("buyer.unknownid");
        }
    }

    private void updateBuyerWithUserInput(Buyer buyer, BuyerForm buyerForm) {
        buyer.setFirstName(buyerForm.getFirstName());
        buyer.setLastName(buyerForm.getLastName());
        buyer.setBirthLocation(buyerForm.getBirthLocation());
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addBuyerForm(Model model) {
        model.addAttribute(BUYER_MODEL_ATTRIBUTE, new BuyerForm());
        return FORWARD_TO_ADD_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String addBuyer(@ModelAttribute(BUYER_MODEL_ATTRIBUTE) @Valid BuyerForm buyerForm,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return FORWARD_TO_ADD_FORM;
        }

        Buyer buyer = new Buyer(buyerForm.getFirstName(), buyerForm.getLastName(), buyerForm.getBirthLocation());
        buyerDao.persist(buyer);
        setSuccessMessage("buyer.add.success", redirectAttributes);
        return REDIRECT_TO_LIST;
    }

    private void setSuccessMessage(String message, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_ATTRIBUTE, message);
    }

    void setBuyerDao(BuyerDao buyerDao) {
        this.buyerDao = buyerDao;
    }

}
