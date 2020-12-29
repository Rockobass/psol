package org.orz.psol.model.dbModel;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductChoice对象", description="")
public class ProductChoice implements Serializable {

    private static final long serialVersionUID=1L;

      private String choiceId;

    private String productId;

    private Float price;

    private Integer storage;

    private String choiceImg;

    private String choice;


}
