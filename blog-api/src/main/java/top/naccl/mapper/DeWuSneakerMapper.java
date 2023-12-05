package top.naccl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.naccl.entity.DeWuSneaker;
import top.naccl.model.vo.Result;

import java.util.List;

@Mapper
@Repository
public interface DeWuSneakerMapper {
    List<DeWuSneaker> getDeWuSneakerGoods(String code);

    Integer addDeWuSneakerGoods(DeWuSneaker deWuSneaker);

    Integer editDeWuSneakerGoods(DeWuSneaker deWuSneaker);

    Integer deleteDeWuSneakerGoods(@Param("id") Long id);
}
