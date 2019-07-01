pipelineJob('NumberGenerator-Pipeline') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/geeksdevops/Tutorial-Pipe.git')
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

