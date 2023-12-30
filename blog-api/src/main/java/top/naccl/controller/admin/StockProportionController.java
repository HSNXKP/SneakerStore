package top.naccl.controller.admin;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.model.vo.Result;
import top.naccl.service.StockProportionService;

/**
 * @author: wdd
 * @date: 2023/12/30 23:16
 */
@RestController
@RequestMapping("/admin")
public class StockProportionController {

    @Autowired
    StockProportionService stockProportionService;

    @GetMapping("/stockProportion")
    public Result getStockProportion(){
        return stockProportionService.getStockProportion();
    }


}
