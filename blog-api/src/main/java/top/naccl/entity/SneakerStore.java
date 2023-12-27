package top.naccl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author: wdd
 * @date: 2023/12/3 3:18
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SneakerStore {
    private Long id; //商品ID
    private String name;//商品名称
    private String brandName;//品牌名称
    private String code;//商品货号
    private String imageUrl;// 商品链接
    private Double amount;//商品总数
    private Double profit;//商品盈亏
    private Double price;//库存成本
    private LocalDateTime createTime;//创建时间

}
