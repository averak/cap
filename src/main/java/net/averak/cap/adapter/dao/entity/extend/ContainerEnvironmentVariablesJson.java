package net.averak.cap.adapter.dao.entity.extend;

import net.averak.cap.domain.primitive.project.ContainerEnvironmentVariable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContainerEnvironmentVariablesJson implements Serializable {

    public List<ContainerEnvironmentVariable> variables;

    public ContainerEnvironmentVariablesJson(final List<ContainerEnvironmentVariable> variables) {
        this.variables = variables;
    }

    public ContainerEnvironmentVariablesJson() {
        this.variables = new ArrayList<>();
    }

}
