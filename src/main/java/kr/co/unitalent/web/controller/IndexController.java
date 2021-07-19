package kr.co.unitalent.web.controller;

import kr.co.unitalent.service.TalentProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final TalentProductService talentProductService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("product-sell-preview", talentProductService.findByHomeData("talent-sell"));
        model.addAttribute("product-grow-preview", talentProductService.findByHomeData("talent-grow"));
        return "home";
    }

    @GetMapping("/product/detail/{productId}")
    public String productDetail(@PathVariable Long productId, Model model) {
        model.addAttribute("product-detail", talentProductService.findByProductId(productId));
        model.addAttribute("product-review", talentProductService.findByProductReviews(productId));
        return "product-detail";
    }

    @GetMapping("/product/talent-sell/list/page/{page}/amount/{amount}")
    public String productTalentSellList(@PathVariable int page, @PathVariable int amount, Model model) {
        model.addAttribute("talent-sell-count", talentProductService.talentProductCount("talent-sell"));
        return "product-sell-list";
    }
}
