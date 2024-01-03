package top.naccl.controller.admin;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.naccl.entity.StockProportion;
import top.naccl.model.vo.Result;
import top.naccl.model.vo.StockVo;
import top.naccl.service.StockProportionService;

import java.util.Map;

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


    @PostMapping("addStockProportion")
    public Result addStockProportion(StockProportion stockProportion){
        return stockProportionService.addStockProportion(stockProportion);
    }

    @PostMapping("editStockProportion")
    public Result editStockProportion(StockVo stockVo){
        return stockProportionService.editStockProportion(stockVo);
    }

}
