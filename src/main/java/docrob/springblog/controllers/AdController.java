//package docrob.springblog.controllers;
//
//import docrob.springblog.models.Ad;
//import docrob.springblog.repositories.AdRepository;
//import docrob.springblog.repositories.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//class AdController {
//
//    // These two next steps are often called dependency injection, where we create a Repository instance and initialize it in the controller class constructor.
//    private final AdRepository adDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;
//
//    public AdController(AdRepository adDao, UserRepository userDao, EmailService emailService) {
//        this.adDao = adDao;
//        this.userDao = userDao;
//        this.emailService = emailService;
//    }
//
//    @GetMapping("/ads")
//    public String index(Model model) {
//        model.addAttribute("ads", adDao.findAll());
//        return "ads/index";
//    }
//
//    @GetMapping("/ads/create")
//    public String create(Model model) {
//        model.addAttribute("ad", new Ad());
//        return "create";
//    }
//
//    @PostMapping("/ads/create")
//    public String insert(@ModelAttribute Ad ad) {
//        ad.setParentUser(userDao.getOne(1L));
//        adDao.save((ad));
////        emailService.prepareAndSend(ad,ad.getTitle(),ad.getDescription());
//        return "hello";
//    }
//
//}
