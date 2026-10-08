package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.GymEquipmentProfile
import com.kveld9.trackgym.domain.model.WeightUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class GymEquipmentProfileEngineTest {

    @Test
    fun parseProfiles_emptyOrNull_returnsDefaults() {
        val defaultsNull = GymEquipmentProfileEngine.parseProfiles(null)
        val defaultsBlank = GymEquipmentProfileEngine.parseProfiles("   ")
        val defaultsInvalid = GymEquipmentProfileEngine.parseProfiles("{invalid json")

        assertEquals(3, defaultsNull.size)
        assertEquals(3, defaultsBlank.size)
        assertEquals(3, defaultsInvalid.size)
        assertEquals(GymEquipmentProfile.DEFAULT_COMMERCIAL_ID, defaultsNull.first().id)
        assertTrue(defaultsNull.first().isDefault)
    }

    @Test
    fun serializeAndParse_roundTrip_preservesAllFields() {
        val custom = listOf(
            GymEquipmentProfile(
                id = "custom_garage",
                name = "Garage Gym",
                barWeightKg = 18.5,
                availablePlatesKg = listOf(20.0, 10.0, 5.0, 1.25),
                minWeightIncrementKg = 1.25,
                isDefault = true
            )
        )

        val serialized = GymEquipmentProfileEngine.serializeProfiles(custom)
        val parsed = GymEquipmentProfileEngine.parseProfiles(serialized)

        assertEquals(1, parsed.size)
        val p = parsed.first()
        assertEquals("custom_garage", p.id)
        assertEquals("Garage Gym", p.name)
        assertEquals(18.5, p.barWeightKg, 0.001)
        assertEquals(listOf(20.0, 10.0, 5.0, 1.25), p.availablePlatesKg)
        assertEquals(1.25, p.minWeightIncrementKg, 0.001)
        assertTrue(p.isDefault)
    }

    @Test
    fun getActiveProfile_validId_returnsMatchingProfile() {
        val profiles = GymEquipmentProfile.defaultProfiles()
        val active = GymEquipmentProfileEngine.getActiveProfile(profiles, GymEquipmentProfile.DEFAULT_HOME_ID)
        assertEquals(GymEquipmentProfile.DEFAULT_HOME_ID, active.id)
        assertEquals("Home Gym", active.name)
    }

    @Test
    fun getActiveProfile_unknownId_fallsBackToDefault() {
        val profiles = GymEquipmentProfile.defaultProfiles()
        val active = GymEquipmentProfileEngine.getActiveProfile(profiles, "non_existent_id")
        assertEquals(GymEquipmentProfile.DEFAULT_COMMERCIAL_ID, active.id)
    }

    @Test
    fun roundToIncrement_calculatesExpectedMultiples() {
        assertEquals(102.5, GymEquipmentProfileEngine.roundToIncrement(101.8, 2.5), 0.001)
        assertEquals(100.0, GymEquipmentProfileEngine.roundToIncrement(101.1, 2.5), 0.001)
        assertEquals(81.25, GymEquipmentProfileEngine.roundToIncrement(81.2, 1.25), 0.001)
        assertEquals(85.0, GymEquipmentProfileEngine.roundToIncrement(83.0, 5.0), 0.001)

        // Safety boundary cases
        assertEquals(0.0, GymEquipmentProfileEngine.roundToIncrement(0.0, 2.5), 0.001)
        assertEquals(0.0, GymEquipmentProfileEngine.roundToIncrement(Double.NaN, 2.5), 0.001)
        assertEquals(50.0, GymEquipmentProfileEngine.roundToIncrement(50.0, 0.0), 0.001)
    }

    @Test
    fun addOrUpdateProfile_addsNewAndUpdatesExisting() {
        val initial = GymEquipmentProfile.defaultProfiles()
        val newProfile = GymEquipmentProfile(
            id = "crossfit_box",
            name = "Box",
            barWeightKg = 20.0,
            availablePlatesKg = listOf(20.0, 15.0, 10.0),
            minWeightIncrementKg = 2.5
        )

        val updatedList = GymEquipmentProfileEngine.addOrUpdateProfile(initial, newProfile)
        assertEquals(4, updatedList.size)
        assertNotNull(updatedList.find { it.id == "crossfit_box" })

        // Now update existing
        val modifiedProfile = newProfile.copy(name = "CrossFit Box HQ")
        val reUpdatedList = GymEquipmentProfileEngine.addOrUpdateProfile(updatedList, modifiedProfile)
        assertEquals(4, reUpdatedList.size)
        assertEquals("CrossFit Box HQ", reUpdatedList.find { it.id == "crossfit_box" }?.name)
    }

    @Test
    fun deleteProfile_removesTargetAndProtectsLastProfile() {
        val initial = GymEquipmentProfile.defaultProfiles()
        val afterDelete = GymEquipmentProfileEngine.deleteProfile(initial, GymEquipmentProfile.DEFAULT_HOTEL_ID)
        assertEquals(2, afterDelete.size)
        assertFalse(afterDelete.any { it.id == GymEquipmentProfile.DEFAULT_HOTEL_ID })

        // Try deleting until only 1 profile remains
        val onlyOne = listOf(initial.first())
        val deleteAttempt = GymEquipmentProfileEngine.deleteProfile(onlyOne, initial.first().id)
        assertEquals(1, deleteAttempt.size) // Should not delete the last remaining profile
    }

    @Test
    fun profile_convertsUnitsCorrectly() {
        val profile = GymEquipmentProfile(
            id = "test",
            name = "Test",
            barWeightKg = 20.0,
            availablePlatesKg = listOf(20.0, 10.0, 5.0),
            minWeightIncrementKg = 2.5
        )

        assertEquals(20.0, profile.barWeight(WeightUnit.KG), 0.001)
        assertEquals(44.1, profile.barWeight(WeightUnit.LB), 0.2)

        assertEquals(listOf(20.0, 10.0, 5.0), profile.availablePlates(WeightUnit.KG))
        val lbPlates = profile.availablePlates(WeightUnit.LB)
        assertEquals(3, lbPlates.size)
        assertTrue(lbPlates[0] > 40.0) // 20 kg ≈ 44.1 lb
    }
}
