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
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Product对象", description="")
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

      private String id;

    private String storeId;

    private String name;

    private String coverImg;

    private String address;

    private Float displayPrice;

    private Float freight;

    private String description;

    private String productArgs;

    private Integer sales;

    private String type;


}
