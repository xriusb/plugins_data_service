package com.xriusb.pluginsdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HostResponse {
    private String hostName;
    private Integer pingTime;
    private String viewCode;
}
