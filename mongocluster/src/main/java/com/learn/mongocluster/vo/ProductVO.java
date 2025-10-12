package com.learn.mongocluster.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class ProductVO {

    private String id;
    private String name;

}
