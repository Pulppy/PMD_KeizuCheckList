/*
	* PMDCheckListDemo
	* @created: 2019/10/12 Lu Chi Hao
	* @modified:
*/

public class PMDCheckListDemo{

/*
	* general01
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Tên method phải viết thường , từ chữ thứ 2 trở đi phải viết hoa chữ đầu tiên
	* @modified:
*/

	// private void general01(){

	//}

	//=========================================================================================
/*
	* general02
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Trong xu ly, những method hoặc funtion public phai co try catch de xu ly loi.
	* @modified:
*/

	// public void general02(){
	//     // try{

	//     // }catch(Exception e){}
	// }

	//=========================================================================================
/*
	* general09
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Dat ten cac bien sao cho de dung, de nho. Su dung cac tu nhu is, has….de dat ten bien
	* @modified:
*/

	// private static Boolean isTest1;
	// private void general09(){
	//     isTest1 = true;
	//     Boolean hasTest2 = false;
	//     if(test2){}
	// }
	// private void test(){
	//     isTest1 = false;
	// }

	//=========================================================================================

/*
	* general12
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Khi su dung check size of list thi phai su dung ham isEmpty()
	* @modified:
*/

	// private void general12(){
	//     list<int> listTest = new list<int>();
	//     if(listTest.size() > 0){}
	//     if(listTest.isEmty()){}
	// }

	//=========================================================================================

	/**
	* general13
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Tên biến bằng chữ thường, từ chữ thứ 2 trở đi phải viết hoa chữ đầu tiên
	* @modified:
	*/

	// private int test1;
	// private void general13(){
	//     int test2 = 5;
	//     test1 = true;
	// }
	// private void test2(){
	//     test1 = true;
	// }

	//=========================================================================================

	/**
	* general15
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Không được thiết lập biến  class, biến  instance ở trạng thái public.
	* @modified:
	*/

	// public int a;
	// private void general151(){
	//     a = 5;
	// }
	//  private void general152(){
	//     a = 9;
	// }
	// public PMDCheckListDemo(){
	// 	int bbbbbbb;
	// }

	//=========================================================================================

	/**
	* general16
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Hằng số phải viết hoa nguyên chữ, dùng dấu gạch dưới để ngắt từng chữ
	* @modified:
	*/

	// private static final string aaa = 'XXXXX';
	// private string b_b_b = 'XXXXX';
	// private void general161(){
	//     if(aaa == 'x'){}
	//     if(b_b_b == 'x'){}
	// }
	//  private void general162(){
	//     if(aaa == 'x'){}
	//     if(b_b_b == 'x'){}
	// }

	//=========================================================================================

	/**
	* general17
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Sau trước dấu toán tử phải là space 1 byte
	* @modified:
	*/

	private void general17(){
	   Integer x = a+b;
	}

	//=========================================================================================

	/**
	* general18
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Ngắt từng parameter bằng dấu phẩy,  sau đó phải là 1 space 1byte
	* @modified:
	*/

	// private void general181(){
	//     int a = 0;
	//     int c = 1;
	//     int e = 2;
	//     general182(a, 'xxxxxx',c,
	//                 'sxsxsx',   e);
	// }
	// private void general182(int a, string b, int c,
	//                             string d,int e){
		
	// }

	//=========================================================================================

	/**
	* general19
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Khong khai bao bien toan cuc ko can thiet, uu tien khai bao trong method
	* @modified:
	*/

	// private string gen19 = 'xxxx';
	// private void general191(){
	//     if(gen19.size() > 0){}
	//     if(gen19 == 'null'){}
	// }
	// private void general192(){
	//     //gen19 += 'asd';
	// }

	//=========================================================================================

	/**
	* general20
	* @created: 2019/10/22 Truong Trang Ngoc Phuc
	* @decription: Trong salseforce co gioi han, nen giam thieu toi da su dung loop.
	* @modified:
	*/

	// private void general20(){
    //      Boolean isTrue= true;
    //      List<String> stringList = new List<String>();
    //     //  Loop: 1
    //      for (String str : stringList) {
    //         // Loop: 2
    //         for (Integer i = 0; i < stringList.size(); i++){
    //             // Loop: 3
    //             while(true){}
    //          }

