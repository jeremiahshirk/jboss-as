/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */package org.jboss.as.server.client.impl.deployment;

import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

import org.jboss.as.server.client.api.deployment.DeploymentAction;

/**
 * Implementation of {@link DeploymentAction}.
 *
 * @author Brian Stansberry
 */
public class DeploymentActionImpl implements DeploymentAction, Serializable {

    private static final long serialVersionUID = 613098200977026475L;

    public static DeploymentActionImpl getAddAction(String deploymentName, String fileName, InputStream in) {
        return new DeploymentActionImpl(Type.ADD, deploymentName, fileName, in, null);
    }

    public static DeploymentActionImpl getDeployAction(String deploymentName) {
        return new DeploymentActionImpl(Type.DEPLOY, deploymentName, null, null, null);
    }

    public static DeploymentActionImpl getRedeployAction(String deploymentName) {
        return new DeploymentActionImpl(Type.REDEPLOY, deploymentName, null, null, null);
    }

    public static DeploymentActionImpl getUndeployAction(String deploymentName) {
        return new DeploymentActionImpl(Type.UNDEPLOY, deploymentName, null, null, null);
    }

    public static DeploymentActionImpl getReplaceAction(String deploymentName, String replacedName) {
        return new DeploymentActionImpl(Type.REPLACE, deploymentName, null, null, replacedName);
    }

    public static DeploymentActionImpl getFullReplaceAction(String deploymentName, String fileName, InputStream in) {
        return new DeploymentActionImpl(Type.FULL_REPLACE, deploymentName, fileName, in, null);
    }

    public static DeploymentActionImpl getRemoveAction(String deploymentName) {
        return new DeploymentActionImpl(Type.REMOVE, deploymentName, null, null, null);
    }

    private final UUID uuid = UUID.randomUUID();
    private final Type type;
    private final String deploymentUnitName;
    private final String oldDeploymentUnitName;
    private final InputStream contents;
    private final String newContentFileName;

    private DeploymentActionImpl(Type type, String deploymentUnitName, String newContentFileName, InputStream contents, String replacedDeploymentUnitName) {
        this.type = type;
        this.deploymentUnitName = deploymentUnitName;
        this.newContentFileName = newContentFileName;
        this.contents = contents;
        this.oldDeploymentUnitName = replacedDeploymentUnitName;
    }

    @Override
    public UUID getId() {
        return uuid;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getDeploymentUnitUniqueName() {
        return deploymentUnitName;
    }

    @Override
    public String getReplacedDeploymentUnitUniqueName() {
        return oldDeploymentUnitName;
    }

    public String getNewContentFileName() {
        return newContentFileName;
    }

    public InputStream getContents() {
        return contents;
    }

}
