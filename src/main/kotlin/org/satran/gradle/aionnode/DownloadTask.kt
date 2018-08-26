package org.satran.gradle.aionnode

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DownloadTask: DefaultTask() {

    @TaskAction
    fun download() {
        println("Download task .............")
    }

}
