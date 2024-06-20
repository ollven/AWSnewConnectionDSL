import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile
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

version = "2024.03"

project {

    params {
        param("teamcity.internal.aws.connection.reactUiEnabled", "true")
    }

    features {
        awsConnection {
            id = "AwsConnectionUsesSakura_AmazonWebServicesAws"
            name = "AWS Key"
            regionName = "eu-west-1"
            credentialsType = static {
                accessKeyId = "AKIA5JH2VERVJFGDHSDZ"
                secretAccessKey = "credentialsJSON:5eb86222-6fe6-4757-99f0-d5b683d9dc2f"
                stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
            }
            allowInSubProjects = true
            allowInBuilds = true
        }
        awsConnection {
            id = "AwsConnectionUsesSakura_AmazonWebServicesAws1"
            name = "AWS IAM role"
            regionName = "eu-west-1"
            credentialsType = iamRole {
                roleArn = "arn:aws:iam::913206223978:role/olga_sventukh_for_iam_commection"
                awsConnectionId = "AwsConnectionUsesSakura_AmazonWebServicesAws"
                stsEndpoint = "https://sts.eu-west-1.amazonaws.com"
            }
            allowInBuilds = false
        }
        awsConnection {
            id = "AwsConnectionUsesSakura_AmazonWebServicesAws_2"
            name = "AWS default credentials"
            credentialsType = default()
            allowInBuilds = false
        }
        amazonEC2CloudProfile {
            id = "amazon-5"
            name = "nnn"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.US_EAST_N_VIRGINIA
            param("awsConnectionId", "AwsConnectionUsesSakura_AmazonWebServicesAws")
        }
    }
}
