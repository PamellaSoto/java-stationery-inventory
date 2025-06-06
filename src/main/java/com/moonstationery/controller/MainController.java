package com.moonstationery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moonstationery.model.Anime;
import com.moonstationery.model.AnimeService;
import com.moonstationery.model.Category;
import com.moonstationery.model.CategoryService;
import com.moonstationery.model.Product;
import com.moonstationery.model.ProductService;

@Controller
public class MainController {
    
    @Autowired
    private AnimeService animeServ;
    @Autowired
    private CategoryService categoryServ;
    @Autowired
    private ProductService productServ;

    @GetMapping("/")
    public String index(Model model) {
        List<Map<String,Object>> products = productServ.listAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Initialize all forms on this page
        model.addAttribute("anime", new Anime());
        model.addAttribute("category", new Category());
        model.addAttribute("product", new Product());

        List<Map<String,Object>> animes = animeServ.listAll();
        List<Map<String,Object>> productTypes = categoryServ.listAll();
        List<Map<String,Object>> products = productServ.listAll();
        model.addAttribute("animes", animes);
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("products", products);
        //Updates when a new order is added to the system
        // model.addAttribute("orders-total", "5");
        return "dashboard";
    }
    
    //Create new anime title
    @PostMapping("/new-anime")
    public String insertAnime(@ModelAttribute Anime anime, RedirectAttributes redirectMessage) {
        if (!animeServ.insertAnime(anime)) { // Call the method and see if it's FALSY(error) or TRUTHY(success)
            redirectMessage.addFlashAttribute("animeError", "Anime title is already in the system."); 
        } else {
            redirectMessage.addFlashAttribute("animeSuccess", "New anime title added!");
        }
        return "redirect:/dashboard"; // When redirecting, a message will display telling if the action was a success or not.
    }

    //Create new category type
    @PostMapping("/new-category")
    public String insertCategory(@ModelAttribute Category category, RedirectAttributes redirectMessage) {
        if(!categoryServ.insertCategory(category)) {
            redirectMessage.addFlashAttribute("categoryError", "Category type is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("categorySuccess", "New category type added!");
        }
        return "redirect:/dashboard";
    }

    //Create new product
    @PostMapping("/new-product")
    public String insertProduct(@ModelAttribute Product product, RedirectAttributes redirectMessage) {
        productServ.insertProduct(product);
        redirectMessage.addFlashAttribute("addProductSuccess", "New product added!");
        return "redirect:/dashboard";
    }
}
