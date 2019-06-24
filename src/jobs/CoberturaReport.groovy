String basePath = 'CoberturaReport'
String repo = 'geeksdevops/Clock-CoberturaReport'

folder(basePath) {
    description 'This example shows Clock-CoberturaReport basic folder/job creation.'
}

pipelineJob("$basePath/ReportCobertura") {
    scm {
        github repo
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
	shell 'echo "Publishing Report on board"'
}
    definition {
        cps {
            script(readFileFromWorkspace('Jenkinsfile'))
            sandbox()
        }
    }

}

