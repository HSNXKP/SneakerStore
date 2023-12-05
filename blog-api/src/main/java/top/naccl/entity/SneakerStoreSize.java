package top.naccl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author: wdd
 * @date: 2023/12/4 22:10
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SneakerStoreSize {

    private Long id; //商品ID
    private String name;//商品尺码
    private Double price;//商品价格
    private Double amount;//商品总数
    private Double amountPrice;//商品总价格
    private LocalDateTime createTime;//创建时间

}
