# Jenkins Job DSL Gradle Example 

[![License](https://img.shields.io/github/license/geeksdevops/dsl-seed-job)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/geeksdevops/dsl-seed-job?style=social)](https://github.com/geeksdevops/dsl-seed-job/stargazers)

An example [Job DSL](https://github.com/jenkinsci/job-dsl-plugin) project that uses Gradle for building and testing. Check out [this presentation](https://www.youtube.com/watch?v=SSK_JaBacE0) for a walkthrough of this example (starts around 14:00). 

## File structure

    .
    ├── src
    │   ├── jobs                # DSL script files
    │   ├── main
    │   │   ├── groovy          # support classes
    │   │   └── resources
    │   │       └── idea.gdsl   # IDE support for IDEA
    │   ├── scripts             # scripts to use with "readFileFromWorkspace"
    │   └── test
    │       └── groovy          # specs
    └── build.gradle            # build file

## Testing

`./gradlew test` runs the specs.

Unit specs live under `src/test/groovy`; for example, [StepsUtilSpec](src/test/groovy/com/dslexample/util/StepsUtilSpec.groovy)
checks that the proxy-aware Gradle step uses the wrapper, forwards the requested tasks, and sets the proxy switches.
Plugins providing auto-generated DSL must be added to the build dependencies when adding specs that exercise those DSLs.

## Add this project to Jenkins

This repository contains Job DSL scripts. Jenkins runs them from a **seed job** to create or update
the jobs described by those scripts. The seed job is separate from the jobs it generates.

### 1. Install Jenkins plugins

Install these plugins from **Manage Jenkins > Plugins**:

* **Job DSL** — provides the Process Job DSL build step.
* **Git** — checks out this repository for the seed job.
* **Folders** — required by scripts that create Jenkins folders.
* **GitHub** — required by scripts that use the `github` SCM DSL.
* **Gradle** — required for generated jobs that use the Gradle build step.

Restart Jenkins if the plugin manager requests it. Give the Jenkins user that will run the seed job
permission to create and configure jobs and folders.

### 2. Create the seed job

1. In Jenkins, select **New Item**, enter a name such as `dsl-seed-job`, choose **Freestyle project**,
   and select **OK**.
2. Under **Source Code Management**, choose **Git** and enter this repository's clone URL. Add
   credentials if the repository is private, then choose the branch Jenkins should use.
3. Under **Build Steps**, add **Invoke Gradle script**:
   * Enable **Use Gradle Wrapper**.
   * Set **Tasks** to `clean test`.
4. Add another build step, **Process Job DSLs**:
   * Set **DSL Scripts** to `src/jobs/**/*Jobs.groovy`.
   * Set **Additional classpath** to `src/main/groovy` if the scripts use project support classes.
5. Optionally add **Publish JUnit test result report** with the pattern
   `build/test-results/**/*.xml`.
6. Save the job and select **Build Now**.

The Gradle step runs this repository's tests before processing the DSL. If the checkout is shallow
or branch-specific, ensure the configured branch contains the scripts you want Jenkins to process.

### 3. Check generated jobs

After a successful seed build, open the Jenkins dashboard and look for the generated folders and
jobs. For example, [example2Jobs.groovy](src/jobs/example2Jobs.groovy) creates an `example2` folder
with build and deploy jobs for each branch it retrieves from the configured GitHub repository.

That example currently targets `sheehan/gradle-example`, fetches its branches from the public GitHub
API, and uses placeholder deploy commands. Change the repository and deploy steps in the DSL before
using it for your own application. The Jenkins controller or agent running the seed job must be
able to reach `api.github.com`; unauthenticated API requests are subject to GitHub rate limits.

### Notes

* Use **Manage Jenkins > In-process Script Approval** to review and approve signatures when
  prompted. Starting with Job DSL 1.60, the **Additional classpath** setting is unavailable when
  [Job DSL script security](https://github.com/jenkinsci/job-dsl-plugin/wiki/Script-Security) is
  enabled; use the supported script-security workflow for your Jenkins version.
* Verify `./gradlew test` locally before configuring Jenkins, using a Java runtime supported by the
  Gradle version configured in the wrapper.
* The repository's current [Jenkinsfile](Jenkinsfile) is a separate Maven/Sonar pipeline example;
  it is not the Gradle seed-job configuration described above.

## Other Resources

* [Job DSL Playground](http://job-dsl.herokuapp.com/) - App for debugging Job DSL scripts.
* [Job DSL API Viewer](https://jenkinsci.github.io/job-dsl-plugin/) - Full Job DSL syntax reference.
* [Job DSL REST Example](https://github.com/sheehan/job-dsl-rest-example) - Example that shows how to make Job DSL updates via the Jenkins REST API. Originally part of this repo, later split off.

#### Gradle Plugins
Plugins that automatically apply the functionality laid out in this example repo.
* [Gradle Job DSL Support Plugin](https://github.com/AOEpeople/gradle-jenkins-job-dsl-plugin) - Plugin for easy management of Jenkins Job DSL scripts with Gradle.
* [Gradle Jenkins Job DSL Plugin](https://github.com/heremaps/gradle-jenkins-jobdsl-plugin) - Plugin to manage Jenkins Job DSL projects in a Gradle project.



## License

This project is licensed under the [Apache License 2.0](LICENSE).

##  PLUGIN TO INSTALL 

Installing Plugins/Upgrades
Preparation to install to fix Example8 and Example9

* Badge	 				: Success
* Groovy Postbuild	 		: Success
* GitHub Pull Request Builder	 	: Success
* CVS	 				: Success
