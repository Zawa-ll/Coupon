package com.example.coupon.thymeleaf;

import com.alibaba.fastjson.JSON;
import com.example.coupon.constant.CouponCategory;
import com.example.coupon.constant.DistributeTarget;
import com.example.coupon.constant.GoodsType;
import com.example.coupon.constant.PeriodType;
import com.example.coupon.constant.ProductLine;
import com.example.coupon.dao.CouponTemplateDao;
import com.example.coupon.entity.CouponTemplate;
import com.example.coupon.service.IBuildTemplateService;
import com.example.coupon.vo.TemplateRequest;
import com.example.coupon.vo.TemplateRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/template/thy")
public class ThyTemplateController {

    /** CouponTemplate Dao */
    private final CouponTemplateDao templateDao;

    private final IBuildTemplateService templateService;

    @Autowired
    public ThyTemplateController(CouponTemplateDao templateDao, IBuildTemplateService templateService) {
        this.templateDao = templateDao;
        this.templateService = templateService;
    }

    /**
     * 127.0.0.1:7001/coupon-template/template/thy/home
     * */
    @GetMapping("/home")
    public String home() {

        log.info("view home.");
        return "home";
    }

    /**
     * 127.0.0.1:7001/coupon-template/template/thy/info/{id}
     * */
    @GetMapping("/info/{id}")
    public String info(@PathVariable Integer id, ModelMap map) {

        log.info("view template info.");
        Optional<CouponTemplate> templateO = templateDao.findById(id);
        if (templateO.isPresent()) {
            CouponTemplate template = templateO.get();
            map.addAttribute("template", ThyTemplateInfo.to(template));
        }

        return "template_detail";
    }

    /**
     * 127.0.0.1:7001/coupon-template/template/thy/list
     * */
    @GetMapping("/list")
    public String list(ModelMap map) {

        log.info("view template list.");
        List<CouponTemplate> couponTemplates = templateDao.findAll();
        List<ThyTemplateInfo> templates =
                couponTemplates.stream().map(ThyTemplateInfo::to).collect(Collectors.toList());

        map.addAttribute("templates", templates);
        return "template_list";
    }

    /**
     * 127.0.0.1:7001/coupon-template/template/thy/create
     * */
    @GetMapping("/create")
    public String create(ModelMap map, HttpSession session) {

        log.info("view create form.");

        session.setAttribute("category", CouponCategory.values());
        session.setAttribute("productLine", ProductLine.values());
        session.setAttribute("target", DistributeTarget.values());
        session.setAttribute("period", PeriodType.values());
        session.setAttribute("goodsType", GoodsType.values());

        map.addAttribute("template", new ThyCreateTemplate());
        map.addAttribute("action", "create");
        return "template_form";
    }

    /**
     * 127.0.0.1:7001/coupon-template/template/thy/create
     * */
    @PostMapping("/create")
    public String create(@ModelAttribute ThyCreateTemplate template) throws Exception {

        log.info("create form.");
        log.info("{}", JSON.toJSONString(template));

        TemplateRule rule = new TemplateRule();
        rule.setExpiration(new TemplateRule.Expiration(
                template.getPeriod(), template.getGap(),
                new SimpleDateFormat("yyyy-MM-dd").parse(template.getDeadline()).getTime()
        ));
        rule.setDiscount(new TemplateRule.Discount(template.getQuota(), template.getBase()));
        rule.setLimitation(template.getLimitation());
        rule.setUsage(new TemplateRule.Usage(template.getProvince(), template.getCity(),
                JSON.toJSONString(template.getGoodsType())));
        rule.setWeight(
                JSON.toJSONString(Stream.of(template.getWeight().split(",")).collect(Collectors.toList()))
        );

        TemplateRequest request = new TemplateRequest(
                template.getName(), template.getLogo(), template.getDesc(),
                template.getCategory(), template.getProductLine(), template.getCount(),
                template.getUserId(), template.getTarget(), rule
        );

        log.info("create coupon template: {}", JSON.toJSONString(templateService.buildTemplate(request)));

        return "redirect:/template/thy/list";
    }
}
