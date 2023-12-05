package top.naccl.controller.admin;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.naccl.entity.DeWuSneaker;
import top.naccl.model.vo.Result;
import top.naccl.service.DeWuSneakerService;

/**
 * @author: wdd
 * @date: 2023/12/3 2:49
 */
@RestController
@RequestMapping("/admin")
public class DeWuSneakerController {

    @Autowired
    DeWuSneakerService deWuSneakerService;


    @GetMapping("/getDeWuSneakerGoods")
    public Result getDeWuSneakerGoods(String code,
                                          @RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        return deWuSneakerService.getDeWuSneakerGoods(code,pageNum, pageSize);
    }


    @PostMapping("/addDeWuSneakerGoods")
    public Result addDeWuSneakerGoods(@RequestBody DeWuSneaker deWuSneaker){
        return deWuSneakerService.addDeWuSneakerGoods(deWuSneaker);
    }

    @PostMapping("/editDeWuSneakerGoods")
    public Result editDeWuSneakerGoods(@RequestBody DeWuSneaker deWuSneaker){
        return deWuSneakerService.editDeWuSneakerGoods(deWuSneaker);
    }



    @PostMapping("/deleteDeWuSneakerGoods")
    public Result deleteDeWuSneakerGoods(@RequestParam("id") Long id){
        return deWuSneakerService.deleteDeWuSneakerGoods(id);
    }






}
