package com.apakhomov.leetenv.plugins

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

class PickTask extends DefaultTask {
    @Option(option = "num", description = "The problem number.")
    @Input
    String num

    @TaskAction
    void performTask() {
        println "Picking problem: $num to $project.projectDir.absolutePath"
        new LeetupLeetcodeClient(
        project.projectDir.absolutePath
        ).pick(Integer.valueOf(num));
    }
}
