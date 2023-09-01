package com.integra.webShop.controllers;

import com.integra.webShop.models.lashSvc;
import com.integra.webShop.repo.lashSvcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PriceController {

    @Autowired
    private lashSvcRepository lashSvcRepository;

    @GetMapping("/price")
    public String price(Model model)
    {
        Iterable<lashSvc> lashSvcs = lashSvcRepository.findAll();
        model.addAttribute("lashSvcs", lashSvcs);
        return "price";

    }

    @GetMapping("/priceAdd")
    public String priceAdd(Model model)
    {
        return "priceAdd";
    }

    @PostMapping("/priceAdd")
    public String priceNewAdd(@RequestParam String title,@RequestParam String fullText, @RequestParam int price, Model model)
    {
        lashSvc lashSvc = new lashSvc(title, fullText, price);
        lashSvcRepository.save(lashSvc);
        return "redirect:/price";
    }


    @GetMapping("/price/{id}")
    public String priceInfo(@PathVariable(value ="id") long id, Model model)
    {
        if(!lashSvcRepository.existsById(id))
        {
            return"redirect:/price";
        }
        Optional<lashSvc> lashSvc = lashSvcRepository.findById(id);
        ArrayList<lashSvc> res = new ArrayList<>();
        lashSvc.ifPresent(res::add);
        model.addAttribute("lashSvc", res);
        return "priceInfo";
    }

    @GetMapping("/price/{id}/edit")
    public String priceEdit(@PathVariable(value ="id") long id, Model model)
    {
        if(!lashSvcRepository.existsById(id))
        {
            return"redirect:/price";
        }
        Optional<lashSvc> lashSvc = lashSvcRepository.findById(id);
        ArrayList<lashSvc> res = new ArrayList<>();
        lashSvc.ifPresent(res::add);
        model.addAttribute("lashSvc", res);
        return "priceEdit";
    }


    @PostMapping("/price/{id}/edit")
    public String priceUpdate(@RequestParam String title,@RequestParam String fullText, @RequestParam int price, @PathVariable(value ="id") long id, Model model)
    {
    lashSvc lashSvc = lashSvcRepository.findById(id).orElseThrow();
    lashSvc.setTitle(title);
    lashSvc.setFullText(fullText);
    lashSvc.setPrice(price);
    lashSvcRepository.save(lashSvc);
        return "redirect:/price";
    }



}
