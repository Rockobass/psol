package org.orz.psol.model.dbModel;

import java.util.Date;
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
@ApiModel(value="Store对象", description="")
public class Store implements Serializable {

    private static final long serialVersionUID=1L;

      private String id;

    private String ownerId;

    private String name;

    private String logo;

    private String location;

    private Date openTime;

    private String description;

    private Boolean activated;

    private Boolean isSpecial;

    private String telephone;


}
