$(function(){
   $('#reply').click(function(){
      document.parentform.action='writeForm.sp';
      document.parentform.submit();
   });
   
   $('#list1').click(function(){
       //javascript:location.href='list.do';
	   document.parentform.action='boardList.sp';
	   document.parentform.submit();
   }); 
   $('#list2').click(function(){
	   //document.writeForm.submit();
	   //document.writeForm.action='list.do';
	   javascript:location.href='boardList.sp';
   });
   $('#list3').click(function(){
	   document.updateForm.action='boardList.sp';
	   document.updateForm.submit();
   });
   
   
})