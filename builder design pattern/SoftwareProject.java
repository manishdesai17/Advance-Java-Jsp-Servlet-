class SoftwareProject {
    private String requirements;
    private String design;
    private String development;
    private String testing;
    private String deployment;

    // Private constructor to enforce the builder usage
    SoftwareProject(String requirements,String design,String development,String testing,String deployment) {
        this.requirements =requirements;
        this.design = design;
        this.development = development;
        this.testing = testing;
        this.deployment = deployment;
    }

    public String showProjectDetails() {
        return "Software Development Life Cycle:\n" +
               "Requirements: " + requirements + "\n" +
               "Design: " + design + "\n" +
               "Development: " + development + "\n" +
               "Testing: " + testing + "\n" +
               "Deployment: " + deployment;
    }
    
}