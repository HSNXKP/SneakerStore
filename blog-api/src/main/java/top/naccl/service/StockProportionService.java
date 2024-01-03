package top.naccl.service;

import top.naccl.entity.StockProportion;
import top.naccl.model.vo.Result;
import top.naccl.model.vo.StockVo;

import java.util.Map;

public interface StockProportionService {
    Result getStockProportion();

    Result addStockProportion(StockProportion stockProportion);

    Result editStockProportion(StockVo stockVo);
}
