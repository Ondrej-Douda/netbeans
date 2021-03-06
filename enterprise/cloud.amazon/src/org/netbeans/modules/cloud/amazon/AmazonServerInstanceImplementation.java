/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.cloud.amazon;

import javax.swing.JComponent;
import org.netbeans.modules.cloud.amazon.ui.AmazonInstanceNode;
import org.netbeans.modules.cloud.amazon.ui.AmazonWizardComponent;
import org.netbeans.spi.server.ServerInstanceImplementation;
import org.openide.nodes.Node;

/**
 * Representation of single amazon cloud account under "Cloud" node.
 */
public class AmazonServerInstanceImplementation implements ServerInstanceImplementation {

    private AmazonInstance ai;

    public AmazonServerInstanceImplementation(AmazonInstance ai) {
        this.ai = ai;
    }
    
    @Override
    public String getDisplayName() {
        return ai.getName();
    }

    @Override
    public String getServerDisplayName() {
        return "Amazon Beanstalk";
    }

    @Override
    public Node getFullNode() {
        return getBasicNode();
    }

    @Override
    public Node getBasicNode() {
        return new AmazonInstanceNode(ai);
    }

    @Override
    public JComponent getCustomizer() {
        AmazonWizardComponent panel = new AmazonWizardComponent(null, ai);
        return panel;
    }

    @Override
    public void remove() {
        AmazonInstanceManager.getDefault().remove(ai);
    }

    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public String getProperty(String key) {
        return ai.getServerInstance().getProperty(key);
    }

}
