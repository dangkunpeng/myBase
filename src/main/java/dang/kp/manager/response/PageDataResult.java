package dang.kp.manager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Title: PageDataResult
 * @Description: 封装DTO分页数据（记录数和所有记录）
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 11:15
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDataResult {

    private Integer code=200;

    //总记录数量
    private Integer totals;

    private List<?> list;

}
