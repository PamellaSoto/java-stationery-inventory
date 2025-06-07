package com.moonstationery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String home(Model model) {
        List<Map<String,Object>> products = productServ.listAllAvailable();
        model.addAttribute("products", products);

        List<Map<String,Object>> animes = animeServ.listAll();
        model.addAttribute("animes", animes);

        List<Map<String,Object>> productTypes = categoryServ.listAll();
        model.addAttribute("productTypes", productTypes);

        return "home";
    }

    @GetMapping("/product/{id}")
    public String productPage(@PathVariable Integer id, Model model) {
        Product product = productServ.getProductById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Initialize all forms on this page
        model.addAttribute("anime", new Anime());
        model.addAttribute("category", new Category());

        model.addAttribute("product", new Product());

        List<Map<String,Object>> animes = animeServ.listAll();
        model.addAttribute("animes", animes);

        List<Map<String,Object>> productTypes = categoryServ.listAll();
        model.addAttribute("productTypes", productTypes);

        List<Map<String,Object>> products = productServ.listAll();
        model.addAttribute("products", products);

        //Updates when a new order is added to the system
        // model.addAttribute("orders-total", "5");
        return "dashboard";
    }
    @GetMapping("/about")
    public String aboutProject() {
        return "project-info";
    }
    //Create new anime title
    @PostMapping("/category/anime/new")
    public String insertAnime(@ModelAttribute Anime anime, RedirectAttributes redirectMessage) {
        if (!animeServ.insertAnime(anime)) { // Call the method and see if it's FALSY(error) or TRUTHY(success)
            redirectMessage.addFlashAttribute("animeError", "Anime title is already in the system."); 
        } else {
            redirectMessage.addFlashAttribute("animeSuccess", "New anime title added!");
        }
        return "redirect:/dashboard"; // When redirecting, a message will display telling if the action was a success or not.
    }

    //Create new category type
    @PostMapping("/category/type/new")
    public String insertCategory(@ModelAttribute Category category, RedirectAttributes redirectMessage) {
        if(!categoryServ.insertCategory(category)) {
            redirectMessage.addFlashAttribute("categoryError", "Category type is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("categorySuccess", "New category type added!");
        }
        return "redirect:/dashboard";
    }

    //Opens new product form page
    @GetMapping("/product/new")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        List<Map<String,Object>> animes = animeServ.listAll();
        model.addAttribute("animes", animes);

        List<Map<String,Object>> productTypes = categoryServ.listAll();
        model.addAttribute("productTypes", productTypes);
        return "product-form";
    }

    //Create new product
    @PostMapping("/product/new")
    public String insertProduct(@ModelAttribute Product product, RedirectAttributes redirectMessage) {
        productServ.insertProduct(product);
        redirectMessage.addFlashAttribute("addProductSuccess", "New product added!");
        return "redirect:/dashboard";
    }

    //Delete product
    @PostMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Integer id, @ModelAttribute Product product, RedirectAttributes redirectMessage) {
        productServ.deleteProduct(id);
        redirectMessage.addFlashAttribute("deleteProductSuccess", "Product deleted!");
        return "redirect:/dashboard";
    }
    // /product/{id}/edit
}
