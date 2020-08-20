package testSuites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tome.tests.AccountCreate;
import tome.tests.AccountUpdate;
import tome.tests.PasswordUpdate;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   AccountCreate.class,
   AccountUpdate.class,
   PasswordUpdate.class
})
public class TestSuite {

}
