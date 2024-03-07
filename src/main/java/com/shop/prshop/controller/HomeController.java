package com.shop.prshop.controller;

import com.shop.prshop.Cart;
import com.shop.prshop.model.Item;
import com.shop.prshop.repository.ItemRepository;
import com.shop.prshop.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final CartService cartService;

    @Autowired
    public HomeController(CartService cartService)
    {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("items", cartService.getAllItems());
        return "home";
    }

    @GetMapping("/applepage")
    public String applePage(Model model) {
        List<Item> items = cartService.getAllItems();
        model.addAttribute("items", items);
        return "applepage";
    }

    @GetMapping("/add/{itemId}")
    public String addItem(@PathVariable("itemId") Long itemId, Model model) {
        cartService.addItemToCart(itemId);
        model.addAttribute("items", cartService.getAllItems());
        return "applepage";
    }

    @GetMapping("/acountpage")
    public String showAcountPage() {
        return "acountpage";
    }

}
