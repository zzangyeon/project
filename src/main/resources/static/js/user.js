function userUpdate(id,event) {

    event.preventDefault();
    let data = $("#userUpdateForm").serialize();
    //console.log(data);

    $.ajax({
        type:"post",
        url:'/api/user/'+id,
        data:data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{//json으로 파싱해서 res변수로 받는다.  //HttpStatus 상태코드 200번대면 done  //브라우저끼리 ajax통신할때는 http상태코드를 같이 보내주는것이 좋다.
        //console.log("성공",res);
        $('#username').val(res.data.username);
        $('#blogName').val(res.data.blogName);
        $('#blogIntro').val(res.data.blogIntro);
        alert("수정이 완료되었습니다.")
    }).fail(error=>{//HttpStatus 상태코드 200번대가 아니면 error
        if(error.responseJSON.data==null){
            alert(error.responseJSON.message);   //errorMap 있을떄, 없을때를 분기해 놓은것. 유효성검사할때 프론트,전처리,후처리중에 전처리 후처리할때 errorMap여부가 다르기 때문에 이렇게 분기로 받아준다.
        }else{
            alert(JSON.stringify(error.responseJSON.data));//JSON.stringify() - js object를 문자열로 나타내줌.
        }
    });

}

// 구독블로그 모달창 띄우기
function subscribeModalOpen(principalId) {
    $(".modal-subscribe").css("display", "flex");

    // $.ajax({
    //     type:"get",
    //     url: "/api/user/"+principalId+"/subscribe",
    //     dataType:"json"
    // }).done(res=>{
    //     console.log(res.data);
    //
    //     res.data.forEach((u)=>{
    //         let item = getSubscribeModalItem(u);
    //         $("#subscribeModalList").append(item);
    //     })
    // }).fail(error=>{
    //     console.log(error);
    // });
}

function getSubscribeModalItem(u) {
    let item =
        `<div class="subscribe__item" id="subscribeModalItem-${u.id}">
		<div class="subscribe__img">
			<img src="/upload/${u.profileImageUrl}" onerror="this.src='/images/person.jpeg'" />
		</div>
		<div class="subscribe__text">
			<h2>${u.username}</h2>
		</div>
		<div class="subscribe__btn">`;

    if(!u.equalUserState){//동일 유저가 아닐때 버튼이 만들어짐
        if(u.subscribeState){//구독한 상태
            item+=`<button class="cta blue" onclick="toggleSubscribe(${u.id},this)">구독취소</button>`;
        }else{//구독 안 한 상태
            item+=`<button class="cta" onclick="toggleSubscribe(${u.id},this)">구독하기</button>`;
        }
    }
    item+=`
		</div>
	</div>`;

    return item;
}

function subscribe(fromId,toId) {

    let data = {
        fromId:fromId,
        toId:toId
    }

    $.ajax({
        type:"post",
        url:'/api/subscribe',
        data:data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
    }).done(res=>{
        if(res.code == 1){
            alert("구독이 완료되었습니다.")
        }

    }).fail(error=>{
        alert("구독에 실패하였습니다.")

    });



}
