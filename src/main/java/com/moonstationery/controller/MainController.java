package com.moonstationery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("products", productServ.listAllAvailable());
        model.addAttribute("animes", animeServ.listAll());
        model.addAttribute("productTypes", categoryServ.listAll());
        return "home";
    }

    @GetMapping("/product/{id}")
    public String productPage(@PathVariable Integer id, Model model) {
        Product product = productServ.getProductById(id);
        if (product == null) return "redirect:/";

        model.addAttribute("product", product);

        List<Map<String, Object>> relatedProducts = productServ.getProductsByCategory(product.getProduct_type_id());
        relatedProducts.removeIf(p -> (Integer) p.get("id") == product.getId());

        model.addAttribute("relatedProducts", relatedProducts);
        return "product-page";
    }

    @GetMapping("/category/{slug}")
    public String productCategory(@PathVariable String slug, Model model) {
        Category category = categoryServ.getCategoryBySlug(slug);
        if (category == null) return "redirect:/";

        model.addAttribute("category", category);
        model.addAttribute("products", productServ.getProductsByCategory(category.getId()));
        return "category-page";
    }

    @GetMapping("/anime/{slug}")
    public String productAnime(@PathVariable String slug, Model model) {
        Anime anime = animeServ.getAnimeBySlug(slug);
        if (anime == null) return "redirect:/";

        model.addAttribute("anime", anime);
        model.addAttribute("products", productServ.getProductsByAnime(anime.getId()));
        return "anime-page";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("anime", new Anime());
        model.addAttribute("category", new Category());
        model.addAttribute("product", new Product());

        model.addAttribute("animes", animeServ.listAll());
        model.addAttribute("productTypes", categoryServ.listAll());
        model.addAttribute("products", productServ.listAll());

        return "dashboard";
    }

    @GetMapping("/about")
    public String aboutProject() {
        return "project-info";
    }

    @PostMapping("/category/anime/new")
    public String insertAnime(@ModelAttribute Anime anime, RedirectAttributes redirectMessage) {
        if (!animeServ.insertAnime(anime)) {
            redirectMessage.addFlashAttribute("animeError", "Anime title is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("animeSuccess", "New anime title added!");
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/category/type/new")
    public String insertCategory(@ModelAttribute Category category, RedirectAttributes redirectMessage) {
        if (!categoryServ.insertCategory(category)) {
            redirectMessage.addFlashAttribute("categoryError", "Category type is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("categorySuccess", "New category type added!");
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/product/new")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("animes", animeServ.listAll());
        model.addAttribute("productTypes", categoryServ.listAll());
        return "product-form";
    }

    @PostMapping("/product/new")
    public String insertProduct(@ModelAttribute Product product, RedirectAttributes redirectMessage) {
        productServ.insertProduct(product);
        redirectMessage.addFlashAttribute("addProductSuccess", "New product added!");
        return "redirect:/dashboard";
    }

    @PostMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirectMessage) {
        productServ.deleteProduct(id);
        redirectMessage.addFlashAttribute("deleteProductSuccess", "Product deleted!");
        return "redirect:/dashboard";
    }
}
