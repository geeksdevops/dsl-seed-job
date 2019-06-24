String basePath = 'CoberturaReport'
String repo = 'geeksdevops/Clock-CoberturaReport'

folder(basePath) {
    description 'This example shows Clock-CoberturaReport basic folder/job creation.'
}

pipelineJob("$basePath") {
    publishers {
	buildPipelineTrigger("$basePath/ReportJob")
	}
    definition {
        cps {
            script(readFileFromWorkspace('Jenkinsfile'))
            sandbox()
        }
    }

}

