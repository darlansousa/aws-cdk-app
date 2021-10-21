package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.util.Arrays;

public class AwsCdkAppApp {
    public static void main(final String[] args) {
        App app = new App();

        VPCStack vpcStack = new VPCStack(app, "Vpc");

        ClusterStack clusterStack = new ClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);
        ServiceStack serviceStack = new ServiceStack(app, "Service01", clusterStack.getCluster());
        serviceStack.addDependency(clusterStack);


        app.synth();
    }
}
