package top.naccl.entity;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author: wdd
 * @date: 2023/12/3 1:21
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DeWuSneaker {
    private Long id; //商品ID
    private String name;//商品名称
    private String brandName;//品牌名称
    private String code;//商品货号
    private String imageUrl;// 商品链接
    private LocalDateTime createTime;//创建时间
}
