package org.satran.gradle.aionnode

import org.gradle.api.Plugin
import org.gradle.api.Project

open class NodePlugin: Plugin<Project> {

    lateinit var project: Project

    override fun apply(target: Project) {

        target?.let {
            project = target
            createTasks()
        }

    }

    private fun createTasks() {
        project.tasks?.let {
            it.create("download", DownloadTask::class.java)
        }
    }


}