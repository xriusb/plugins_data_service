package com.xriusb.pluginsdataservice.service.loadbalancer;

import java.util.List;

public interface LoadBalancer {
    void setCluster(List<String> hosts);
    String getHost();
}
