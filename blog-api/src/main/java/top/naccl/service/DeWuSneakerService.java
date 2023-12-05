package top.naccl.service;

import top.naccl.entity.DeWuSneaker;
import top.naccl.model.vo.Result;

public interface DeWuSneakerService {
    Result getDeWuSneakerGoods(String code ,Integer pageNum, Integer pageSize);

    Result addDeWuSneakerGoods(DeWuSneaker deWuSneaker);

    Result editDeWuSneakerGoods(DeWuSneaker deWuSneaker);

    Result deleteDeWuSneakerGoods(Long id);
}
