package com.xriusb.pluginsdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Account {
    private String code;
    private List<Device> devices;
}
