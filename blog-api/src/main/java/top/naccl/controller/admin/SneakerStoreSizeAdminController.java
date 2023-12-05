package top.naccl.controller.admin;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.service.SneakerStoreSizeService;

/**
 * @author: wdd
 * @date: 2023/12/6 1:33
 */
@RestController
@RequestMapping("/admin")
public class SneakerStoreSizeAdminController {


    @Autowired
    SneakerStoreSizeService sneakerStoreSizeService;



}
