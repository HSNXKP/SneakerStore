package top.naccl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: wdd
 * @date: 2023/12/6 1:01
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StoreWithSize {
    private Long id;
    private Long SneakerStoreSizeId;
    private Long SneakerStoreId;
}
