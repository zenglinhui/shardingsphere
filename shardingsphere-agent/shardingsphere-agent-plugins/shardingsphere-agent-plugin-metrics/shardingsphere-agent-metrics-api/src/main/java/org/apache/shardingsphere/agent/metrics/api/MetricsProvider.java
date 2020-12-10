/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.agent.metrics.api;

import org.apache.shardingsphere.agent.core.config.AgentConfiguration;
import org.apache.shardingsphere.agent.core.spi.AgentTypedSPIRegistry;
import org.apache.shardingsphere.agent.core.utils.SingletonHolder;

/**
 * The enum metrics provider.
 */
public enum MetricsProvider {
    
    /**
     * Instance metrics provider.
     */
    INSTANCE;
    
    private static MetricsRegisterFactory metricRegisterFactory;
    
    static {
        AgentConfiguration.MetricsConfiguration metricsConfiguration = SingletonHolder.INSTANCE.get(AgentConfiguration.class).getMetrics();
        metricRegisterFactory = AgentTypedSPIRegistry.getRegisteredService(MetricsRegisterFactory.class, metricsConfiguration.getType());
    }
    
    /**
     * Create metric register.
     *
     * @return metric register
     */
    public MetricsRegister newInstance() {
        return metricRegisterFactory.newInstance();
    }
}
