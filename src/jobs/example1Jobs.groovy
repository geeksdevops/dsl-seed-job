String basePath = 'Example-Multiple-Jobs'
String repo = 'sheehan/gradle-example'

folder(basePath) {
    description 'This example shows basic folder/job creation.'
}

job("$basePath/PublishHostname") {
    scm {
        github repo
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        shell 'hostnamectl status'
    }
}

job("$basePath/ParameterJob") {
    parameters {
        stringParam 'host'
    }
    steps {
        shell 'echo \$host; echo "BUILD SUCCESS"'
    }
}