    //         // Loop: 2
    //         while(isTrue){
    //             // Loop: 3
    //             for (String str : stringList) {
    //                 // Loop: 4
    //                 while(true){}
    //             }

    //             // Loop: 3
    //             while(isTrue){}
    //         }

    //         // Loop: 2
    //         for (Integer i = 0; i < stringList.size(); i++){}
    //      }

	//=========================================================================================

	/**
	* general22
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Chỉ sử dụng tên biến 1 ký tự trong vòng lặp for 
	* @modified:
	*/

	// private void general22(){
	//     int ten = 10;
	//     list<int> lst = new list<int>();
	//     for(int ad = 0; ad < ten; a++){}
	//     for(int ad : lst){}
	// }

	//=========================================================================================

	/**
	* general24
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Trong xử lý loop, không sử dụng xử lý @future
	* @modified:
	*/
	
	// private void general241(){
	//     int ten = 10;
	//     for(int i = 0; i < ten; i++){
	//         general242();
	//     }
	// }

	// @future
	// private void general242(){

	// }

	//=========================================================================================

	/**
	* general25
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Cau điều kiện if phải có dấu () va dấu  {}
	* @modified:
	*/

	// private void general25(){
	//     Integer x = 0;
	//     if(true)
	//     x++;
	//     else 
	//     x--;    
	// }

	//=========================================================================================

	/**
	* general26
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Trong câu điều kiện if không được lồng vào nhau , không nên lồng phức tạp
	* @modified: 
	*/

	// private void general26(){
	//     if(true){
	//         if(true){
	//             if(true){
	//                 if(true){
	//                     if(true){
	//                         if(true){

	//                         }
	//                     }
	//                 }
	//             }
	//         }
	//     }
	// }

	//=========================================================================================

	/**
	* general33
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Nhung source comment ko can thiet thi phai delete sau khi hoan thanh coding, ke ca cac dong system.debug
	* @modified: 
	*/

	// private void general33(){
	//     system.debug();
	// }

	//=========================================================================================

	/**
	* general39
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Trong xử lý loop, không sử dụng xử lý DML
	* @modified: 
	*/

	// private void general39(){
	//     int ten = 10;
	//     Account b;
	//     for(int i = 0; i < ten; i++){
	//         insert b;
	//     }
	//     for(int i = 0; i < ten; i++){
	//        testmethodxxx(); 
	//     }
	// }

	// private void testmethodxxx(){
	//     Account a;
	//     insert a;
	// }

	//=========================================================================================

	/**
	* general43
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Tranh su dung nhieu SOQL khong can thiet, giam thieu phat sinh SOQL cang nhieu cang tot.
	* @modified: 
	*/

	// private void general43(){
	//     String strQuery = 'SELECT year FROM account LIMIT 1';
	//     int a;
	//     a = [SELECT year FROM account LIMIT 1];
	//     a = [SELECT year FROM account LIMIT 1];
	//     a = [SELECT year FROM account LIMIT 1];
	//     Database.query(strSOQL);
	//     Database.query(strSOQL);
	//     a = [SELECT year FROM account LIMIT 1];
	//     a = [SELECT year FROM account LIMIT 1];
	//     a = [SELECT year FROM account LIMIT 1];
	//     Database.query(strSOQL);
	//     Database.query(strSOQL);
	//     Database.query(strSOQL);
	// }

	//=========================================================================================

	/**
	* general46
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Trong xử lý loop không được sử dụng SOQL, SOSL
	* @modified: 
	*/

	// private void general4601(){
	// 	int ten = 10;
	// 	int a;
	// 	String strQuery = 'SELECT year FROM account LIMIT 1';
	// 	Account b;
	// 	for(int i = 0; i < ten; i++){
	// 		Database.query(strSOQL);
	// 		a = [SELECT year FROM account LIMIT 1];
	// 	}
	// 	for(int i = 0; i < ten; i++){
	// 		general46_02();
	// 	}
	// }

	// private void general4602(){
	// 	int a;
	// 	String strQuery = 'SELECT year FROM account LIMIT 1';
	// 	a = [SELECT year FROM account LIMIT 1];
		
	// }

	//=========================================================================================

