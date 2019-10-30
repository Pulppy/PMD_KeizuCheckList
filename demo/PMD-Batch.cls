/*
	* PMDBatch
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @modified:
*/

/*
	* general05
	* @created: 2019/10/30 Truong Trang Ngoc Phuc
	* @decription: Trường hợp là class sử dụng trong batch thì đặt tên theo kiểu: danh từ ＋ “Batch”
	* @modified:
*/
// global class PMDBatc implements Database.Batchable<sObject>, Schedulable{
global class PMDBatch implements Database.Batchable<sObject>, Schedulable{

    /*
	 * Batch-03
	 * @created: 2019/10/30 Truong Trang Ngoc Phuc
	 * @decription: Uu tien lay cac doi tuong cutom trong method start
	 * @modified:
	*/
    // global Database.QueryLocator start(Database.BatchableContext bc, List<sObject> scope){
    //     HocSinh__c hs = HocSinh__c.getOrgDefaults();
    //     HocSinh__c hs = HocSinh__c.getInstance();
    //     HocSinh__c hs = HocSinh__c.getValues();
    //     return Database.getQueryLocator(strQuery);
    // }
    // global void execute(Database.BatchableContext bc, List<sObject> scope){
    //   HocSinh__c hs = HocSinh__c.getOrgDefaults();
    //   HocSinh__c hs = HocSinh__c.getInstance();
    //   HocSinh__c hs = HocSinh__c.getValues();
    // }

    //==================================================================================================================

   /*
	 * Batch-04
	 * @created: 2019/10/24 Truong Trang Ngoc Phuc
	 * @decription: Trong truog hop xu ly du lieu lon bat buoc phai get du lieu trong Database.getQueryLocator de lay
	 * @modified:
	*/

//    global Database.QueryLocator start(Database.BatchableContext bc){
//     //   return foo();
//     //   return Database.getQueryLocator(strQuery);
//     //   return [SELECT Id FROM HocSinh__c];
//    }

   global void execute(Database.BatchableContext bc, List<sObject> scope){
      HocSinh__c hs = HocSinh__c.getOrgDefaults();
      for(Sobject s : scope){
            s.put(Field, Value); 
      }
      [SELECT Id FROM HocSinh__c];
      Database.query();
   }
   /*
	 * Batch-08
	 * @created: 2019/10/24 Truong Trang Ngoc Phuc
	 * @decription: Trong batch ko the dang ki dc nhieu schedule chu y
	 * @modified:
	 */
   global void finish(Database.BatchableContext bc) {
        HocSinh_Batch hsBatch = new HocSinh_Batch();
        System.schedule('Hoc_Sinh_Batch', '0 0 * * * ?', hsBatch);
        System.schedule('Hoc_Sinh_Batch', '0 0 * * * ?', hsBatch);
        // System.schedule('Hoc_Sinh_Batch', '0 0 * * * ?', hsBatch);
        // System.schedule('Hoc_Sinh_Batch', '0 0 * * * ?', hsBatch);
	}

   global Database.QueryLocator foo(Database.BatchableContext bc){
      return Database.getQueryLocator(query);
   }
}