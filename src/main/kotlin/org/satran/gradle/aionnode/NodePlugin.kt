package org.satran.gradle.aionnode

import de.undercouch.gradle.tasks.download.Download
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import java.io.File

open class NodePlugin: Plugin<Project> {

    lateinit var project: Project

    override fun apply(target: Project) {

        target?.let {
            project = target
            createTasks()

            target.plugins.apply("de.undercouch.download");
        }
    }

    private fun createTasks() {
        project.tasks?.let {

            var dTask = it.create("downloadFile", Download::class.java)
            dTask.src("https://github.com/aionnetwork/aion/releases/download/v0.3.0/aion-v0.3.0.284fa1e-2018-08-21.tar.bz2")
            dTask.dest(project.buildDir)
            dTask.onlyIfModified(true)

            var unzipTask = it.create("unzip", Copy::class.java)
            unzipTask.from(project.tarTree(File(dTask.dest, "aion-v0.3.0.284fa1e-2018-08-21.tar.bz2")))
            unzipTask.into(project.buildDir)


            it.create("install_aion", InstallAion::class.java).dependsOn(dTask, unzipTask)
        }
    }


}