
/**
	* general06
	* @created: 2019/10/12 Lu Chi Hao
	* @decription: Trường hợp là class sử dụng trong trigger thì đặt tên theo kiểu : tên Object (xoa chu __c)　＋　“Trigger”
	* @modified: 
*/
// trigger AccountTrigger on Account (before insert, after insert, after delete) {
// trigger HocSinhTrigger on Account (before insert, after insert, after delete) {
trigger AccountTrigger on Account (before insert, after insert, after delete) {
    AccountTriggerHandler handler = new AccountTriggerHandler();
	    if (Trigger.isInsert) {
	        if (Trigger.isBefore) {
                /**
                * Trigger02
                * @created: 2019/10/30 Truong Trang Ngoc Phuc
                * @decription: Khi xu ly trigger thi phai tao cac lop handler de xe ly, trong file trigger chi xu ly su kien va delegate cho cac lop handler thuc hien
                * @modified: 
                */
                // handler.doBeforeInsert();
                // demoTrigger();
	        }
        }
        private void demoTrigger(){}
}