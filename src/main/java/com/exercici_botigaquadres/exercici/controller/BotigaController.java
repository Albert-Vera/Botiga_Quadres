package com.exercici_botigaquadres.exercici.controller;

import com.exercici_botigaquadres.exercici.model.Picture;
import com.exercici_botigaquadres.exercici.model.ShopStore;
import com.exercici_botigaquadres.exercici.service.IBotigaService;
import com.exercici_botigaquadres.exercici.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class BotigaController {
    @Autowired
    private IBotigaService botigaService;
    @Autowired
    private IPictureService pictureService;

    @GetMapping("/listShopStore")
    public String listarShopStore(Model model){
        ShopStore shopStore = new ShopStore();
        List<ShopStore> shopStoreList= botigaService.findAll();
        model.addAttribute("shopStore", shopStoreList);
//        model.addAttribute("pictureList", shopStore.getPictureList().size());
        System.out.println("bla bla bla list: " + shopStore.getPictureList().size() );
        return "index";
    }

    @GetMapping("/new")
    public String nuevo(ShopStore shopStore){

        return "insert";
    }
    @PostMapping("insert")
    public String insert(@Validated ShopStore shopStore){
        botigaService.save(shopStore);
        return "redirect:/listShopStore";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){

        ShopStore shopStore = botigaService.findByIdStore(id);
        model.addAttribute("shopStore", shopStore);
        return "editar";
    }
    @PostMapping("/actualizar")
    public String actualizar(@Validated ShopStore shopStore){
        botigaService.save(shopStore);
        return "redirect:/listShopStore";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        botigaService.deleteById(id);
        return "redirect:/listShopStore";
    }
}
