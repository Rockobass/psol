package org.orz.psol.model.VO;

import lombok.Data;

@Data
public class AddressVO {
    String telephone;
    String consignee;
    String detailAddress;
    boolean isDefault;
}
