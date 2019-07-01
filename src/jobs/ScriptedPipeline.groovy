pipelineJob('NumberGenerator-Pipeline') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/geeksdevops/Tutorial-Pipe.git')
            }
        }
    }
    definition {
        cps {
            script(readFileFromWorkspace('Num-Jenkinsfile'))
            sandbox()
        }
    }
}

