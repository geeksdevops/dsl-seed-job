package com.dslexample.util

import spock.lang.Specification

class StepsUtilSpec extends Specification {

    def 'configures proxied Gradle tasks'() {
        given:
        Map configuration = [:]
        def gradleDsl = new Expando()
        gradleDsl.useWrapper = { boolean enabled -> configuration.useWrapper = enabled }
        gradleDsl.tasks = { String tasks -> configuration.tasks = tasks }
        gradleDsl.switches = { String switches -> configuration.switches = switches }

        def context = new Expando()
        context.gradle = { Closure gradleConfiguration ->
            gradleConfiguration.delegate = gradleDsl
            gradleConfiguration.resolveStrategy = Closure.DELEGATE_FIRST
            gradleConfiguration.call()
        }

        when:
        StepsUtil.proxiedGradle(context, 'clean test')

        then:
        configuration.useWrapper
        configuration.tasks == 'clean test'
        configuration.switches.readLines() == [
            '-Dhttp.proxyHost=xxx',
            '-Dhttps.proxyHost=xxx',
            '-Dhttp.proxyPort=xxx',
            '-Dhttps.proxyPort=xxx'
        ]
    }
}
