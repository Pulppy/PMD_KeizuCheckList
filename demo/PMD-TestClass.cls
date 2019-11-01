/*
	* PMDTestClass
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @modified:
*/
/*
	* testclass01
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @decription: Trong file test ko duoc su dung @isTest(seeAllData = true)
	* @modified:
*/
// @isTest(seeAllData= true)
@isTest(seeAllData= false)
/*
	* testclass06
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @decription: Phai su dung method Test.startTest(), Test.testStop de giam governor limits khi thuc thi test
	* @modified:
*/
// private class PMDTestClass {
private class PMDTestClassTest {
/*
	* testclass02
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @decription: Phai su dung method Test.startTest(), Test.testStop de giam governor limits khi thuc thi test
	* @modified:
*/
    // private static testMethod void testMethod02a(){}
    // private static testMethod void testMethod02b(){
    //     Test.startTest();
    //     Test.stopTest();
    // }

    // =================================================================

/*
	* testclass07
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @decription: Ten method test phai bat dau va chu test + Method muon test. Vidu: testSubString()
	* @modified:
*/
    private static testMethod void method07() {
        Test.startTest();
        Test.stopTest();
        System.assertEquals();
    }
    private static testMethod void testaaMethod07() {
        Test.startTest();
        Test.stopTest();
        System.assertEquals();
    }

    // =================================================================

/*
	* testclass08
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @decription: Khong duoc hardcode Id trong testclass
	* @modified:
*/
    // private static testMethod void testMethod08() {
    //     String strID01;
    //     strID01 = '04i6D0000007A1EQAU';
    //     String strID02 = '0016D0000007A1EQAU';

    //     String name = '001TrangNgocPhucTr';
    //     Test.startTest();
    //     Test.stopTest();
    //     System.assertEquals();
    // }

    // =================================================================

/*
	* batch10
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @decription: Trong test class phai dat method Test.StartTest, Test.StopTest khi goi batch
	* @modified:
*/
    // private static testMethod void testBatch10(){
    //     DataMigrateBatch dataMigrateBatch = new　DataMigrateBatch();
    //     Database.executeBatch(dataMigrateBatch);
    //     Test.startTest();
    //         Database.executeBatch(dataMigrateBatch);
    //     Test.stopTest();
    //     System.assertEquals();
    // }
}