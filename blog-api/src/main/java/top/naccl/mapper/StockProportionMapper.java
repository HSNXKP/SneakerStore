package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.naccl.entity.StockProportion;

import java.util.List;

@Mapper
@Repository
public interface StockProportionMapper {
    List<StockProportion> getStockProportion();

}
