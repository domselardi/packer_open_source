package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.CommitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build")) {
    triggers {
        val trigger1 = find<VcsTrigger> {
            vcs {
                triggerRules = "-:*.md"
                branchFilter = """
                    +:*
                    -:temp-*
                    -:pull/*
                """.trimIndent()
            }
        }
        trigger1.apply {
            triggerRules = """
                -:*.md
                -.teamcity/
            """.trimIndent()
        }
    }

    features {
        val feature1 = find<CommitStatusPublisher> {
            commitStatusPublisher {
                publisher = github {
                    githubUrl = "https://api.github.com"
                    authType = personalToken {
                        token = "credentialsJSON:95bbfc46-3141-4bed-86ec-f8ec751f3e94"
                    }
                }
                param("github_oauth_user", "mkuzmin")
            }
        }
        feature1.apply {
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:5ead3bb1-c370-4589-beb8-24f8d02c36bc"
                }
            }
        }
        val feature2 = find<PullRequests> {
            pullRequests {
                provider = github {
                    authType = token {
                        token = "credentialsJSON:39727f26-62ed-4152-ab9a-f6845076a979"
                    }
                    filterAuthorRole = PullRequests.GitHubRoleFilter.EVERYBODY
                }
            }
        }
        feature2.apply {
            provider = github {
                authType = token {
                    token = "credentialsJSON:5ead3bb1-c370-4589-beb8-24f8d02c36bc"
                }
                filterTargetBranch = ""
                filterAuthorRole = PullRequests.GitHubRoleFilter.EVERYBODY
            }
        }
    }
}
