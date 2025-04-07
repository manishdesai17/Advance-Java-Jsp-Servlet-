interface SDLCBuilder {
    SDLCBuilder setRequirements(String requirements);
    SDLCBuilder setDesign(String design);
    SDLCBuilder setDevelopment(String development);
    SDLCBuilder setTesting(String testing);
    SDLCBuilder setDeployment(String deployment);
    SoftwareProject build();
}