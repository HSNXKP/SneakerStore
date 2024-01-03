package top.naccl.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: wdd
 * @date: 2024/1/3 23:38
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StockVo {
    private Long id;
    private String stockId;//0全部，剩下的id对应的id
    private String operate;// 操作
    private Long money;//金额
    private String remark;//备注
}
