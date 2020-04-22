package io.bloco.biblioteca

import android.net.Uri
import io.bloco.biblioteca.helpers.FileManager
import io.bloco.biblioteca.testhelpers.InstrumentationContext
import org.hamcrest.CoreMatchers.*
import org.hamcrest.core.Is
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Test


class FileManagerTest {
    private val context = InstrumentationContext.useContext()
    private val fileManager = FileManager(context)

    @Test
    fun fileCreationTest() {
        val file = fileManager.createImageFile()
        assertNotNull(file)
        assertThat(file.name, startsWith("JPEG"))
        assertThat(file.name, endsWith(".jpg"))
    }

    @Test
    fun fileDeletionByStringTest() {
        val file = fileManager.createImageFile()
        assertNotNull(file)
        assert(file.exists())
        fileManager.deletePhotoFile(file.path)
        assertThat(file.exists(), Is(equalTo(false)))
    }

    @Test
    fun fileDeletionByUriTest() {
        val file = fileManager.createImageFile()
        assertNotNull(file)
        assert(file.exists())
        val uri = Uri.fromFile(file)
        fileManager.deletePhotoFile(uri)
        assertThat(file.exists(), Is(equalTo(false)))
    }

    @Test
    fun copyFileToAppFolderTest() {
        val file = fileManager.createImageFile()
        assertNotNull(file)
        assert(file.exists())
        val uri = Uri.fromFile(file)
        val appStorageFile = fileManager.copyPhotoFileToAppStorage(uri)
        assertNotNull(appStorageFile)
        assert(appStorageFile!!.exists())
        assertThat(appStorageFile.name, startsWith("JPEG"))
        assertThat(appStorageFile.name, endsWith(".jpg"))
        val path = appStorageFile.path
        assertThat(path, containsString(context.packageName))
    }
}