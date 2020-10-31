package com.xriusb.pluginsdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Host {
    private String name;
    private Integer weight;
}
