package top.naccl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.naccl.entity.StockProportion;
import top.naccl.entity.StockProportionLog;
import top.naccl.mapper.StockProportionLogMapper;
import top.naccl.mapper.StockProportionMapper;
import top.naccl.model.vo.Result;
import top.naccl.model.vo.StockVo;
import top.naccl.service.StockProportionService;

import java.time.LocalDateTime;
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

    @Autowired
    StockProportionLogMapper stockProportionLogMapper;

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

    @Override
    public Result addStockProportion(StockProportion stockProportion) {
        if (stockProportionMapper.addStockProportion(stockProportion)!=0){
            return Result.error("添加失败");
        }
        return Result.ok("添加成功");
    }

    @Override
    public Result editStockProportion(StockVo stockVo) {
        StockProportionLog stockProportionLog = new StockProportionLog();
        if (stockVo.getStockId().equals("0")){
            List<StockProportion> stockProportion = stockProportionMapper.getStockProportion();
            // 总金额
            Long money = 0L;
            // 金额改动信息
            String stockRemark = "";
            // 计算总金额
            for (StockProportion proportion : stockProportion) {
                money = money + proportion.getValue();
            }
            //更新金额
            for (StockProportion proportion : stockProportion) {
                Double prop= ((double) proportion.getValue() / (double)money);
                stockRemark = stockRemark + proportion.getName() + "操作前股份:" + proportion.getValue() + ",";
                proportion.setValue((long) ((stockVo.getOperate().equals("加仓股份")?(prop * stockVo.getMoney() + proportion.getValue()):(-(prop * stockVo.getMoney())) + proportion.getValue())));
                stockRemark = stockRemark + proportion.getName() + "操作后股份:" + proportion.getValue() + "。";
            }
            // 更新股份
            for (StockProportion proportion : stockProportion) {
                stockProportionMapper.editStockProportion(proportion);
            }
            // 日志
            stockProportionLog.setName(stockVo.getOperate());
            stockProportionLog.setObject("全部股东");
            stockProportionLog.setMoney(stockVo.getMoney());
            stockProportionLog.setRemark(stockVo.getRemark());
            stockProportionLog.setCreateTime(LocalDateTime.now());
            stockProportionLog.setStockRemark(stockRemark);
            stockProportionLogMapper.addStockProportionLog(stockProportionLog);
            return Result.ok("更新完成");
        }
        // 不上全部的就是单体的金额
        StockProportion stockProportionById = stockProportionMapper.getStockProportionById(Long.valueOf(stockVo.getStockId()));
        // 前后金额改动信息
        String stockRemark = "";
        stockRemark = stockProportionById.getName() + "操作前股份:" + stockProportionById.getValue() + ",";
        //更新持仓金额
        stockProportionById.setValue(stockVo.getOperate().equals("加仓股份")?stockProportionById.getValue() + stockVo.getMoney():stockProportionById.getValue() + -stockVo.getMoney());
        stockRemark = stockRemark + stockProportionById.getName() + "操作后股份:" + stockProportionById.getValue() + "。";
        stockProportionMapper.editStockProportion(stockProportionById);
        //日志
        stockProportionLog.setName(stockVo.getOperate());
        stockProportionLog.setObject(stockProportionById.getName());
        stockProportionLog.setMoney(stockVo.getMoney());
        stockProportionLog.setRemark(stockVo.getRemark());
        stockProportionLog.setCreateTime(LocalDateTime.now());
        stockProportionLog.setStockRemark(stockRemark);
        stockProportionLogMapper.addStockProportionLog(stockProportionLog);
        return Result.ok("更新完成");
    }
}
