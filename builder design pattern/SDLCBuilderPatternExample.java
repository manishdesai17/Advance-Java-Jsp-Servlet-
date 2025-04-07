public class SDLCBuilderPatternExample {
    public static void main(String[] args) {
        SoftwareProject project = new SDLCConcreteBuilder()
                             .setRequirements("Requirement analysis done")
                             .setDesign("Design completed")
                             .setDevelopment("Development done")
                             .setTesting("Testing done")
                             .setDeployment("Deployed to production successfully....")
                             .build();

System.out.println(project.showProjectDetails());

    }
}