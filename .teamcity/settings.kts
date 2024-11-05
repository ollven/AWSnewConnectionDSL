import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.07"

project {

    buildType(Build)

    features {
        awsConnection {
            id = "Aws_AmazonWebServicesAws1"
            name = "Amazon Web Services (AWS) (1)"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVJFGDHSDZ"
                secretAccessKey = "zxx268812497aef1753dee6f463183d6865f68bfe4a4fa33e5650da1a642154a6060df8f6b68cf03140775d03cbe80d301b"
                stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
            }
            allowInBuilds = true
        }
        awsConnection {
            id = "Aws_AmazonWebServicesAws2"
            name = "Amazon Web Services (AWS) (2)"
            credentialsType = iamRole {
                roleArn = "arn:aws:iam::913206223978:role/olga_sventukh_for_iam_commection"
                awsConnectionId = "Aws_AmazonWebServicesAws1"
            }
            allowInBuilds = false
        }
    }
}

object Build : BuildType({
    name = "Build"

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "pwd"
        }
    }
})
