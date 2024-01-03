package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.naccl.entity.StockProportionLog;

@Mapper
@Repository
public interface StockProportionLogMapper {

    int addStockProportionLog(StockProportionLog stockProportionLog);
}
