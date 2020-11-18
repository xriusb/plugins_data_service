package com.xriusb.pluginsdataservice.service.loadbalancer;

import com.xriusb.pluginsdataservice.model.Host;

import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class WeightedLoadBalancer implements LoadBalancer{
    private final NavigableMap<Integer, String> clusterWeightMap = new TreeMap<>();
    private final Random random;
    private Integer total;

    public WeightedLoadBalancer(List<Host> hosts) {
        this.random = new Random();
        this.total = 0;
        hosts.forEach(host -> {
            total += host.getWeight();
            clusterWeightMap.put(total, host.getName());
        });
    }

    @Override
    public String getHost() {
        Integer randomValue = random.nextInt(total);
        return clusterWeightMap.higherEntry(randomValue).getValue();
    }
}
