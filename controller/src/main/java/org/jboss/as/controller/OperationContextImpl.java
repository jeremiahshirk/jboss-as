/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
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
 */

package org.jboss.as.controller;

import java.io.InputStream;
import java.util.List;

import org.jboss.as.controller.client.ExecutionAttachments;
import org.jboss.as.controller.registry.ModelNodeRegistration;
import org.jboss.dmr.ModelNode;

/**
 * A base implementation of an operation context.
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public class OperationContextImpl implements OperationContext {
    private final ModelController controller;
    private final ModelNodeRegistration registry;
    private final ModelNode subModel;
    private final ExecutionAttachments executionAttachments;

    /**
     * Construct a new instance.
     *
     * @param controller the model controller
     * @param registry the registry
     * @param subModel the affected submodel
     */
    public OperationContextImpl(final ModelController controller, final ModelNodeRegistration registry, final ModelNode subModel, final ExecutionAttachments executionAttachments) {
        this.controller = controller;
        this.registry = registry;
        this.subModel = subModel;
        this.executionAttachments = executionAttachments;
    }

    /** {@inheritDoc} */
    public ModelController getController() {
        return controller;
    }

    /** {@inheritDoc} */
    public ModelNodeRegistration getRegistry() {
        return registry;
    }

    /** {@inheritDoc} */
    public ModelNode getSubModel() {
        final ModelNode subModel = this.subModel;
        if (subModel == null) {
            throw new IllegalArgumentException("Operation does not apply to a submodel");
        }
        return subModel;
    }

    public RuntimeOperationContext getRuntimeContext() {
        return null;
    }

    @Override
    public List<InputStream> getInputStreams() {
        return executionAttachments.getInputStreams();
    }
}
