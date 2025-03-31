package me.tofaa.entitylib.version

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class ELVersionTask : DefaultTask() {

    companion object {
        const val TASK_NAME = "generateVersionsFile"
    }

    @get:Input
    abstract var packageName: String

    @get:Input
    abstract var version: String

    @get:OutputDirectory
    abstract var outputDir: Provider<Directory>

    @TaskAction
    fun generate() {
        val dir = outputDir.get().dir(packageName.replace('.', '/'))
        dir.asFile.mkdirs()

        val file = dir.file("ELVersions.java").asFile
        if (!file.exists()) {
            file.createNewFile()
        }

        val ver = Version.fromString(version)
        logger.info("Generating ELVersions.java with version $ver")

        file.writeText("""
            /**
             * This file is generated by the auto-version task. Modifying it will have no effect.
             */
            package $packageName;
           
            import java.text.DateFormat;
            import com.github.retrooper.packetevents.util.PEVersion;
            
            public final class ELVersions {
            
                public static final String RAW = "$version";
                public static final PEVersion CURRENT = new PEVersion(${ver.major}, ${ver.minor}, ${ver.patch}, ${ver.snapShot});
                public static final PEVersion UNKNOWN = new PEVersion(0, 0, 0);
                
                private ELVersions() {
                    throw new IllegalStateException();
                }
                
                public static class Version {
                    
                    private final long timestamp;
                    
                    public Version(long timestamp) {
                        this.timestamp = timestamp;
                    }
                    
                    public long getTimestamp() {
                        return timestamp;
                    }
                    
                    public String getTimestampFormatted() {
                        return  DateFormat.getDateTimeInstance().format(new java.util.Date(timestamp));
                    }
                
                    public boolean isOlderThan(Version version) {
                        return this.timestamp < version.timestamp;
                    }
                
                }
                
            }
        """.trimIndent())
    }

    private data class Version(
        val major: Int,
        val minor: Int,
        val patch: Int,
        val snapShot: Boolean
    ) {
        companion object {
            private val REGEX = Regex("""(\d+)\.(\d+)\.(\d+)(?:\+[0-9a-f]+)?(-SNAPSHOT)?""")

            fun fromString(version: String): Version {
                val match = REGEX.matchEntire(version) ?: throw IllegalArgumentException("Invalid version: $version")
                return Version(
                    match.groupValues[1].toInt(),
                    match.groupValues[2].toInt(),
                    match.groupValues[3].toInt(),
                    match.groupValues[4].isNotEmpty()
                )
            }
        }
    }

}