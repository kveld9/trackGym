package com.kveld9.trackgym.data.backup

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AutoBackupEngineTest {

    @Test
    fun testGenerateBackupFileNameFormat() {
        val timestamp = 1712574000000L // specific fixed time
        val fileName = AutoBackupEngine.generateBackupFileName(timestamp)
        assertTrue(fileName.startsWith(AutoBackupEngine.BACKUP_FILE_PREFIX))
        assertTrue(fileName.endsWith(AutoBackupEngine.BACKUP_FILE_EXTENSION))
        assertTrue(AutoBackupEngine.isAutoBackupFile(fileName))
    }

    @Test
    fun testIsAutoBackupFileFilter() {
        assertTrue(AutoBackupEngine.isAutoBackupFile("trackgym_autobackup_20261008_120000.json"))
        assertFalse(AutoBackupEngine.isAutoBackupFile("trackgym_backup_manual.json"))
        assertFalse(AutoBackupEngine.isAutoBackupFile("trackgym_workouts.csv"))
        assertFalse(AutoBackupEngine.isAutoBackupFile("trackgym_autobackup_file.txt"))
        assertFalse(AutoBackupEngine.isAutoBackupFile(null))
        assertFalse(AutoBackupEngine.isAutoBackupFile(""))
    }
}
