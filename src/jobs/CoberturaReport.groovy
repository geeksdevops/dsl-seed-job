pipelineJob('') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/geeksdevops/Clock-CoberturaReport.git')
                scriptPath('JenkinsFile')
            }
        }
    }
    definition {
        cps {
            script(readFileFromWorkspace('Jenkinsfile'))
            sandbox()
        }
    }

}

