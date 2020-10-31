package com.xriusb.pluginsdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Device {
    private String name;
    private String pluginVersion;
    private Integer pingTime;
}
