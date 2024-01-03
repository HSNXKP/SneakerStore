package top.naccl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author: wdd
 * @date: 2024/1/1 2:19
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StockProportionLog {
    private Long id; //商品ID
    private String name;//操作名称
    private String object;//操作对象
    private Long money;//操作金额
    private String remark;//备注
    private String stockRemark;//当前的股份金额
    private LocalDateTime createTime;//时间
}
