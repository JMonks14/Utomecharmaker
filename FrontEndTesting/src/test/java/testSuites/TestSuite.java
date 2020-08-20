package testSuites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tome.tests.AccountCreate;
import tome.tests.AccountUpdate;
import tome.tests.BuySkills;
import tome.tests.CharCreate;
import tome.tests.KillChar;
import tome.tests.PasswordUpdate;
import tome.tests.ResetXP;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   AccountCreate.class,
   AccountUpdate.class,
   PasswordUpdate.class,
   CharCreate.class,
   BuySkills.class,
   ResetXP.class,
   KillChar.class
})
public class TestSuite {

}
