// 내 블로그 정보 수정
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

// 구독블로그 모달창 띄우기 -1
function subscribeModalOpen(userId) {

    $.ajax({
        type:"get",
        url: `/api/subscribe/${userId}`,
        dataType: "json"
    }).done(res=>{
        let closeBtn = `<span onclick="subscribeModalClose()" style="margin-left: 290px;margin-top: -7px">❌</span>`
        $(".subscribeList-container").append(closeBtn);

        res.data.forEach((s)=>{
            let item = getSubscribeModalItem(s);
            $(".subscribeList-container").append(item);
        })
        $(".modal-subscribe").css("display", "flex");

    }).fail(error=>{
        alert("실패")
    });
}
// 구독블로그 모달창 띄우기 -2
function getSubscribeModalItem(s) {
    let item = `
    <div class="item">
        <div class="imgDiv">
            <img class="profile2" src="/profile/${s.profileImageUrl}">
        </div>
        <span class="username" onclick="location.href='/blog/${s.id}'">${s.username}</span>
        <!--<button class="btn">구독취소</button>-->
    </div>
    <hr class="line">
    `

    return item;
}

// 구독블로그 모달창 닫기
function subscribeModalClose() {
    $(".modal-subscribe").css("display", "none");
    $(".subscribeList-container").empty();
}

// 구독하기, 구독취소
function toggleSubscribe(toUserId,obj) {
    if ($(obj).text() === "구독취소") {

        $.ajax({
            type:"delete",
            url: "/api/subscribe/"+toUserId,
            dataType:"json"
        }).done(res=>{
            $(obj).text("구독하기");
            $(obj).toggleClass("red");
        }).fail(error=>{
            console.log("구독취소 실패",error);
        });

    } else {

        $.ajax({
            type:"post",
            url: "/api/subscribe/"+toUserId,
            dataType:"json"
        }).done(res=>{
            $(obj).text("구독취소");
            $(obj).toggleClass("red");
        }).fail(error=>{
            console.log("구독하기 실패",error);
        });

    }
}

// function subscribe(fromId,toId) {
//
//     let data = {
//         fromId:fromId,
//         toId:toId
//     }
//
//     $.ajax({
//         type:"post",
//         url:'/api/subscribe',
//         data:data,
//         contentType:"application/x-www-form-urlencoded; charset=utf-8",
//     }).done(res=>{
//         if(res.code == 1){
//             alert("구독이 완료되었습니다.")
//         }
//
//     }).fail(error=>{
//         alert("구독에 실패하였습니다.")
//     });
// }

