class SDLCConcreteBuilder implements SDLCBuilder {
    String requirements;
    String design;
    String development;
    String testing;
    String deployment;

    @Override
    public SDLCConcreteBuilder setRequirements(String requirements) {
        this.requirements = requirements;
        return this;
    }

    @Override
    public SDLCConcreteBuilder setDesign(String design) {
        this.design = design;
        return this;
    }

    @Override
    public SDLCConcreteBuilder setDevelopment(String development) {
        this.development = development;
        return this;
    }

    @Override
    public SDLCConcreteBuilder setTesting(String testing) {
        this.testing = testing;
        return this;
    }

    @Override
    public SDLCConcreteBuilder setDeployment(String deployment) {
        this.deployment = deployment;
        return this;
    }

    @Override
    public SoftwareProject build() {
        return new SoftwareProject(requirements, design, development, testing, deployment);
    }
}
