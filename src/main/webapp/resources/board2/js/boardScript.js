function sendData(){
   if(document.writeForm.subject.value==""){
      alert("제목은 필수 입니다.");
      document.writeForm.subject.focus();
      return false;
   }
   if(document.writeForm.content.value==""){
      alert("내용은 필수 입니다.");
      document.writeForm.content.focus();
      return false;
   }   
   if(document.writeForm.writer.value==""){
      alert("작성자는 필수 입니다.");
      document.writeForm.writer.focus();
      return false;
   }
   if(document.writeForm.passwd.value==""){
      alert("비밀번호는 필수 입니다.");
      document.writeForm.passwd.focus();
      return false;
   }
}
   function pwCheck(s){
	   alert(s)
      window.name = "parentform";
      if(s =='u'){
    	  document.parentform.action = "updateForm.sp";
      }else if(s =='d'){
    	  document.parentform.action = "deletePro.sp";
      }
          
      openWin=window.open("/idCheck.sp", "idCheck",
            "width=500, height=200, toolbar=10, location=10, member=no, scrollbars=no");
   }
   
   function setParentText(){
      opener.document.parentform.cpass.value= document.getElementById("pwCheck").value;
     window.close();
     compare();
   }
   
   
   function compare() {
   var pw = opener.document.parentform.passwd.value;
   var cpw = opener.document.parentform.cpass.value;
   
   if(pw==cpw){
      var f = opener.document.parentform;
      f.submit();
      window.close();
   }else{
      alert("비밀번호 오류");
     window.close();
   }
}
   
   