package com.moonstationery.controller;

import java.util.List;
import java.util.Map;
<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)

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
<<<<<<< HEAD
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
        
        model.addAttribute("animes", animeServ.listAll());
        model.addAttribute("productTypes", categoryServ.listAll());
        return "category-page";
    }

    @GetMapping("/anime/{slug}")
    public String productAnime(@PathVariable String slug, Model model) {
        Anime anime = animeServ.getAnimeBySlug(slug);
        if (anime == null) return "redirect:/";

        model.addAttribute("anime", anime);
        model.addAttribute("products", productServ.getProductsByAnime(anime.getId()));

        
        model.addAttribute("animes", animeServ.listAll());
        model.addAttribute("productTypes", categoryServ.listAll());
        return "anime-page";
    }

=======
    private AnimeService animeService;

    @Autowired
    private CategoryService typeService;

    @Autowired
    private ProductService productService;

    //About project
    @GetMapping("/about")
    public String aboutProject() {
        return "project-info";
    }

    //Category page (anime)
    @GetMapping("/anime/{slug}")
    public String productAnime(@PathVariable String slug, Model model) {
        Anime anime = animeService.getAnimeBySlug(slug);

        model.addAttribute("anime", anime);
        model.addAttribute("products", productService.getProductsByAnime(anime.getId()));

        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());

        return "anime-page";
    }

    //Category page (type)
    @GetMapping("/category/{slug}")
    public String productCategory(@PathVariable String slug, Model model) {
        Category category = typeService.getCategoryBySlug(slug);

        model.addAttribute("category", category);
        model.addAttribute("products", productService.getProductsByCategory(category.getId()));
        
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());

        return "category-page";
    }

    //Dashboard
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("anime", new Anime());
        model.addAttribute("category", new Category());
        model.addAttribute("product", new Product());

<<<<<<< HEAD
        model.addAttribute("animes", animeServ.listAll());
        model.addAttribute("productTypes", categoryServ.listAll());
        model.addAttribute("products", productServ.listAll());
=======
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());
        model.addAttribute("products", productService.listAll());
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)

        return "dashboard";
    }

<<<<<<< HEAD
    @GetMapping("/about")
    public String aboutProject() {
        return "project-info";
    }

    @PostMapping("/category/anime/new")
    public String insertAnime(@ModelAttribute Anime anime, RedirectAttributes redirectMessage) {
        if (!animeServ.insertAnime(anime)) {
=======
    //Add new category category anime title (dashboard)
    @PostMapping("/category/anime/new")
    public String insertAnime(@ModelAttribute Anime anime, RedirectAttributes redirectMessage) {
        if (!animeService.insertAnime(anime)) {
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
            redirectMessage.addFlashAttribute("animeError", "Anime title is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("animeSuccess", "New anime title added!");
        }
        return "redirect:/dashboard";
    }

<<<<<<< HEAD
    @PostMapping("/category/type/new")
    public String insertCategory(@ModelAttribute Category category, RedirectAttributes redirectMessage) {
        if (!categoryServ.insertCategory(category)) {
=======
    //Add new category category type (dashboard)
    @PostMapping("/category/type/new")
    public String insertCategory(@ModelAttribute Category category, RedirectAttributes redirectMessage) {
        if (!typeService.insertCategory(category)) {
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
            redirectMessage.addFlashAttribute("categoryError", "Category type is already in the system.");
        } else {
            redirectMessage.addFlashAttribute("categorySuccess", "New category type added!");
        }
        return "redirect:/dashboard";
    }

<<<<<<< HEAD
    @GetMapping("/product/new")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("animes", animeServ.listAll());
        model.addAttribute("productTypes", categoryServ.listAll());
=======
    //Delete existing category category anime title (dashboard)
    @PostMapping("anime/{id}/delete")
    public String deleteAnime(@PathVariable Integer id, RedirectAttributes redirectMessage) {
        Anime animeName = animeService.getAnimeById(id);

        if (!animeService.deleteAnime(id)) {
            redirectMessage.addFlashAttribute("deleteAnimeError", "Anime title '" + animeName.getName() + "' has products assigned to it and can't be deleted.");
        } else {
            redirectMessage.addFlashAttribute("deleteAnimeSuccess", "Anime title '" + animeName.getName() + "' deleted!");
        }
        return "redirect:/dashboard";
    }
    
    //Delete existing category type (dashboard)
    @PostMapping("category/{id}/delete")
    public String deleteCategoryType(@PathVariable Integer id, RedirectAttributes redirectMessage) {
        Category typeName = typeService.getCategoryById(id);

        if (!typeService.deleteCategoryType(id)) {
            redirectMessage.addFlashAttribute("deletedTypeError", "Category type '" + typeName.getName() + "' has products assigned to it and can't be deleted."); 
        } else {
            redirectMessage.addFlashAttribute("deletedTypeSuccess", "Category type '" + typeName.getName() + "' has products assigned to it and can't be deleted.");
     
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

    

    

    

    @GetMapping("/product/new")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("animes", animeService.listAll());
        model.addAttribute("productTypes", typeService.listAll());
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
        return "product-form";
    }

    @PostMapping("/product/new")
    public String insertProduct(@ModelAttribute Product product, RedirectAttributes redirectMessage) {
<<<<<<< HEAD
        productServ.insertProduct(product);
=======
        productService.insertProduct(product);
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
        redirectMessage.addFlashAttribute("addProductSuccess", "New product added!");
        return "redirect:/dashboard";
    }

    @PostMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirectMessage) {
<<<<<<< HEAD
        productServ.deleteProduct(id);
=======
        productService.deleteProduct(id);
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
        redirectMessage.addFlashAttribute("deleteProductSuccess", "Product deleted!");
        return "redirect:/dashboard";
    }
}
