package io.github.edwinchang24.salvage

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.findPluginId(alias: String): String = findPlugin(alias).get().get().pluginId
