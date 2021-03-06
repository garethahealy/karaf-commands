/*
 * #%L
 * GarethHealy :: Karaf Commands :: container-status
 * %%
 * Copyright (C) 2013 - 2018 Gareth Healy
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.garethahealy.karaf.commands.container.status.predicates;

import io.fabric8.api.Container;
import io.fabric8.api.DataStore;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContainerStoppedPredicate implements StatusPredicate {

    public static final String STOPPED = "stopped";

    private static final Logger LOG = LoggerFactory.getLogger(ContainerStoppedPredicate.class);

    private String containerName;
    private DataStore dataStore;

    public ContainerStoppedPredicate(String containerName, DataStore dataStore) {
        this.containerName = containerName;
        this.dataStore = dataStore;
    }

    public Boolean matches(Container container) {
        String provisionStatus = container.getProvisionStatus();
        Boolean isProvisionStopped = provisionStatus.equalsIgnoreCase(Container.PROVISION_STOPPED);
        Boolean isAlive = container.isAlive();

        LOG.trace(new ToStringBuilder(this)
                      .append("containerName", containerName)
                      .append("provisionStatus", provisionStatus)
                      .append("isProvisionStopped", isProvisionStopped)
                      .append("isAlive", isAlive)
                      .toString());

        return !isAlive && isProvisionStopped;
    }
}
