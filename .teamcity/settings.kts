import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage

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

version = "2024.03"

project {

    params {
        param("teamcity.internal.aws.connection.reactUiEnabled", "true")
    }

    features {
        awsConnection {
            id = "AwsConnectionUsesSakura_AmazonWebServicesAws"
            name = "New Name"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVJFGDHSDZ"
                secretAccessKey = "credentialsJSON:5eb86222-6fe6-4757-99f0-d5b683d9dc2f"
                stsEndpoint = "https://sts.amazonaws.com"
            }
            allowInSubProjects = true
            allowInBuilds = true
        }
        awsConnection {
            id = "AwsConnectionUsesSakura_AmazonWebServicesAws1"
            name = "Amazon Web Services (AWS) (1)"
            regionName = "eu-west-1"
            credentialsType = iamRole {
                roleArn = "arn:aws:iam::913206223978:role/olga_sventukh_for_iam_commection"
                awsConnectionId = "AwsConnectionUsesSakura_AmazonWebServicesAws"
                stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
            }
            allowInBuilds = false
        }
        s3Storage {
            id = "PROJECT_EXT_9"
            bucketName = "ollven-test"
            forceVirtualHostAddressing = true
            awsEnvironment = default {
                awsRegionName = "eu-west-1"
            }
            connectionId = "AwsConnectionUsesSakura_AmazonWebServicesAws"
        }
    }
}
