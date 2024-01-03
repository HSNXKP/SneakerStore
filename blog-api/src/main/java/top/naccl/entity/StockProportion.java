package top.naccl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: wdd
 * @date: 2023/12/28 11:12
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StockProportion {
    private Long id; //商品ID
    private String name;//名称
    private Long value;//金额
    private String proportion;//比例
}
