package storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MilestonesTest {

    private static final String TEST_FILE_PATH = "data/testMilestones.txt";
    private Milestones milestones;

    @BeforeEach
    void setUp() {
        // Delete test file if it exists
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        milestones = new Milestones(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testInitialDifficultyIsEasy() {
        assertEquals("easy", milestones.getCurrentDifficulty());
    }

    @Test
    void testMilestoneAchievedAndPromoted() {
        boolean achieved = milestones.checkAndUpdate("easy", 61.0);
        assertTrue(achieved);
        assertTrue(milestones.isAchieved("easy"));
        assertEquals("intermediate", milestones.getCurrentDifficulty());
    }

    @Test
    void testNoPromotionIfBelowGoal() {
        boolean achieved = milestones.checkAndUpdate("easy", 40.0);
        assertFalse(achieved);
        assertEquals("easy", milestones.getCurrentDifficulty());
    }

    @Test
    void testDuplicateMilestoneNotRePromoted() {
        milestones.checkAndUpdate("easy", 70.0); // First time
        boolean secondTime = milestones.checkAndUpdate("easy", 90.0); // Already achieved
        assertFalse(secondTime);
    }

    @Test
    void testSaveAndReloadPreservesState() {
        milestones.checkAndUpdate("easy", 70.0);

        Milestones reloaded = new Milestones(TEST_FILE_PATH);
        assertTrue(reloaded.isAchieved("easy"));
        assertEquals("intermediate", reloaded.getCurrentDifficulty());
    }

    @Test
    void testSetCurrentDifficultyDirectly() {
        milestones.setCurrentDifficulty("difficult");
        assertEquals("difficult", milestones.getCurrentDifficulty());
    }

    @Test
    void testSetInvalidDifficultyIgnored() {
        milestones.setCurrentDifficulty("impossible");
        assertEquals("easy", milestones.getCurrentDifficulty());
    }
}