	/* Demo code - General 53
    *
    */
    // private PageReference page() {
    //     String unsafeLocation = ApexPage.getCurrentPage().getParameters.get('url_param');
    //     return new PageReference(unsafeLocation);
    // }


	//Batch 03, 04

// global with sharing class HocSinhBatch implements Database.Batchable<sObject>, Schedulable{
//     private list<Diem__c> diemList;
// 	global Database.QueryLocator start(Database.batchableContext bc){

// 		String query = 'SELECT Name, ID, Diem1__c, Diem2__c, Diem3__c FROM HocSinh__c';
//       	 Database.query(query);
// 		hj();
// 	}

// 	global void execute(Database.BatchableContext bc, List<HocSinh__c> scope){
//         PaymentRequest__c objSetting = PaymentRequest__c.getOrgDefaults();
// 		diemList = new list<Diem__c>();
// 		Diem__c diem;
// 		Database.query(query);

// 		for(HocSinh__c hs: scope){
// 			//Thêm điểm Hóa
// 			diem = new Diem__c();
// 			diem.HocSinh__c = hs.ID;
// 			diem.Mon__c = 'Hóa';
// 			diem.Diem__c = hs.Diem1__c;
// 			diem.Name = hs.Name + '-' + diem.Mon__c;
// 			diem.Key__c = diem.HocSinh__c + '-hoa';
// 			diemList.add(diem);
// return Database.getquerylocator(query);
// 			//Thêm điểm Lý
// 			diem = new Diem__c();
// 			diem.HocSinh__c = hs.ID;
// 			diem.Mon__c = 'Lý';
// 			diem.Diem__c = hs.Diem2__c;
// 			diem.Name = hs.Name + '-' + diem.Mon__c;
// 			diem.Key__c = diem.HocSinh__c + '-ly';
// 			diemList.add(diem);

// 			//Thêm điểm Toán
// 			diem = new Diem__c();
// 			diem.HocSinh__c = hs.ID;
// 			diem.Mon__c = 'Toán';
// 			diem.Diem__c = hs.Diem3__c;
// 			diem.Name = hs.Name + '-' + diem.Mon__c;
// 			diem.Key__c = diem.HocSinh__c + '-toan';
// 			diemList.add(diem);
            
			
// 		}
		
		
// 	}
// 	global void execute(final SchedulableContext context){
// 		try{
// 			Database.executeBatch(this);
// 		}finally{
// 			System.abortJob(context.getTriggerId());
// 		}
// 	}
// 	global void finish(Database.BatchableContext bc){
// 		//DateTime dt = DateTime.now();
// 		//Integer miNow = dt.minute();
// 		//string miSet;
// 		//if(miNow >= 0 && miNow < 15){
// 		//	miSet = '15';
// 		//}else if(miNow >=15 && miNow < 30){
// 		//	miSet = '30';
// 		//}else if(miNow >=30 && miNow < 45){
// 		//	miSet = '45';
// 		//}else{
// 		//	miSet = '0';
// 		//}
// 		//string cron = '0 ' + miSet + ' * * * ?';
//         insertMethod();
//                 insert a;
// 		System.schedule('Update score every hour', '0 0 * * * ?' , new HocSinhBatch());
// 	}

//     private void insertMethod(){
//         insert diemList;
//     }
// }

//General10

// @isTest
// private class BillingControlTest {
//     static testMethod void testInitSearchCondition(){
        
// 　　DataMigrateBatch dataMigrateBatch = new　DataMigrateBatch();
//     Database.executeBatch(dataMigrateBatch);
//     Test.StartTest();
//     Test.StopTest();
   
//     }
// }

// public class test{
//     public void test2(){
//         List<int> listA;
//         if(!listA.isEmty()){
//             update listA;
//         }
//         if(!listA.isBlank() && !listC.isEmty()){
//             update listA;
// 			List<int> listB;
		
// 			insert listB;
		
// 		}
//     }
// }


//General-06

// trigger ContextExampleTrigger on Account (before insert, after insert, after delete) {
//     if (Trigger.isInsert) {
//         if (Trigger.isBefore) {
//             // Process before insert
//         } else if (Trigger.isAfter) {
//             // Process after insert
//         }        
//     }
//     else if (Trigger.isDelete) {
//         // Process after delete
//     }
// }
}