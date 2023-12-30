package top.naccl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.naccl.entity.StockProportion;
import top.naccl.mapper.StockProportionMapper;
import top.naccl.model.vo.Result;
import top.naccl.service.StockProportionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wdd
 * @date: 2023/12/30 23:24
 */
@Service
public class StockProportionServiceImpl implements StockProportionService {


    @Autowired
    StockProportionMapper stockProportionMapper;
    @Override
    public Result getStockProportion() {
        List<StockProportion> stockProportionList = stockProportionMapper.getStockProportion();
        if (stockProportionList.get(0).getId() == null){
            return Result.error("未添加股权人");
        }
        Map<Object, Object> map = new HashMap<>();
        ArrayList<Object> list = new ArrayList<>();
        for (StockProportion proportion : stockProportionList) {
            list.add(proportion.getName());
        }
        map.put("legend",list);
        map.put("series",stockProportionList);
        System.out.println(map);
        return Result.ok("查询成功",map);
    }
}
