String basePath = 'CoberturaReport'
String repo = 'geeksdevops/Clock-CoberturaReport'

folder(basePath) {
    description 'This example shows Clock-CoberturaReport basic folder/job creation.'
}

job("$basePath") {
	buildPipelineTrigger("$basePath/ReportJob"
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

