package com.webz.Second.Hand.Shop.domain;

import com.webz.Second.Hand.Shop.commons.PageResultsImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class GarmentSearchResponse  extends PageResultsImpl<Garment> {
}
