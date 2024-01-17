package com.apakhomov.leetenv.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class LeetenvPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.create("leetenv", LeetenvPluginExtension)

        project.tasks.register("pick", PickTask) {
            num = project.extensions.leetenv.num
        }
    }
}

