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
@ApiModel(value="Puser对象", description="")
public class Puser implements Serializable {

    private static final long serialVersionUID=1L;

      private String id;

    private String nickname;

    private String name;

    private String idNumber;


}
