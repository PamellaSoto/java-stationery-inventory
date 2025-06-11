package com.moonstationery.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private AnimeService animeService;

    @Autowired
    private CategoryService typeService;

    @Autowired
    private ProductService productService;

    // Product form (Create new product)
    @GetMapping("/product/new")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());

        return "add-product-form";
    }

    @PostMapping("/product/new")
    public String insertProduct(@ModelAttribute Product product, RedirectAttributes redirectMessage) {
        productService.insertProduct(product);
        redirectMessage.addFlashAttribute("addProductSuccess", "New product added!");

        return "redirect:/dashboard";
    }

    // Category page (anime)
    @GetMapping("/anime/{slug}")
    public String productAnime(@PathVariable String slug, Model model) {
        Anime anime = animeService.getAnimeBySlug(slug);

        model.addAttribute("anime", anime);
        model.addAttribute("products", productService.getProductsByAnime(anime.getId()));

        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());

        return "anime-page";
    }

    // Category page (type)
    @GetMapping("/category/{slug}")
    public String productCategory(@PathVariable String slug, Model model) {
        Category category = typeService.getCategoryBySlug(slug);

        model.addAttribute("category", category);
        model.addAttribute("products", productService.getProductsByCategory(category.getId()));
        
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());

        return "category-page";
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("anime", new Anime());
        model.addAttribute("category", new Category());
        Integer count = productService.getInventoryQuantity();
        model.addAttribute("inventoryCounter", count);
        
        System.out.println("Inventory count: " + count);
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());
        model.addAttribute("products", productService.listAll());

        return "dashboard";
    }

    // Product form (Edit existing product)
    @GetMapping("/product/{id}/edit")
    public String editProduct(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());
        return "edit-product-form";
    }

    @PostMapping("/product/{id}/edit")
    public String editProduct(@PathVariable Integer id, @ModelAttribute Product product, RedirectAttributes redirectMessage) {
        product.setId(id);
        productService.editProduct(product);
        redirectMessage.addFlashAttribute("editProductSuccess", "Product updated successfully!");

        return "redirect:/dashboard";
    }

    // Delete existing product (dashboard)
    @PostMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirectMessage) {
        productService.deleteProduct(id);
        redirectMessage.addFlashAttribute("deleteProductSuccess", "Product deleted!");

        return "redirect:/dashboard";
    }

    // Add new category anime title (dashboard)
    @PostMapping("/category/anime/new")
    public String insertAnime(@ModelAttribute Anime anime, RedirectAttributes redirectMessage) {
        if (!animeService.insertAnime(anime)) {
            redirectMessage.addFlashAttribute("addAnimeError", "Anime title is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("addAnimeSuccess", "New anime title added!");
        }
        return "redirect:/dashboard";
    }

    // Add new category type (dashboard)
    @PostMapping("/category/type/new")
    public String insertCategory(@ModelAttribute Category category, RedirectAttributes redirectMessage) {
        if (!typeService.insertCategory(category)) {
            redirectMessage.addFlashAttribute("addTypeError", "Category type is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("addTypeSuccess", "New category type added!");
        }
        return "redirect:/dashboard";
    }

    // Delete existing category anime title (dashboard)
    @PostMapping("/anime/{id}/delete")
    public String deleteAnime(@PathVariable Integer id, RedirectAttributes redirectMessage) {
        Anime animeName = animeService.getAnimeById(id);

        if (!animeService.deleteAnime(id)) {
            redirectMessage.addFlashAttribute("deleteAnimeError", "Anime title '" + animeName.getName() + "' has products assigned to it and can't be deleted.");
        } else {
            redirectMessage.addFlashAttribute("deleteAnimeSuccess", "Anime title '" + animeName.getName() + "' deleted!");
        }
        return "redirect:/dashboard";
    }    

    // Delete existing category type (dashboard)
    @PostMapping("/category/{id}/delete")
    public String deleteCategoryType(@PathVariable Integer id, RedirectAttributes redirectMessage) {
        Category typeName = typeService.getCategoryById(id);

        if (!typeService.deleteCategoryType(id)) {
            redirectMessage.addFlashAttribute("deleteTypeError", "Category type '" + typeName.getName() + "' has products assigned to it and can't be deleted."); 
        } else {
            redirectMessage.addFlashAttribute("deleteTypeSuccess", "Category type '" + typeName.getName() + "' has products assigned to it and can't be deleted.");
        }
        return "redirect:/dashboard";
    }

    // Home
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.listAllAvailable());
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());

        return "home";
    }

    // Product page
    @GetMapping("/product/{id}")
    public String productPage(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        List<Map<String, Object>> relatedProducts = productService.getProductsByCategory(product.getProduct_type_id());
        relatedProducts.removeIf(p -> Objects.equals((Integer) p.get("id"), product.getId()));
        model.addAttribute("relatedProducts", relatedProducts);

        return "product-page";
    }
    
    // About Project
    @GetMapping("/about")
    public String aboutProject() {
        return "project-info";
    }
    
    // Search Result Page
    @GetMapping("/search/{input}")
    public String searchPage(@PathVariable String input, Model model) {
        model.addAttribute("searchInput", input);
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());
        model.addAttribute("products", productService.listAll());

        return "search-page-result";
    }
}

