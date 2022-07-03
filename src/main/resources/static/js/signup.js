function usernameCheck(event) {

    event.preventDefault();
    let username = $('.username').val();
    console.log(username);

    $.ajax({
        type:'get',
        url:'/api/auth',
        data:{ 'username' : username },
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{
        if(res == true){
            alert("✔ 사용 가능한 아이디입니다.")
        }else{
            alert("❗ 이미 사용중인 아이디입니다.")
        }


    }).fail(error=>{
        if(error.responseJSON.data == null){
            alert(error.responseJSON.message);   //errorMap 있을떄, 없을때를 분기해 놓은것. 유효성검사할때 프론트,전처리,후처리중에 전처리 후처리할때 errorMap여부가 다르기 때문에 이렇게 분기로 받아준다.
        }else{
            alert(JSON.stringify(error.responseJSON.data));//JSON.stringify() - js object를 문자열로 나타내줌.
        }
    });

}